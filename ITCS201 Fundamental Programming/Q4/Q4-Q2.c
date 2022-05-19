#include <stdio.h>

int is_triangle(float side1, float side2, float side3){
  // side1 + side2 > side3 is a true is_triangle
  // with condition side 3 is longest side
  // hate to find max without an array
  int is_valid = -1;
  if(side1 + side2 > side3){
    is_valid = 1;
  }
  return is_valid;
}

int main(void) {
  float side[3];
  scanf("%f %f %f", &side[0], &side[1], &side[2]);
  // bruh i dont want to do this find max thing, but it is only way in my mind
  int find_max = 0;
  float temp; 
  for(int i = 1; i < 3; i++){
    if(side[find_max] < side[i]){
      find_max = i;
    }
  }
  temp = side[2];
  side[2] = side[find_max];
  side[find_max] = temp; // swap last place with max value
  
  printf("%d", is_triangle(side[0], side[1], side[2]));
  return 0;
}