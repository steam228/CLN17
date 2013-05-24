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



  int MARGEN=50;
  int ALTURA=300;
  int LARGURA=1200;
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
  float largura_tam;
  float altura_tam;

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
	float getX() {return posicaoX;}
	float getY() {return posicaoY;}
	float getXX() {return posx;}
	float getYY() {return posy;}
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
    this.colisao();
}


void colisao(){
    //testamos para ver se a nossa bola colide com os lados da janela
    //mas temos em conta o raio da bola
    //sempre que há uma colisão, colocamos a bola no ponto de colisão
    //e invertemos a velocidade nesse eixo
    // if(posicaoX < raio){
    //   posicaoX = raio;
    //   velocidadeX *= -1; 
    // }
    if(posicaoX > ((width - largura_tam)-MARGEN))
    {
    	posicaoX = (width - largura_tam)-MARGEN;

    	velocidadeX *= -1; 
    	if (velocidadeX>0)
    	velocidadeX--;
    	else 
    	velocidadeX++;	
    }
    if(posicaoX <  (largura_tam+MARGEN))
    {
    	posicaoX =  largura_tam+MARGEN;
    	velocidadeX *= -1; 
    	if (velocidadeX>0)
    	velocidadeX--;
    	else 
    	velocidadeX++;	
    }
    // if(posicaoY < raio){
    //   posicaoY = raio;
    //   velocidadeY *= -1; 
    // }

    if(    posicaoY > (((height - altura_tam)-MARGEN) +20)  )
    {
    	posicaoY = (height - altura_tam)-MARGEN;
    	velocidadeY *= -1; 
    	if (velocidadeY>0)
    	velocidadeY--;
    	else 
    	velocidadeY++;	
    }
    if(posicaoY <  (altura_tam+MARGEN))
    {
    	posicaoY = altura_tam+MARGEN ;
    	velocidadeY *= -1; 
    	if (velocidadeY>0)
    	velocidadeY--;
    	else 
    	velocidadeY++;	
    }


    
}





void desenha(PShape aa ,PShape bb 	)
{
		//smooth();
		
		
		// fill(0, 0, 255);
		
		// text("#"+tag, posicaoX+tamLetra, posicaoY-tamLetra);
		
		// fill(255,0,0);
		
		largura_tam=((largura+(dim*0.7))/ESCALA);
		altura_tam=((altura+dim)/ESCALA);

		noStroke();
		//fill(cor);
		//stroke(color(255,0,10));
		fill(color(255,0,10));
		 //posicaoX
		// shape(fundo, posx-((largura+(dim*0.7))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
		 //shape(desenho, posx-((largura+(dim*0.7))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
		 //shape(bb, posicaoX-((largura+(dim*0.7))/ESCALA), posicaoY-((altura+dim)/ESCALA), largura_tam, altura_tam);
		 shape(aa, posicaoX-((largura+(dim*0.7))/ESCALA), posicaoY-((altura+dim)/ESCALA), largura_tam, altura_tam);
		}
	}