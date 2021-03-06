class Bola{

  //variáveis usadas para definir a posição
  float posicaoX, posicaoY;

  //variáveis onde guardamos a velocidade
  float velocidadeX, velocidadeY;

  //variáveis onde guardamos os valores de aceleraçao
  float aceleracaoX;
  float aceleracaoY;

  //definimos um valor para o raio
  float raio;

  //resistencia
  float resistencia;

  Bola(){
    //definimos um valor inicial para a posição
    //no eixo do X
    posicaoX = 30;
    //e no eixo do Y
    posicaoX = 50;

    //definimos o valor de resistencia
    //este valor convem ser entre 0 e 1
    //em que zero = resistencia total (o objecto não mexe)
    //e 1 = nenhuma resistencia
    resistencia = 0.95;

    //raio
    raio = 20;
  }

  void mover(){
    //somamos a aceleração à velocidade

    aceleracaoX = (mouseX - posicaoX) / 50;
    aceleracaoY = (mouseY - posicaoY) / 50;


    velocidadeX += aceleracaoX;
    velocidadeY += aceleracaoY;

    velocidadeX *= resistencia;
    velocidadeY *= resistencia;

    //somamos a nossa velocidade à posição
    posicaoX += velocidadeX;
    posicaoY += velocidadeY;
  }

  void colisao(){
    //testamos para ver se a nossa bola colide com os lados da janela
    //mas temos em conta o raio da bola
    //sempre que há uma colisão, colocamos a bola no ponto de colisão
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


  void desenhar(){
    //desenhamos um circulo baseado na posição e no raio
    ellipse(posicaoX, posicaoY, raio * 2, raio * 2);
  } 
}






