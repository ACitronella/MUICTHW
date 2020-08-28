/* Type Your Code here */
#include <stdio.h>
int main(){
  
  int i;
  scanf("%d", &i);
  int j = i;
  while(j <= 100){
    printf("%d ", j);
    j = j + i;
  }
  return 0;
}