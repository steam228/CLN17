import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import org.json.*; 
import java.util.*; 
import de.bezier.data.*; 
import codeanticode.syphon.*; 
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


 



int estou_x=0;
int estou_y=0;
int estouu=0;
PGraphics layerTOPO;
PImage portaIMG;
int vouiiii=0;
int vouaaaa=0;
PImage[] portass = new PImage[6];
boolean[] jaja = new boolean[6];
//jaja=false;
 //jaja[0]=false;
// jaja[1]=false;
// jaja[2]=false;
//jaja[3]=false;
// jaja[4]=false;
// jaja[5]=false;
// jaja[6]=false;

  PImage mascara;
mostraCoisas mostraINSTA;
mostraCoisas mostraTWII;


SimpleThread threadINSTA;
SimpleThread threadTWII;
char cococo;
  int MARGEN_BAIXO=50;
  int LARGURA_PORTA=100;
int[][] porta1_x = new int[6][4];
int[][] porta1_y = new int[6][4];

PGraphics canvas;
SyphonServer server;


int vou=0;

PShape desenho1;
PShape desenho2;
PShape desenho3;
PShape desenho4;
PShape fundo;

int largura=800;
int altura=600;
float xxx=0;
float  yyy=0;
float xxx1=0;
float  yyy1=0;
int id_area;

int NUM_CASAS=124;
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
  frameRate(24);
 jaja[0]=false;
 jaja[1]=false;
 jaja[2]=false;
jaja[3]=false;
 jaja[4]=false;
 jaja[5]=false;

  portaIMG = loadImage("img.png");
  mascara = loadImage("1.gif");
  mascara.resize(50,100);
  // desenho1 = loadShape("peca_01.svg");
  // desenho2 = loadShape("peca_02.svg");
  // desenho3 = loadShape("peca_03.svg");
  // desenho4 = loadShape("peca_04.svg");
  // fundo= loadShape("CasaFundo.svg");
  // desenho1.disableStyle();
  // desenho2.disableStyle();
  // desenho3.disableStyle();
  // desenho4.disableStyle();
//fundo.disableStyle();
PFont font;   
font = loadFont("AGaramondPro-Bold-48.vlw");
textFont(font, 15);
areas= new exclusoes(this);
id_area= areas.addPoligno();


areas.addPonto(id_area,400,0);
areas.addPonto(id_area,400,300);
areas.addPonto(id_area,800,300);
areas.addPonto(id_area,800,0);




porta1_x[0][0]=100; porta1_y[0][0]=200;
porta1_x[0][1]=100; porta1_y[0][1]=300;
porta1_x[0][2]=150; porta1_y[0][2]=300;
porta1_x[0][3]=150; porta1_y[0][3]=200;

id_area= areas.addPoligno();
areas.addPonto(id_area,100,200);
areas.addPonto(id_area,100,300);
areas.addPonto(id_area,150,300);
areas.addPonto(id_area,150,200);


porta1_x[1][0]=250; porta1_y[1][0]=200;
porta1_x[1][1]=200; porta1_y[1][1]=300;
porta1_x[1][2]=250; porta1_y[1][2]=300;
porta1_x[1][3]=250; porta1_y[1][3]=200;
id_area= areas.addPoligno();
areas.addPonto(id_area,200,200);
areas.addPonto(id_area,200,300);
areas.addPonto(id_area,250,300);
areas.addPonto(id_area,250,200);

porta1_x[2][0]=450; porta1_y[2][0]=200;
porta1_x[2][1]=300; porta1_y[2][1]=300;
porta1_x[2][2]=350; porta1_y[2][2]=300;
porta1_x[2][3]=350; porta1_y[2][3]=200;
id_area= areas.addPoligno();
areas.addPonto(id_area,300,200);
areas.addPonto(id_area,300,300);
areas.addPonto(id_area,350,300);
areas.addPonto(id_area,350,200);




porta1_x[3][0]=650; porta1_y[3][0]=200;
porta1_x[3][1]=850; porta1_y[3][1]=300;
porta1_x[3][2]=900; porta1_y[3][2]=300;
porta1_x[3][3]=900; porta1_y[3][3]=200;
id_area= areas.addPoligno();
areas.addPonto(id_area,850,200);
areas.addPonto(id_area,850,300);
areas.addPonto(id_area,900,300);
areas.addPonto(id_area,900,200);



porta1_x[4][0]=850; porta1_y[4][0]=200;
porta1_x[4][1]=950; porta1_y[4][1]=300;
porta1_x[4][2]=1000; porta1_y[4][2]=300;
porta1_x[4][3]=1000; porta1_y[4][3]=200;
id_area= areas.addPoligno();
areas.addPonto(id_area,950,200);
areas.addPonto(id_area,950,300);
areas.addPonto(id_area,1000,300);
areas.addPonto(id_area,1000,200);

porta1_x[5][0]=1050; porta1_y[5][0]=200;
porta1_x[5][1]=1050; porta1_y[5][1]=300;
porta1_x[5][2]=1100; porta1_y[5][2]=300;
porta1_x[5][3]=1100; porta1_y[5][3]=200;
id_area= areas.addPoligno();
areas.addPonto(id_area,1050,200);
areas.addPonto(id_area,1050,300);
areas.addPonto(id_area,1100,300);
areas.addPonto(id_area,1100,200);
  //size(largura, altura,P3D);
  size(largura, altura,P3D);
  layerTOPO = createGraphics(largura, altura);

   canvas = createGraphics(largura, altura, P3D);
  server = new SyphonServer(this, "Processing Syphon");

  casas = new ArrayList();
  tweets= new ArrayList();
  instagrams= new ArrayList();
  pessoas= new ArrayList();
  background(0);
  carregaCasas();
  cococo='T';
threadINSTA = new SimpleThread(cococo);
mostraINSTA = new mostraCoisas(cococo);
 threadINSTA.start();
 //mostraINSTA.start();
   cococo='I';
threadTWII = new SimpleThread(cococo);
mostraTWII = new mostraCoisas(cococo);
 threadTWII.start();
// mostraTWII.start();
}

public void draw(){
   canvas.beginDraw();
   canvas.background(255);
//   if (hideee)
// {areas.desenharTodos(); }
// else  {
 
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
    //procuraTweets();
  // procuraInstas();
   lastTime = millis();
   // mostraInsta();
//mostraInsta();
   // mostraTweet();
 } 


//}
moveMundo();
//  if ( millis() - ultimaVez > espera ) 
//  {
//   ultimaVez=millis();

// }
//animaMundo();
//layerTOPO.background(255,0,0);
//layerTOPO.beginDraw();
    //layerTOPO.background(255,0,0);
  //    layerTOPO.endDraw();

//canvas.image(layerTOPO , 0, 0); 


//image(portaIMG,0,0);
// for (int aae = 0; aae<6; aae++){
//   if (jaja[aae]==true)
// {
// canvas.image(portass[aae], porta1_x[aae][0],porta1_y[aae][0]);
// //println("aaaaaaaaa");
// //image(portass[0], porta1_x[0][0],porta1_y[0][0]);
// }
//}


// canvas.fill(0,255,0);
// canvas.ellipse(estou_x, estou_y, 10, 10);

//  canvas.text(estou_x +" <-> "+ estou_y,100,100);
 canvas.endDraw();
  image(canvas, 0, 0);
  server.sendImage(canvas);

}


public void carregaCasas()
{
	XlsReader reader;
  float[] posii = {0,0};
  reader = new XlsReader(this, "cln17.xls" ); 

  int numcasas = NUM_CASAS;
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
     float ypos = map (yvals[i], ymin, ymax, 50, height-50);
    float xpos = map (xvals[i], xmin, xmax, 50, width-50);
//---------------------------------
// float nova_x;

// int MEIO=600;
// int LARGUURA=800;
// int MEIALARGURA=100;

// if (xpos>MEIO)
// {
// nova_x = map (xpos, MEIO,LARGUURA,MEIO + MEIALARGURA,LARGUURA );
// }
// else 
// {
//  nova_x = map (xpos, 0,MEIO,0,MEIO - MEIALARGURA ); 
// }


// xpos=nova_x;

//-------------------


// porta1_x[0]=100; porta1_y[0]=200;
// porta1_x[1]=100; porta1_y[1]=300;
// porta1_x[2]=150; porta1_y[2]=300;
// porta1_x[3]=150; porta1_y[3]=200;
//int lala=0;
// for (int lala=0; lala<4 ; lala++)
// {
// if ( (xpos>porta1_x[lala][0]) &&  (xpos<porta1_x[lala][3]) && (ypos>porta1_y[lala][0]) )
// {
//   float cima_dis=dist(xpos, ypos, xpos, porta1_y[lala][0]);//DISTANCIA PARA CIMA
//   float esq_dis=dist(xpos, ypos, porta1_x[lala][0], ypos);//DISTANCIA PARA ESQ
//   float dir_dis=dist(xpos, ypos, porta1_x[lala][0], ypos);//DISTANCIA PARA DIR
//   if ( (dir_dis<esq_dis) && (dir_dis<cima_dis) )
//   {
//     xpos-=dir_dis;
//   }
//   else if (  (cima_dis<esq_dis)&&(cima_dis<dir_dis)      ) {
//     ypos-=cima_dis;
//   }
//   else if ( (esq_dis<=cima_dis)&&(esq_dis<=dir_dis)   ) {
//      xpos+=dir_dis;
//   }
// }
// }
//---------------------------------
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

      bolaA.desenha();
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

public void desenhaCaminhos()
{
  println("NUMERO DE UTILIZADORES -> "+pessoas.size());
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


  if (key == 'f' || key == 'F') {//TERMINA O DESENHO DE UMA ARE DE EXCUSAO
    estou_y--;
    canvas.fill(255,0,0);
  canvas.text(estou_x +" <-> "+ estou_y,100,100);
}
  if (key == 'g' || key == 'G') {//TOOGLE DE VISAO DAS AREAS DE EXCLUSAO
    estou_y++;
    canvas.fill(255,0,0);
    canvas.text(estou_x +" <-> "+ estou_y,100,100);
  }


  if (key == 'v' || key == 'V') {//TERMINA O DESENHO DE UMA ARE DE EXCUSAO
    estou_x--;
    canvas.fill(255,0,0);
  canvas.text(estou_x +" <-> "+ estou_y,100,100);
}
  if (key == 'b' || key == 'B') {//TOOGLE DE VISAO DAS AREAS DE EXCLUSAO
    estou_x++;
    canvas.fill(255,0,0);
   
  }


}


public void mostraTWEE()
{

if (tweets.size()>0)
{

  tweet aux_T;

    aux_T= (tweet) tweets.get(vouaaaa);
    int pos =PApplet.parseInt(random(0,6));
//portass[pos]=aux_I.dameca();
aux_T.pinta();
jaja[pos]=true;

vouaaaa++;
if (vouiiii>=tweets.size())
vouaaaa=0;
}
}






public void mostraInsta()
{

if (instagrams.size()>0)
{
//int  qual = int ( random(instagrams.size()) );
  insta aux_I;

    // if (i==qual)
    // {
    //int i=int(random(instagrams.size() ));

    aux_I= (insta) instagrams.get(vouiiii);
    int pos =PApplet.parseInt(random(0,6));
    println(pos);
    // println(porta1_y[pos][0]+" < - > "+porta1_y[pos][0]);
   // aux_I.mostra(porta1_x[pos][0],porta1_y[pos][0]);
//PImage coiso=aux_I.dameca();
//portass[int(random(6))]=aux_I.dameca();
portass[pos]=aux_I.dameca();
jaja[pos]=true;
//image(coiso, porta1_x[pos][0],porta1_y[pos][0]);
  // }


vouiiii++;
if (vouiiii>=(instagrams.size()))
vouiiii=0;

  // insta aux_I;
  // // for (int i = 0; i <instagrams.size(); i++) 
  // // {
  //   int i=int(random(instagrams.size() ));
  //   aux_I= (insta) instagrams.get(i);
  //   int pos =int(random(0,6));
  //   println(pos);
  //   println(porta1_y[pos][0]+" < - > "+porta1_y[pos][0]);
  //   aux_I.mostra(porta1_x[pos][0],porta1_y[pos][0]);
  // // }
}
}

// void mouseMoved()
// {
//   println("XX-> " +mouseX+" YY-> "+mouseY);
// }


// Daniel Shiffman
// <http://www.shiffman.net>

// A Thread using loadStrings()

class SimpleThread extends Thread {
int vouT=0;
int vouI=0;
  boolean running;    // Is the thread running?  Yes or no?
  boolean available;  // Are there new tweets available?
char tipo;
  // Start with something 


  SimpleThread (char _tipo) {
    running = false;
    available = true; // We start with "loading . . " being available
    tipo=_tipo;
  }



  public boolean available() {
    return available;
  }

  // Overriding "start()"
  public void start () {
    running = true;
    super.start();
  }

  // We must implement run, this gets triggered by start()
  public void run () {
    while (running) {
       // println("."+tipo);
       // println("CASA"+tipo);
       // println("CASAS-> "+casas.size());
//-------------------------------------
if (tipo=='T')
{
 // println("T");
this.procuraInstas();
}
else if (tipo=='I')
{
  //println("I");
this.procuraTweets();
}
else  {
  //println("aaaaaa  ")  ;
}
     //--------------------------------------
   //   available = true;
    //   try {
    //     // Wait five seconds
    //     sleep((long)(1000));
    //   } 
    //   catch (Exception e) {
    //   }
     }
  }
public void procuraTweets()
{
println("NUMERO DE TWEETS -> "+tweets.size());
  casa aux;
  String twitterSite[];
  String jsonstring ;
  String ultimoURL;
  // for (int i = 0; i <casas.size(); i++) 
  // {
   aux= (casa) casas.get(vouT);
   //println(aux.getTweet());
   twitterSite = loadStrings("http://search.twitter.com/search.json"+aux.getTweet());
   if (twitterSite!=null)
{
   jsonstring =twitterSite[0];
   JSON twiits = JSON.parse(jsonstring);
   ultimoURL=twiits.getString("refresh_url");
   twiits =twiits.getJSON("results");

   if (twiits.length()>0)
   {
    //println("yayayyyayyya");
    for (int t = 0; t<twiits.length(); t++)
    {
    JSON  este =twiits.getJSON(t);//numero do tweet nesta query
    tweets.add( new tweet(tweets.size()+1,este.getInt("from_user_id"),este.getString("text")));//adiciona instagram a lista
    aux.addTweet();
    adicionaAOuser(este.getString("from_user"),'T',aux.getTag());
  }
  aux.setTweet(ultimoURL);
}
else  {
 // println("NETNET");
}
// }
}
 vouT++;
    if (vouT>=NUM_CASAS)
    vouT=0;
}


  
public void procuraInstas()
{
println("NUMERO DE INSTTAS -> "+instagrams.size());
  String instaSite[];
  String tag ;
  int count;
  String jsonstring ;
  String username;
  casa aux;
  
  // for (int i = 0; i <casas.size(); i++) 
  // {
    aux= (casa) casas.get(vouI);
    tag =aux.getTag();
    instaSite = loadStrings("https://api.instagram.com/v1/tags/"+tag+"//media/recent?client_id=df3cb46a40c042faa62e1e2e29c89697&min_tag_id="+aux.getInsta()+"");
   if (instaSite!=null)
{
  //println("ESTOU A PROCURA: ");
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
        //println("INSTA  USER-> "+user_id+" TAG -> "+tag);
        
      }
    // }
  }
  else  {
    println("FALHA DA NET ");
  }
    vouI++;
    if (vouI>=NUM_CASAS)
    vouI=0;
  }
  // Our method that quits the thread
  public void quit() {
    System.out.println("Quitting."); 
    running = false;  // Setting running to false ends the loop in run()
    // In case the thread is waiting. . .
    interrupt();
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
  int[] porta1_x = new int[4];
  int[] porta1_y = new int[4];

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
  int ALTURA=600;
  int LARGURA=800;
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
  int largura=17;
  int altura=24;
  float largura_tam;
  float altura_tam;

  casa (String nome , float xx, float yy) 
  {


    porta1_x[0]=400; porta1_y[0]=0;
    porta1_x[1]=400; porta1_y[1]=300;
    porta1_x[2]=800; porta1_y[2]=300;
    porta1_x[3]=800; porta1_y[3]=0;





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
    if(posicaoX > ((width )))
    {
    	posicaoX = (width - largura_tam);

    	velocidadeX *= -1; 
    	if (velocidadeX>0)
    	velocidadeX--;
    	else 
    	velocidadeX++;	
      posx--;
    }
    if(posicaoX <  (largura_tam))
    {
    	posicaoX =  largura_tam;
    	velocidadeX *= -1; 
    	if (velocidadeX>0)
    	velocidadeX--;
    	else 
    	velocidadeX++;	

      // if (posicaoX>=(width ))
      // posicaoX--;

    }
    // if(posicaoY < raio){
    //   posicaoY = raio;
    //   velocidadeY *= -1; 
    // }

    if(    posicaoY > (((height )) )  )
    {
    	posicaoY = (height - altura_tam);
    	velocidadeY *= -1; 
    	if (velocidadeY>0)
    	velocidadeY--;
    	else 
    	velocidadeY++;	

      // if (posicaoY>=(height -(MARGEN)))
       posy--;

    }
    if(posicaoY <  (altura_tam))
    {
    	posicaoY = altura_tam ;
    	velocidadeY *= -1; 
    	if (velocidadeY>0)
    	velocidadeY--;
    	else 
    	velocidadeY++;	
    }




}





public void desenha(	)
{
		//smooth();
		
		
		// fill(0, 0, 255);
		
		// text("#"+tag, posicaoX+tamLetra, posicaoY-tamLetra);
		
		// fill(255,0,0);
		
		largura_tam=((largura+(dim*0.7f))/ESCALA);
		altura_tam=((altura+dim)/ESCALA);

		//noStroke();
		//fill(cor);
		canvas.stroke(color(255,0,10));
		//fill(color(255,0,10));
    canvas.noFill();
    canvas.strokeWeight(1+(dim)/16);
		 //posicaoX
		// shape(fundo, posx-((largura+(dim*0.7))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
		 //shape(desenho, posx-((largura+(dim*0.7))/ESCALA), posy-((altura+dim)/ESCALA), (largura+(dim*0.7))/ESCALA, (altura+dim)/ESCALA);
		 //shape(bb, posicaoX-((largura+(dim*0.7))/ESCALA), posicaoY-((altura+dim)/ESCALA), largura_tam, altura_tam);
		 // shape(aa, posicaoX-((largura+(dim*0.7))/ESCALA), posicaoY-((altura+dim)/ESCALA), largura_tam, altura_tam);
   //   shape(bb, posicaoX-((largura+(dim*0.7))/ESCALA), posicaoY-((altura+dim)/ESCALA), largura_tam, altura_tam);
   //   shape(cc, posicaoX-((largura+(dim*0.7))/ESCALA), posicaoY-((altura+dim)/ESCALA), largura_tam, altura_tam);
   //   shape(dd, posicaoX-((largura+(dim*0.7))/ESCALA), posicaoY-((altura+dim)/ESCALA), largura_tam, altura_tam);
   canvas.rect(posicaoX-((largura+(dim*0.7f))/ESCALA), posicaoY-((altura+dim)/ESCALA), largura_tam, largura_tam);
   canvas.line(posicaoX-((largura+(dim*0.7f))/ESCALA), posicaoY-((altura+dim)/ESCALA),posicaoX-((largura+(dim*0.7f))/ESCALA)+largura_tam, posicaoY-((altura+dim)/ESCALA)+largura_tam);
   canvas.line(posicaoX-((largura+(dim*0.7f))/ESCALA), posicaoY-((altura+dim)/ESCALA)+largura_tam,posicaoX-((largura+(dim*0.7f))/ESCALA)+largura_tam, posicaoY-((altura+dim)/ESCALA));
   canvas.line(posicaoX-((largura+(dim*0.7f))/ESCALA), posicaoY-((altura+dim)/ESCALA),posicaoX-((largura+(dim*0.7f))/ESCALA)+(largura_tam/2), posicaoY-((altura+dim)/ESCALA)-(largura_tam/2));
   canvas.line(posicaoX-((largura+(dim*0.7f))/ESCALA)+largura_tam, posicaoY-((altura+dim)/ESCALA),posicaoX-((largura+(dim*0.7f))/ESCALA)+(largura_tam/2), posicaoY-((altura+dim)/ESCALA)-(largura_tam/2));

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
 	PImage foto;
 	
 	insta (int _id,int _user,String _url) 
 	{
 		id=_id;
 		userID=_user;
 		url=_url;
 		foto = loadImage(url);
 		foto.resize(PApplet.parseInt(foto.width-(foto.width*0.3f)	),PApplet.parseInt( foto.height-(foto.height*0.3f)) );

 		// for (int x=0; x<foto.height;x++)
 		// {

 		// 	for (int xx=0; xx<foto.width;xx++)
 		// 	{
 		// 		if (xx>(foto.width*0.5))
 		// 		foto.set(xx,x,255);
 		// 	}

 		// }

 	}

 	public void mostra(int posx , int posy)
 	{

 		layerTOPO.beginDraw();
 		layerTOPO.background(0, 0, 255);
 		layerTOPO.image(foto, posx, posy);
 		layerTOPO.endDraw();
 	}
 	
public PImage dameca()
{
	return foto;
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

        stroke(cor);
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
// Daniel Shiffman
// <http://www.shiffman.net>

// A Thread using loadStrings()

class mostraCoisas extends Thread {
int vouT=0;
int vouI=0;
  boolean running;    // Is the thread running?  Yes or no?
  boolean available;  // Are there new tweets available?
char tipo;
  // Start with something 


  mostraCoisas (char _tipo) {
    running = false;
    available = true; // We start with "loading . . " being available
    tipo=_tipo;
  }



  public boolean available() {
    return available;
  }

  // Overriding "start()"
  public void start () {
    running = true;
    super.start();
  }

  // We must implement run, this gets triggered by start()
  public void run () {
    while (running) {
       // println("."+tipo);
       // println("CASA"+tipo);
       // println("CASAS-> "+casas.size());
//-------------------------------------
if (tipo=='T')
{
 // println("T");
this.mostraTweet();
}
else if (tipo=='I')
{
  //println("I");
this.mostraInsta();
}
else  {
  //println("aaaaaa  ")  ;
}
     //--------------------------------------
   //   available = true;
      try {
        // Wait five seconds
        sleep((long)(2000));
      } 
      catch (Exception e) {
      }
     }
  }
public void mostraInsta()
{
  int  qual = PApplet.parseInt ( random(instagrams.size()) );
  insta aux_I;
  for (int i = 0; i <instagrams.size(); i++) 
  {
    if (i==qual)
    {
    //int i=int(random(instagrams.size() ));
    aux_I= (insta) instagrams.get(i);
    int pos =PApplet.parseInt(random(0,6));
    println(pos);
    // println(porta1_y[pos][0]+" < - > "+porta1_y[pos][0]);
    aux_I.mostra(porta1_x[pos][0],porta1_y[pos][0]);
  }
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
  // Our method that quits the thread
  public void quit() {
    System.out.println("Quitting."); 
    running = false;  // Setting running to false ends the loop in run()
    // In case the thread is waiting. . .
    interrupt();
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
 		canvas.fill(255,0,0);
 		canvas.textSize(letra);
 		canvas.text(texto, posx, posy);
 	}

public void pinta()
{
canvas.fill(255,0,0);
     canvas.textSize(10);
     canvas.text(texto, 20  , 20);

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
 	  int[] palette=new int[10];
 	user (int _id, String _user) 
 	{
 		id=_id;
 		username=_user;
 		caminho= new ArrayList();
 		//corcor=color(random(0,256),random(0,256),random(0,256));
 		  palette[0]=color(0,102,153);
        palette[1]=color(0,153,255);
        palette[2]=color(0,51,102);
        palette[3]=color(51,204,255);
        palette[4]=color(153,204,255);
        palette[5]=color(51,102,153);
        palette[6]=color(51,51,102);
        palette[7]=color(102,51,102);
        palette[8]=color(255,51,102);
        palette[9]=color(255,102,153);
      
                int i = PApplet.parseInt(random(0,9));
 		corcor=palette[i];
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
 				 p_xx=casola.getX();
 					 p_yy=casola.getY();
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
 					//ellipse(x, y, 5, 5);
 					traco.novapos (x,y);	
 				}
 			//}
 			canvas.stroke(corcor);
 			canvas.strokeWeight(1);
 			//canvas.line(p_yy,p_yy,c_xx,c_yy);
 			
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
