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

public class linhasMover extends PApplet {

boolean changed = false;
 
ArrayList x = new ArrayList();
ArrayList y = new ArrayList();
 
public void setup()
{
  size(400,400);
  background(255);
  smooth();
   
  fill(0);
  text("[CLICK] add,  [ANY KEY] delete",20,20);
}
 
public void draw(){
  background(255);
  fill(0);
  text("[CLICK] add,  [ANY KEY] delete",20,20);
  drawSpline(x,y);
   
  // randomize
  for(int i=0; i<x.size(); i++){
    float x0 = ((Float)x.get(i)).floatValue();
    float y0 = ((Float)y.get(i)).floatValue();
     
    x.set(i, new Float(x0 + random(2.0f)-1.0f));
    y.set(i, new Float(y0 + random(2.0f)-1.0f));
     
  }
   
}
 
public void mousePressed(){
  x.add(new Float(mouseX));
  y.add(new Float(mouseY));
   
  changed = true;
}
 
public void keyPressed(){
  if(x.size()<1) return;
   
  x.remove(x.size()-1);
  y.remove(y.size()-1);
   
  changed = true;
}
 
public void drawSpline(ArrayList x, ArrayList y){
  int s = x.size();
  float[] mx = new float[s];
  float[] my = new float[s];
   
  for(int i=0; i<s; i++){
    mx[i] = ((Float)x.get(i)).floatValue();
    my[i] = ((Float)y.get(i)).floatValue();
  }
   
  drawSpline(mx,my);
}
 
public void drawSpline(float[] x, float[] y)
{
  if(x.length<1) return;
   
  Spline xs, ys;
  xs = new Spline(x);
  ys = new Spline(y);
   
  stroke(0);
  noFill();
  beginShape();
   
  for(float t=0.0f; t<=x.length-1; t += 0.01f){
    vertex(xs.calc(t), ys.calc(t));
    //point(xs.calc(t), ys.calc(t));
  }
   
  endShape();
   
  noStroke();
  fill(255,0,0);
  ellipseMode(CENTER);
   
  for(int i=0; i<x.length; i++){
    ellipse(x[i],y[i],4,4);
  }
}
 
class Spline
{
  int n;
  float[] a, b, c, d;
  float[] x;
   
  Spline(float[] sp)
  {
    float[] w;
    float tmp;
    n = sp.length;
     
    a = new float[n];
    b = new float[n];
    c = new float[n];
    d = new float[n];
    w = new float[n];
     
    for(int i=0; i<n; i++){
      a[i] = sp[i];
    }
     
    c[0] = 0.0f;
    c[n-1] = 0.0f;
     
    for(int i=1; i<n-1; i++){
      c[i] = 3.0f * (a[i-1] - 2.0f*a[i] + a[i+1]);
    }
     
    w[0] = 0.0f;
    for(int i=1; i<n-1; i++){
      tmp = 4.0f - w[i-1];
      c[i] = (c[i] - c[i-1])/tmp;
      w[i] = 1.0f / tmp;
    }
     
    for(int i=n-2; i>0; i--){
      c[i] = c[i] - c[i+1] * w[i];
    }
     
    b[n-1] = 0.0f;
    d[n-1] = 0.0f;
     
    for(int i=0; i<n-1; i++){
      d[i] = (c[i+1] - c[i])/3.0f;
      b[i] = a[i+1] - a[i] - c[i] - d[i];
    }
     
    /*
    for(int i=0; i<n; i++){
      print("[" + i + "] ");
      println("a:" + a[i] + " b:" + b[i] + " c:" + c[i] + " d:" + d[i]);
    }
    println();
    */
     
  }
   
  public float calc(float t)
  {
    int j;
    float dt;
     
    j = floor(t);
     
    if(j<0) j=0;
    else if(j>n) j=n;
    
    dt = t - (float)j;
    return a[j] + (b[j] + (c[j] + d[j]*dt)*dt)*dt;
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "linhasMover" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
