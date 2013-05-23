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
  //traco =new linha();
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
 // background(0);
   if ( millis() - lastTime > pausa ) 
   {
     procuraTweets();
procuraInstas();
  lastTime = millis();
     mostraInsta();
     mostraTweet();
 } 
desenhaCaminhos();
animaMundo();

//areas.desenharTodos();
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

      // if (i==0)
      //   traco = new linha(aux.getX(),aux.getY());
      //   else
      // traco.novapos (aux.getX(),aux.getY());

    }
  //traco.desenhalinha();

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
    println("USER -> "+i+" INSTAS -> "+aux.countInsta()+" CAMI -> "+aux.getCaminhoSize()+" NAME-> "+aux.getID());
    aux.desenha(casas);
  }
}

void adicionaAOuser(String _user , char tipo , String _tag)
{
  user aux;

  for (int i = 0; i <pessoas.size(); i++) 
  {
    aux= (user) pessoas.get(i);
//println("USER "+_user+" ID -> "+aux.getID());
    if  (_user.equals(aux.getID())==true)
    {
    
      if (tipo=='T')
      {
       aux.addTweet();
       aux.addCaminho(retornaCasaID(_tag));
     }
     else if (tipo=='I')
     {
      //println("Toooooooooooooo"+_user);
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
     // _tag.equals(
      if (_tag.equals(aux.getTag())==true )
      return i;
    }
    return -1;
}