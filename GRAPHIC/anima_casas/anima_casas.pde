 //variáveis
 
 
 
 void setup(){
 
 
 
 
 }
 
 
 void draw(){
 
 
 
 
 
 
 }
 
 
 
  void desenha (x,y,d) {

    smooth();
    stroke (204, 20, 0);
    strokeWeight(1);
    noFill();

//    textSize(t);
//    text("#"+n, x+t, y-t);
    rect (x, y, d, d);
    line (x, y, x+d/2, y-d/2);
    line (x+d/2, y-d/2, x+d, y);
    line (x+d, y, x, y+d);
    line (x, y, x+d, y+d);
  }
