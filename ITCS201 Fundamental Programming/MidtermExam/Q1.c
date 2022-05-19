/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */

#include <stdio.h>
int main(){
  int n;
  do{
    scanf("%d", &n);
  }while(n <= 0);
  
  for(int i = 1; i <= n; i++){
    for(int j = 1; j <= i; j++){
      for(int k = 1; k <= i; k++){
        
        printf("%d ", i);
      }
      printf("\n");
    }
  }
  return 0;
}