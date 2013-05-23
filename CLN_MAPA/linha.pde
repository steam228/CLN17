import java.util.*;
import toxi.geom.*;

class linha 
{

  ArrayList points=new ArrayList();
  int numP=points.size();

  linha() 
  {
    noFill();
  }

  void setTamanho(float _tam)
  {
    tamanho=_tam;
  }
  void setInicio(float _posx, float _posy)
  {
    x=_posx;
    y=_posy;
  }

  void inicia(float _posx, float _posy)
  {

    beginX = x;
    beginY = y;
    endX = _posx;
    endY = _posy;
    distX = endX - beginX;
    distY = endY - beginY;
  }

  void desenha() 
  {
    Vec2D currP=new Vec2D(x, y);

    if (numP>0) {
      // check distance to previous point
      Vec2D prevP=(Vec2D)points.get(numP-1);

      points.add(currP);}

else {
  // add first point regardless
  points.add(currP);
}
numP=points.size();
if (showLine) {
  stroke(255, 0, 0, 50);
  beginShape();
  for (int i=0; i<numP; i++) {
    Vec2D p=(Vec2D)points.get(i);
    vertex(p.x, p.y);
  }
  endShape();
  
   // need at least 4 vertices for a spline
  if (numP>3 && showSpline) {
    // pass the points into the Spline container class
    Spline2D spline=new Spline2D(handles);
    // sample the curve at a higher resolution
    // so that we get extra 8 points between each original pair of points
    java.util.List vertices=spline.computeVertices(8);
    // draw the smoothened curve
    beginShape();
    for(Iterator i=vertices.iterator(); i.hasNext(); ) {
      Vec2D v=(Vec2D)i.next();
      vertex(v.x,v.y);
    }
    endShape();
}

