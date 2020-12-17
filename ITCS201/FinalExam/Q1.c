/*  -----  Please fill in your information in this comment block -----  
   Student ID: 6388003
   Fullname: Phuriwat Angkoondittaphong
   Section: 1
---------------------------------------------------------------------- */

/*  ===== Put your code here ===== */
#include <stdio.h>

struct Item{
    char item_name[20];
    int quatity;
    float price;
    int is_taxed;
    
};

struct Order{
    int order_id;
    char customer_name[20];
    struct Item items[3];
    float total;
    float vat_refund;
};


int main(){
    
    int n;
    scanf("%d", &n);
    struct Order orders[n];
    
    for(int i = 0; i < n; i++){
        scanf("%d %s", &orders[i].order_id, orders[i].customer_name);
        float vat_refund = 0.0;
        float total = 0.0;
        for(int j = 0; j < 3; j++){
            scanf("%s %d %f %d", 
                orders[i].items[j].item_name, 
                &orders[i].items[j].quatity, 
                &orders[i].items[j].price, 
                &orders[i].items[j].is_taxed
            );
            if(orders[i].items[j].is_taxed){
                vat_refund = vat_refund + (orders[i].items[j].price * orders[i].items[j].quatity) * 7.0 / 100.0;
            }
            total = total + orders[i].items[j].price * orders[i].items[j].quatity;
        }
        orders[i].vat_refund = vat_refund;
        orders[i].total = total;
    }
    
    for (int i = 0; i < n; i++){
        printf("Order#%d from %s\n", orders[i].order_id, orders[i].customer_name);
        printf("Total: %.2f VAT-refunded: %.2f\n", orders[i].total, orders[i].vat_refund);
    }
    
    return 0;
}

// 8 kptSOQQojGgZItkZv7W
// kdIuhAfmudYMdZDmDfl 1 6764.19 1
// qNARDrT43qL3gD30JVP 16 8204.5 1
// tWuMToeZyrd5XUpXxMz 8 9751.29 1