ArrayList bolas;

//número inicial de bolas 
int numeroDeBolas = 500;

void setup(){
  size(800, 600,OPENGL);
  background(0); 
  bolas = new ArrayList();
frameRate(30);
//criamos numeroDeBolas para começar....
  for(int i=0; i<numeroDeBolas; i++){
    bolas.add(new Bola()); 
  }
}

void draw(){
  // bolas.add(new Bola()); 
  background(0);
  stroke(255, 255, 255, 100);
  //nosso loop a interar por todas as bolas
  for(int i=0; i<bolas.size(); i++){
    Bola bolaA = (Bola)bolas.get(i);
    bolaA.mover();
    
    //dentro deste loop, temos outro loop
    //onde cada bola vai interagir com todas as outras bolas
    //aplicando uma força de repulsa
    for(int j=i+1; j<bolas.size(); j++){
      Bola bolaB = (Bola)bolas.get(j); 
      //dx e dy representam a diferença de posição entre as 2 bolas
      float dx = bolaA.posicaoX - bolaB.posicaoX;
      float dy = bolaA.posicaoY - bolaB.posicaoY;
      float distancia = dist (bolaA.posicaoX, bolaA.posicaoY, bolaB.posicaoX, bolaB.posicaoY);
      //dividindo dx e dy pela distancia ficamos com um vector unário que aponta desde bolaA até bolaB
      dx /= distancia;
      dy /= distancia;
      //como este vector é unário (tem tamanho 1) podemos então aplicar a fórmula de repulsa a cada um dos eixos
      float forcaX = dx * (5 / distancia);
      float forcaY = dy * (5 / distancia);
      //somamos a força à bolaA
      bolaA.aceleracaoX += forcaX;
      bolaA.aceleracaoY += forcaY;
      //e subtraimos à bolaB
      bolaB.aceleracaoX -= forcaX;
      bolaB.aceleracaoY -= forcaY;
      
      //se a distância for menor que 40, 
      //desenhamos ainda uma linha a unir ambos os pontos
      if(distancia < 40){
        line(bolaA.posicaoX, bolaA.posicaoY, bolaB.posicaoX, bolaB.posicaoY);
      }
    }
    float dx = bolaA.posicaoX - mouseX;
    float dy = bolaA.posicaoY - mouseY;
    float distancia = dist(bolaA.posicaoX, bolaA.posicaoY, mouseX, mouseY);
    dx /= distancia;
    dy /= distancia;
    float forcaX = dx * (200 / distancia);
    float forcaY = dy * (200 / distancia);
    bolaA.aceleracaoX += forcaX;
    bolaA.aceleracaoY += forcaY;
  }
}

void mouseReleased(){
  Bola bola = new Bola();
  bola.posicaoX = mouseX;
  bola.posicaoY = mouseY;
  bolas.add(bola);
}



