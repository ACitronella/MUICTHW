/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */

#include <stdio.h>
#include <string.h>

int main(){
  char str[21];
  fgets(str, 21, stdin);
  char *pos;
  char storage[20];
  int count[20];
  int len_storage = 0;
  
  if ((pos=strchr(str, '\n')) != NULL)
   *pos = '\0';
  
  for(int i = 0; i < strlen(str); i++){
    // printf("%c ", str[i]);
    int is_found = 0;
    for(int j = 0; j < len_storage; j++){
      // printf("%d ", str[i] == storage[j]);
      if(str[i] == storage[j]){
        count[j]++;
        is_found = 1;
        break;
      }
    }
    if(!is_found){
      
      storage[len_storage] = str[i];
      count[len_storage] = 1;
      len_storage++;
    }
    
  }
  
  int max = count[0];
  char target = storage[0];
  // printf("%d\n", len_storage);
  for(int i = 0; i < len_storage; i++){
    if(max < count[i]){
      max = count[i];
      target = storage[i];
    }
    
    // printf("%d %c\n", count[i], storage[i]);
  }
  
  if(target == ' '){
    printf("%d space", max);
  }
  else{
    printf("%d %c", max, target);
  }
  
  return 0;
}