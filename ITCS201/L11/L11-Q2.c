#include <stdio.h>
#include <math.h>
float compute_avg(int *a, int n){
  float sum = 0;
  for(int i = 0; i < n; i++){
    sum = sum + *(a+i);
  }
  return sum/n;
}

float compute_std(int *a, int n){
  float x_bar = compute_avg(a, n);
  float s = 0;
  for(int i = 0; i < n; i++){
    s = s + (*(a+i) - x_bar) * (*(a+i) - x_bar);
  }
  return sqrt(s/(n-1));
  
}

int main(void) {
  int n;
  scanf("%d", &n);
  int a[n];
  for(int i = 0; i < n; i++){
    scanf("%d", &a[i]); 
  }
  printf("%.2f", compute_std(a, n));
  return 0;
}