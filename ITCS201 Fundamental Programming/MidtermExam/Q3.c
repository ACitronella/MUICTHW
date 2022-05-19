/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */

#include <stdio.h>
int main(){
  int days;
  int month = 1;
  scanf("%d", &days);
  if(days > 31){
    days = days - 31;
    month++;
    
    if(days > 28){
      days = days - 28;
      month++;
      
      if(days > 31){
        days = days - 31;
        month++;
        
        if(days > 30){
          days = days - 30;
          month++;
          
          if(days > 31){
            days = days - 31;
            month++;
            
            if(days > 30){
              days = days - 30;
              month++;
              
              if(days > 31){
                days = days - 31;
                month++;
                
                if(days > 31){
                  days = days - 31;
                  month++;
                  
                  if(days > 30){
                    days = days - 30;
                    month++;
                    
                    if(days > 31){
                      days = days - 31;
                      month++;
                      
                      if(days > 30){
                        days = days - 30;
                        month++;
                        
                        if(days > 31){
                          days = days - 31;
                          month++;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  printf("%d %d", days, month);
  return 0;
}