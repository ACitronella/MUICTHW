/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */
#include <stdio.h>
#include <string.h>

int main(){
    char str[51];
    char set[51] = "";
    
    fgets(str, 51, stdin);
    char *pos;
    if ((pos=strchr(str, '\n')) != NULL)
        *pos = '\0';
    int i, j, x = 0;
    for(i = 0; i < strlen(str); i++){
        int is_dup = 0;
        for(j = 0; j < i; j++){
            if(str[i] == str[j]){
                is_dup = 1;
            }
        }
        
        if(!is_dup){
            set[x] = str[i];
            x++;
        }
    }
    printf("%s %d", set, i - x);
    return 0;
}