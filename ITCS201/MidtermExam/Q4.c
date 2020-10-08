/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */

#include <stdio.h>
int main(){
  int nvec1, nvec2;
  
  int iseq = 1;
  
  do{
    scanf("%d", &nvec1);
  }while(nvec1 <= 0);
  int vec1[nvec1];
  
  for(int i = 0; i < nvec1; i++){
    scanf("%d", &vec1[i]);
  }
  
  do{
    scanf("%d", &nvec2);
  }while(nvec2 <= 0);
  int vec2[nvec2];
  for(int i = 0; i < nvec2; i++){
    scanf("%d", &vec2[i]);
  }
  
  int npro = nvec1 - nvec2 + 1;
  if(npro >= 1){

    
    
    int provec[npro];
    for (int i = 0; i < npro; i++){
      provec[i] = 0;
    }
    
    for(int i = 0; i < npro; i++){
      for(int j = 0; j < nvec2; j++){
        provec[i] = provec[i] + (vec1[i+j] * vec2[j]);
       
      }
    }
    
    for(int i = 0; i < npro; i++){
      printf("%d ", provec[i]);
    }
  }
  else{
    printf("invalid");
  }
  return 0;
}