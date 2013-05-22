float beginX = 20.0;  // Initial x-coordinate
float beginY = 10.0;  // Initial y-coordinate
float endX = 570.0;   // Final x-coordinate
float endY = 320.0;   // Final y-coordinate
float distX;          // X-axis distance to move
float distY;          // Y-axis distance to move
//float exponent = 4;   // Determines the curve ANULAR
float x = 0.0;        // Current x-coordinate
float y = 0.0;        // Current y-coordinate
float step = 0.01;    // Size of each step along the path
//float pct = 0.0;      // Percentage traveled (0.0 to 1.0)

//VARIÁVEIS NOVAS

float radiusbezier = 0; //raio de distribuição de origem e fim indexar a dim casa
float dim = 20; // dim que vem do tamanho actual da casa

void setup() {
  size(640, 360);
  noStroke();
  distX = endX - beginX;
  distY = endY - beginY;
}

void draw() {
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


void mousePressed() { //substituir por função de novo twitt
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
