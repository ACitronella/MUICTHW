/* Type Your Code here */
#include <stdio.h>
#include <stdlib.h>
int main(){
  int c = 0;
  int temp;
  do{
    scanf("%d", &temp);
    if (temp % 10 == 0){
      c++;
    }
    
  }while(!(abs(temp) % 2 == 1  && temp < 0));
  printf("%d", c);
  return 0;
}