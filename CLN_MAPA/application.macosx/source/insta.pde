 class insta 
 {

 	int id;
 	int userID;
 	String url;
 	PImage foto;
 	
 	insta (int _id,int _user,String _url) 
 	{
 		id=_id;
 		userID=_user;
 		url=_url;
 		foto = loadImage(url);
 		foto.resize(int(foto.width-(foto.width*0.3)	),int( foto.height-(foto.height*0.3)) );

 		// for (int x=0; x<foto.height;x++)
 		// {

 		// 	for (int xx=0; xx<foto.width;xx++)
 		// 	{
 		// 		if (xx>(foto.width*0.5))
 		// 		foto.set(xx,x,255);
 		// 	}

 		// }

 	}

 	void mostra(int posx , int posy)
 	{

 		layerTOPO.beginDraw();
 		layerTOPO.background(0, 0, 255);
 		layerTOPO.image(foto, posx, posy);
 		layerTOPO.endDraw();
 	}
 	
PImage dameca()
{
	return foto;
}
 	int getUser(){return userID;}
 	int getId(){return id;}
 }
