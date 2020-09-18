/* Type Your Code here */
#include <stdio.h>
int main(){
  int n;
  scanf("%d", &n);
  int a[n];
  int b[n];
  int prod = 0, i;
  
  for(i = 0; i < n; i++){
    scanf("%d", &a[i]); 
  }
  for(i = 0; i < n; i++){
    scanf("%d", &b[i]); 
  }
  for(i = 0; i < n; i++){
    prod = prod + a[i] * b[i];
  }
  printf("%d", prod);
  return 0;
}