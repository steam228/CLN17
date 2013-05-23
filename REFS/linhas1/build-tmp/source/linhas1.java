import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class linhas1 extends PApplet {

float beginX = 20.0f;  // Initial x-coordinate
float beginY = 10.0f;  // Initial y-coordinate
float endX = 570.0f;   // Final x-coordinate
float endY = 320.0f;   // Final y-coordinate
float distX;          // X-axis distance to move
float distY;          // Y-axis distance to move
//float exponent = 4;   // Determines the curve ANULAR
float x = 0.0f;        // Current x-coordinate
float y = 0.0f;        // Current y-coordinate
float step = 0.01f;    // Size of each step along the path
//float pct = 0.0;      // Percentage traveled (0.0 to 1.0)

//VARI\u00c1VEIS NOVAS

float radiusbezier = 0; //raio de distribui\u00e7\u00e3o de origem e fim indexar a dim casa
float dim = 20; // dim que vem do tamanho actual da casa

public void setup() {
  size(640, 360);
  noStroke();
  distX = endX - beginX;
  distY = endY - beginY;
}

public void draw() {
//  fill(0, 2);
//  rect(0, 0, width, height);
//  pct += step;
//  if (pct < 1.0) {
    x = beginX + distX;
    y = beginY + distY;
//  }
  
  fill(255);
  noFill();
  stroke(0);
  bezier(beginX, beginY, beginX, beginY, endX, endY, endX, endY);
  
  
  
//  ellipse(x, y, 20, 20);
}


public void mousePressed() { //substituir por fun\u00e7\u00e3o de novo twitt
//  pct = 0.0;
  beginX = x;
  beginY = y;
  endX = mouseX;
  endY = mouseY;
  distX = endX - beginX;
  distY = endY - beginY;
  radiusbezier = random (dim, dim+random(20,40));
  ellipse (endX, endY, radiusbezier,radiusbezier);
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "linhas1" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
