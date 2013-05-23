import java.util.*;
import toxi.geom.*;

class linha 
{

float beginX;  // Initial x-coordinate
float beginY;  // Initial y-coordinate
float endX;   // Final x-coordinate
float endY;   // Final y-coordinate
float x = 0.0;  // Current x-coordinate
float y = 0.0;  // Current y-coordinate

float tamanho=4;

boolean showLine=true;
boolean showSpline=true;
boolean showHandles=true;
ArrayList points=new ArrayList();
int numP;

linha(float ox,float oy) 
{
  Stroke(0);
  numP=points.size();
  showLine=true;
  showSpline=true;
  showHandles=true;
  Vec2D currP=new Vec2D(x,y);
  beginX = ox;
  beginY = oy;
  
 
}


void novapos(float posX, float posY) {

  Vec2D currP=new Vec2D(posX,posY);
  points.add(currP);

}


void desenhalinha() 
{
  numP=points.size();
  beginShape();
  for(int i=0; i<numP; i++) {
  Vec2D p=(Vec2D)points.get(i);
  vertex(p.x,p.y);
  }
  endShape();
   
  
}



void setTamanho(float _tam)
{
  tamanho=_tam;
}

//void inicia(float _posx, float _posy)
//{
//  beginX = x;
//  beginY = y;
//  endX = _posx;
//  endY = _posy;
// 
//}


}
