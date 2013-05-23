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

public class AtraccaoRepulsa extends PApplet {

ArrayList bolas;

//n\u00famero inicial de bolas 
int numeroDeBolas = 500;

public void setup(){
  size(800, 600);
  background(0); 
  bolas = new ArrayList();

//criamos numeroDeBolas para come\u00e7ar....
  for(int i=0; i<numeroDeBolas; i++){
    bolas.add(new Bola()); 
  }
}

public void draw(){
  background(0);
  stroke(255, 255, 255, 100);
  //nosso loop a interar por todas as bolas
  for(int i=0; i<bolas.size(); i++){
    Bola bolaA = (Bola)bolas.get(i);
    bolaA.mover();
    
    //dentro deste loop, temos outro loop
    //onde cada bola vai interagir com todas as outras bolas
    //aplicando uma for\u00e7a de repulsa
    for(int j=i+1; j<bolas.size(); j++){
      Bola bolaB = (Bola)bolas.get(j); 
      //dx e dy representam a diferen\u00e7a de posi\u00e7\u00e3o entre as 2 bolas
      float dx = bolaA.posicaoX - bolaB.posicaoX;
      float dy = bolaA.posicaoY - bolaB.posicaoY;
      float distancia = dist (bolaA.posicaoX, bolaA.posicaoY, bolaB.posicaoX, bolaB.posicaoY);
      //dividindo dx e dy pela distancia ficamos com um vector un\u00e1rio que aponta desde bolaA at\u00e9 bolaB
      dx /= distancia;
      dy /= distancia;
      //como este vector \u00e9 un\u00e1rio (tem tamanho 1) podemos ent\u00e3o aplicar a f\u00f3rmula de repulsa a cada um dos eixos
      float forcaX = dx * (5 / distancia);
      float forcaY = dy * (5 / distancia);
      //somamos a for\u00e7a \u00e0 bolaA
      bolaA.aceleracaoX += forcaX;
      bolaA.aceleracaoY += forcaY;
      //e subtraimos \u00e0 bolaB
      bolaB.aceleracaoX -= forcaX;
      bolaB.aceleracaoY -= forcaY;
      
      //se a dist\u00e2ncia for menor que 40, 
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

public void mouseReleased(){
  Bola bola = new Bola();
  bola.posicaoX = mouseX;
  bola.posicaoY = mouseY;
  bolas.add(bola);
}



class Bola{

  //vari\u00e1veis usadas para definir a posi\u00e7\u00e3o
  float posicaoX, posicaoY;

  //vari\u00e1veis onde guardamos a velocidade
  float velocidadeX, velocidadeY;

  //vari\u00e1veis onde guardamos os valores de acelera\u00e7ao
  float aceleracaoX;
  float aceleracaoY;

  //definimos um valor para o raio
  float raio;

  //resistencia
  float resistencia;

  int vida;

  float r;
  float g;
  float b;

  Bola(){
    //definimos um valor inicial para a posi\u00e7\u00e3o
    //no eixo do X
    posicaoX = random(0, width);
    //e no eixo do Y
    posicaoY = random(0, height);

    //definimos o valor de resistencia
    //este valor convem ser entre 0 e 1
    //em que zero = resistencia total (o objecto n\u00e3o mexe)
    //e 1 = nenhuma resistencia
    resistencia = 0.95f;

    //raio
    raio = 5;

    vida = 50;
    
    r = 0;
    g = 255;
    b = 255;
  }

  public void mover(){

   float centroX = width / 2;
   float centroY = height / 2;
   aceleracaoX += (centroX - posicaoX) / 20;
   aceleracaoY += (centroY - posicaoY) / 20;


    //somamos a acelera\u00e7\u00e3o \u00e0 velocidade
    velocidadeX += aceleracaoX;
    velocidadeY += aceleracaoY;

    velocidadeX *= resistencia;
    velocidadeY *= resistencia;

    //somamos a nossa velocidade \u00e0 posi\u00e7\u00e3o
    posicaoX += velocidadeX;
    posicaoY += velocidadeY;

    vida--;
    
    aceleracaoX = 0;
    aceleracaoY = 0;
  }

  public void colisao(){
    //testamos para ver se a nossa bola colide com os lados da janela
    //mas temos em conta o raio da bola
    //sempre que h\u00e1 uma colis\u00e3o, colocamos a bola no ponto de colis\u00e3o
    //e invertemos a velocidade nesse eixo
    if(posicaoX < raio){
      posicaoX = raio;
      velocidadeX *= -1; 
    }
    if(posicaoX > width - raio){
      posicaoX = width - raio;
      velocidadeX *= -1; 
    }
    if(posicaoY < raio){
      posicaoY = raio;
      velocidadeY *= -1; 
    }
    if(posicaoY > height - raio){
      posicaoY = height - raio;
      velocidadeY *= -1; 
    }
  }


  public void desenhar(){
    //desenhamos um circulo baseado na posi\u00e7\u00e3o e no raio
    fill(r, g, b, 50);
    ellipse(posicaoX, posicaoY, raio*2, raio*2);
  } 
}


  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AtraccaoRepulsa" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
