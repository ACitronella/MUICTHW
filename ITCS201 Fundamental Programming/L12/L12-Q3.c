/* Type Your Code here */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

int main(){
  short count_removed = 0;
  char *str = (char *)malloc(sizeof(char) * 21);
  char *new_str = (char *)malloc(sizeof(char) * 21);
  fgets(str, 21, stdin);
  short actual_length = strlen(str);
  char *address_at_last_char = str + actual_length - 1; 
  if(*address_at_last_char == '\n'){
    *address_at_last_char = '\0';
    actual_length = strlen(str);
  }
  short c = 0;
  for(short i = 0; i < actual_length; i++){
    if(isdigit(*(str+i)) || isalpha(*(str+i)) || *(str+i) == ' '){
      *(new_str + c) = *(str+i);
      c++;
    }
    else{
      // printf("%c", *(str+i)); 
      count_removed++;
    }
  }
  printf("%d %s", count_removed, new_str);
  free(str);
  return 0;
}