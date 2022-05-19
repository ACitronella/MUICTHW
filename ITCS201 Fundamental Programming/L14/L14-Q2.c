#include <stdio.h>
#define NCourse 3

////////////////////////////////////////////////////////
//                 Start of your code                 //
////////////////////////////////////////////////////////

/*
    TODO: 
        1. Define a structure of structure to store 
        Instructor information.

        2. Write two self-defined functions:
          2.1. get_info:   a function to get instructor
                           information from a terminal.
          2.2. print_info: a function to print instructor
                           information to a terminal.
        
        You can find the function definition 
        from the code in the main function.
*/

struct Course{
	char course_name[7]; 
	int credit; 
	int hours; 
};

struct Instructor{
	char name[50]; 
	char gender;
   	struct Course courses[NCourse];
}; 

struct Instructor get_info(){
	struct Instructor new_instructor;
	
	scanf("%s %c", new_instructor.name, &new_instructor.gender);
	for(int i = 0; i < NCourse; i++){ 
		scanf("%s %d %d", new_instructor.courses[i].course_name, &new_instructor.courses[i].credit, &new_instructor.courses[i].hours);
	}

	return new_instructor;

}

void print_info(struct Instructor to_print){
	printf("%s (%c)\n", to_print.name, to_print.gender);
   	for(int i = 0; i < NCourse; i++){
		printf("- %s (%d credits, %d hours)\n", to_print.courses[i].course_name, to_print.courses[i].credit, to_print.courses[i].hours);		
	}


}
////////////////////////////////////////////////////////
//                  End of your code                  //
////////////////////////////////////////////////////////

int main() {
    int n;  // n: number of instructors
    scanf("%d", &n);
    struct Instructor instructors[n];
    for (int i=0 ; i<n ; i++) {
        instructors[i] = get_info();
    }
    for (int i=0 ; i<n ; i++) {
        print_info(instructors[i]);
    }
    return 0;
}
