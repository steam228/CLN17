class exclui 
{

//int aux_id;
ArrayList zonas;


exclui() 
{
//	size(680,382);
zonas = new ArrayList();
// aux_id=addPoligno();
// addPonto(aux_id,10,0);
// addPonto(aux_id,100,50);
// addPonto(aux_id,110,80);
// addPonto(aux_id,40,100);

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

void desenhaID()
{
	Area zonita ;
	zonita=(Area)zonas.get(i);
	zonita.desenha();
}

boolean  contem(int _id , int _posx , int _posy)
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
	zona_aux = new Area(id,this);
	zonas.add(zona_aux);	
	return id;
}

}