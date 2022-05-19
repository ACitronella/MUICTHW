/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003 
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */
#include <stdio.h> 
int main(){
  
  int m,n; 
  int s;
  int i, j;
  int sum = 0;
  do{
    scanf("%d %d", &m, &n);
  }while(!(m > 0) || !(n > 0));
  
  int mat[m][n];
  for(i = 0; i < m; i++){
    for(j = 0; j < n; j++){
      scanf("%d", &mat[i][j]);
    }
  }
  
  do{
    scanf("%d", &s);
  }while(s != n);
  int vec[s];
  
  for(i = 0; i < s; i++){
    scanf("%d", &vec[i]);
  }
  
  for(i = 0; i < m; i++){
    sum = 0;
    for(j = 0; j < n; j++){
      sum = mat[i][j] + vec[j];

    }
    printf("%d\n", sum);
  }
  return 0;
}