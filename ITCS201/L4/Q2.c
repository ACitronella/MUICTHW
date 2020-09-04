
/* Type Your Code here */

#include <stdio.h>
int main(){
  int i, temp;
  for(i = 0; i < 5; i++){
    scanf("%d", &temp);
    if(temp % 2 == 0 && temp >= -100 && temp <= 100){
      printf("approved");  
      i = 5;
    }
  }
  if(i == 5){
    printf("time out");
  }
  return 0;
}