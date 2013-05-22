import org.json.*;
import java.util.*; 
import de.bezier.data.*;

import toxi.geom.*;
import toxi.processing.*;

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

void setup(){
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

void draw(){
  smooth();
  //background(0);
 // if ( millis() - lastTime > pausa ) 
  //{
 //   procuraTweets();
 //   procuraInstas();
 //lastTime = millis();
 //   mostraInsta();
 //   mostraTweet();
//} 
animaMundo();
areas.desenharTodos();
}


void carregaCasas()
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
void procuraTweets()
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

void procuraInstas()
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
void animaMundo()
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


void adicionaAOuser(String _user , char tipo)
{
  user aux;
  for (int i = 0; i <pessoas.size(); i++) 
  {
    aux= (user) pessoas.get(i);
   if  (aux.getID()==_user)
   {
    if (tipo=='T')
    aux.addTweet();
    else if (tipo=='I')
    aux.addInsta();
    break;
   }

  }
}