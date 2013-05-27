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
 		canvas.fill(255,0,0);
 		canvas.textSize(letra);
 		canvas.text(texto, posx, posy);
 	}

void pinta()
{
canvas.fill(255,0,0);
     canvas.textSize(10);
     canvas.text(texto, 20  , 20);

}

 	int getUser(){return userID;}
 	int getId(){return id;}
 }
