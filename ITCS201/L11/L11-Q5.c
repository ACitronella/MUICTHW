#include <stdio.h>

int intersect(int *arr1, int *arr2, int n, int *out){
  int c = 0;
  for(int i = 0; i < n; i++){
    for(int j = 0; j < n; j++){
      if(*(arr1+i) == *(arr2+j)){
        *(out+c) = *(arr1+i);
        c++;
        
      }
    }
  }
  return c;
}
int main(void) {
  int n;
  scanf("%d", &n);
  int a[n], b[n], c[n], len_of_set;
  for(int i = 0; i < n; i++){
    scanf("%d", &a[i]);
  }
  for(int i = 0; i < n; i++){
    scanf("%d", &b[i]);
  }
  
  len_of_set = intersect(a, b, n, c);
  
  if(len_of_set > 0){
    for(int i = 0; i < len_of_set; i++){
      printf("%d ", c[i]);
    }
  }
  else{
    printf("No intersect");
  }
  
  return 0;
}