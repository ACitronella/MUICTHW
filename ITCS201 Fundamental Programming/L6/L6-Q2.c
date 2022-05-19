/* Type Your Code here */
#include <stdio.h>
int main(){

  int a[5];
  int i;
  int sum = 0;
  int temp;
  for(i = 0; i < 5; i++){
    scanf("%d", &temp);
    sum = sum + temp;
    a[i] = sum;
  }
  for(i = 0; i < 5; i++){
    
    printf("%d ", a[i]);
  }
  return 0;
}