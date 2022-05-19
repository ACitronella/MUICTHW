/* Type Your Code here */
/* Type Your Code here */
#include <stdio.h>
int main(){
  float x, y; 
  scanf("%f %f", &x, &y);
  if(x==0||y==0){
    printf("No quadrant");
    
  }
  else if (x > 0 && y > 0){
    
    printf("Q1");
  }
  else if (x < 0 && y > 0){
    
    printf("Q2");
  }
  else if (x < 0 && y < 0){
    
    printf("Q3");
  }
  else{
    
    printf("Q4");
  }
  
  return 0;
}