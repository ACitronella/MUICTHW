/* Type Your Code here */
# include <stdio.h>

int main(){
  int m, n;
  do{
  	scanf("%d %d", &m, &n);
  }while(!(m > 0 && n > 0));
  int l[m][n];
  int i, j, maxrow = 0, maxvalue, sumrow;
	
  for(i = 0; i < m; i++){
    for(j = 0; j < n; j++){
      scanf("%d", &l[i][j]);
    }
  }
  for(i = 0; i < m; i++){
    sumrow = 0;
    for(j = 0; j < n ; j++){
      sumrow += l[i][j];
      
    }
    if(i == 0){
      maxvalue = sumrow;
    }
    else if (sumrow > maxvalue){
	    maxvalue = sumrow;
      maxrow = i; 

    }
  }
  printf("%d", maxrow);
  
  
  return 0;
}