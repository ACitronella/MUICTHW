/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */
#include <stdio.h>
#include <string.h>

struct Book{
    int id;
    char title[51];
    char author[51];
    int remaining;
  
};

struct Book scanbook(){
    struct Book book;
    scanf("%d %s %s %d", &book.id, book.title, book.author, &book.remaining);
    
    return book;
}

int checkbook(struct Book mybook){
    if(mybook.remaining){
        return 1;
    }
    return 0;
}

void printinfo(struct Book mybook){
    printf("Book ID: %d\n", mybook.id);
    printf("Book Name: %s\n", mybook.title);
    printf("Author: %s\n", mybook.author);
    printf("Amount: %d\n", mybook.remaining);
}
// Book ID: 1
// Book Name: MaryPoppins
// Author: P.L.Travers 
// Amount: 5

struct Book updatebook(struct Book mybook, char *query){
    strcpy(mybook.title, query);
    return mybook;
}

int main(){

    
    struct Book book = scanbook();
    int funcname;
    scanf("%d", &funcname);
    if(funcname == 0){
        if(checkbook(book)){
            printf("Available");
        }
        else{
            printf("Not available");
        }
    }
    else if(funcname == 1){
        printinfo(book); 
      
      
    }
    else if(funcname == 2){
        char query[51];
        scanf("%s", query);
        book = updatebook(book, query);
        printinfo(book);
      
    }
    return 0;
}