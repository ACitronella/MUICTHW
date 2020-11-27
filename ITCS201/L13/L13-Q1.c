#include <stdio.h>

typedef struct Food{
  float calories;
  char name[50];
  int meal;
} food;

int main(void) {
  int amount_meals = 3;
  food meals[amount_meals];
  int i;
  for(i = 0; i < amount_meals; i++){
    scanf("%s %d %f", meals[i].name, &meals[i].meal ,&meals[i].calories);
    
  }
  float sum_cal = 0;
  
  for(i = 0; i < amount_meals; i++){
    sum_cal = sum_cal + meals[i].calories;  
  }
  printf("Total eat: %.2f\n", sum_cal);
  for(i = 0; i < amount_meals; i++){
    printf("%d-%s", meals[i].meal, meals[i].name);
    if(i < amount_meals-1){
      printf(",");
    }
  }
  return 0;
}
