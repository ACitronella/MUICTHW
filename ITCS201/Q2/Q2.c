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
  }while(n <= 2);
  int i, j;
  char d;
  for(i = 0; i < n; i++){
    for(j = 0; j < n; j++){
      if((i == 0) || (i == n-1) || (j == 0) || (j == n-1)){
        d = '*';
      }
      else if((i + j) % 2){
        d = 'X';
      }
      else{
        d = 'O';
      }
      printf("%c ", d);
    }
    printf("\n");
  }
  
  return 0;
}