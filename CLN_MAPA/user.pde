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
 	color corcor;
 	int vai=0;
 	user (int _id, String _user) 
 	{
 		id=_id;
 		username=_user;
 		caminho= new ArrayList();
 		corcor=color(random(0,256),random(0,256),random(0,256));
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
 			int last=(Integer)caminho.get(tam-1);
 			if (last!=_casa)
 			{
 				caminho.add(_casa);
 				println("SIMMM");
 			}
 			else  {
 				println("NAOOO");
 			}
 		//println("LAST ->"+caminho.get(tam)+" ESTE-> "+_casa);
 		vai=0;
 	}
 	else  {
 		caminho.add(_casa);
 	}

 }
 int getCaminhoSize() {return caminho.size();}
 void desenha(ArrayList casitas)
 {
 	// if (caminho.size()>=4)
 	// {
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
 				//traco.novapos (casola.getX(),casola.getY());	
 			}

 		}

if (username.equals("davidorosario")==true)
{
traco.setCor(color(255,0,0));
 		traco.desenhalinha();	
}



 	// }
 }

}