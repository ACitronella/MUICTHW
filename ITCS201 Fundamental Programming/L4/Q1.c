  /* Type Your Code here */
#include <stdio.h>
int main(){
  
  int max, min, i, temp;
  for( i = 0; i < 5; i++){
    scanf("%d", &temp);
    if(i == 0){
      min = temp;
      max = temp;
      
    }
    if(temp < min){
      min = temp;
    }
    if(temp > max){
      max = temp;
    }
    
  }
  if(min % 2 == 1){
    min += 1;
  }
  if(max % 2 == 1){
    max -= 1;
  }
  for(i = min; i <= max; i+=2){
    printf("%d", i);  
    if(max != i){
      printf(", ");
    }
  }
  
  return 0;
}