import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import org.json.*; 
import java.util.*; 
import de.bezier.data.*; 
import toxi.geom.*; 
import toxi.processing.*; 
import java.util.*; 
import toxi.geom.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class CLN_MAPA extends PApplet {


 





int largura=1200;
int altura=300;

int id_area;
boolean comeca  = false;
boolean areclu = false;
boolean finale = false;
boolean prinale = true;

ArrayList casas;
ArrayList tweets;
ArrayList instagrams;
ArrayList pessoas;

exclusoes areas;

int pausa=2000;
int espera=5000;
long lastTime = 0;
long ultimaVez = 0;

public void setup(){

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
  if (comeca)
  {
    smooth();
 // background(0);
 if ( millis() - lastTime > pausa ) 
 {
   procuraTweets();
   procuraInstas();
   lastTime = millis();
   // mostraInsta();
   // mostraTweet();
 } 
 // if ( millis() - ultimaVez > espera ) 
 // {
 //  ultimaVez=millis();
 desenhaCaminhos();
//}
animaMundo();


}
areas.desenharTodos();
}


public void carregaCasas()
{
	XlsReader reader;
  float[] posii = {0,0};
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
    posii =verificaCasa(xpos,ypos);
    casas.add(new casa(designa, posii[0], posii[1]));
  }
}



public float[] verificaCasa(float _posx , float _posy) { 
int idd;
 float[] arra ={0,0};
 areas.contemTodos(_posx , _posy);
 
 arra[0]=_posx;
 arra[1]=_posy;
  return arra;  // Returns an array of 3 ints: 20, 40, 60 
}




public void procuraTweets()
{

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
    adicionaAOuser(este.getString("from_user"),'T',aux.getTag());
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
  String username;
  casa aux;
  for (int i = 0; i <casas.size(); i++) 
  {
    aux= (casa) casas.get(i);
    tag =aux.getTag();
    instaSite = loadStrings("https://api.instagram.com/v1/tags/"+tag+"//media/recent?client_id=9d6af7341f7a4fd39e888fd12ab8d8a0&min_tag_id="+aux.getInsta()+"");
    jsonstring =instaSite[0];
    JSON data = JSON.parse(jsonstring);
    JSON ultimo = data.getJSON("pagination");
    data = data.getJSON("data");
    if (data.length()>0)
    aux.setInsta( ultimo.getString("min_tag_id"));
    int user_id;
    for (int f=0; f<data.length();f++)
    {
        JSON unico = data.getJSON(f);//NUMERO DA FOTO NO ARRAY
        JSON imagens = unico.getJSON("images");
        imagens = imagens.getJSON("thumbnail");//melhor tamanho 612x612 standard_resolution
        String fotoURL =(String) imagens.getString("url"); 
        JSON user= unico.getJSON("user");     
        user_id=user.getInt("id");//identificador do user
        username=user.getString("username");
        instagrams.add( new insta(instagrams.size()+1,user_id,fotoURL));//adiciona instagram a lista
        aux.addInsta();//aumenta o num de instagrams na casa
        adicionaAOuser(username,'I',tag); 
        //println("INSTA  USER-> "+user_id+" TAG -> "+tag);
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
  public void desenhaCaminhos()
  {
    user aux;
    for (int i = 0; i <pessoas.size(); i++) 
    {
      aux= (user) pessoas.get(i);
     //println("USER -> "+i+" INSTAS -> "+aux.countInsta()+" CAMI -> "+aux.getCaminhoSize()+" NAME-> "+aux.getID());
     aux.desenha(casas);
   }
 }

 public void adicionaAOuser(String _user , char tipo , String _tag)
 {
  user aux;

  for (int i = 0; i <pessoas.size(); i++) 
  {
    aux= (user) pessoas.get(i);

    if  (_user.equals(aux.getID())==true)
    {

      if (tipo=='T')
      {
       aux.addTweet();
       aux.addCaminho(retornaCasaID(_tag));
     }
     else if (tipo=='I')
     {

       aux.addInsta();
       aux.addCaminho(retornaCasaID(_tag));
     }
      //desenha utilizador de novo com novas espessuras
      return ; 
    }
  }
//create new user if needed

pessoas.add(new user(pessoas.size(),_user));
aux= (user) pessoas.get(pessoas.size()-1);
if (tipo=='T')
aux.addTweet();
else if (tipo=='I')
aux.addInsta();

aux.addCaminho(retornaCasaID(_tag));
}


public int retornaCasaID(String _tag)
{
 casa aux;
 for (int i = 0; i <casas.size(); i++) 
 {
  aux= (casa) casas.get(i);
  if (_tag.equals(aux.getTag())==true )
  {
    return i;

  }
}
return -1;
}


public void mousePressed(){
  if (!comeca)
  {
    if (prinale)
    {
    id_area= areas.addPoligno();
     areas.addPonto(id_area,mouseX,mouseY);
    prinale=false;
    }
    else
    areas.addPonto(id_area,mouseX,mouseY);
  }
}

public void keyPressed()
{
 if (key == 's' || key == 'S') {
   comeca=!comeca;
 }
 if (key == 'a' || key == 'A') {
   areclu=!areclu;
 }
 if (key == 'f' || key == 'F') {
  prinale=true;
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
		stroke(0,255,0);
		grafico.polygon2D(poligno);
	}
	public boolean contem(float _posx , float _posy)
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
 	int cor;
 	String ultimoInsta;
 	String ultimoTweet;
 	PShape desenho;
 	PShape fundo;
 	int largura=PApplet.parseInt(60*0.28333f);
 	int altura=PApplet.parseInt(85*0.28333f);

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

	public void addTweet(){numTweets++;if (dim<LIMITE)dim+=INCREMENTO;}
	public void addInsta(){numInsta++;if (dim<LIMITE)dim+=INCREMENTO;}
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
		PFont font;		
		font = loadFont("AGaramondPro-Bold-48.vlw");
		textFont(font, tamLetra);
		text("#"+tag, posx+tamLetra, posy-tamLetra);
		
		fill(255,0,0);
		fundo.disableStyle();
		 noStroke();
		 fill(cor);
		 shape(fundo, posx-((largura+(dim*0.7f))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7f))/ESCALA, (altura+dim)/ESCALA);
		 shape(desenho, posx-((largura+(dim*0.7f))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7f))/ESCALA, (altura+dim)/ESCALA);
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

	public int  contemTodos(float _posx , float _posy) 
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

int ESCALA = 2;
  float beginX;  // Initial x-coordinate
  float beginY;  // Initial y-coordinate
  //  float endX;   // Final x-coordinate
  //  float endY;   // Final y-coordinate
  float x = 0.0f;  // Current x-coordinate
  float y = 0.0f;  // Current y-coordinate

  float tamanho=2;

  boolean showLine=true;
  boolean showSpline=true;
  boolean showHandles=false;
  ArrayList points=new ArrayList();
  int numP;
  int cor;

  linha(float ox, float oy) 
  {
    stroke(255);
    numP=points.size();
    showLine=true;
    showSpline=true;
    showHandles=true;
    Vec2D currP=new Vec2D(x, y);
    beginX = ox;
    beginY = oy;
  }


  public void novapos(float posX, float posY) 
  {
    Vec2D currP=new Vec2D(posX, posY);
    
    points.add(currP);
  }


  public void desenhalinha() 
  {
    noFill();

        stroke (cor);
    //stroke(255);
    strokeWeight (tamanho/ESCALA); // alterar quando a path ficar invisivel e for percorrida
    numP=points.size();
    beginShape();
    for (int i=0; i<numP; i++) {
      Vec2D p=(Vec2D)points.get(i);
      vertex(p.x, p.y);
      //melhorar desenho da linha
    }
    endShape();

    Vec2D[] handles=new Vec2D[numP];
    for (int i=0; i<numP; i++) 
    {
      Vec2D p=(Vec2D)points.get(i);
      handles[i]=p;
      if (showHandles) 
      ellipse(p.x, p.y, 5, 5);
    }

  // need at least 4 vertices for a spline
//  if (numP>3 && showSpline) {
  float estica = random (0, 0.5f);
  //setTightness (estica)
  if (numP>3) {
    // pass the points into the Spline container class
    Spline2D spline=new Spline2D(handles);
    // sample the curve at a higher resolution
    // so that we get extra 8 points between each original pair of points
    java.util.List vertices=spline.computeVertices(8);
    // draw the smoothened curve
    beginShape();
    for (Iterator i=vertices.iterator(); i.hasNext(); ) {
      Vec2D v=(Vec2D)i.next();
      vertex(v.x, v.y);
    }
    endShape();
  }
}



public void setCor(int cor1) {

  cor = cor1;
}

public void setTamanho(float _tam)
{
  tamanho=_tam;
}
}
// class linha 
// {

// float beginX = 20.0;  // Initial x-coordinate
// float beginY = 10.0;  // Initial y-coordinate
// float endX = 570.0;   // Final x-coordinate
// float endY = 320.0;   // Final y-coordinate
// float distX;          // X-axis distance to move
// float distY;          // Y-axis distance to move
// float exponent = 4;   // Determines the curve
// float x = 0.0;        // Current x-coordinate
// float y = 0.0;        // Current y-coordinate
// float step = 0.001;    // Size of each step along the path
// float pct = 0.0;      // Percentage traveled (0.0 to 1.0)
// float tamanho=4;

// linha() 
// {
//   noStroke();
//   distX = endX - beginX;
//   distY = endY - beginY;
// }

// void setTamanho(float _tam)
// {
//   tamanho=_tam;
// }
// void setInicio(float _posx, float _posy)
// {
//   x=_posx;
//   y=_posy;
// }

// void inicia(float _posx, float _posy)
// {
//   pct = 0.0;
//   beginX = x;
//   beginY = y;
//   endX = _posx;
//   endY = _posy;
//   distX = endX - beginX;
//   distY = endY - beginY;
// }

// void desenha() 
// {
//   while (pct < 1.0)
//   {
//     noStroke();
//     //fill(0, 2);
//     //rect(0, 0, width, height);
//     pct += step;
//     x = beginX + (pct * distX);
//     y = beginY + (pow(pct, exponent) * distY);
//     fill(255);
//     ellipse(x, y, tamanho, tamanho);
//   }
// }

// }
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
 	linha traco;
 	int corcor;
 	int vai=0;
 	user (int _id, String _user) 
 	{
 		id=_id;
 		username=_user;
 		caminho= new ArrayList();
 		corcor=color(random(0,256),random(0,256),random(0,256));
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
 	public int countInsta(){ return numInsta; }
 	public int countTweets(){ return numTweets; }
 	public String getID()  {return username;}
 	public void addCaminho(int _casa) 
 	{
 		int tam=caminho.size();
 		if (tam>0)
 		{
 			int last=(Integer)caminho.get(tam-1);
 			if (last!=_casa)
 			{
 				caminho.add(_casa);
 				//println("SIMMM");
 			}
 			else  {
 				//println("NAOOO");
 			}
 		//println("LAST ->"+caminho.get(tam)+" ESTE-> "+_casa);
 		vai=0;
 	}
 	else  {
 		caminho.add(_casa);
 	}

 }
 public int getCaminhoSize() {return caminho.size();}
 public void desenha(ArrayList casitas)
 {
 		int numDaCasa;
 		casa casola;
 		casa cas_aux;
 		for (int i = 0; i <caminho.size(); i++) 
 		{

 			numDaCasa= (Integer) caminho.get(i);
 			casola=(casa)casitas.get(numDaCasa);
 			if (i==0)
 			{
 				traco = new linha(casola.getX(),casola.getY());
 				traco.setCor(corcor);
 			}
 			else
 			{
 				float p_xx=casola.getX();
 				float p_yy=casola.getY();
 				numDaCasa= (Integer) caminho.get(i-1);
 				cas_aux=(casa)casitas.get(numDaCasa);
 				float c_xx=cas_aux.getX();
 				float c_yy=cas_aux.getY();
 				for (int aa= 0; aa <= 3; aa++) 
 				{
 					float x = lerp(p_xx, c_xx, aa/3);
 					float y = lerp(p_yy, c_yy, aa/3);
 					//point(x, y);
 					traco.novapos (x,y);	
 				}
 			}
 		}
 		traco.setCor(color(255,0,0));
 		traco.desenhalinha();	
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
