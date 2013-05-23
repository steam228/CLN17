 class tweet 
 {

 	int id;
 	int userID;
 	String texto;

 	tweet (int _id,int _user,String _texto) 
 	{
 		id=_id;
 		userID=_user;
 		texto=_texto;
 	}

 	void mostra(int posx , int posy, int letra )
 	{
 		fill(255,0,0);
 		textSize(letra);
 		text(texto, posx, posy);
 	}

 	int getUser(){return userID;}
 	int getId(){return id;}
 }