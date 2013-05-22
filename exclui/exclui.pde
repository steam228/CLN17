import toxi.geom.*;
import toxi.processing.*;

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
void setup() {
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

void draw() 
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
void keyPressed()
{
	if (key == 'E' || key == 'e') 
	{
		fill(0);
	} 
}
