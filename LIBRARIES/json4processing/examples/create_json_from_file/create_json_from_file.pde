/*
 * JSON 4 Processing
 * Basic example 5: Loading JSON from a file
 */

import org.json.*;

void setup(){
  JSON json = JSON.load(dataPath("data.json"));

  println( json );
}

void draw(){
}