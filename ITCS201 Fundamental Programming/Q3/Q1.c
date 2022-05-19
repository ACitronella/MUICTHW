    /*  -----  Please fill in your information in this comment block -----  
    Student ID: 6388003 
    Fullname: Phuriwat Angkoondittaphong
    Section: 1
    ---------------------------------------------------------------------- */

    /*  ===== Put your code here ===== */
    #include <stdio.h> 
    int main(){
    
    int n; 
    scanf("%d", &n);
    int a[n];
    int mode, i;
    int util = 0, isnotfound = 1;
    for (i = 0; i < n; i++){
        scanf("%d", &a[i]);
    }
    
    scanf("%d", &mode);
    switch(mode){
        case 1:
        for(i = 0; i < n; i++){
            util = util + a[i];
        }
        printf("%.2f", (float)util/n);
        break;
        
        case 2: 
        scanf("%d", &util);
        for(i = 0; i < n; i++){
            if(util >= a[i]){
            printf("%d ", i);
            isnotfound = 0;
            }
        }
        if(isnotfound){
            printf("no match");
        }
        break;
    }
    return 0;
    }