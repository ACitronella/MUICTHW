/* Type Your Code here */
#include <stdio.h>
int main(){
  
  int n, m, i, s = 0; 
  scanf("%d", &n);
  for(i = 0; i < n; i++){
    scanf("%d", &m);
    s = s + m;
  }
  printf("%d", s);
  return 0;
}