#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main(void) {
  char input_str[51];
  fgets(input_str, 51, stdin);
  char *pos;
  if ((pos=strchr(input_str, '\n')) != NULL){
      *pos = '\0';
  }
  int is_sp = 1;
  for(int i = 0; i < strlen(input_str); i++){
    
    if(input_str[i] == ' '){
        is_sp = 1;
    }
    else if(is_sp && islower(input_str[i])){
        input_str[i] = toupper(input_str[i]);
        is_sp = 0;
    }
    else if(is_sp && isupper(input_str[i])){
        is_sp = 0;
    }
    else if(!is_sp && isupper(input_str[i])){
        input_str[i] = tolower(input_str[i]);

    }
    else{
        is_sp = 0;
    }
  }
  printf("%s", input_str);
  return 0;
}