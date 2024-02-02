package com.example.CSC340Assignment2;

import java.lang.Math;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

//Author: Meghan Wilcox
//Class: CSC 340 Assignment 2
//Assignment: Assignment 2

//Description: This program uses the Star Wars API to pull JSON data about planets in the Star Wars series. 
@SpringBootApplication
public class Csc340Assignment2Application {

	public static void main(String[] args) {
		SpringApplication.run(Csc340Assignment2Application.class, args);
                getStarWarsPlanet();
	}
        
        //getRandomNumber: this method generates a random number between 1 and 10, and typecasts it into a string, and then returns the string.
        public static String getRandomNumber(){
            double randomDouble = Math.random();
            int randomInt = (int) (randomDouble * 10) + 1;
            String randomStr = Integer.toString(randomInt);
            
            return randomStr;
        }
        
        //getStarWarsPlanet: This method uses the getRandomNumberMethod to retrieve JSON data from the Star Wars API about a random Star Wars planet. It then parses the data, 
        //and outputs it to the console. 
        public static void getStarWarsPlanet() {
            try {
               
                String url = "https://swapi.dev/api/planets/" + getRandomNumber();
                RestTemplate restTemplate = new RestTemplate();
                ObjectMapper mapper = new ObjectMapper();

                String jSonFact = restTemplate.getForObject(url, String.class);
                JsonNode root = mapper.readTree(jSonFact);

                String name = root.findValue("name").asText();
                String climate = root.findValue("climate").asText();
                String terrain = root.findValue("terrain").asText();

                System.out.println("**********Star Wars Planet Info********");
                System.out.println("Name: " + name);
                System.out.println("Climate: " + climate);
                System.out.println("Terrain: " + terrain);

            } catch (JsonProcessingException ex) {
                Logger.getLogger(Csc340Assignment2Application.class.getName()).log(
                        Level.SEVERE,
                        null, ex);
                System.out.println("error in getStarWarsPlanet");

        }
    }
}
