/* Type your code here */ 
#include <stdio.h>
int main(){
  int n;
  do{
    scanf("%d", &n);
  }while(!(n >= 2 && n <= 9));
  
  char p = 'a';
  int i, j;
  for(i = 0; i < n; i++){
    for(j = 0; j <= i; j++){
      printf("%c ", p);
      p++;
      if(p > 'z'){
        p = 'a';
      }
    }
    printf("\n");
  }
  for(i = 0; i < n-1; i++){
    for(j = n-1; j > i; j--){
      printf("%c ", p);
      p++;
      if(p > 'z'){
        p = 'a';
      }
    }
    printf("\n");
  }
  return 0;
}
