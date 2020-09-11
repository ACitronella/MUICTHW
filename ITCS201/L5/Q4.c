/* Type your code here */
#include <stdio.h>
int main(){
  int n;
  do{
    scanf("%d", &n);
  }while(!(n > 1 && n < 1000));
  
  int i, j, isprime;
  for(i = 2; i <= n; i++){
    isprime = 1;
    for(j = 2; j < i; j++){
      if(i % j == 0){
        isprime = 0;
        break;
      }
    }
    if(isprime){  
      printf("%d ", i);
    }
  }
  return 0;
}