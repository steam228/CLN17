 class casa 
 {


 float posicaoX, posicaoY;
 float velocidadeX, velocidadeY;

  //variáveis onde guardamos os valores de aceleraçao
  float aceleracaoX;
  float aceleracaoY;

  //definimos um valor para o raio
  float raio;

  //resistencia
  float resistencia;





 	int ESCALA=2;
	int INCREMENTO=1;
 	int LIMITE=60;

 	float posx;
 	float posy;
 	float dim;
 	String tag;
 	int numTweets;
 	int numInsta;
 	float tamLetra;
 	color cor;
 	String ultimoInsta;
 	String ultimoTweet;
 	PShape desenho;
 	PShape fundo;
 	int largura=int(60*0.28333);
 	int altura=int(85*0.28333);

 	casa (String nome , float xx, float yy) 
 	{
		tag=nome;
		posx=xx;
		posy=yy;
 resistencia = 0.95;
		posicaoX=xx;
		posicaoY=yy;
		tamLetra=15;
		dim=1;
		numInsta=0;
		numTweets=0;
		ultimoInsta="0";
		ultimoTweet="?q=%23"+nome;
		// desenho = loadShape("Casacaldas.svg");
		// fundo = loadShape("CasaFundo.svg");
		cor = color(0,0,0);
	}

	void addTweet(){numTweets++;if (dim<LIMITE)dim+=INCREMENTO;}
	void addInsta(){numInsta++;if (dim<LIMITE)dim+=INCREMENTO;}
	int countTwetts() {return numTweets;}
	int countInsta() {return numInsta;}
	float getX() {return posx;}
	float getY() {return posy;}
	void setInsta(String ident) {ultimoInsta = ident;}
	void setTweet(String ident) {ultimoTweet = ident;}
	String getInsta() {return ultimoInsta;}
	String getTweet() {return ultimoTweet;}
	String getTag(){return tag;}
	

  void mover(){

   // float centroX = posx;
   float centroX = posx;
 // float centroY = posy;
  //   float centroX = width / 2;
    float centroY = random(posy, posy+1);
   aceleracaoX += (centroX - posicaoX) / 20;
   aceleracaoY += (centroY - posicaoY) / 20;


    //somamos a aceleração à velocidade
    velocidadeX += aceleracaoX;
    velocidadeY += aceleracaoY;

    velocidadeX *= resistencia;
    velocidadeY *= resistencia;

    //somamos a nossa velocidade à posição
    posicaoX += velocidadeX;
    posicaoY += velocidadeY;

    
    aceleracaoX = 0;
    aceleracaoY = 0;
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





	void desenha(PShape aa ,PShape bb)
	{
		//smooth();
		
		
		// fill(0, 0, 255);
		// PFont font;		
		// font = loadFont("AGaramondPro-Bold-48.vlw");
		// textFont(font, tamLetra);
		// text("#"+tag, posx+tamLetra, posy-tamLetra);
		
		fill(255,0,0);
		bb.disableStyle();
		 noStroke();
		 fill(cor);
		 //posicaoX
		// shape(fundo, posx-((largura+(dim*0.7))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
		 //shape(desenho, posx-((largura+(dim*0.7))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
		 shape(bb, posicaoX-((largura+(dim*0.7))/ESCALA), posicaoY-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
		 shape(aa, posicaoX-((largura+(dim*0.7))/ESCALA), posicaoY-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
	}
}