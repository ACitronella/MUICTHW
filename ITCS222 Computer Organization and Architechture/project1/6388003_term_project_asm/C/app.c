// gcc app.c -o app && ./app
#include <stdio.h>
#define len 3

int find_min_index(int arr[], int start, int end){
    int i;
    int current_min = start;
    for(i = start+1; i < end; i++){
        if(arr[i] < arr[current_min]){
            current_min = i;
        }
    }
    return current_min;
}

void sort_array(int arr[], int start, int end){
    int i, j, t;
    for(i = start; i < end; i++){
        j = find_min_index(arr, i, end);
        t = arr[j];
        arr[j] = arr[i];
        arr[i] = t;
    }
}


void print_array(int arr[], int start, int end){
    for(int i = start; i < end; i++){
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int main(){
    
    int a[len];
    int i;
    printf("input 3 numbers: ");
    for(i = 0; i < len; i++){
        scanf("%d", &a[i]);
    }
    printf("before sort: ");
    print_array(a, 0, len);
    sort_array(a, 0, len);
    printf("after sort : ");
    print_array(a, 0, len);
    return 0;
}
