//André Rocha
//DAR - Caldas Late Night
//The MAP

import javax.media.opengl.*;
import processing.opengl.*;

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
int margem;

ArrayList Points;

void setup() {
  background (0);
  size(1024, 720, P3D);
  smooth();
  stroke (255);
  strokeWeight(1);
  noFill();
  smooth();
  frameRate(25);

  background(0, 0, 30);
  noFill();
  for (int i = 0; i < 140; i++) {   
    float strk1 = random (0, 20);
    float strk2 = random (35, 40);
    float strk3 = random (35, 40);
    stroke (strk1, strk2, strk3);
    float l = random (1, 2);
    strokeWeight(l);
    x = random(20, width-20);
    y= random(20, height-20);
    d = random (5, 30);
    rect (x, y, d, d);
    line (x, y, x+d/2, y-d/2);
    line (x+d/2, y-d/2, x+d, y);
    line (x+d, y, x, y+d);
    line (x, y, x+d, y+d);
  }
  
  
  // Formatação de texto
  fill (0, 255, 255);
  float sizer = random (64, 72);
  font = createFont("BigNoodleTitling.ttf", sizer);
  textFont (font);
  text ("ONDE ESTÁ O CALDAS?", width/5, height/2);
  float sizer2 = random (23, 24);
  font2 = createFont("BigNoodleTitling-Oblique.ttf", sizer2);
  textFont (font2);
  text ("A DAR estará a tentar perceber isso", (width/5)+3, height/2+27);
  text ("no Largo do Hospital Termal", (width/5)+3, height/2+51);
  text ("SÁBADO, dia 25, entre as 10:00 e as 24:00", (width/5)+3, height/2+75);
}

void draw () {

  //  println (lastTime);
  //  println (millis());
  if ( millis() - lastTime > 1000 ) {
    //    
    //    float bg = random(0,20);
  }
  
}

void desenhacasa (float x, float y, float z, float d)

