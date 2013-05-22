class area 
{
	//ArrayList pontos;
	int id;
	Polygon2D poligno=new Polygon2D();
	ToxiclibsSupport grafico;
	area (int _id , PApplet coiso) 
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
	boolean contem(Vec2D cenas)
	{
		if( poligno.containsPoint(cenas))
		return true;
		else
		return false;
	}

	int getID()
	{
		return id;
	}
	// Vec2D getPonto(int i)
	// {
	// 	return (Vec2D)pontos.get(i);
	// }

	// int getSize()
	// {
	// 	return pontos.size();
	// }
}