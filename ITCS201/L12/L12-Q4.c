/* Type Your Code here */
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

int main(){
  char *str = (char *)malloc(sizeof(char) * 21);
  // worse case for all 20 char in is unique.
  char *holder = (char *)malloc(sizeof(char) * 20); 
  short *count_holder = (short *)calloc(20, sizeof(short));
  // short *count_holder = (short *)malloc(20 * sizeof(short));
  int i, j, c = 0, is_found;
  
  fgets(str, 21, stdin);
  short actual_length = strlen(str);
  
  for(i = 0; i < actual_length; i++){
    is_found = 0;
    for(j = 0; j < c; j++){
      if(*(str + i) == *(holder + j)){
        is_found = 1;
        *(count_holder + j) = *(count_holder + j) + 1;
        break;
      }
    }
    if(!is_found){
      *(holder+c) = *(str+i);
      *(count_holder+c) = 1;
      c++;
    }
  }
  
  short index_max = 0;  
  for(i = 1; i < c; i++){
    if(*(count_holder + index_max) < *(count_holder + i)){
      index_max = i;
    }
  }
  printf("%d %c\n", *(count_holder+index_max), *(holder+index_max));
  
  free(str);
  free(holder);
  free(count_holder);
  return 0;
}