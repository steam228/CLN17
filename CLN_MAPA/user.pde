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

 	// void desenha(ArrayList casitas)
 	// {
 	// 	int aux,aux1;

 	// 	for (int i = 0; i <caminho.size(); i++) 
 	// 	{

 	// 		aux= (int) caminho.get(i);
 	// 		//aux.desenha();
 	// 		if ((i+1)<caminho.size())
 	// 		{
 	// 			aux1= (int) caminho.get(i+1);


 				
 	// 			traco.setTamanho(10);  
 	// 			traco.setInicio(aux.getX(),aux.getY());
 	// 			traco.inicia(aux1.getX(),aux1.getY());
 	// 			traco.desenha();
 	// 		}
 	// 	}
 	// }

 }