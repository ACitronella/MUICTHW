/* Type Your Code here */
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

int main(){
  int UPPERs = 0, lowers = 0, digits = 0;
  char *str = (char *)malloc(sizeof(char) * 20+1);
  fgets(str, 20+1, stdin);
  for(int i = 0; i < strlen(str); i++){
  // printf("%c\n", *(str+i));
    if( isupper(*(str+i)) ){
      UPPERs++;
    }
    else if( islower(*(str+i)) ){
      lowers++;
    }
    else if( isdigit(*(str+i)) ){
      digits++;
    }
  }
  printf("%d %d %d", UPPERs, lowers, digits);
  free(str);
  return 0;
}