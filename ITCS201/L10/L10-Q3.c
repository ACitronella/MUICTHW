#include <stdio.h>


int compute_gcd(int a, int b){
  while(a != b){
    if (a > b){
      a = a - b;
    
    }
    else{
      b = b - a;
    }
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
  for(int i = 0; i < n; i++){
    if(l[i] > 0){
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