/* Type Your Code here */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
  char *all_strs = (char *)malloc(sizeof(char) * 33);
  char *temp_str = (char *)malloc(sizeof(char) * 12 * 3); // locate more 2 byte for '\n' + '\0'
  for(int i = 0; i < 3; i++){
    fgets(temp_str+(12 * i), 12, stdin);
    char *address_at_last_char = temp_str + i * 12 + strlen(temp_str + i * 12) - 1; // lmao this thing
    // printf("%d ", *address_at_last_char);
    if(*address_at_last_char == '\n'){
      *address_at_last_char = '\0'; 
    }
  }
  for(int i = 0; i < 3; i++){
    strcat(all_strs, temp_str + (12 * i));
    if(i <= 1){
      strcat(all_strs, ",");
    }
  }
  printf("%s", all_strs);
  free(all_strs);
  free(temp_str);
  
  return 0;
}