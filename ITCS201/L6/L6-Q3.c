/* Type Your Code here */
#include <stdio.h>
int main(){
  
  
  int check, to_replace, i, isnotfound = 1, n;
  scanf("%d", &n);
  int a[n];
  
  for(i = 0; i < n; i++){
    scanf("%d", &a[i]);
  }
  scanf("%d", &check);
  scanf("%d", &to_replace);
  
  for(i = 0; i < n; i++){
    if(a[i] == check){
      a[i] = to_replace;
      isnotfound = 0;
    }
  }
  
  if(isnotfound){
    printf("not found");
  }
  else{
    for(i = 0; i < n; i++){
      
      printf("%d ", a[i]);
    }
  }
  return 0;
}