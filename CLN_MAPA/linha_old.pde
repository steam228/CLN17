// class linha 
// {

// float beginX = 20.0;  // Initial x-coordinate
// float beginY = 10.0;  // Initial y-coordinate
// float endX = 570.0;   // Final x-coordinate
// float endY = 320.0;   // Final y-coordinate
// float distX;          // X-axis distance to move
// float distY;          // Y-axis distance to move
// float exponent = 4;   // Determines the curve
// float x = 0.0;        // Current x-coordinate
// float y = 0.0;        // Current y-coordinate
// float step = 0.001;    // Size of each step along the path
// float pct = 0.0;      // Percentage traveled (0.0 to 1.0)
// float tamanho=4;

// linha() 
// {
//   noStroke();
//   distX = endX - beginX;
//   distY = endY - beginY;
// }

// void setTamanho(float _tam)
// {
//   tamanho=_tam;
// }
// void setInicio(float _posx, float _posy)
// {
//   x=_posx;
//   y=_posy;
// }

// void inicia(float _posx, float _posy)
// {
//   pct = 0.0;
//   beginX = x;
//   beginY = y;
//   endX = _posx;
//   endY = _posy;
//   distX = endX - beginX;
//   distY = endY - beginY;
// }

// void desenha() 
// {
//   while (pct < 1.0)
//   {
//     noStroke();
//     //fill(0, 2);
//     //rect(0, 0, width, height);
//     pct += step;
//     x = beginX + (pct * distX);
//     y = beginY + (pow(pct, exponent) * distY);
//     fill(255);
//     ellipse(x, y, tamanho, tamanho);
//   }
// }

// }