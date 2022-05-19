/* Type Your Code here */
#include <stdio.h>
int main(){
  int sec;
  scanf("%d",&sec);

  int minute = sec / 60;
  sec = sec % 60;
  int hour = minute / 60;
  minute = minute % 60;
  printf("%d:%d:%d", hour, minute, sec);
  return 0;
}