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




int mo_x;
int mo_y;
Polygon2D aux;
Polygon2D outro;

Vec2D rato;
ToxiclibsSupport grafico;
ToxiclibsSupport grafico1;
ArrayList zonas;
area zonita ;
area z_aux ;
public void setup() {
	size(680,382);
	zonas = new ArrayList();
	aux =new Polygon2D();
	outro =new Polygon2D();
	zonita = new area(zonas.size()+1,this);
	zonas.add(zonita);
	z_aux=(area)zonas.get(0);

	grafico=new ToxiclibsSupport(this);
	grafico1=new ToxiclibsSupport(this);
	
	
	z_aux.addPonto(10,0);
	z_aux.addPonto(100,50);
	z_aux.addPonto(110,80);
	z_aux.addPonto(40,100);

	// aux.add(new Vec2D(10,0));
	// aux.add(new Vec2D(100,50));
	// aux.add(new Vec2D(110,80));
	// aux.add(new Vec2D(40,100));

	// zonas.add(aux);
	// aux =new Polygon2D();
	// aux.add(new Vec2D(300,0));
	// aux.add(new Vec2D(300,50));
	// aux.add(new Vec2D(310,80));
	// aux.add(new Vec2D(340,100));
	// zonas.add(aux);
	//outro=(Polygon2D)zonas.get(1);
	println("TAMANHO: "+zonas.size());
}

public void draw() 
{
	background(255);
	for (int i = 0; i <zonas.size(); i++) 
	{
		zonita=(area)zonas.get(i);
		zonita.desenha();
		//zonita.contem();
	// grafico.polygon2D(outro);
	// outro=(Polygon2D)zonas.get(2);
	// grafico1.polygon2D(outro);
	mo_y=mouseY;
	mo_x=mouseX;
	rato =new Vec2D(mouseX	,mouseY);
	if ( zonita.contem(rato) )
	{
		println("bingo -> "+zonita.getID());
	}
	}
}
public void keyPressed()
{
	if (key == 'E' || key == 'e') 
	{
		fill(0);
	} 
}
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

	public void addPonto( int _x , int _y)
	{
		poligno.add(new Vec2D(_x,_y));
	}
	public void desenha()
	{
		grafico.polygon2D(poligno);	
	}
	public boolean contem(Vec2D cenas)
	{
		if( poligno.containsPoint(cenas))
		return true;
		else
		return false;
	}

	public int getID()
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
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "exclui" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
