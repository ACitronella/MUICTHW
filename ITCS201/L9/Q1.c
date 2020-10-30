#include <stdio.h>
#include <math.h>
double euc_dist(double x1, double x2, double y1, double y2){
  double deltax = x2 - x1;
  double deltay = y2 - y1;
  return sqrt((deltax*deltax) + (deltay*deltay));
}

int main(void) {
  float x1, x2, y1, y2;
  scanf("%f %f %f %f", &x1, &y1, &x2, &y2);
  printf("%.2lf", euc_dist(x1, x2, y1, y2));
  return 0;
}