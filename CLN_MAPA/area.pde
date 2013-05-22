class Area 
{
	int id;
	Polygon2D poligno;
	ToxiclibsSupport grafico;
	
	Area (int _id , PApplet coiso) 
	{
		id =_id;
		poligno=new Polygon2D();
		grafico=new ToxiclibsSupport(coiso);
	}

	void addPonto( int _x , int _y)
	{
		poligno.add(new Vec2D(_x,_y));
	}
	void desenha()
	{
		grafico.polygon2D(poligno);
	}
	boolean contem(int _posx , int _posy)
	{
		return poligno.containsPoint(new Vec2D(_posx,_posy));
	}
	int getID()
	{
		return id;
	}
}