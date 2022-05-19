#include <stdio.h>

struct Date{
    int day;
    int month;
    int year;
};

struct Movie{
    int movieID;
    char title[50];
    float income;
    struct Date date;
};

void swap(struct Movie *m1, struct Movie *m2)
{   /* --- Do not modify swap function --- 
    Example use: swap(&movieA, &movieB); */ 
    struct Movie m = *m1;
    *m1 = *m2;
    *m2 = m;
}

int main(){
    int n;
    scanf("%d", &n);
    // Get movie information
    struct Movie movies[n];
    float mean_income = 0;
    for(int i = 0; i < n; i++){
        scanf("%d %s %f %d %d %d", 
            &movies[i].movieID, 
            movies[i].title, 
            &movies[i].income, 
            &movies[i].date.day, 
            &movies[i].date.month, 
            &movies[i].date.year
        );

        mean_income = mean_income + movies[i].income;
    }
    mean_income = mean_income / n;
    // Sort the movie list by income
    /* 
    * it is better to sort only filtered. but whatever teacher says.
    * swap index i with max value, where i will start from 0 -> n.
    * This list of movies will be descending order.
    */
    for(int i = 0; i < n; i++){
        int max_index = i;
        for(int j = i + 1; j < n; j++){
            if(movies[j].income > movies[max_index].income){
                max_index = j;
            }
        }
        swap(&movies[i], &movies[max_index]);
    }

    // Print the movie with income above average
    printf("%.2f\n", mean_income);
    for (int i = 0; i < n; i++){
        if(movies[i].income > mean_income){
            printf("#%d:%s (%d-%d-%d) $%.2fM\n", 
                movies[i].movieID, 
                movies[i].title, 
                movies[i].date.year, 
                movies[i].date.month, 
                movies[i].date.day,
                movies[i].income
            );
        }
    }
}