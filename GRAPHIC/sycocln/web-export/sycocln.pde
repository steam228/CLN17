//André Rocha
//DAR - Caldas Late Night
//The MAP



//Variables

/* @pjs
crisp=true;
font=/din-bold.otf;
font=/din-regular.otf;
*/

long lastTime = 0;
float x;
float y;
float d;
String nome;
float texts;
PFont font;
PFont font2;

void setup() {
  background (0);
  size(1024, 720);
  smooth();
  stroke (255);
  strokeWeight(1);
  noFill();
  smooth();
  frameRate(25);
  
  
}

void draw () {

  //  println (lastTime);
  //  println (millis());
  if ( millis() - lastTime > 1000 ) {
//    
//    float bg = random(0,20);
    
    background(254,254,255);
    noFill();
 for (int i = 0; i < random(height/20,height/10); i++){   
    float strk1 = random (0,3);
    float strk2 = random (0,3);
    float strk3 = random (30,60);
    stroke (strk1,strk2,strk3);
    float l = random (1,2);
    strokeWeight(l);
    x = random(0,width);
    y= random(0,height);
    d = random (5,30);
   
  
    rect (x, y, d, d);
    line (x, y, x+d/2, y-d/2);
    line (x+d/2, y-d/2, x+d, y);
    line (x+d, y, x, y+d);
    line (x, y, x+d, y+d);
    
    
    
    
 }
 fill (0,0,30);
 float sizer = random (54,58);
 font = createFont("/din-bold.otf",sizer);
 textFont (font);
 text ("ONDE ESTÁ O CALDAS?", width/5, height/2);
 float sizer2 = random (23,24);
 font2 = createFont("/din-regular.otf", sizer2);
 textFont (font2);
 text ("A DAR estará a tentar perceber isso", (width/5)+3, height/2+27);
 text ("no Largo do Hospital Termal", (width/5)+3, height/2+51);
 text ("SÁBADO, dia 25, entre as 10:00 e as 24:00", (width/5)+3, height/2+75);
    
  }
}


