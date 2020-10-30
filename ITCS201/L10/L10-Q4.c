#include <stdio.h>
int MIN_VAL = -4;
int MAX_VAL = 42;

int clip_value(int v){
  int temp;
  if(v > MAX_VAL){
    temp = MAX_VAL;
    // MAX_VAL = v;
  }
  else if(v < MIN_VAL){
    temp = MIN_VAL;
    // MIN_VAL = v;
  }
  else{
    temp = v;
  }
  return temp;
}


int main(void) {
  int n;
  scanf("%d", &n);
  int a[n];
  for(int i = 0; i < n; i++){
    scanf("%d", &a[i]);
  }
  for(int i = 0; i < n; i++){
    printf("%d ", clip_value(a[i])); 
  }
  return 0;
}