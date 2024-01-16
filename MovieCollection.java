import java.util.*;
import java.io.*;

/**
 * class MovieCollection is a program that creates and maintains
 * movie objects in a vector. Aside from the main and toString
 * method, this class contains seven other methods. 
 * 
 * @author Dechen, Lorena, and Josie
 * @version May 2, 2023
 */
public class MovieCollection {
    //instance variables
    protected Vector<Movie> information; //stores all movie obejcts
    protected int counter; 

    /**
     * Constructor. Calls the method readData which instantiates our instance
     * variables. 
     * 
     * @param file the file to be read in
     */
    public MovieCollection(String file) {
        information = readData(file);
    }

    /**
     * readData is a method that takes in a file as input and reads, line by
     * line, the characteristics of the movie object to be created. Assumes
     * that the first line of the file will not have the information of
     * a movie obeject. The following lines are placed into an array which
     * is used to get the information for one movie object.
     * Assumes the file is formatted correctly. 
     * 
     * @param fileName the name of the file to be read in
     * 
     * @return info a vector containing the movie objects from the file
     */
    public Vector<Movie> readData(String fileName) {
        Vector<Movie> info = new Vector<Movie>();

        try {
            Scanner fileScan = new Scanner(new File(fileName));
            String skip = fileScan.nextLine();

            while (fileScan.hasNextLine()) {
                String oneMovie = fileScan.nextLine();
                String[] tempInfo = oneMovie.split(",");

                String m = tempInfo[0];
                //System.out.println(m);
                String a = tempInfo[1];
                //System.out.println(a);
                String c = tempInfo[2];
                //System.out.println(c);
                String t = tempInfo[3];
                //System.out.println(t);
                String b = tempInfo[4];
                //System.out.println(b);
                String g = tempInfo[5];
                //System.out.println(g);

                Movie aMovie = new Movie(m, a, c, t, b, g); //creates Movie obeject
                info.add(aMovie); //adds to the vector
                counter++;

            }

            fileScan.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return info; //vector of all Movie objects from that file
    }

    /**
     * Obtains the size of this collection which is the numer of Movie
     * objects. 
     * 
     * @return counter the amount of Movie objects in this collection
     */
    public int size(){
        return counter; 
    }

    /**
     * Obtains the desired Movie object based on the inputted index.
     * 
     * @param i the index of the movie in this collection
     * 
     * @return the desired Movie object
     */
    public Movie getThisMovie(int i){
        return information.get(i);
    }

    /**
     * allMovies is a method that iterates through the vector of
     * Movie objects and obtains only the names of all the Movies
     * within this collection, storing them in another vector. 
     * 
     * @return allMovies a vector of all the movie names in this collection
     */
    public Vector<String> allMovies(){
        Vector<String> allMovies = new Vector<String> ();

        for(int i = 0; i < information.size(); i++){ 
            //loop thru collection
            Movie oneMovie = information.get(i);
            String movie = oneMovie.getMovieName(); //name of the movie
            
            if(!allMovies.contains(movie)){
                allMovies.add(movie);  //adds name only if not already in the vector
            }            
        }

        return allMovies; //vector with names of all movies in this collection
    }

    /**
     * allActors is a method that iterates through the vector of
     * Movie objects and obtains all the names of the actors that
     * are in this collection.
     * 
     * @return allActors a vector of all the actors in this collection
     */
    public Vector<String> allActors(){
        Vector<String> allActors = new Vector <String> ();

        for(int i = 0; i < information.size(); i++){
            Movie oneMovie = information.get(i);
            String actor = oneMovie.getActor();
            
            if(!allActors.contains(actor)){
                allActors.add(actor);
            }
        }

        return allActors; 
    }

    /**
     * allActorsOneMovie returns all the actors that played in
     * the movie from the parameter. If the movie name is not
     * found in the collection, an empty vector is returned. 
     * 
     * @param movieName the name of the desired movie
     * 
     * @return allActorsOneMovie a vector of all the actors that have played in the movie given as a parameter
     */
    public Vector<String> allActorsOneMovie(String movieName){
        Vector<String> allActorsOneMovie = new Vector<String> ();

        for(int i = 0; i < information.size(); i++){
            Movie oneMovie = information.get(i);
            String name = oneMovie.getMovieName();
            if(movieName.equals(name)){ //movie we want?
                String actor = oneMovie.getActor(); //if yes, get the actor
                if(!allActorsOneMovie.contains(actor)){ 
                    allActorsOneMovie.add(actor); //add actor only if not there already
                }
            }
            //else{
            //System.out.println("Movie not found.");
            //how do we know if clients would want an error message like this?
            //}
        }

        return allActorsOneMovie; //vector of all the actors in that movie
    }

    /**
     * allMoviesOneActor returns all the movies that the inputted
     * actor played in within this collection. If the actor's name is not
     * found in collection, an empty vector is returned.
     * 
     * @param actor the desired actor
     * 
     * @return allMoviesOneActor a vector of all the movies that the inputted actor starred in
     */
    public Vector<String> allMoviesOneActor(String actor){
        Vector<String> allMoviesOneActor = new Vector<String> ();

        for(int i = 0; i < information.size(); i++){
            Movie oneMovie = information.get(i);
            String name =oneMovie.getMovieName();
            String thisActor = oneMovie.getActor();

            if(actor.equals(thisActor)){
                if(!allMoviesOneActor.contains(thisActor)){
                    allMoviesOneActor.add(name);
                }
            }
        }
        
        return allMoviesOneActor;
    }
    
    /**
     * toString() method that allows us to print the Movie objects
     * in this collection. 
     * 
     * @return s string representation of this collection
     */
    public String toString() {
        String s = "A collection of movies: " + "\n";

        for (int i = 0; i < information.size(); i++) {
            s += information.get(i) + "\n";
        }

        return s;
    }

    /**
     * Testing method. 
     */
    public static void main(String[] args) {
        MovieCollection mc1 = new MovieCollection("small_castGender.txt");
        MovieCollection mc2 = new MovieCollection("nextBechdel_castGender.txt");

        // System.out.println(mc1);
        // Vector<String> test1 = mc1.allMovies();
        // System.out.println("Movies: " + test1);
        // System.out.println("Movies in this collection: " + mc1.size());

        // Vector<String> test2 = mc1.allActors();
        // System.out.println("Actors: " + test2);
        // System.out.println("Expect: Alpha movie with Stella actor. Got: \n" + mc1.getThisMovie(3));

        // Vector<String> test3 = mc1.allActorsOneMovie("hello"); //doesn't exist
        // System.out.println("Expect: empty vector. Got: ");
        // System.out.println("All actors in this movie: " + test3);

        // Vector<String> test4 = mc1.allActorsOneMovie("\"Beta\"");
        // System.out.println("Expect: 4 actors in vector. Got: ");
        // System.out.println("All actors in this movie: " + test4);

        // Vector<String> test5 = mc1.allMoviesOneActor("\"Tyler Perry\"");
        // System.out.println("Expect: 3 movies in vector. Got: ");
        // System.out.println("Movies this actor is in: " + test5);
        
        
        System.out.println("\n---Testing with nextBechdel_castGender.txt---\n");
        System.out.println("Actor in question: Jennifer Lawrence");
        Vector<String> test6 = mc2.allMoviesOneActor("\"Jennifer Lawrence\"");
        System.out.println("Movies this actor is in: " + test6);

        System.out.println("-------------------------------");

        System.out.println("Movie in question: The Jungle Book");
        Vector<String> test7 = mc2.allActorsOneMovie("\"The Jungle Book\"");
        System.out.println("Actors in this movie: " + test7);

        //System.out.println("nextBechdel_castGender.txt size: " + mc2.size());

    }
}