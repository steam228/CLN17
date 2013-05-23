 class casa 
 {

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
		tamLetra=15;
		dim=1;
		numInsta=0;
		numTweets=0;
		ultimoInsta="0";
		ultimoTweet="?q=%23"+nome;
		desenho = loadShape("Casacaldas.svg");
		fundo = loadShape("CasaFundo.svg");
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
	
	void desenha()
	{
		smooth();
		
		stroke (204, 20, 0);
		strokeWeight(1);
		PFont font;		
		font = loadFont("AGaramondPro-Bold-48.vlw");
		textFont(font, tamLetra);
		text("#"+tag, posx+tamLetra, posy-tamLetra);
		
		fill(255,0,0);
		fundo.disableStyle();
		 noStroke();
		 fill(cor);
		 shape(fundo, posx-((largura+(dim*0.7))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
		 shape(desenho, posx-((largura+(dim*0.7))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
	}
}