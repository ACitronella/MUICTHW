/* Type Your Code here */
#include <stdio.h>
int main(){
  int s;
  
  scanf("%d", &s);
  if (s >= 1){
    if (s % 2 == 0){
      s = s - 1;
    }
    int i;
    for (i = s; i >= 1; i-=2){
      printf("%d ", i);
    }
  }
  else{
    printf("Unable to print the sequence");
  }
  return 0;
}