#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <pthread.h>
#include "Queue.c"
#include <time.h>
#include <sys/time.h>
#include <unistd.h>
#include <stdbool.h>
#include "common.h"
#include "common_threads.h"

#ifdef linux
#include <semaphore.h>
#elif __APPLE__
#include "zemaphore.h"
#endif


typedef struct __config__ { // Singleton, dedicated to be setting of the simulation
    int32_t PROCESSES_COUNT;
    int32_t DEVICES_COUNT;
    int32_t REQUESTS_COUNT;
    int32_t MIN_TIME_PROCESS;
    int32_t MAX_TIME_PROCESS;
    int32_t QUEUE_POLICY;
    int32_t RANGE_TIME_PROCESS; // max - min time process
    int32_t SCHEDULING_ALGO; // 0 for FIFO(default), 1 for SJF
    bool VERBOSE;
} Config;

typedef struct __request__ {
    pthread_t tidx;
    struct timeval at_enqueue;
    struct timeval at_dequeue;
    int32_t request_idx;
    int32_t delaybeforeissue; // in millisecond
    int32_t cpu_time;
    bool is_dropped;
} Request;

Queue* q;
sem_t mutex; 
sem_t full;
sem_t empty;

int32_t created_requests;
volatile bool is_request_more;
volatile bool is_work_more;
volatile bool bypass_queue_policy;

const int32_t MIN_ISSUE_TIME = 100;
const int32_t MAX_ISSUE_TIME = 500;
const int32_t RANGE_ISSUE_TIME = MAX_ISSUE_TIME - MIN_ISSUE_TIME;
const int32_t BUFFER_SIZE = 10;

void config_print(Config* c){
    printf("PROCESSES_COUNT:\t%d processes\n", c -> PROCESSES_COUNT);
    printf("DEVICES_COUNT:\t\t%d devices\n", c -> DEVICES_COUNT);
    printf("REQUESTS_COUNT:\t\t%d requests\n", c -> REQUESTS_COUNT);
    printf("MIN_TIME_PROCESS:\t%dms\n", c -> MIN_TIME_PROCESS);
    printf("MAX_TIME_PROCESS:\t%dms\n", c -> MAX_TIME_PROCESS);
    // too lazy to find how string works
    switch (c -> QUEUE_POLICY){
        case 1:
            printf("QUEUE_POLICY:\t\tWait util buffer has empty spot\n");
            break; 
        case 2:
            printf("QUEUE_POLICY:\t\tDrop all incoming requests\n");
            break;
        case 3:
            printf("QUEUE_POLICY:\t\tReplace old requests with new incoming one\n");
            break;
        default:
            printf("QUEUE_POLICY:\t\tInvalid Input\n");
            break;
    }
    switch (c -> SCHEDULING_ALGO){
        case 2:
            printf("SCHEDULING_ALGO:\tSJF\n");
            break;
        default:
            printf("SCHEDULING_ALGO:\tFIFO\n"); 
            break;
    }
    printf("VERBOSE:\t\t%d\n", c -> VERBOSE);

}
void print_request(Request* r){
    if(r != NULL)
        printf("Request[idx: %d, tidx: %ld, cputime: %d] ", r -> request_idx, r -> tidx, r -> cpu_time);
    else
        printf("NULL ");
}

void print_queue_request(Queue* q){
    printf("[ ");
    for (size_t i = 0; i < q -> len; i++){             
        print_request(*(q -> datas + i));
    }
    printf("]\n"); 
}

Request* request_create(int32_t idx, int32_t delaybeforeissue, int32_t min_time_process, int32_t range_time_process, pthread_t tidx){
    Request* p = malloc(sizeof(Request));
    p -> tidx = tidx;
    p -> request_idx = idx; // (*p).request_idx = idx;
    p -> is_dropped = false;
    p -> delaybeforeissue = delaybeforeissue;
    p -> cpu_time = (rand() % range_time_process) + min_time_process;
    return p;
}

void* request_pusher(void* c){ // producer
    Config *config = (Config*) c;
    int32_t requests_count = config -> REQUESTS_COUNT, delaybeforeissue;
    int32_t min_time_process = config -> MIN_TIME_PROCESS;
    int32_t range_time_process = config -> RANGE_TIME_PROCESS; 
    int32_t queue_policy = config -> QUEUE_POLICY;
    Request **ps = (Request**)calloc(requests_count, sizeof(Request*)); // allocate array of pointer (which point to Request)
    Request *p;
    int i = 0; 
    pthread_t thread_id = pthread_self();
    bool verbose = config -> VERBOSE;

    if(queue_policy == 1){
        while(is_request_more){ 
            Sem_wait(&empty);
            Sem_wait(&mutex);

                if(!is_request_more){
                    Sem_post(&mutex);
                    Sem_post(&empty); // since we didn't append to queue, so empty and full semaphore should be the same as before 
                    break;
                }
                if(verbose)
                    printf("producer %ld: Creating request: %d\n", thread_id, created_requests);
                p = request_create(i, delaybeforeissue, min_time_process, range_time_process, thread_id); // init case delaybeforeissue is 0
                *(ps + i) = p;
                    
                gettimeofday(&(p -> at_enqueue), NULL); // write timestamp
                enqueue(q, (void*)p);
                created_requests++;
                is_request_more = (created_requests < requests_count);
            
            Sem_post(&mutex);
            Sem_post(&full);

            delaybeforeissue = (rand() % RANGE_ISSUE_TIME) + MIN_ISSUE_TIME;
            if(verbose)
                printf("producer %ld: Request will sleep before next request: %d ms\n", thread_id, delaybeforeissue);
            usleep(delaybeforeissue * 1000);
            i++;
        }

    }
    else if(queue_policy == 2) { //Drop all incoming requests
        bool is_enqueue;
        while(is_request_more){
            //Handle semaphore 
            if(bypass_queue_policy){
                Sem_wait(&empty);
            }
            Sem_wait(&mutex);

                if(!is_request_more){
                    Sem_post(&mutex);
                    break;
                }
                if(verbose)
                    printf("producer %ld: Creating request: %d\n", thread_id, created_requests);
                p = request_create(i, delaybeforeissue, min_time_process, range_time_process, thread_id);
                *(ps + i) = p;
                gettimeofday(&(p -> at_enqueue), NULL); // write timestamp
                if(q -> len < BUFFER_SIZE){
                    enqueue(q, (void*)p);
                    is_enqueue = true;
                } else { // drop request
                    p -> is_dropped = true;
                    is_enqueue = false;
                }
            
                created_requests++;
                is_request_more = (created_requests < requests_count);
                
            Sem_post(&mutex);
            if(is_enqueue){
                Sem_post(&full);
            }
        
            delaybeforeissue = (rand() % RANGE_ISSUE_TIME) + MIN_ISSUE_TIME;
            if(verbose)
                printf("producer %ld: Request will sleep before next request: %d ms\n", thread_id, delaybeforeissue);
            usleep(delaybeforeissue * 1000);
            i++; 
        }
    } 
    else if(queue_policy == 3) { //Replace old requests with incoming ones
        bool is_enqueue;
        while(is_request_more){
            //Handle semaphore 
            is_enqueue = true;
            if(bypass_queue_policy){
                Sem_wait(&empty);
            }
            Sem_wait(&mutex);

                if(!is_request_more){
                    Sem_post(&mutex);
                    break;
                }
                if(verbose)
                    printf("producer %ld: Creating request: %d\n", thread_id, created_requests);
                p = request_create(i, delaybeforeissue, min_time_process, range_time_process, thread_id);
                *(ps + i) = p;
                gettimeofday(&(p -> at_enqueue), NULL); // write timestamp
                if(!(q -> len < BUFFER_SIZE)){
                    // edit queue, drop that old request
                    Request* old_process = (Request*) pop_first_end(q);
                    old_process -> is_dropped = true;
                    if(verbose)
                        printf("producer %ld: pop end of the queue\n", thread_id);
                    is_enqueue = false;
                }
                enqueue(q, (void*)p);
                is_request_more = (created_requests < requests_count);
                created_requests++;

            Sem_post(&mutex);
            if(is_enqueue){
                Sem_post(&full);
            }
        
            delaybeforeissue = (rand() % RANGE_ISSUE_TIME) + MIN_ISSUE_TIME;
            if(verbose)
                printf("producer %ld: Request will sleep before next request: %d ms\n", thread_id, delaybeforeissue);
            usleep(delaybeforeissue * 1000);
            i++; 
        }
    }
    if(verbose)
        printf("producer %ld: exit\n", thread_id);
    return (void *)ps; 
}

void* that_one_thread_that_has_to_make_consumer_exit(void* c){
    Config *config = (Config *)c;
    bool verbose = config -> VERBOSE;
    pthread_t thread_id = pthread_self();
    
    Request** ps = request_pusher(c);
    if(config -> QUEUE_POLICY != 1){
        Sem_wait(&mutex);
        Sem_init(&empty, BUFFER_SIZE - q -> len);
        bypass_queue_policy = true;
        Sem_post(&mutex);
    }

    if(verbose)
        printf("producer %ld: check consumer exit\n", thread_id);
    for(int32_t i = 0; i < config -> DEVICES_COUNT; i++){
        Sem_wait(&empty);
        Sem_wait(&mutex);
            enqueue(q, NULL);
        Sem_post(&mutex);
        Sem_post(&full);
    }
    return (void*) ps;
}    

Request* select_lowest_cpu_time(Queue* q){
    //use int32_t instead of int
    int32_t min = 2147483647;
    int32_t index = 0;
    int32_t min_i;
    for(int32_t i = 0; i < q -> len; i++){
        if(*(q->datas + i) != NULL){
            min_i = ((Request*)*(q->datas + i)) -> cpu_time; 
            if (min_i <= min){// if cpu time of r have less CPU time than min -> min = cpu time of r
                min = min_i;
                index = i;
            }
        }
    }                      
    Request* min_cpu_time_request = pop_i(q, index);
    return min_cpu_time_request;
}

void* device_work(void* c){ // consumer
    // check if there is a work in queue, 
    pthread_t thread_id = pthread_self();
    Config* config = ((Config*)c);
    int32_t schduling_algo = config -> SCHEDULING_ALGO;
    bool verbose = config -> VERBOSE;
    Request* p;
    
    // wait until queue is not max, then enqueue    
    while(true){
        Sem_wait(&full);
        Sem_wait(&mutex);
            if(schduling_algo == 1) { // SJF
                p = (Request *)select_lowest_cpu_time(q);
            }
            else { // default behavior, FIFO
                p = (Request *)dequeue(q);
            }
            if(p == NULL){
                Sem_post(&mutex);
                Sem_post(&empty);
                break;
            }
            gettimeofday(&(p -> at_dequeue), NULL); // write timestamp

        Sem_post(&mutex); 
        Sem_post(&empty);
        if(verbose)
            printf("\tconsumer %ld: device working for: %d ms\n", thread_id, p -> cpu_time);
        usleep((p -> cpu_time) * 1000);
    }
    if(verbose)
        printf("\tconsumer %ld: exit\n", thread_id);
    return NULL; 
}

void simulate(Config* c){
    int32_t processes_count = c -> PROCESSES_COUNT;
    int32_t devices_count = c -> DEVICES_COUNT;
    int32_t requests_count = c -> REQUESTS_COUNT;
    pthread_t devices[devices_count];
    pthread_t processes[processes_count];
    Request*** requests_foreach_process = malloc(sizeof(Request**)*processes_count); // alloc for every processes
    Request* v;
    int32_t i, j;
    double acc_wait_time = 0., acc_cpu_time = 0., cur_wait_time = 0., dropped_count = 0., total_time;
    struct timeval start, end;
    bool verbose = c -> VERBOSE;
    
    is_request_more = (created_requests < c -> REQUESTS_COUNT);
    is_work_more = (is_request_more || q -> len);
    bypass_queue_policy = false;

    q = create_queue(); 
    created_requests = 0;
    Sem_init(&mutex, 1);
    Sem_init(&full, 0);
    Sem_init(&empty, BUFFER_SIZE); // for policy 2, 3. Unused until bypass queue policy
    

    gettimeofday(&start, NULL);
    pthread_create(processes, NULL, that_one_thread_that_has_to_make_consumer_exit, (void*)c);
    for(i = 1; i < processes_count; i++){
        pthread_create(processes+i, NULL, request_pusher, (void*)c);
    }
    for(i = 0; i < devices_count; i++){
        pthread_create(devices+i, NULL, device_work, (void*)c); 
    }
    for(i = 0; i < devices_count; i++){
        pthread_join(devices[i], NULL); 
    }
    if(verbose)
        printf("main: all comsumer exited\n");
    for(i = 0; i < processes_count; i++){
        pthread_join(processes[i], (void**) (requests_foreach_process+i));
    }
    gettimeofday(&end, NULL);
    total_time = ((end.tv_sec - start.tv_sec) * 1e6 + (end.tv_usec - start.tv_usec)) * 1e-3;
    
    for(i = 0; i < processes_count; i++){
        if(verbose)
            printf("process idx %d\n", i);
        for(j = 0; j < requests_count; j++){
            // ***(requests_foreach_process + i) + j
            v = *(*(requests_foreach_process + i) + j); // v = requests_foreach_process[i][j];
            if(v != NULL){
                if(v -> is_dropped){ // dropped requests
                    if(verbose)
                        printf("\trequest idx: %d, delaybeforeissue: %dms, dropped\n", v -> request_idx, v -> delaybeforeissue);
                }
                else{
                    cur_wait_time = (((v -> at_dequeue).tv_sec - (v -> at_enqueue).tv_sec) * 1e6 + (v -> at_dequeue).tv_usec - (v -> at_enqueue).tv_usec) * 1e-3; 
                    acc_wait_time += cur_wait_time;
                    acc_cpu_time += v -> cpu_time;
                    if(verbose)
                        printf("\trequest idx: %d, delaybeforeissue: %dms, waittime: %.2fms, cputime: %dms\n", v -> request_idx, v -> delaybeforeissue, cur_wait_time, v -> cpu_time);
                }
                dropped_count += v -> is_dropped;
            }
        }
    }
    printf("avg wait time: %.4lfms\n", acc_wait_time / (requests_count - dropped_count)); // use dropped count instead of request count 
    printf("avg cpu time: %.4lfms\n", acc_cpu_time / (requests_count - dropped_count)); // since dropped one didn't actually eating cpu time
    printf("dropped request: %.4lf%%\n", dropped_count / requests_count * 100);
    printf("whole simulation elaped: %.4f ms\n", total_time);
    // free memory part
    for(i = 0; i < processes_count; i++){
        for(j = 0; j < requests_count; j++){
            if(*(*(requests_foreach_process + i) + j) != NULL){
                free(*(*(requests_foreach_process + i) + j));
            }
        }
        free(*(requests_foreach_process+i));
    }
    free(requests_foreach_process);
    free_queue(q);
}

int main(int argc, char const *argv[]){
    if (argc < 7 || argc > 9) {
        fprintf(stderr, "usage: %s <NUM OF PROCESSES> <NUM OF DEVICES> <NUM OF REQUEST> <MIN TIME TO PROCESS A REQUEST(ms)> <MAX TIME TO PROCESS A REQUEST(ms)> <ACTION WHEN QUEUE IS FULL> (SCHEDULING ALGORITHM) (VERBOSE)\n", argv[0]);
        exit(1);
    }
    srand(time(NULL));
    
    Config c = {
        atoi(argv[1]),
        atoi(argv[2]),
        atoi(argv[3]),
        atoi(argv[4]),
        atoi(argv[5]),
        atoi(argv[6]),
        0, // range time process, i don't want to call atoi again so i compute it in
        0,
        false
    };
    c.RANGE_TIME_PROCESS = c.MAX_TIME_PROCESS - c.MIN_TIME_PROCESS;
    if(argc > 7){
        c.SCHEDULING_ALGO = atoi(argv[7]);
    }
    if(argc > 8){
        c.VERBOSE = atoi(argv[8]);
    }
    config_print(&c);
    if(c.PROCESSES_COUNT <= 0 || c.DEVICES_COUNT <= 0 || c.REQUESTS_COUNT <= 0 || c.MIN_TIME_PROCESS <= 0 || c.MAX_TIME_PROCESS <= 0 || c.MAX_TIME_PROCESS < c.MIN_TIME_PROCESS || c.QUEUE_POLICY <= 0 || c.QUEUE_POLICY >= 4){
        fprintf(stderr, "invalid args\n");
        exit(1);
    }
    printf("SIMULATING PROCESSES AND DEVICES AS GIVEN INITIAL CONDITION\n");
    simulate(&c);  

    return 0;
}