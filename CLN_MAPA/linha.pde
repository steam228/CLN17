import java.util.*;
import toxi.geom.*;

class linha 
{

  float beginX;  // Initial x-coordinate
  float beginY;  // Initial y-coordinate
  //  float endX;   // Final x-coordinate
  //  float endY;   // Final y-coordinate
  float x = 0.0;  // Current x-coordinate
  float y = 0.0;  // Current y-coordinate

  float tamanho=4;

  boolean showLine=true;
  boolean showSpline=true;
  boolean showHandles=false;
  ArrayList points=new ArrayList();
  int numP;
  color cor;

  linha(float ox, float oy) 
  {
    stroke(255);
    numP=points.size();
    showLine=true;
    showSpline=true;
    showHandles=true;
    Vec2D currP=new Vec2D(x, y);
    beginX = ox;
    beginY = oy;
  }


  void novapos(float posX, float posY) {

    Vec2D currP=new Vec2D(posX, posY);
    points.add(currP);
  }


  void desenhalinha() 
  {
    //    stroke (cor);
    stroke(255);
    strokeWeight (tamanho); // alterar quando a path ficar invisivel e for percorrida
    numP=points.size();
    beginShape();
    for (int i=0; i<numP; i++) {
      Vec2D p=(Vec2D)points.get(i);
      vertex(p.x, p.y);
      //melhorar desenho da linha
    }
    endShape();
  
  Vec2D[] handles=new Vec2D[numP];
  for (int i=0; i<numP; i++) {
    Vec2D p=(Vec2D)points.get(i);
    handles[i]=p;
    if (showHandles) ellipse(p.x, p.y, 5, 5);
  }

  // need at least 4 vertices for a spline
//  if (numP>3 && showSpline) {
  float estica = random (0, 0.5);
  setTightness (estica)
  if (numP>3) {
    // pass the points into the Spline container class
    Spline2D spline=new Spline2D(handles);
    // sample the curve at a higher resolution
    // so that we get extra 8 points between each original pair of points
    java.util.List vertices=spline.computeVertices(8);
    // draw the smoothened curve
    beginShape();
    for (Iterator i=vertices.iterator(); i.hasNext(); ) {
      Vec2D v=(Vec2D)i.next();
      vertex(v.x, v.y);
    }
    endShape();
  }
}



void setCor(color cor1) {

  cor = cor1;
}

void setTamanho(float _tam)
{
  tamanho=_tam;
}
}
