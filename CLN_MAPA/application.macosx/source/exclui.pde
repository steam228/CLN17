class exclusoes 
{

	ArrayList zonas;
	PApplet grafismo;

	exclusoes(PApplet _gra) 
	{
		zonas = new ArrayList();
		grafismo=_gra;
	}

	void desenharTodos() 
	{
		Area zonita ;
		for (int i = 0; i <zonas.size(); i++) 
		{
			zonita=(Area)zonas.get(i);
			zonita.desenha();
		}
	}


	void desenhaID(int _id)
	{
		Area zonita ;
		zonita=(Area)zonas.get(_id);
		zonita.desenha();
	}

	int  contemTodos(float _posx , float _posy) 
	{
		Area zonita ;
		for (int i = 0; i <zonas.size(); i++) 
		{
			zonita=(Area)zonas.get(i);
			
			if ( zonita.contem(_posx , _posy) )
			return zonita.getID();
		}
		return -1;
	}

	boolean   contemAlgum(float _posx , float _posy) 
	{
		Area zonita ;
		for (int i = 0; i <zonas.size(); i++) 
		{
			zonita=(Area)zonas.get(i);
			
			if ( zonita.contem(_posx , _posy) )
			return true;
		}
		return false;
	}

	boolean  contemID(int _id , int _posx , int _posy)
	{
		Area zonita ;
		zonita=(Area)zonas.get(_id);
		return zonita.contem(_posx ,_posy);
	}

	void addPonto(int _id , int _posx , int _posy)
	{
		Area zona_aux ;
		zona_aux =(Area)zonas.get(_id);
		zona_aux.addPonto(_posx,_posy);
	}

	int addPoligno()
	{	
		Area zona_aux ;
		int id=zonas.size();
		zona_aux = new Area(id,grafismo);
		zonas.add(zona_aux);	
		return id;
	}

}
