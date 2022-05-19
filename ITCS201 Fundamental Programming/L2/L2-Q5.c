/* Type Your Code here */
#include <stdio.h>
int main(){
  char c;
  char* str;
  scanf("%c", &c);
  
  if(c >= 'a' && c <= 'z'){
    c = c - (char)32;
  }

  
  switch(c){
    case 'M':
      str = "Mastery";
      break;
    case 'A':
      str = "Altruism";
      break;
    case 'H':
      str = "Harmony";
      break;
    case 'I':
      str = "Integrity";
      break;
    case 'D':
      str = "Determination";
      break;
    case 'O':
      str = "Originality";
      break;
    case 'L':
      str = "Leadership";
      break;
    default:
      str = "Invalid character";
  }
  puts(str);
  
}