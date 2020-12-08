/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */
#include <stdio.h>
////////////////////////////////////////////////////////
//            Start of your code Part 1               //
////////////////////////////////////////////////////////
/*
    TODO: -- Part 1 --
        1. Define a structure to store Dog information.

        2. Write two self-defined functions:
          2.1. get_info:   a function to get dog
                           information from a terminal
               The function prototype is 
               struct Dog get_info();
               
          2.2. print_info: a function to print dog
                           information to a terminal.
               The function prototype is 
               void print_info(struct Dog d);
        
    More To-do (Part 2) in the main function.
*/

struct Dog{
    char breed[21];
    int lifespan;
    int height;
  
};

struct Dog get_info(){
    struct Dog mydog;
    scanf("%s %d %d", mydog.breed, &mydog.lifespan, &mydog.height);
    return mydog;
}

void print_info(struct Dog d){
    printf("breed=%s lifespan=%dyr height=%dcm\n", d.breed, d.lifespan, d.height);
  
}
////////////////////////////////////////////////////////
//              End of your code Part 1               //
////////////////////////////////////////////////////////

int main() {
    int n;
    scanf("%d", &n);

    ////////////////////////////////////////////////////////
    //              Start of your code Part 2             //
    ////////////////////////////////////////////////////////
    /*
        TODO: -- Part 2 --
        3. Create an array of struct Dog size n
        4. Get each dog info. by calling get_info() function one by one
        5. Print out dog info. by calling print_info() fucntion one by one
    */

    struct Dog doggo[n];
    for(int i = 0; i < n; i++){
        doggo[i] = get_info();
    }
    for(int i = 0; i < n; i++){
        print_info(doggo[i]);
    }
    ////////////////////////////////////////////////////////
    //                End of your code Part 2             //
    ////////////////////////////////////////////////////////
    return 0;
}