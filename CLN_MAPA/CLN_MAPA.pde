import org.json.*;
import java.util.*; 
import de.bezier.data.*;

import toxi.geom.*;
import toxi.processing.*;

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

void setup(){

  areas= new exclusoes(this);
  id_area= areas.addPoligno();
  areas.addPonto(id_area,300,0);
  areas.addPonto(id_area,400,50);
  areas.addPonto(id_area,410,80);
  areas.addPonto(id_area,400,100);
  //size(largura, altura,P3D);
  size(largura, altura,OPENGL);
  casas = new ArrayList();
  tweets= new ArrayList();
  instagrams= new ArrayList();
  pessoas= new ArrayList();
  background(0);
   carregaCasas();

}

void draw(){
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

 // if ( millis() - lastTime > pausa ) 
 // {
 //   procuraTweets();
 //   procuraInstas();
 //   lastTime = millis();
   // mostraInsta();
   // mostraTweet();
 //} 
 // if ( millis() - ultimaVez > espera ) 
 // {
 //  ultimaVez=millis();
//desenhaCaminhos();
//}
//animaMundo();
moveMundo();

//}


// fill(0,0,255);
// ellipse(xxx, yyy, 50, 50);
// fill(0,255,0);
// ellipse(xxx1, yyy1, 50, 50);

// stroke(0, 0, 255);
// //line(0,0,);
// line();
// stroke(0, 0, 255);
// line();
// line();

}


void carregaCasas()
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

    float ypos = map (yvals[i], ymin, ymax, margin/2, height-(margin/2));
    float xpos = map (xvals[i], xmin, xmax, margin, width-margin);
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

void procuraInstas()
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
      if(distancia < 40){
        line(bolaA.posicaoX, bolaA.posicaoY, bolaB.posicaoX, bolaB.posicaoY);
      }

      bolaA.desenha();
    }
    float dx = bolaA.posicaoX - mouseX;
    float dy = bolaA.posicaoY - mouseY;
    float distancia = dist(bolaA.posicaoX, bolaA.posicaoY, mouseX, mouseY);
    dx /= distancia;
    dy /= distancia;
    float forcaX = dx * (200 / distancia);
    float forcaY = dy * (200 / distancia);
    bolaA.aceleracaoX += forcaX;
    bolaA.aceleracaoY += forcaY;

  }

      
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