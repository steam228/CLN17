 class casa 
 {
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
 	casa (String nome , float xx, float yy) 
 	{
		tag=nome;
		posx=xx;
		posy=yy;
		tamLetra=15;
		dim=20;
		numInsta=0;
		numTweets=0;
		ultimoTweet="?q=%23"+nome;
	}
	void addTweet(){numTweets++;dim+=5;}
	void addInsta(){numInsta++;dim+=5;}
	int countTwetts() {return numTweets;}
	int countInsta() {return numInsta;}
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
		noFill();
		
		PFont font;
		
		font = loadFont("AGaramondPro-Bold-48.vlw");
		textFont(font, tamLetra);

		//textSize(tamLetra);
		text("#"+tag, posx+tamLetra, posy-tamLetra);
		rect (posx, posy, dim, dim);
		line (posx, posy, posx+dim/2, posy-dim/2);
		line (posx+dim/2, posy-dim/2, posx+dim, posy);
		line (posx+dim, posy, posx, posy+dim);
		line (posx, posy, posx+dim, posy+dim);
	}
}