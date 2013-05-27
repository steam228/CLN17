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
 int getCaminhoSize() {return caminho.size();}
 void desenha(ArrayList casitas)
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
 				

 				float x = lerp(p_xx, c_xx, aa/3.0);
 				float y = lerp(p_yy, c_yy, aa/3.0);
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
