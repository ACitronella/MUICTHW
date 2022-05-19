#include <stdio.h>

int compute_gcd(int a, int b){
  if(a < b){
      int temp = b;
      b = a;
      a = temp;
  }
  // with condition a > b
  int k, r = -1;
  while(r != 0){
      k = a / b;
      r = a % b;
    
      a = b;
      b = r;
  }
  return a;
}

int main() {
  int n;
  scanf("%d", &n);
  int l[n];
  for(int i = 0; i < n; i++){
    scanf("%d", &l[i]);
  }

  int gcd = l[0];
  for(int i = 1; i < n; i++){
    if(l[i] > 0){
      // this property is a key of the whole problem
      gcd = compute_gcd(gcd, l[i]); 
    }
    else{
      gcd = 1;
      break;
    }
  }
  printf("%d", gcd);
  return 0;
}