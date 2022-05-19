/* Type Your Code here */
/* Type Your Code here */
#include <stdio.h>
int main(){
  int s;
  scanf("%d", &s);
  if(s >= '0' && s <= '9'){
    printf("The ASCII value of %d is a Number \'%c\'", s, s);
  }
  else if(s >= 'A' && s <= 'Z'){
    printf("The ASCII value of %d is a Uppercase Letter \'%c\'", s, s);
  }
  else if(s >= 'a' && s <= 'z'){
    printf("The ASCII value of %d is a Lowercase Letter \'%c\'", s, s);
  }
  else if(s >= 0 && s <= 127){
    printf("The ASCII value of %d is a Control character or Special character \'%c\'", s, s);
  }
  else{
    printf("Invalid ASCII value");
  }
  return 0;
}