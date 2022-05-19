/* Type your code here */
#include <stdio.h>
int main(){
  int n, i, j;
  do{
    scanf("%d", &n);
  }while(n <= 0);
  
  for (i = 0; i < n ; i++){
    int k = 0;
    if(i % 2 == 1){
      k = 1;
    }
    for(j = 0 ; j <= i; j++){
      printf("%d ", k);      
      k = !k; 
    }
    
    printf("\n");
  }
  return 0; 
}