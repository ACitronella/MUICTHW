/* Type Your Code here */
# include <stdio.h>
int main(){
  int n, m;
  do{
    scanf("%d %d", &m, &n);
    
  }while(!(n > 0 && m > 0));
  int i, j, sum = 0, tofind, isfound = 0;
  int a[m][n];
  for(i = 0; i < m; i++){
    for(j = 0; j < n; j++){
      scanf("%d", &a[i][j]);
    }
  }
  scanf("%d", &tofind);
  for(i = 0; i < m; i++){
    for(j = 0; j < n; j++){
      if(a[i][j] == tofind){
        printf("[%d,%d] ", i, j);
        isfound = 1;
      }
    }
  }
  if(!isfound){
    printf("not found");
  }
  return 0;
}