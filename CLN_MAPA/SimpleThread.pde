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



  boolean available() {
    return available;
  }

  // Overriding "start()"
  void start () {
    running = true;
    super.start();
  }

  // We must implement run, this gets triggered by start()
  void run () {
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
void procuraTweets()
{
//println("TSI_> "+tweets.size());
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


  
void procuraInstas()
{
//println("ISI_> "+instagrams.size());
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
        //println("INSTA  USER-> "+user_id+" TAG -> "+tag);
        
      }
    // }
  }
    vouI++;
    if (vouI>=NUM_CASAS)
    vouI=0;
  }
  // Our method that quits the thread
  void quit() {
    System.out.println("Quitting."); 
    running = false;  // Setting running to false ends the loop in run()
    // In case the thread is waiting. . .
    interrupt();
  }
}
