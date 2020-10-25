#include <stdio.h>

int find_min(int a, int b){
  return (a>b)*(b) + (a<=b)*(a); 
}

int main(void) {
  int a, b;
  scanf("%d %d", &a, &b);
  printf("%d", find_min(a, b));
  return 0;
}