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
void mostraInsta()
{
  int  qual = int ( random(instagrams.size()) );
  insta aux_I;
  for (int i = 0; i <instagrams.size(); i++) 
  {
    if (i==qual)
    {
    //int i=int(random(instagrams.size() ));
    aux_I= (insta) instagrams.get(i);
    int pos =int(random(0,6));
    println(pos);
    // println(porta1_y[pos][0]+" < - > "+porta1_y[pos][0]);
    aux_I.mostra(porta1_x[pos][0],porta1_y[pos][0]);
  }
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
  // Our method that quits the thread
  void quit() {
    System.out.println("Quitting."); 
    running = false;  // Setting running to false ends the loop in run()
    // In case the thread is waiting. . .
    interrupt();
  }
}
