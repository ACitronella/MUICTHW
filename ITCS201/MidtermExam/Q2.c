/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */

#include <stdio.h>
int main(){
  int n, m;
  int iseq = 1;
  
  do{
    scanf("%d %d", &m, &n);
  }while(n <= 0);
  int a[m][n];
  for(int i = 0; i < m; i ++){
    for (int j = 0; j < n; j++){
      scanf("%d", &a[i][j]);  
    }
  }
  if (m == n){
    for(int i = 0; i < m; i ++){
      for (int j = 0; j < n; j++){
        if(a[i][j] != a[j][i]){
          iseq = 0;
          break;
        }
      }
    }
  }
  else{
    iseq = 0;
  }
  
  if(iseq){
    printf("Symmetric");
  }
  else{
    printf("Not symmetric");
    
  }
  if(n != m ){
    printf(" (not a square matrix)");
  }
  return 0;
}