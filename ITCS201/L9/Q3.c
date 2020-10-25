#include <stdio.h>

int find_min(int a, int b){
  return (a>b)*(b) + (a<=b)*(a); 
}

int main(void) {
  int n, m;
  scanf("%d %d", &n, &m);
  int a[n][m];
  for(int i = 0;i < n; i++){
    for(int j = 0; j < m; j++){
      scanf("%d", &a[i][j]);
    }
  }
  int min = a[0][0]; 
  for(int i = 0; i < n; i++){
    for(int j = 0; j < m; j++){
      min = find_min(min, a[i][j]);
      
    }
  }
  printf("%d", min);
  return 0;
}