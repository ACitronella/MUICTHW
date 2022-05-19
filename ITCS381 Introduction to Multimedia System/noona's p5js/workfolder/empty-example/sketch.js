//Paveena Kumnerdpun 6388088 Section 2
//Reference: https://editor.p5js.org/defneone/sketches/Skp4-0YYX
var mySound;
var myVoice;

function preload() {
  mySound = loadSound('song.mp3');
  myVoice = loadSound('funny laughing.mp3');
}

function setup() {
  canvas = createCanvas(400, 400);
  canvas.mousePressed(laughing);
  saveButton = createButton("SAVE PNG");
  saveButton.mousePressed(savePNG);
  mySound.loop();
}

var angle = 0.0; 
var amplitude = 60;
var speed = 0.06;
var isPause = false;
var i = 0;
var colorPalette = [
  ['#ff00c1', '#9600ff', '#4900ff', '#00b8ff', '#00fff9'],
  ['#feda75', '#fa7e1e', '#d62976', '#962fbf', '#4f5bd5'],
  ['#f23d5e', '#9704bf', '#f2cb07', '#d98f07', '#bf0404'],
  ['#cc3232', '#db7b2b', '#e7b416', '#99c140', '#2dc937'],
  ['#adff00', '#74d600', '#028900', '#00d27f', '#00ff83'],
  ['#f60404', '#ff3800', '#ff4f00', '#f87b05', '#f8e604'],
  ['#77aaff', '#99ccff', '#bbeeff', '#5588ff', '#3366ff']
];

function draw() {
  if(!isPause){
    background(50);
    //sin wave equation
    var y1 = height/2 + sin(angle) * amplitude;
    var y2 = height/2 + sin(angle + 0.5) * amplitude;
    var y3 = height/2 + sin(angle + 1.0) * amplitude;
    var y4 = height/2 + sin(angle + 1.5) * amplitude;
    var y5 = height/2 + sin(angle + 2.0) * amplitude;
    var x = width/2;
    //body of Kadub
    fill(colorPalette[i][0]);
    noStroke();
    c1 = ellipse(x - 100, y1, 100, 100);
    fill(colorPalette[i][1]);
    noStroke();
    c2 = ellipse(x - 50, y2, 100, 100);
    fill(colorPalette[i][2]);
    noStroke();
    c3 = ellipse(x, y3, 100, 100);
    fill(colorPalette[i][3]);
    noStroke();
    c4 = ellipse(x + 50, y4, 100, 100);
    fill(colorPalette[i][4]);
    noStroke();
    c5 = ellipse(x+100, y5, 100, 100);
    //eyes of Kadub
    fill(255);
    ellipse(x + 80, y5 - 5, 20);
    fill(0);
    ellipse(x + 80, y5 - 5, 10);
    fill(255);
    ellipse(x + 120, y5 - 5, 20);
    fill(0);
    ellipse(x + 120, y5 - 5, 10);
    //mouth of Kadub
    fill('#ff6f69');
    arc(x + 100, y5 + 10, 50, 50, 0, PI, CHORD);
    angle += speed;
  }
}

function keyPressed() {
  print(key + " Key is pressed");
  if(key == 'p') {
    if(!isPause){
      mySound.stop();
    }
    else{
      mySound.loop();
    }
    isPause = !isPause;
  }
  if(key == 'c') {
    i = i + 1;
    if(i > 6) i = 0;
  }
  if(key == 'f') {
    speed = speed + 0.02;
    if(speed > 0.2) speed = 0.2;
  }
  if(key == 's') {
    speed = speed - 0.02;
    if(speed < 0.02) speed = 0.02;
  }
}

function laughing() {
  if(!isPause){
    myVoice.play();
  }
}

function savePNG() {
  save(canvas, "Kadub.png");
}

