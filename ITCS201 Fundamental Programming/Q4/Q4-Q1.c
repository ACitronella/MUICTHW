#include<stdio.h>

int DATA[8] = {0, 1, 2, 6, 7, 5, 4, 3};
int max_y = 0;
int i = 0;
int len = 8;

int times() {
    return DATA[i] * DATA[i+1];
}

void loop(int len) {
    // int max_y; //setting new local variable will override global one
    int y;
    // len = len - 1; // iterate though len of the array not len - 1
    for (; i<len; i++) { // delete init state, to not override global one
        y = times();
        if (max_y < y) {
            max_y = y;
        }
    }
    
}

int main(){
    loop(len);
    printf("The answer is %d", max_y);
    return 0;
}
