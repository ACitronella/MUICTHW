#include <stdio.h>

#define N 10
int DATA[N] = {1,6,-7,0,14,55,95,-41,7,33};
int value;
int n_gt;

int count_gt(int v) {

    int n_gt;
    
    ////////////// Start of your code ////////////////
    
    /*
      TODO: Write an algorithm to count how many elements in the `DATA`
            array that are greater than the input `v`.
    */
    n_gt = 0;
    for(int i = 0; i < N; i++){
      if(DATA[i] > v){
        n_gt++;
      }  
    
    }
    /////////////// End of your code /////////////////
    
    return n_gt;

}

int main() {
    scanf("%d", &value);
    
    ////////////// Start of your code ////////////////
    
    /*
        TODO: Write a program that uses the `count_gt` function above to
              count the number of elements in the `DATA` array that are 
              greater than the input `value`.
              Then print out the results as specified in the lab sheet.
    */
    
    printf("%d", count_gt(value));
    /////////////// End of your code /////////////////
    
    return 0;
}