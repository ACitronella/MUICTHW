/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

#include <stdio.h>

void printIntArray(int* arr, int n){
    for(int i = 0; i < n; i++){
        printf("%d ", arr[i]);
    }
    printf("\n");
}

int find_min_max(int* arr, int n, int mode){
    int index = 0;
    for(int i = 0; i < n; i++){
        if(mode == 1 && arr[index] < arr[i]){
            index = i;
        }
        else if(mode == -1 && arr[index] > arr[i]){
            index = i;
        }
    }
    return index;
}

void swap(int* a, int* b){
    int temp = *a;
    *a = *b;
    *b = temp;
}

int main() {
    /*
        Ask a user to input 2 integer arrays
    */
    int n1;
    scanf("%d", &n1);
    int arr1[n1];

    int i;
    for (i=0 ; i<n1 ; i++) {
        scanf("%d", &arr1[i]);
    }

    int n2;
    scanf("%d", &n2);
    int arr2[n2];

    for (i=0 ; i<n2 ; i++) {
        scanf("%d", &arr2[i]);
    }

    /*
        Print the values of the arrays
    */
    printf("Before swap min and max\n");
    printf("arr1: ");
    // for (i=0 ; i<n1 ; i++) {
    //     printf("%d ", arr1[i]);
    // }
    // printf("\n");
    printIntArray(arr1, n1);
    printf("arr2: ");
    // for (i=0 ; i<n2 ; i++) {
    //     printf("%d ", arr2[i]);
    // }
    // printf("\n");
    printIntArray(arr2, n2);
    /*
        For each array:
          - Swap the maximum value with the last element using a pointer
          - Swap the minimum value with the first element using a pointer
    */
    int *p_max, *p_min, *p_first, *p_last;
    int temp;

    // Arrar 1
    // p_max = &arr1[0];
    p_last = &arr1[n1-1];
    // for (i=1 ; i<n1 ; i++) {
    //     if (*p_max < arr1[i]) {
    //         p_max = &arr1[i];
    //     }
    // }
    
    p_max = &arr1[find_min_max(arr1, n1, 1)];
    // temp = *p_max;
    // *p_max = *p_last;
    // *p_last = temp;
    swap(p_max, p_last);
    
    // p_min = &arr1[0];
    p_first = &arr1[0];
    // for (i=1 ; i<n1 ; i++) {
    //     if (*p_min > arr1[i]) {
    //         p_min = &arr1[i];
    //     }
    // }
    p_min = &arr1[find_min_max(arr1, n1, -1)];
    // temp = *p_min;
    // *p_min = *p_first;
    // *p_first = temp;
    swap(p_min, p_first);


    // Arrar 2
    // p_max = &arr2[0];
    p_last = &arr2[n2-1];
    // for (i=1 ; i<n2 ; i++) {
    //     if (*p_max < arr2[i]) {
    //         p_max = &arr2[i];
    //     }
    // }
    p_max = &arr2[find_min_max(arr2, n2, 1)];
    // temp = *p_max;
    // *p_max = *p_last;
    // *p_last = temp;
    swap(p_max, p_last);

    // p_min = &arr2[0];
    p_first = &arr2[0];
    // for (i=1 ; i<n2 ; i++) {
    //     if (*p_min > arr2[i]) {
    //         p_min = &arr2[i];
    //     }
    // }
    p_min = &arr2[find_min_max(arr2, n2, -1)];
    // temp = *p_min;
    // *p_min = *p_first;
    // *p_first = temp;
    swap(p_min, p_first);

    /*
        Print the values of the arrays
    */
    printf("After swap min and max\n");
    printf("arr1: ");
    // for (i=0 ; i<n1 ; i++) {
    //     printf("%d ", arr1[i]);
    // }
    // printf("\n");
    printIntArray(arr1, n1);
    
    
    printf("arr2: ");
    // for (i=0 ; i<n2 ; i++) {
    //     printf("%d ", arr2[i]);
    // }
    // printf("\n");
    printIntArray(arr2, n2);
    return 0;
}