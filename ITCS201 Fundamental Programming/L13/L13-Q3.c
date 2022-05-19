
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct student{
  const char* name;
  float lab;
  float quiz;
  float mid;
  float final;
  float overall;
  char grade;
  
} Student;

Student* init_student(char* iname, float ilab, float iquiz, float imid, float ifinal){
  Student* unlucky_person = (Student*)malloc(sizeof(Student));
  unlucky_person -> name = iname;
  unlucky_person -> lab = ilab;
  unlucky_person -> quiz = iquiz;
  unlucky_person -> mid = imid;
  unlucky_person -> final = ifinal;
  unlucky_person -> overall = ((ilab) * 15 + 
        (iquiz) * 25 + 
        (imid) * 25 + 
        (ifinal) * 35) / 100;
  if(unlucky_person -> overall > 85){
    unlucky_person -> grade = 'A';
  }
  else if(unlucky_person -> overall > 75){
    unlucky_person -> grade = 'B';
  }
  else if(unlucky_person -> overall > 65){
    unlucky_person -> grade = 'C';
  }
  else if(unlucky_person -> overall > 55){
    unlucky_person -> grade = 'D';
  }
  else{
    unlucky_person -> grade = 'F';
  }
  return unlucky_person;
}

int main(void) {
  float lab, quiz, mid, final;
  float overall;
  char name[20];
  scanf("%s %f %f %f %f", name, &lab, &quiz, &mid, &final);
  Student* random_person = init_student(name, lab, quiz, mid, final);
  
  printf("%s's final score: %.2f\n", random_person -> name, random_person -> overall);
  printf("Your grade: %c\n", random_person -> grade);
  if(random_person -> grade  <= 'C'){
    printf("PASS");
  }
  else{
    printf("It's okay. See you next semester!");
  }
  
  free(random_person);
  return 0;
}
