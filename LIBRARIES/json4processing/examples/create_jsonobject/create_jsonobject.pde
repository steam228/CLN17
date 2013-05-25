/*
 * JSON 4 Processing
 * Basic example 1: Creating a JSON object
 *
 * Good for sending values that has a specific meaning (complex values)
 */

import org.json.*;

void setup(){
  
  // 1. Initialize the object
  JSON myJsonObject = JSON.createObject();
  
  // 2. Add some content to the object
  myJsonObject.setInt( "myIntegerValue", 7 );

  println( myJsonObject );
}

void draw(){
}