/* Type your code here */
#include <stdio.h>
int main(){
  int n, i, j, k;
  do{
    scanf("%d", &n);
  }while(!(n > 1));
  for (i = 0; i < n ; i++){
    for(j = 0; j <= i; j++){
      printf("+ ");  
      
    }
    for(; j < n; j++){
      printf("- ");  
      
    }
    printf("\n");
  }
  return 0;
}