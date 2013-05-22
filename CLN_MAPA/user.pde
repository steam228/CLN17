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
 	
 	user (int _id, String _user) 
 	{
 		id=_id;
 		username=_user;
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

 	String getID()
 	{
 		return username;
 	}

 	void desenha(ArrayList casitas)
 	{
 		int aux,aux1;

 		for (int i = 0; i <caminho.size(); i++) 
 		{

 			aux= (Integer) caminho.get(i);
 			//aux.desenha();
 			if ((i+1)<caminho.size())
 			{
 				aux1= (Integer) caminho.get(i+1);

casa casola;

 				traco.setTamanho(10);

 				casola=(casa)casitas.get(aux);

 				traco.setInicio(casola.getX(),casola.getY());
 				traco.inicia(casola.getX(),casola.getY());
 				traco.desenha();
 			}
 		}
 	}

 }