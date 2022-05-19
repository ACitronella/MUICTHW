/* Type Your Code here */
#include <stdio.h>
int main(){
  int n;
  do{
    scanf("%d", &n);
  }while(n <= 0);
  int a[n];
  int i;
  for(i = 0; i < n; i++){
    scanf("%d", &a[i]);
  }
  for(i = n-1; i >= 0; i--){
    
    printf("%d ", a[i]);
  }
  return 0;
}