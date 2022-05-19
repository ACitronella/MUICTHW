
API_KEY = "6e091a09d97f4b988d018046beb95043"; // my apikey

function on_query() {
    let query = document.querySelector("#query").value;
    let div_food_list = document.querySelector("#food-list");
    let old_list = document.querySelector("#food-list-ul");
    let food_list = document.createElement("ul");
    food_list.id = "food-list-ul";
    
    console.log(query);
    let rooturl = "https://api.spoonacular.com/recipes/complexSearch?apiKey=" + API_KEY + "&query=" + query; //Request URL
    fetch(rooturl)
        .then((res) => res.json()) // Get JSON from the response
        .then((data) => {
            console.log(data);
            for(const food_data of data.results) {
                let food_img = document.createElement("img");
                food_img.setAttribute("src", food_data.image);

                let food_title = document.createElement("div")
                food_title.appendChild(document.createTextNode(food_data.title));

                let food_block = document.createElement("li")
                food_block.appendChild(food_title);
                food_block.appendChild(food_img);
                
                food_list.appendChild(food_block);
            }
            div_food_list.replaceChild(food_list, old_list);
        })
        .catch((err) => console.log(err));

}