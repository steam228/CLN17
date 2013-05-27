 class insta 
 {

 	int id;
 	int userID;
 	String url;

 	insta (int _id,int _user,String _url) 
 	{
 		id=_id;
 		userID=_user;
 		url=_url;
 	}

 	void mostra(int posx , int posy)
 	{
 		PImage foto;
 		foto = loadImage(url);
 		image(foto, posx, posy);
 	}
 	void mostra(int posx , int posy, int largura , int altura)
 	{
 		PImage foto;
 		foto = loadImage(url);
 		image(foto, posx, posy, largura	, altura);
 	}

 	int getUser(){return userID;}
 	int getId(){return id;}
 }