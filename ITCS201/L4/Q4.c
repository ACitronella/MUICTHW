/* Type Your Code here */
#include<stdio.h>
int main(){
  int x, i, notprime = 0;
  scanf("%d", &x);
  for(i = 2; i < (x/2); i++){
    if(x % i == 0){
      notprime = 1;
      break;
    }
  }
  if(notprime){
    printf("not prime");
  }
  else{
    printf("prime");
    
  }
  return 0;
}