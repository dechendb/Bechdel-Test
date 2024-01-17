import java.util.*;
import java.io.*;

/**
 * class Movie creates objects of type Movie which contains the
 * name of the movie, actor, character, type, gender, and billing. 
 * Aside from the constructor and toString, there are getter methods. 
 * 
 * @author Dechen, Lorena, and Josie
 * @version May 2, 2023
 */
public class Movie {
    //instance variables
    protected String movie; 
    protected String actor;
    protected String character,type, gender; 
    protected String billing;
    protected int counter; 

    /**
     * Constructor 
     * 
     * @param m name of the movie
     * @param a actor's name
     * @param c character the actor played
     * @param t type of actor 
     * @param b billing
     * @param g gender of the actor 
     */
    public Movie(String m, String a, String c, String t, String b, String g) {
        this.movie = m;
        this.actor = a;
        this.character = c;
        this.type = t;
        this.billing = b;
        this.gender = g;
    }

    /**
     * Obtains the name of the movie.
     * 
     * @return movie String representing the name of the movie
     */
    public String getMovieName() {
        return this.movie;
    }

    /**
     * Obtains the name of the actor in this movie.
     * 
     * @return actor String representing the actor
     */
    public String getActor() {
        return this.actor;
    }

    /**
     * Obtains the character the actor played. 
     * 
     * @return character the character of the actor 
     */
    public String getCharacter() {
        return this.character;
    }

    /**
     * Obtains the type of the actor's role. 
     * 
     * @return type the type of role played
     */
    public String getType() {
        return this.type;
    }

    /**
     * Obtains the billing.
     * 
     * @return billing the billing
     */
    public String getBilling() {
        return this.billing;
    }

    /**
     * Obtains the gender of the actor.
     * 
     * @return gender gender of the actor
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * toString() method that allows us to print the Movie objects
     * in an organized manner. 
     * 
     * @return s string representation of this movie
     */
    public String toString() {
        String s = "Movie Name: " + movie + ". Actor: " + actor + ". Character: " + character + ". Type: " 
            + type + ". Billing: " + billing + ". Gender: " + gender;

        return s;
    }
    
    /**
     * Testing method. 
     */
    public static void main(String[] args){
        System.out.println("---testing movie class---");
        
        Movie test1 = new Movie("Ice Age", "Josie", "Me", "Main", "1", "idk");
        System.out.println(test1);
        
        Movie test2 = new Movie("Divergent", "Shailey", "Tris", "Main", "1", "Female");
        System.out.println(test2);
    }
}