import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import org.json.*; 
import java.util.*; 
import de.bezier.data.*; 
import toxi.geom.*; 
import toxi.processing.*; 

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

int id_area;

linha traco;

ArrayList casas;
ArrayList tweets;
ArrayList instagrams;
ArrayList pessoas;

exclusoes areas;

int pausa=2000;
long lastTime = 0;

public void setup(){
  traco =new linha();
  areas= new exclusoes(this);
  id_area= areas.addPoligno();
  areas.addPonto(id_area,300,0);
  areas.addPonto(id_area,400,50);
  areas.addPonto(id_area,410,80);
  areas.addPonto(id_area,400,100);
  size(largura, altura);
  casas = new ArrayList();
  tweets= new ArrayList();
  instagrams= new ArrayList();
  pessoas= new ArrayList();
  background(0);
  carregaCasas();
}

public void draw(){
  smooth();
 // background(0);
  if ( millis() - lastTime > pausa ) 
  {
    procuraTweets();
    procuraInstas();
 lastTime = millis();
    mostraInsta();
    mostraTweet();
} 
animaMundo();
//areas.desenharTodos();
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
    instaSite = loadStrings("https://api.instagram.com/v1/tags/"+tag+"?client_id=9d6af7341f7a4fd39e888fd12ab8d8a0");
    jsonstring =instaSite[0];
    JSON contador = JSON.parse(jsonstring);
    contador = contador.getJSON("data");
    count=0;
    count=contador.getInt("media_count");//TOTAL DE FOTOS NESSA TAG
    println("NUM DE INSTAS _> "+count+" NUM GUARDADO _> "+aux.countInsta());
    if (count>aux.countInsta())
    {
      //carrega os novos
      instaSite = loadStrings("https://api.instagram.com/v1/tags/"+tag+"//media/recent?client_id=9d6af7341f7a4fd39e888fd12ab8d8a0&min_tag_id="+aux.getInsta()+"");
      jsonstring =instaSite[0];
      JSON data = JSON.parse(jsonstring);
      JSON ultimo = data.getJSON("pagination");
      println("ERRO-> "+ultimo);
      println("TAG -> "+tag+" INSTA -> "+aux.getInsta());
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
  casa aux,aux1;
  for (int i = 0; i <casas.size(); i++) 
  {
    aux= (casa) casas.get(i);
    aux.desenha();
    if ((i+1)<casas.size())
    {
      aux1= (casa) casas.get(i+1);
      traco.setTamanho(1);  
      traco.setInicio(aux.getX(),aux.getY());
      traco.inicia(aux1.getX(),aux1.getY());
      traco.desenha();
    }
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


public void adicionaAOuser(String _user , char tipo)
{
  user aux;
  for (int i = 0; i <pessoas.size(); i++) 
  {
    aux= (user) pessoas.get(i);
    if  (aux.getID()==_user)
    {
      if (tipo=='T')
      {
       aux.addTweet();
     }
     else if (tipo=='I')
     {
       aux.addInsta();
     }
      //desenha utilizador
      break; 
    }
  }
}
class Area 
{
	int id;
	Polygon2D poligno;
	ToxiclibsSupport grafico;
	
	Area (int _id , PApplet coiso) 
	{
		id =_id;
		poligno=new Polygon2D();
		grafico=new ToxiclibsSupport(coiso);
	}

	public void addPonto( int _x , int _y)
	{
		poligno.add(new Vec2D(_x,_y));
	}
	public void desenha()
	{
		grafico.polygon2D(poligno);
	}
	public boolean contem(int _posx , int _posy)
	{
		return poligno.containsPoint(new Vec2D(_posx,_posy));
	}
	public int getID()
	{
		return id;
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
 	PShape desenho;
 	int largura=60;
 	int altura=85;
 	casa (String nome , float xx, float yy) 
 	{
		tag=nome;
		posx=xx;
		posy=yy;
		tamLetra=15;
		dim=0.1f;
		numInsta=0;
		numTweets=0;
		ultimoInsta="";
		ultimoTweet="?q=%23"+nome;
		desenho = loadShape("Casacaldas.svg");
	}
	public void addTweet(){numTweets++;dim+=0.5f;}
	public void addInsta(){numInsta++;dim+=0.5f;}
	public int countTwetts() {return numTweets;}
	public int countInsta() {return numInsta;}
	public float getX() {return posx;}
	public float getY() {return posy;}
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
		// desenho.disableStyle();
		// noStroke();
		// fill(255, 0, 0);
		 shape(desenho, posx-(largura*dim), posy-(altura*dim), largura*dim, altura*dim);
		// rect (posx, posy, dim, dim);
		// line (posx, posy, posx+dim/2, posy-dim/2);
		// line (posx+dim/2, posy-dim/2, posx+dim, posy);
		// line (posx+dim, posy, posx, posy+dim);
		// line (posx, posy, posx+dim, posy+dim);
	}
}
class exclusoes 
{

	ArrayList zonas;
	PApplet grafismo;

	exclusoes(PApplet _gra) 
	{
		zonas = new ArrayList();
		grafismo=_gra;
	}

	public void desenharTodos() 
	{
		Area zonita ;
		for (int i = 0; i <zonas.size(); i++) 
		{
			zonita=(Area)zonas.get(i);
			zonita.desenha();
		}
	}


	public void desenhaID(int _id)
	{
		Area zonita ;
		zonita=(Area)zonas.get(_id);
		zonita.desenha();
	}

	public int  contemTodos(int _posx , int _posy) 
	{
		Area zonita ;
		for (int i = 0; i <zonas.size(); i++) 
		{
			zonita=(Area)zonas.get(i);
			
			if ( zonita.contem(_posx , _posy) )
			return zonita.getID();
		}
		return -1;
	}

	public boolean  contemID(int _id , int _posx , int _posy)
	{
		Area zonita ;
		zonita=(Area)zonas.get(_id);
		return zonita.contem(_posx ,_posy);
	}

	public void addPonto(int _id , int _posx , int _posy)
	{
		Area zona_aux ;
		zona_aux =(Area)zonas.get(_id);
		zona_aux.addPonto(_posx,_posy);
	}

	public int addPoligno()
	{	
		Area zona_aux ;
		int id=zonas.size();
		zona_aux = new Area(id,grafismo);
		zonas.add(zona_aux);	
		return id;
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
class linha 
{

float beginX = 20.0f;  // Initial x-coordinate
float beginY = 10.0f;  // Initial y-coordinate
float endX = 570.0f;   // Final x-coordinate
float endY = 320.0f;   // Final y-coordinate
float distX;          // X-axis distance to move
float distY;          // Y-axis distance to move
float exponent = 4;   // Determines the curve
float x = 0.0f;        // Current x-coordinate
float y = 0.0f;        // Current y-coordinate
float step = 0.001f;    // Size of each step along the path
float pct = 0.0f;      // Percentage traveled (0.0 to 1.0)
float tamanho=4;

linha() 
{
  noStroke();
  distX = endX - beginX;
  distY = endY - beginY;
}

public void setTamanho(float _tam)
{
  tamanho=_tam;
}
public void setInicio(float _posx, float _posy)
{
  x=_posx;
  y=_posy;
}

public void inicia(float _posx, float _posy)
{
  pct = 0.0f;
  beginX = x;
  beginY = y;
  endX = _posx;
  endY = _posy;
  distX = endX - beginX;
  distY = endY - beginY;
}

public void desenha() 
{
  while (pct < 1.0f)
  {
    noStroke();
    //fill(0, 2);
    //rect(0, 0, width, height);
    pct += step;
    x = beginX + (pct * distX);
    y = beginY + (pow(pct, exponent) * distY);
    fill(255);
    ellipse(x, y, tamanho, tamanho);
  }
}

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
 		fill(255,0,0);
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
 	String username;
 	ArrayList caminho;
 	int cor;
 	float grosura;
 	linha desenhador;
 	
 	user (int _id, String _user) 
 	{
 		id=_id;
 		username=_user;
 	}

 	public void addTweet()
 	{
 		numTweets++;
 		grosura++;
 	}
 	public void addInsta()
 	{
 		numInsta++;
 		grosura++;
 	}

 	public String getID()
 	{
 		return username;
 	}

 	public void desenha(ArrayList casitas)
 	{
 		int aux,aux1;

 		for (int i = 0; i <caminho.size(); i++) 
 		{

 			aux= (Integer) caminho.get(i);
 			//aux.desenha();
 			if ((i+1)<caminho.size())
 			{
 				aux1= (Integer) caminho.get(i+1);

casa casola;

 				traco.setTamanho(10);

 				casola=(casa)casitas.get(aux);

 				traco.setInicio(casola.getX(),casola.getY());
 				traco.inicia(casola.getX(),casola.getY());
 				traco.desenha();
 			}
 		}
 	}

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
