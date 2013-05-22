import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import org.json.*; 
import java.util.*; 
import de.bezier.data.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class CLN_MAPA extends PApplet {


 


int largura=700;
int altura=300;

ArrayList casas;
ArrayList tweets;
ArrayList instagrams;
ArrayList pessoas;

int pausa=2000;
long lastTime = 0;

public void setup(){
	size(largura, altura);
	casas = new ArrayList();
	tweets= new ArrayList();
	instagrams= new ArrayList();
	pessoas= new ArrayList();
	background(0);
	carregaCasas();
}

public void draw(){
  //background(0);
  if ( millis() - lastTime > pausa ) 
  {
    procuraTweets();
    procuraInstas();
    lastTime = millis();
    mostraInsta();
    mostraTweet();
  } 
  animaMundo();
}


public void carregaCasas()
{
	XlsReader reader;
  reader = new XlsReader(this, "cln17.xls" ); 

  int numcasas = 11;
  int margin = 80;//calibrate margin
  
  float[] xvals = new float[numcasas];
  float[] yvals = new float [numcasas];
  java.lang.String[] nomes = new String[numcasas];

  for (int i = 0; i < numcasas; i++) 
  {
    xvals[i] = reader.getFloat( i, 1 );
    yvals[i] = reader.getFloat( i, 2 );
    nomes[i]  = reader.getString( i, 0 );   
  }

  float xmax = max(xvals);//ref to coordinates to screen conversion
  float ymax = max(yvals);
  float xmin = min(xvals);
  float ymin = min(yvals);

  for (int i = 0; i <numcasas; i++) 
  {
    String designa = nomes[i];

    float ypos = map (yvals[i], ymin, ymax, margin, height-margin);
    float xpos = map (xvals[i], xmin, xmax, margin, width-margin);
    
    casas.add(new casa(designa,xpos,ypos));
  }
}
public void procuraTweets()
{
//http://search.twitter.com/search.json?since_id=334616285973463040&q=%23cln13
//http://search.twitter.com/search.json?q=%23cln13
casa aux;
String twitterSite[];
String jsonstring ;
String ultimoURL;
for (int i = 0; i <casas.size(); i++) 
{
 aux= (casa) casas.get(i);
 twitterSite = loadStrings("http://search.twitter.com/search.json"+aux.getTweet());
 jsonstring =twitterSite[0];
 JSON twiits = JSON.parse(jsonstring);
 ultimoURL=twiits.getString("refresh_url");
 twiits =twiits.getJSON("results");

 if (twiits.length()>0)
 {
  for (int t = 0; t<twiits.length(); t++)
  {
    JSON  este =twiits.getJSON(t);//numero do tweet nesta query
    tweets.add( new tweet(tweets.size()+1,este.getInt("from_user_id"),este.getString("text")));//adiciona instagram a lista
    aux.addTweet();
  }
  aux.setTweet(ultimoURL);
}
}
}

public void procuraInstas()
{

  String instaSite[];
  String tag ;
  int count;
  String jsonstring ;
  casa aux;
  for (int i = 0; i <casas.size(); i++) 
  {
    aux= (casa) casas.get(i);
    tag =aux.getTag();
    int totalCasa=aux.countInsta();
    instaSite = loadStrings("https://api.instagram.com/v1/tags/"+tag+"?client_id=d89bc143c63a4dbe85785bc181c146e4");
    jsonstring =instaSite[0];
    JSON contador = JSON.parse(jsonstring);
    contador = contador.getJSON("data");
    count=0;
    count=contador.getInt("media_count");//TOTAL DE FOTOS NESSA TAG
    println("NUM DE INSTAS _> "+count+" NUM GUARDADO _> "+aux.countInsta());
    if (count>aux.countInsta())
    {
      //carrega os novos
      instaSite = loadStrings("https://api.instagram.com/v1/tags/"+tag+"//media/recent?client_id=d89bc143c63a4dbe85785bc181c146e4&min_tag_id="+aux.getInsta()+"");
      jsonstring =instaSite[0];
      JSON data = JSON.parse(jsonstring);
      JSON ultimo = data.getJSON("pagination");
      aux.setInsta( ultimo.getString("min_tag_id"));
      data = data.getJSON("data");
      //println("NUM fotos -> "+data.length());
      int user_id;
      for (int f=0; f<data.length();f++)
      {
        JSON unico = data.getJSON(f);//NUMERO DA FOTO NO ARRAY
        JSON imagens = unico.getJSON("images");
        imagens = imagens.getJSON("thumbnail");//melhor tamanho 612x612 standard_resolution
        String fotoURL =(String) imagens.getString("url"); 
        JSON user= unico.getJSON("user");     
        user_id=user.getInt("id");//identificador do user
        instagrams.add( new insta(instagrams.size()+1,user_id,fotoURL));//adiciona instagram a lista
        aux.addInsta();//aumenta o num de instagrams na casa
        println("RECEBI NOVO-> "+aux.getTag());
      }
    }
  }


}
public void animaMundo()
{
  casa aux;
  for (int i = 0; i <casas.size(); i++) 
  {
    aux= (casa) casas.get(i);
    aux.desenha();
  }
}

public void mostraInsta()
{
  insta aux;
  for (int i = 0; i <instagrams.size(); i++) 
  {
    aux= (insta) instagrams.get(i);
    aux.mostra(0,0);

  }
}

public void mostraTweet()
{
  tweet aux;
  for (int i = 0; i <tweets.size(); i++) 
  {
    aux= (tweet) tweets.get(i);
    aux.mostra(0,250,20);

  }
}
 class casa 
 {
 	float posx;
 	float posy;
 	float dim;
 	String tag;
 	int numTweets;
 	int numInsta;
 	float tamLetra;
 	int cor;
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
	public void addTweet(){numTweets++;dim+=5;}
	public void addInsta(){numInsta++;dim+=5;}
	public int countTwetts() {return numTweets;}
	public int countInsta() {return numInsta;}
	public void setInsta(String ident) {ultimoInsta = ident;}
	public void setTweet(String ident) {ultimoTweet = ident;}
	public String getInsta() {return ultimoInsta;}
	public String getTweet() {return ultimoTweet;}
	public String getTag(){return tag;}
	public void desenha()
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
 class insta 
{
	
	int id;
	int userID;
	String url;

	
	 insta (int _id,int _user,String _url) 
	 {
	 	id=_id;
	 	userID=_user;
	 	url=_url;
	 }

	 public void mostra(int posx , int posy)
	 {
	 	PImage foto;
	 	foto = loadImage(url);
	 	image(foto, posx, posy);
	 }
	 public void mostra(int posx , int posy, int largura , int altura)
	 {
	 	PImage foto;
	 	foto = loadImage(url);
	 	image(foto, posx, posy, largura	, altura);
	 }

	 public int getUser(){return userID;}
	 public int getId(){return id;}
}
 class tweet 
{
	
	int id;
	int userID;
	String texto;

	
	 tweet (int _id,int _user,String _texto) 
	 {
	 	id=_id;
	 	userID=_user;
	 	texto=_texto;
	 }

	 public void mostra(int posx , int posy, int letra )
	 {
	 	textSize(letra);
		text(texto, posx, posy);
	 }

	 public int getUser(){return userID;}
	 public int getId(){return id;}
}
 class user 
 {

 	int id;
 	int numTweets;
 	int numInsta;
 	ArrayList caminho;
 	int cor;
 	float grosura;

 	user (int _id) 
 	{
		id=_id;
	}

	public void addTweet(){numTweets++;}
	public void addInsta(){numInsta++;}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "CLN_MAPA" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
