#include <stdio.h>

void find_max_min(int *arr, int n_elems, int *max, int *min);

int main() {
  int n;
  scanf("%d", &n);
  int arr[n];
  int i;
  for (i=0 ; i<n ; i++) {
    scanf("%d", &arr[i]);
  }

  int max, min;

  // TODO: call the function `find_max_min` here
  find_max_min(arr, n, &min, &max);
  printf("%d %d", max, min);

  return 0;
}

//////////  Start of your code  //////////

/*
  TODO: 
    Write a function definition for the function 
    `find_max_min` to determine the maximum and
    the minimum values of the array `arr`.

    The maximum value will be stored in the variable
    that the pointer `max` is pointing to.

    The minimum value will be stored in the variable
    that the pointer `min` is pointing to.
*/
void find_max_min(int *arr, int n_elems, int *max, int *min){
  *max = arr[0];
  *min = arr[0];
  for(int i = 1; i < n_elems; i++){
    if(*min < arr[i]){
      *min = arr[i];
    }
    if(*max > arr[i]){
      *max = arr[i];
    }
  }
  
  
  
}
//////////  End of your code  //////////