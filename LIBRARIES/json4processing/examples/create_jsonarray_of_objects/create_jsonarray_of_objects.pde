/*
 * JSON 4 Processing
 * Basic example 3: Creating a list of JSON objects
 */

import org.json.*;

void setup(){
  
  // 1. Initialize the Array
  JSON myJsonUsers = JSON.createArray();
  
  // 2. Create the first object & add to array
  JSON firstUser = JSON.createObject();
  firstUser.setString( "name", "Andreas" );
  firstUser.setInt( "age", 32 );
  myJsonUsers.append( firstUser );
  
  // 3. Create the second object
  JSON secondUser = JSON.createObject();
  secondUser.setString( "name", "Maria" );
  secondUser.setInt( "age", 28 );
  myJsonUsers.append( secondUser );
  
  println( myJsonUsers ); 
}

void draw(){
}