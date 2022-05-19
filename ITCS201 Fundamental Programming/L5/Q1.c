  /* Type your code here */ 
# include <stdio.h>
int main(){
  
  int w, h, i, j;
  do{
    scanf("%d %d", &h, &w);
  }while(!(h > 0 && w > 0));
  for (i = 0 ;i < h; i++){
    for (j = 0 ; j < w; j++){
      printf("* ");
    }
    printf("\n");
  }
    
  
  return 0;
    
}