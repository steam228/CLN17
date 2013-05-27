import org.json.*;
import java.util.*; 
import de.bezier.data.*;
import codeanticode.syphon.*;

  int MARGEN_BAIXO=50;
int[][] porta1_x = new int[4][4];
int[][] porta1_y = new int[4][4];

PGraphics canvas;
SyphonServer server;
import toxi.geom.*;
import toxi.processing.*;
int vou=0;

PShape desenho1;
PShape desenho2;
PShape desenho3;
PShape desenho4;
PShape fundo;

int largura=1200;
int altura=300;
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

void setup(){
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

id_area= areas.addPoligno();
areas.addPonto(id_area,200,200);
areas.addPonto(id_area,200,300);
areas.addPonto(id_area,250,300);
areas.addPonto(id_area,250,200);

id_area= areas.addPoligno();
areas.addPonto(id_area,300,200);
areas.addPonto(id_area,300,300);
areas.addPonto(id_area,350,300);
areas.addPonto(id_area,350,200);





id_area= areas.addPoligno();
areas.addPonto(id_area,850,200);
areas.addPonto(id_area,850,300);
areas.addPonto(id_area,900,300);
areas.addPonto(id_area,900,200);

id_area= areas.addPoligno();
areas.addPonto(id_area,950,200);
areas.addPonto(id_area,950,300);
areas.addPonto(id_area,1000,300);
areas.addPonto(id_area,1000,200);

id_area= areas.addPoligno();
areas.addPonto(id_area,1050,200);
areas.addPonto(id_area,1050,300);
areas.addPonto(id_area,1100,300);
areas.addPonto(id_area,1100,200);
  //size(largura, altura,P3D);
  size(largura, altura,P3D);
   canvas = createGraphics(1200, 300, P3D);
  server = new SyphonServer(this, "Processing Syphon");

  casas = new ArrayList();
  tweets= new ArrayList();
  instagrams= new ArrayList();
  pessoas= new ArrayList();
  background(0);
  carregaCasas();

}

void draw(){
   canvas.beginDraw();
   background(0);
  if (hideee)
{areas.desenharTodos(); }
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



  canvas.endDraw();
 image(canvas, 0, 0);
  server.sendImage(canvas);
}


void carregaCasas()
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
     float ypos = map (yvals[i], ymin, ymax, 0, height);
    float xpos = map (xvals[i], xmin, xmax, 0, width);
//---------------------------------
float nova_x;

int MEIO=600;
int LARGUURA=1200;
int MEIALARGURA=100;

if (xpos>MEIO)
{
nova_x = map (xpos, MEIO,LARGUURA,MEIO + MEIALARGURA,LARGUURA );
}
else 
{
 nova_x = map (xpos, 0,MEIO,0,MEIO - MEIALARGURA ); 
}


xpos=nova_x;

//-------------------


// porta1_x[0]=100; porta1_y[0]=200;
// porta1_x[1]=100; porta1_y[1]=300;
// porta1_x[2]=150; porta1_y[2]=300;
// porta1_x[3]=150; porta1_y[3]=200;
//int lala=0;
for (int lala=0; lala<4 ; lala++)
{
if ( (xpos>porta1_x[lala][0]) &&  (xpos<porta1_x[lala][3]) && (ypos>porta1_y[lala][0]) )
{
  float cima_dis=dist(xpos, ypos, xpos, porta1_y[lala][0]);//DISTANCIA PARA CIMA
  float esq_dis=dist(xpos, ypos, porta1_x[lala][0], ypos);//DISTANCIA PARA ESQ
  float dir_dis=dist(xpos, ypos, porta1_x[lala][0], ypos);//DISTANCIA PARA DIR
  if ( (dir_dis<esq_dis) && (dir_dis<cima_dis) )
  {
    xpos-=dir_dis;
  }
  else if (  (cima_dis<esq_dis)&&(cima_dis<dir_dis)      ) {
    ypos-=cima_dis;
  }
  else if ( (esq_dis<=cima_dis)&&(esq_dis<=dir_dis)   ) {
     xpos+=dir_dis;
  }
}
}
//---------------------------------
    posii =verificaCasa(xpos,ypos);
    casas.add(new casa(designa, posii[0], posii[1]));
  }
}



float[] verificaCasa(float _posx , float _posy) 
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




void procuraTweets()
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

void procuraInstas()
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
    if (vou>=NUM_CASAS)
    vou=0;
  }
  void animaMundo()
  {
    casa aux;
    for (int i = 0; i <casas.size(); i++) 
    {
      aux= (casa) casas.get(i);
      aux.desenha();
    }
  }

  void moveMundo()
  {
   stroke(255, 255, 255, 100);
   for(int i=0; i<casas.size(); i++){
    casa bolaA = (casa)casas.get(i);
    bolaA.mover();
    
    //dentro deste loop, temos outro loop
    //onde cada bola vai interagir com todas as outras bolas
    //aplicando uma força de repulsa
    for(int j=i+1; j<casas.size(); j++){
      casa bolaB = (casa)casas.get(j); 
      //dx e dy representam a diferença de posição entre as 2 bolas
      float dx = bolaA.posicaoX - bolaB.posicaoX;
      float dy = bolaA.posicaoY - bolaB.posicaoY;
      float distancia = dist (bolaA.posicaoX, bolaA.posicaoY, bolaB.posicaoX, bolaB.posicaoY);
      //dividindo dx e dy pela distancia ficamos com um vector unário que aponta desde bolaA até bolaB
      dx /= distancia;
      dy /= distancia;
      //como este vector é unário (tem tamanho 1) podemos então aplicar a fórmula de repulsa a cada um dos eixos
      float forcaX = dx * (5 / distancia);
      float forcaY = dy * (5 / distancia);
      //somamos a força à bolaA
      bolaA.aceleracaoX += forcaX;
      bolaA.aceleracaoY += forcaY;
      //e subtraimos à bolaB
      bolaB.aceleracaoX -= forcaX;
      bolaB.aceleracaoY -= forcaY;
      
      //se a distância for menor que 40, 
      //desenhamos ainda uma linha a unir ambos os pontos
      // if(distancia < 440){
      //   line(bolaA.posicaoX, bolaA.posicaoY, bolaB.posicaoX, bolaB.posicaoY);
      // }

      bolaA.desenha();
    }
    // ACIONA A OPÇAO DO RATO
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
void fazMagia(){
  
}
void mostraInsta()
{
  insta aux;
  for (int i = 0; i <instagrams.size(); i++) 
  {
    aux= (insta) instagrams.get(i);
    aux.mostra(0,0);

  }
}

void mostraTweet()
{
  tweet aux;
  for (int i = 0; i <tweets.size(); i++) 
  {
    aux= (tweet) tweets.get(i);
    aux.mostra(0,250,20);

  }
}
void desenhaCaminhos()
{
  user aux;
  for (int i = 0; i <pessoas.size(); i++) 
  {
    aux= (user) pessoas.get(i);
     //println("USER -> "+i+" INSTAS -> "+aux.countInsta()+" CAMI -> "+aux.getCaminhoSize()+" NAME-> "+aux.getID());
     aux.desenha(casas);
   }
 }

 void adicionaAOuser(String _user , char tipo , String _tag)
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


int retornaCasaID(String _tag)
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


void mousePressed(){
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

void keyPressed()
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