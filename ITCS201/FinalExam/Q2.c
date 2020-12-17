/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */

#include <string.h>
#include <stdio.h>
#include <ctype.h>

int main(){
    char str[101];
    fgets(str, 101, stdin);
    int n = strlen(str);
    char collection[101] = "";
    int x = 0, i;
    for(i = 0; i < n; i++){
        char diag = tolower(str[i]);
        if((diag >= 'a' && diag < 'z') || (diag >= '0' && diag <= '9')){
            collection[x] = diag;
            x++;
        }
    }
    // printf("collection : %s\n", collection );
    int is_palin = 1;
    for(i = 0; i < x; i++){
        if(collection[i] != collection[x-i-1]){
            is_palin = 0;
            break;
        }
    }
    if(is_palin){
        printf("Palindrome");
    } else {
        printf("Not palindrome");
    }
    return 0;
}