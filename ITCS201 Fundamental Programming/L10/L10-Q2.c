#include <stdio.h>

#define N 10
int DATA[N];

int find_index(int v) {

  int found_index;

  //////////  Start of your code  //////////
  
  /*
    TODO: Write an algorithm to find the index or the position of 
          the input value `v` in the `DATA` array. The index of value
          `v` must be stored in `found_index`.
    Note: If there are more than one matching values, just return
          the first matching index.
  */
  found_index = -1;
  for(int i = 0; i < N; i++){
    if(v == DATA[i]){
      found_index = i;
      break;
    }  
  }
  
  //////////  End of your code  //////////

  return found_index;
}

int main() {
  int target, found_index;

  //////////  Start of your code  //////////

  /*
    TODO: Write a program that uses the `find_index` function above to
          determine whether the input `target` is in the `DATA` array.
    Note: You need to ask a user to fill in all values in `DATA`.
  */
  for(int i = 0; i < N; i++){
    scanf("%d", &DATA[i]);
  }
  scanf("%d", &target);
  found_index = find_index(target);
  if(target != -1){
    printf("Found at %d", found_index);
  }
  else{
    printf("Not found");
  }
  //////////  End of your code  //////////

  return 0;
}