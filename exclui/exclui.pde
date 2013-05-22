import toxi.geom.*;
import toxi.processing.*;

int aux_id;

ArrayList zonas;
Area zonita ;

void setup() 
{
	size(680,382);
	zonas = new ArrayList();
aux_id=addPoligno();
addPonto(aux_id,10,0);
addPonto(aux_id,100,50);
addPonto(aux_id,110,80);
addPonto(aux_id,40,100);
	
}

void draw() 
{
	background(255);
	for (int i = 0; i <zonas.size(); i++) 
	{
		zonita=(Area)zonas.get(i);
		zonita.desenha();
		if ( zonita.contem(mouseX ,mouseY) )
		{
			println("ESTA -> "+zonita.getID());
		}
	}
}

void addPonto(int _id , int _posx , int _posy)
{
Area zona_aux ;
zona_aux =(Area)zonas.get(_id);
zona_aux.addPonto(_posx,_posy);
}

int addPoligno()
{	
	int id=zonas.size();
	Area zona_aux ;
	zona_aux = new Area(id,this);
	zonas.add(zona_aux);	
	return id;
}