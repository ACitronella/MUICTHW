/* Type Your Code here */
#include <stdio.h>
int main(){
  int accsum = 0, max, temp, twicefirst, i=1;
  do{
    
    scanf("%d", &temp);
    
    if(i){ // this if is for only first time 
      twicefirst = temp * 2;
      max = temp;
      i = 0;
    }
    if(max < temp){
      max = temp;
    }
    
    accsum = accsum + temp;
    
  }while(!(accsum > twicefirst));
  printf("%d",max);
  return 0;
}