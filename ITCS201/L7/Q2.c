/* Type Your Code here */
# include <stdio.h>
int main(){
  int m,n;
  do{
    scanf("%d %d",&m ,&n);
  }while(!(n > 0 && m > 0));
  int a[m][n];
  int b[m][n];
  int c[m][n];
  int sum = 0;
  int i, j; 
  for(i = 0; i < m;  i++){
    for(j = 0; j < n; j++ ){
      scanf("%d", &a[i][j]);
    }
  }
  
  for(i = 0; i < m;  i++){
    for(j = 0; j < n; j++ ){
      scanf("%d", &b[i][j]);
    }
  }

  for(i = 0; i < m;  i++){
    for(j = 0; j < n; j++ ){
      c[i][j] = a[i][j] + b[i][j];
    }
  }
  for(i = 0; i < m;  i++){
    for(j = 0; j < n; j++ ){
      printf("%d ", c[i][j]);
    }
    printf("\n");
  }
  
  return 0;
}