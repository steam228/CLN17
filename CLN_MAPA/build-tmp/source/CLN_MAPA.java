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


 




int vou=0;

PShape desenho;
PShape fundo;

int largura=1200;
int altura=300;
float xxx=0;
float  yyy=0;
float xxx1=0;
float  yyy1=0;
int id_area;
boolean comeca  = false;
boolean arryexclu = false;
boolean finale = false;
boolean prinale = true;
boolean hideee = true;

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
  desenho = loadShape("Casacaldas.svg");
  fundo= loadShape("CasaFundo.svg");
  desenho.disableStyle();
//fundo.disableStyle();
PFont font;   
font = loadFont("AGaramondPro-Bold-48.vlw");
textFont(font, 15);
areas= new exclusoes(this);
id_area= areas.addPoligno();
areas.addPonto(id_area,300,0);
areas.addPonto(id_area,400,50);
areas.addPonto(id_area,410,80);
areas.addPonto(id_area,400,100);
  //size(largura, altura,P3D);
  size(largura, altura);
  casas = new ArrayList();
  tweets= new ArrayList();
  instagrams= new ArrayList();
  pessoas= new ArrayList();
  background(0);
  carregaCasas();

}

public void draw(){
//   if (hideee)
// {areas.desenharTodos(); }
// else  {
  background(0);
//}
 //smooth();

//  if (arryexclu)
//  {
//   arryexclu=false;
//   carregaCasas();
// }


// if (comeca)
// {
 desenhaCaminhos();



 if ( millis() - lastTime > pausa ) 
 {
    procuraTweets();
    procuraInstas();
   lastTime = millis();
   // mostraInsta();
   // mostraTweet();
 } 


//}
moveMundo();
//  if ( millis() - ultimaVez > espera ) 
//  {
//   ultimaVez=millis();

// }
//animaMundo();





}


public void carregaCasas()
{
	XlsReader reader;
  float[] posii = {0,0};
  reader = new XlsReader(this, "cln17.xls" ); 

  int numcasas = 125;
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

    // float ypos = map (yvals[i], ymin, ymax, margin/2, height-(margin/2));
    // float xpos = map (xvals[i], xmin, xmax, margin, width-(margin/4));
     float ypos = map (yvals[i], ymin, ymax, 0, height);
    float xpos = map (xvals[i], xmin, xmax, 0, width);
    posii =verificaCasa(xpos,ypos);
    casas.add(new casa(designa, posii[0], posii[1]));
  }
}



public float[] verificaCasa(float _posx , float _posy) 
{ 
//   int idd;
float[] arra ={0,0};
//   if (areas.contemAlgum(_posx , _posy))
//   {
//    println("COLISAO");
//    xxx1=_posx;
//    yyy1=_posy;

//    int cima=0;
//    int dir=0;
//    int esq=0;
//    float aux_x=_posx;
//    float aux_y = _posy;
//    while (areas.contemAlgum(aux_x , aux_y))
//    {
//     aux_y--;
//     cima++;
//     // fill(255, 0, 0);
//     // ellipse(_posx, aux_y, 50, 50);
//   }
//    aux_x=_posx;
//     aux_y = _posy;
//    while (areas.contemAlgum(aux_x , aux_y))
//   {
//     aux_x--;
//     esq++;
//     //   fill(0, 255, 0);
//     // ellipse(aux_x, _posy, 5, 5);
//   }
//   aux_x=_posx;
//     aux_y = _posy;
//   while (areas.contemAlgum(aux_x , aux_y))
//   {
//     aux_x++;
//     dir++;
//     //   fill(0, 0, 255);
//     // ellipse(aux_x, _posy, 5, 5);
//   }
//   arra[0]=_posx;
//   arra[1]=_posy;
//   if ((cima<=dir) && (cima<=esq))
//   {
//     arra[1]-=cima;
//     println("VOU PARA CIMA -> "+cima);
//   }
//   else if ((esq<=cima) &&(esq<=dir)) 
//   {
//     arra[0]-=esq;
//     println("VOU PARA ESQUERDA -> "+esq);
//   }
//   else if ((dir<=cima)&&(dir<=esq)) 
//   {
//     arra[0]+=dir;
//     println("VOU PARA DIREITA -> "+dir);
//   }
//   println("XO-> "+_posx+" YO-> "+_posy+" XF-> "+ arra[0]+" YF-> "+ arra[1]);
//   return arra;  
// } 
// else  
// {

 arra[0]=_posx;
 arra[1]=_posy; 
// }
// xxx=arra[0];
// yyy=arra[1];

return arra;  
}




public void procuraTweets()
{

  casa aux;
  String twitterSite[];
  String jsonstring ;
  String ultimoURL;
  // for (int i = 0; i <casas.size(); i++) 
  // {
   aux= (casa) casas.get(vou);
   twitterSite = loadStrings("http://search.twitter.com/search.json"+aux.getTweet());
   if (twitterSite!=null)
{
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
// }
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
  //println("CASAS-> "+casas.size());
  // for (int i = 0; i <casas.size(); i++) 
  // {
    aux= (casa) casas.get(vou);
    tag =aux.getTag();
    instaSite = loadStrings("https://api.instagram.com/v1/tags/"+tag+"//media/recent?client_id=9d6af7341f7a4fd39e888fd12ab8d8a0&min_tag_id="+aux.getInsta()+"");
   if (instaSite!=null)
{
   jsonstring =instaSite[0];
    JSON data = JSON.parse(jsonstring);
    JSON ultimo = data.getJSON("pagination");
    data = data.getJSON("data");
    if (data.length()>0)
    aux.setInsta( ultimo.getString("min_tag_id"));
    else  {
     // println("TAG-> "+tag);
    }
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
        println("INSTA  USER-> "+user_id+" TAG -> "+tag);
      }
    // }
  }
    vou++;
    if (vou>125)
    vou=0;
  }
  public void animaMundo()
  {
    casa aux;
    for (int i = 0; i <casas.size(); i++) 
    {
      aux= (casa) casas.get(i);
      aux.desenha(desenho , fundo);
    }
  }

  public void moveMundo()
  {
   stroke(255, 255, 255, 100);
   for(int i=0; i<casas.size(); i++){
    casa bolaA = (casa)casas.get(i);
    bolaA.mover();
    
    //dentro deste loop, temos outro loop
    //onde cada bola vai interagir com todas as outras bolas
    //aplicando uma for\u00e7a de repulsa
    for(int j=i+1; j<casas.size(); j++){
      casa bolaB = (casa)casas.get(j); 
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
      // if(distancia < 440){
      //   line(bolaA.posicaoX, bolaA.posicaoY, bolaB.posicaoX, bolaB.posicaoY);
      // }

      bolaA.desenha(desenho,fundo);
    }
    // ACIONA A OP\u00c7AO DO RATO
    // float dx = bolaA.posicaoX - mouseX;
    // float dy = bolaA.posicaoY - mouseY;
    // float distancia = dist(bolaA.posicaoX, bolaA.posicaoY, mouseX, mouseY);
    // dx /= distancia;
    // dy /= distancia;
    // float forcaX = dx * (200 / distancia);
    // float forcaY = dy * (200 / distancia);
    // bolaA.aceleracaoX += forcaX;
    // bolaA.aceleracaoY += forcaY;

  }


}
public void fazMagia(){
  
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
 if (key == 's' || key == 'S') {//COMECA O PROGRAMA NORMAL
   comeca=!comeca;
 }
 if (key == 'a' || key == 'A') {// CARREGAS AS CASAS COM AS AREAS DE EXCLUSAO 1 COISA A SER FEITA
   arryexclu=!arryexclu;
 }
 if (key == 'f' || key == 'F') {//TERMINA O DESENHO DE UMA ARE DE EXCUSAO
  prinale=true;
}
  if (key == 'h' || key == 'H') {//TOOGLE DE VISAO DAS AREAS DE EXCLUSAO
    hideee=!hideee;
  }


}

// void mouseMoved()
// {
//   println("XX-> " +mouseX+" YY-> "+mouseY);
// }
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


 	float posicaoX, posicaoY;
 	float velocidadeX, velocidadeY;

  //vari\u00e1veis onde guardamos os valores de acelera\u00e7ao
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
  int cor;
  String ultimoInsta;
  String ultimoTweet;
  PShape desenho;
  PShape fundo;
  int largura=PApplet.parseInt(60*0.28333f);
  int altura=PApplet.parseInt(85*0.28333f);
  float largura_tam;
  float altura_tam;

  casa (String nome , float xx, float yy) 
  {
  	tag=nome;
  	posx=xx;
  	posy=yy;
  	resistencia = 0.95f;
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

	public void addTweet(){numTweets++;if (dim<LIMITE)dim+=INCREMENTO;}
	public void addInsta(){numInsta++;if (dim<LIMITE)dim+=INCREMENTO;}
	public int countTwetts() {return numTweets;}
	public int countInsta() {return numInsta;}
	public float getX() {return posicaoX;}
	public float getY() {return posicaoY;}
	public float getXX() {return posx;}
	public float getYY() {return posy;}
	public void setInsta(String ident) {ultimoInsta = ident;}
	public void setTweet(String ident) {ultimoTweet = ident;}
	public String getInsta() {return ultimoInsta;}
	public String getTweet() {return ultimoTweet;}
	public String getTag(){return tag;}
	

	public void mover(){

   // float centroX = posx;
   float centroX = posx;
 // float centroY = posy;
  //   float centroX = width / 2;
  float centroY = random(posy, posy+1);
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

    
    aceleracaoX = 0;
    aceleracaoY = 0;
    this.colisao();
}


public void colisao(){
    //testamos para ver se a nossa bola colide com os lados da janela
    //mas temos em conta o raio da bola
    //sempre que h\u00e1 uma colis\u00e3o, colocamos a bola no ponto de colis\u00e3o
    //e invertemos a velocidade nesse eixo
    // if(posicaoX < raio){
    //   posicaoX = raio;
    //   velocidadeX *= -1; 
    // }
    if(posicaoX > ((width )-MARGEN))
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

      if (posx>=(width -MARGEN))
      posx--;

    }
    // if(posicaoY < raio){
    //   posicaoY = raio;
    //   velocidadeY *= -1; 
    // }

    if(    posicaoY > (((height )-MARGEN) )  )
    {
    	posicaoY = (height - altura_tam)-MARGEN;
    	velocidadeY *= -1; 
    	if (velocidadeY>0)
    	velocidadeY--;
    	else 
    	velocidadeY++;	

if (posy>=(height -(MARGEN)))
posy--;

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





public void desenha(PShape aa ,PShape bb 	)
{
		//smooth();
		
		
		// fill(0, 0, 255);
		
		// text("#"+tag, posicaoX+tamLetra, posicaoY-tamLetra);
		
		// fill(255,0,0);
		
		largura_tam=((largura+(dim*0.7f))/ESCALA);
		altura_tam=((altura+dim)/ESCALA);

		noStroke();
		//fill(cor);
		//stroke(color(255,0,10));
		fill(color(255,0,10));
		 //posicaoX
		// shape(fundo, posx-((largura+(dim*0.7))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
		 //shape(desenho, posx-((largura+(dim*0.7))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
		 //shape(bb, posicaoX-((largura+(dim*0.7))/ESCALA), posicaoY-((altura+dim)/ESCALA), largura_tam, altura_tam);
		 shape(aa, posicaoX-((largura+(dim*0.7f))/ESCALA), posicaoY-((altura+dim)/ESCALA), largura_tam, altura_tam);
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

	public boolean   contemAlgum(float _posx , float _posy) 
	{
		Area zonita ;
		for (int i = 0; i <zonas.size(); i++) 
		{
			zonita=(Area)zonas.get(i);
			
			if ( zonita.contem(_posx , _posy) )
			return true;
		}
		return false;
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

 		if ((i+1)<caminho.size())
 		{
 		numDaCasa= (Integer) caminho.get(i);
 		casola=(casa)casitas.get(numDaCasa);
 		// if (i==0)
 		// {
 			
 			
 		// }
 		// else
 		// {
 			float p_xx=0;
 			float p_yy=0;
 			numDaCasa= (Integer) caminho.get(i+1);
 			cas_aux=(casa)casitas.get(numDaCasa);
 			float c_xx=cas_aux.getX();
 			float c_yy=cas_aux.getY();
 			for (int aa= 0; aa <= 3; aa++) 
 			{

 				if (aa==0)
 				{
 				traco = new linha(casola.getX(),casola.getY());
 					 p_xx=casola.getX();
 					 p_yy=casola.getY();
 				}
 				

 				float x = lerp(p_xx, c_xx, aa/3.0f);
 				float y = lerp(p_yy, c_yy, aa/3.0f);
 					//point(x, y);
 					fill(0);
 					ellipse(x, y, 5, 5);
 					traco.novapos (x,y);	
 				}
 			//}
 			traco.setCor(corcor);
 			traco.desenhalinha();	
 		}
 		//traco.setCor(color(255,0,0));
		
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
