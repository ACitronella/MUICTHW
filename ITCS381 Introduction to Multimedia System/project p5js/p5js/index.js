const dt = 0.050;
const radius_scale = 0.98;
let OT;
let function_list;
let params;

function generate_circle_eq(init, frequency, orbit_radius, no_circle, cc, radius, color){
  return function(t){
    let theta;
    let ar = [];
    for(let i = 0; i < no_circle; i++){
      theta = 2 * PI * frequency * cc * (t - dt * i) + init;
      ar.push([orbit_radius * cos(theta), orbit_radius * sin(theta), radius*pow(radius_scale, i), color]);
    }
    return ar;
  }
}

function setup() {
  createCanvas(400, 400);
  noStroke();
  OT = Math.floor(millis() / 1000);
}

function draw() {
  background(255);
  let t = millis() / 1000;
  
  if(Math.floor(t) >= OT){
    params = [
      [(Math.random()*2*PI), 0.4, 5, 20, 1, 5, [0, 0, 0]], 
      [(Math.random()*2*PI), 0.041, 15, 110, -1, 6, [250, 0, 250]],
      [(Math.random()*2*PI), 0.042, 30, 120, 1, 7, [220, 0, 220]], 
      [(Math.random()*2*PI), 0.043, 45, 130, -1, 8, [190, 0, 190]],
      [(Math.random()*2*PI), 0.044, 60, 140, 1, 9, [160, 0, 160]],
      [(Math.random()*2*PI), 0.045, 80, 150, -1, 9, [130, 0, 130]],
      [(Math.random()*2*PI), 0.046, 100, 160, 1, 9, [110, 0, 110]],
      [(Math.random()*2*PI), 0.047, 120, 170, -1, 10, [80, 0, 80]],
      [(Math.random()*2*PI), 0.048, 140, 180, 1, 10, [50, 0, 50]],
      [(Math.random()*2*PI), 0.048, 160, 190, -1, 10, [10, 0, 10]]
    ];
  
    function_list = params.map(x => generate_circle_eq(...x));
    OT = Math.floor(t+0.8);
  }
  
  let center_circles = function_list.map(f => f(t));
  for(let a = 0; a < center_circles.length; a++){
    for(let i = 0; i < center_circles[a].length; i++ ){
      let [x,y,r,c] = center_circles[a][i];
      fill(color(...c));
      circle(x + width/2, y + height/2, r);  
    }  
  }
}
