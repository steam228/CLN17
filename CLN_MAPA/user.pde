 class user 
 {

 	int id;
 	int numTweets;
 	int numInsta;
 	String username;
 	ArrayList caminho;
 	color cor;
 	float grosura;
 	linha desenhador;
 	linha traco;
 	user (int _id, String _user) 
 	{
 		id=_id;
 		username=_user;
 		caminho= new ArrayList();
 	}

 	void addTweet()
 	{
 		numTweets++;
 		grosura++;
 	}
 	void addInsta()
 	{
 		numInsta++;
 		grosura++;
 	}
 	int countInsta(){ return numInsta; }
 	int countTweets(){ return numTweets; }
 	String getID()  {return username;}
 	void addCaminho(int _casa) 
 	{
 		int tam=caminho.size();
 		if (tam>0)
 		{
 			int last=(Integer)caminho.get(tam);
 		if (last!=_casa)
 		{
 		caminho.add(_casa);
 		println("SIMMM");
 		}
 		else  {
 			println("NAOOO");
 		}
 		println("LAST ->"+caminho.get(tam)+" ESTE-> "+_casa);
 		}
 	}
 	int getCaminhoSize() {return caminho.size();}
 	void desenha(ArrayList casitas)
 	{
 		if (caminho.size()>=4)
 		{
 			int numDaCasa;
 			casa casola;
 			for (int i = 0; i <caminho.size(); i++) 
 			{

 				numDaCasa= (Integer) caminho.get(i);
 				casola=(casa)casitas.get(numDaCasa);
 				if (i==0)
 				traco = new linha(casola.getX(),casola.getY());
 				else
 				traco.novapos (casola.getX(),casola.getY());
 			}
 			traco.desenhalinha();
 		}
 	}

 }