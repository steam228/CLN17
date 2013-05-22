import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import toxi.geom.*; 
import toxi.processing.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class exclui extends PApplet {




int aux_id;

ArrayList zonas;
Area zonita ;

public void setup() 
{
	size(680,382);
	zonas = new ArrayList();

	//zonita = new Area(zonas.size()+1,this);
	//zonita.addPonto(10,0);
	//zonita.addPonto(100,50);
	//zonita.addPonto(110,80);
	//zonita.addPonto(40,100);
aux_id=addPoligno();
addPonto(aux_id,10,0);
addPonto(aux_id,100,50);
addPonto(aux_id,110,80);
addPonto(aux_id,40,100);
	
}

public void draw() 
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

public void addPonto(int _id , int _posx , int _posy)
{
Area zona_aux ;
zona_aux =(Area)zonas.get(_id);
zona_aux.addPonto(_posx,_posy);
}

public int addPoligno()
{	
	int id=zonas.size();
	Area zona_aux ;
	zona_aux = new Area(id,this);
	zonas.add(zona_aux);	
	return id;
}
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

	public void addPonto( int _x , int _y)
	{
		poligno.add(new Vec2D(_x,_y));
	}
	public void desenha()
	{
		grafico.polygon2D(poligno);
	}
	public boolean contem(int _posx , int _posy)
	{
		return poligno.containsPoint(new Vec2D(_posx,_posy));
	}
	public int getID()
	{
		return id;
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "exclui" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
