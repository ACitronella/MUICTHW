#include <stdio.h>

typedef struct Food{
  float calories;
  char name[50];
  int meal;
} food;


int main(void) {
  int amount_meals = 3;
  food meals[amount_meals];
  int count = 0;
  int i;
  for(i = 0; i < amount_meals; i++){
    scanf("%s %d %f", meals[i].name, &meals[i].meal ,&meals[i].calories); 
  }
  for(i = 0; i < amount_meals; i++){
    if(meals[i].calories > 500){
      count++;
    }
  }
  
  if(!count){
    printf("No fatty food. You are good.");
  }
  else{
    printf("You have %d meal(s) exceed 500 cal.", count);
  }
  return 0;
}
