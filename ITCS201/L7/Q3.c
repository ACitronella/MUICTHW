/* Type Your Code here */
# include <stdio.h>
int main(){
  int n;
  do{
    scanf("%d", &n);
    
  }while(!(n > 0));
  int i, j, sum = 0;
  int a[n][n];
  for(i = 0; i < n; i++){
    for(j = 0; j < n; j++){
      scanf("%d", &a[i][j]);
      
    }
  }
  for(i = 0; i < n; i++){
    printf("%d, ", a[i][i]);
    sum = sum + a[i][i];
  }
  printf("%d", sum);
  return 0;
}