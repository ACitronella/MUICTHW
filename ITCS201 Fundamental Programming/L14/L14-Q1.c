#include <stdio.h>
#include <stdlib.h> 
typedef struct human_stat{
	char name[50];
	float weight;
	float height;
	float bmi;
} Human_Stat; 

int main(){
	int n; 
	scanf("%d", &n); 
	Human_Stat *human = (Human_Stat *)malloc(sizeof(Human_Stat) * n);
	for(int i = 0; i < n; i++){
		scanf("%s %f %f", (human + i) -> name, &(human + i) -> weight, &(human + i) -> height); 
		(human + i) -> bmi = (human + i) -> weight/((human + i) -> height * (human + i) -> height); 
	}			
	int isnoone = 1;	
	for(int i = 0; i < n; i++){
		if((human + i) -> bmi < 18.5){ 
			printf("%s is underweight: w=%.2f, h=%.2f, bmi=%.2f\n", (human + i) -> name, (human + i) -> weight, (human + i) -> height, (human + i) -> bmi);
			isnoone = 0;
		}
	}
	if(isnoone){
		printf("No underweight subjects");
	}
	free(human);				
	return 0;
}
