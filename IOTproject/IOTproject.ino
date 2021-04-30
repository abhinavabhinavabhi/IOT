
#include "FirebaseESP8266.h"


#include <ESP8266WiFi.h>

const char *ssid =  "JioFi3_420C4D";     // replace with your wifi ssid and wpa2 key
const char *pass =  "ec623j1bw2";

WiFiClient client;
const int trigPin = 4; 
      const int echoPin = 0;
      long duration;
      int distance;
      
   
 
void setup() 
{
       Serial.begin(9600);
       delay(10);
               
       Serial.println("Connecting to ");
       Serial.println(ssid); 
 
    WiFi.begin(ssid, pass); 
       while (WiFi.status() != WL_CONNECTED) 
      {
          delay(500);
            Serial.print(".");
          }
      Serial.println("");
      Serial.println("WiFi connected"); 
      pinMode(trigPin, OUTPUT);
      pinMode(echoPin, INPUT);
      pinMode(D0,INPUT);
      pinMode(A0,INPUT);


}
 
void loop() 
{     
  
  digitalWrite(trigPin, LOW);
delayMicroseconds(2);

// Sets the trigPin on HIGH state for 10 micro seconds
digitalWrite(trigPin, HIGH);
delayMicroseconds(10);
digitalWrite(trigPin, LOW);

// Reads the echoPin, returns the sound wave travel time in microseconds
 duration = pulseIn(echoPin, HIGH);
  distance = (duration/2) / 29.1;

// Calculating the distance

// Prints the distance on the Serial Monitor
Serial.print("Distance: ");
Serial.println(distance);
if(distance<=3)
{
  Serial.println("");
  Serial.println("water level is full");
}
if(distance>3 and distance<=10)
{
  Serial.println("");
  Serial.println("water level is in middle");
}
if(distance>10 and distance<=20)
{
  Serial.println("");
  Serial.println("tank is about to empty");
}
if(distance>20 )
{
  Serial.println("");
  Serial.println("tank is empty");
}
delay(2000);
 Serial.println("");
  Serial.println("");
float moisture_percentage;


delay(1000);
  moisture_percentage = ( 100.00 - ( (digitalRead(D0)/1023.00) * 100.00 ) );
  delay(1000);

  Serial.print("Soil Moisture(in Percentage) = ");
  Serial.print(moisture_percentage);
  Serial.println("%");
  
if(moisture_percentage<=10 )
{
  Serial.println("");
  Serial.println("Plants need water");
}
if(moisture_percentage>10 )
{
  Serial.println("");
  Serial.println("Plants need water");
}


delay(1000);
int ldrStatus = analogRead(A0); 
delay(1000);

 Serial.println("");
  Serial.println("");
//Serial.print(ldrStatus);
 Serial.println("");
  if (ldrStatus <=300) {

  
  Serial.print(ldrStatus);
  Serial.println("condition is DARK,Plants need sunlight");
   Serial.println("");

  }
  if (ldrStatus >300) {

  
  Serial.print(ldrStatus);
  Serial.println("condition is Bright,Plants have sunlight");
   Serial.println("");

  }
 
  

  delay(1000);

}
