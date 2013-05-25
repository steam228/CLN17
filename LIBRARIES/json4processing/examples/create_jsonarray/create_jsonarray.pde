/*
 * JSON 4 Processing
 * Basic example 2: Creating a list of JSON values
 */

import org.json.*;

void setup(){
  
  // 1. Initialize the Array
  JSON myJsonArray = JSON.createArray();
  
  // 2. Add some content to the array
  myJsonArray.append( 4 );
  myJsonArray.append( 2 );
  
  println( myJsonArray ); 
}

void draw(){
}
