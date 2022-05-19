/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003 
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */

#include <stdio.h>
int main(){
  int n;
  char t = 'z';
  scanf("%d", &n);
  for(int i = 1; i <= n; i++){
    
    printf("%d%c",i, t--);
    if(!(i == n)){
      printf(", ");
    }
  }
  
  return 0;
}