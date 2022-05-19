#include <stdio.h>
float compute_avg(int *a, int n){
  float sum = 0;
  for(int i = 0; i < n; i++){
    sum = sum + *(a+i);
  }
  
  return sum/n;
}
int main(void) {
  int n;
  scanf("%d", &n);
  int a[n];
  for(int i = 0; i < n; i++){
    scanf("%d", &a[i]);
  }
  printf("%.2f", compute_avg(a, n));
  return 0;
}