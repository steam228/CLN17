/*
 * JSON 4 Processing
 * Basic example 3: Parsing a JSON formatted string
 */

import org.json.*;

void setup(){
  
  // 1. Get the json-string (we'll just create one...) 
  String jsonstring = "{\"myIntegerValue\":7}";
  
  // 2. Initialize the object
  JSON myJsonObject = JSON.parse(jsonstring);

  println( myJsonObject );
}

void draw(){
}