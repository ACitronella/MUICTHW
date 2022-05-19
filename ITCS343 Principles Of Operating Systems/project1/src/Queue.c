#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>

int32_t STD_SIZE_QUEUE = 5;

// using array, since im too lazy deal with node
// not queue any more since we added pop_i more like list
typedef struct __queue__ {
    void** datas; 
    int32_t len;
    int32_t size;
} Queue;

// q -> [ [a] [int*] [float*] [] [] ]

// int num = 12;
// float num_float = 12.5; //address 47/62

// int[10] num_array = [1,2,3,4,5,5,6,7,8,9];
// int* b = &num_array; 

// *(b) -> 1
// *(b+1) -> 2

// float* a = &num_float; //47/62

// float**  -> [ [a] [b] [float*] [float*] ]


Queue* create_queue(){
    Queue* q = malloc(sizeof(Queue));
    q -> datas = (void**)malloc(sizeof(void *) * STD_SIZE_QUEUE); //STD_SIZE_QUEUE -> Size of init Queue  //double pointer (array of void pointer 
    q -> len = 0;
    q -> size = STD_SIZE_QUEUE;
    return q;
}

void enqueue(Queue* q, void* data){
    int32_t len = q -> len;
    int32_t size = q -> size;

    if(len >= size){
        // queue is full, extends it (double its size)
        int32_t new_size = (q -> size) * 2;
        void** new_datas = (void**) malloc(sizeof(void *) * new_size);
        void** old_datas = q -> datas;
        for(size_t i = 0; i < q -> size; i++){
            *(new_datas + i) = *((q -> datas) + i);
        }
        q -> datas = new_datas;
        q -> size = new_size;
        free(old_datas);
    }

    *((q -> datas) + len) = data;
    (q -> len)++;
}

void* dequeue(Queue* q){
    int32_t len = q -> len;
    if(len <= 0){
        // empty queue
        fprintf(stderr, "pop q too much\n");
        return NULL;
    }
    void** datas = q -> datas;
    void* ret = *(datas); // first element
    for(size_t i = 0; i < len-1; i++){
        *(datas + i) = *(datas + i + 1);
    }
    (q -> len)--;
    return ret;
}

void* peak(Queue* q){
    if(q -> len > 0){
        return *(q -> datas);
    }
    fprintf(stderr, "peak q too much\n");
    return NULL;
}

void* pop_first_end(Queue* q){
    if(q -> len > 0){
        // void** pv = *((q -> datas) + (q -> len - 1));
        // pv = NULL;
        void* v = *((q -> datas) + (q -> len - 1));  // datas[len(datas)-1]
        q -> len--;
        return v;
    }
    fprintf(stderr, "pop queue too much\n");
    return NULL;
}

void* pop_i(Queue* q, size_t idx){
    //Delete an item at index idx
    //For loop to shift further requests to the former index (index0)
    if(q -> len > 0){
        void* tmp = *(q->datas + idx);
        for(int32_t i = idx; i < q -> len - 1; i++){
            // q[i] = q[i+1];
            *((q -> datas) + i) = *((q -> datas) + i + 1);    
        }
        q -> len--;
        return tmp;
    }
    fprintf(stderr, "pop queue too much\n");
    return NULL;
}

void free_queue(Queue* q){
    free(q -> datas);
    free(q);
}

void print_int_queue(Queue* q){
    size_t len = q -> len;
    for (size_t i = 0; i < len; i++){
        printf("%d ", *(int *)((q -> datas)[i]));
    }
    printf("\n");
}

void print_str_queue(Queue* q){
    size_t len = q -> len;
    for (size_t i = 0; i < len; i++){
        printf("%s ", (char *)((q -> datas)[i]));
    }
    printf("\n");
}

void test_SFJ(){

    Queue* q = create_queue();
    // int data1 = 12;
    // int data2 = 1;
    // int data3 = 5;
    // int data4 = 3;
    // int data5 = -1;
    // enqueue(q, (void*)&data1);
    // enqueue(q, (void*)&data2);
    // enqueue(q, (void*)&data3);
    // enqueue(q, (void*)&data4);
    // enqueue(q, (void*)&data5);
    // print_int_queue(q);
    // printf("expected: %d, actual: %d\n", data1, *(int*)dequeue(q));
    // printf("expected: %d, actual: %d\n", data2, *(int*)dequeue(q));
    // printf("expected: %d, actual: %d\n", data3, *(int*)dequeue(q));
    // printf("expected: %d, actual: %d\n", data4, *(int*)dequeue(q));
    // printf("expected: %d, actual: %d\n", data5, *(int*)dequeue(q));

    char data1[] = "a1\0";
    char data2[] = "b2\0";
    char data3[] = "c3\0";
    char data4[] = "d4\0";
    char data5[] = "e5\0";
    enqueue(q, (void*)&data1);
    enqueue(q, (void*)&data2);
    enqueue(q, (void*)&data3);
    enqueue(q, (void*)&data4);
    enqueue(q, (void*)&data5);
    print_str_queue(q);
    
    printf("%s\n", (char*)pop_i(q,2));
    print_str_queue(q);

    char data6[] = "f6\0";
    enqueue(q, (void*)&data6);

    pop_i(q,3);
    pop_i(q,1);
    print_str_queue(q);

    char data7[] = "new1\0";
    char data8[] = "new2\0";
    char data9[] = "new3\0";
    char data10[] = "new4\0";
    char data11[] = "new5\0";
    enqueue(q, (void*)&data7);
    enqueue(q, (void*)&data8);
    enqueue(q, (void*)&data9);
    enqueue(q, (void*)&data10);
    enqueue(q, (void*)&data11);

    print_str_queue(q);
    printf("\n\n\n");

    for(int32_t i = 0; i < 7; i++){
        printf("%s\n", (char*)pop_i(q,1));
        print_str_queue(q);
    }

    free(q -> datas);
    free(q);
}

// int main(){
//     test_SFJ();
//     return 0;
// }
