Bola bola;

void setup(){
  size(800, 600);
  background(255);

  bola = new Bola();
}


void draw(){
  background(255, 255, 255);
  noStroke();
  fill(255, 0, 0);
  bola.mover();
  bola.desenhar();
}



