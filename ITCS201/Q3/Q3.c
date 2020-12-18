/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003 
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */
#include <stdio.h> 
int main(){
  int n;
  scanf("%d", &n);
  int a[n*(n+1)/2];
  a[0] = 0;
  a[1] = 1;
  for (int i = 2; i < n*(n+1)/2; i++){
    a[i] = a[i-1] + a[i-2];
  }
  int c = 0;
  for(int i = 0;i < n; i++){
    for(int j = 0; j <= i ; j ++){
      printf("%d\t", a[c]);
      c++;
    }
    printf("\n");
  }
  return 0;
}