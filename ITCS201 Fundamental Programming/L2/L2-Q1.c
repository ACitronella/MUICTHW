#include <stdio.h>

int main(){
  float x1, y1, x2, y2;
  scanf("%f %f %f %f", &x1, &y1, &x2, &y2);
  printf("%.2f", (y2-y1)/(x2-x1));
  return 0;
}