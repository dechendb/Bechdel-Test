import javafoundations.AdjListsGraph;
import javafoundations.LinkedStack;
import java.util.Scanner;
import java.util.Vector;
import java.util.LinkedList;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * HollywoodApp class uses an undirected graph G which represents the relationship 
 * between movies and actors. Each actor and movie is considered a
 * vertex and if actor "played in the movie" this would be an edge.
 * Also, there are only edges from actors to movies. 
 * Outside of the constructor, toString, and main method, there are
 * three other methods. 
 *
 * @author Dechen, Lorena, and Josie
 * @version May 2, 2023
 */
public class HollywoodApp<T> {
    //instance variables
    protected AdjListsGraph<String> graph;
    MovieCollection movieAndInfo;

    /**
     * Constructor which initializes its instance variables and
     * calls the methods actorsAndMovies() and playedInMovie().
     * Creates a new graph and a MovieCollection based on the inputted file.
     * 
     * @param file the file to be read in
     */
    public HollywoodApp(String file){
        graph = new AdjListsGraph<String> ();
        movieAndInfo = new MovieCollection(file);
        this.actorsAndMovies();
        this.playedInMovie();

    }

    /**
     * actorsAndMovies is a method that uses methods created in the
     * MovieCollection class to obtain all movies and actors to add
     * them as vertices for this graph.
     * 
     */
    public void actorsAndMovies(){
        Vector<String> movies = movieAndInfo.allMovies();
        for(int i = 0; i < movies.size(); i++){
            graph.addVertex(movies.get(i));
            /*
             * adding every movie from information which is
             * a vector of all the movies from the given file
             */
        }

        Vector<String> actors = movieAndInfo.allActors();
        for(int i = 0; i < actors.size(); i++){
            graph.addVertex(actors.get(i));
        }
    }

    /**
     * playedInMovie is a method that adds edges if the actor
     * "played in the movie." Does so by looping through each movie
     * and obtaining each actor who starred in that movie, then adds
     * an edge to reflect that relationship. 
     *
     */
    public void playedInMovie(){
        Vector<String> allMovies = movieAndInfo.allMovies();

        for(int i = 0; i < allMovies.size(); i++){
            String oneMovie = allMovies.get(i);
            Vector<String> allActors = movieAndInfo.allActorsOneMovie(oneMovie);
            for(int j = 0; j < allActors.size(); j++){
                String oneActor = allActors.get(j);
                graph.addEdge(oneMovie, oneActor);
            }
        }
    }

    /**
     * moviesAndBechdel is a method that finds all movies that have
     * over 48% women in its cast.
     * Also, assigns a bechdel like value for each movie which represents whether or
     * not they have passed this test; 0 meaning did not pass and 1 meaning passed.
     * Calls the method bechdelValue to store the values, order based on when a movie is read in. 
     * 
     * @return moviesAndBechdel a vector of all the movies that have
     * over 48% women in its cast
     */
    public Vector<String> moviesAndBechdel(){
        //vector to keep track of all the different types of movies in the given collection
        Vector<String> diffMovies = movieAndInfo.allMovies();

        // array to keep track of all the female actors per Movie
        int[] femaleCounter = new int[diffMovies.size()];
        //array to keep track of the bechdel value per Movie
        int[] bechdelValue = new int[diffMovies.size()];

        // array to keep track of all the actors per Movie
        int[] allCounter = new int[diffMovies.size()];

        //vector of movies that pass the bechdel test
        Vector<String> moviesAndBechdel = new Vector<String> ();

        for (int i = 0; i< movieAndInfo.size(); i++){
            Movie oneMovie = movieAndInfo.getThisMovie(i);
            String name = oneMovie.getMovieName();
            String gender = oneMovie.getGender();
            int movieindex= diffMovies.indexOf(name);
            allCounter[movieindex]++;
            if (gender.equals("\"Female\"")){
                femaleCounter[movieindex]++;
            }
        }

        for (int i = 0; i< diffMovies.size(); i++){
            double female = femaleCounter[i];
            double all = allCounter[i];
            double bechdel = female/all;
            //System.out.println(bechdel); //checking if calculation working
            if (bechdel >0.48){
                moviesAndBechdel.add(diffMovies.elementAt(i));
                bechdelValue[i] = 1;
            }
            else{
                bechdelValue[i] = 0;
            }
        }
        
        
        
        //To see the generated values for each movie, uncomment this. 
        
        for(int i : bechdelValue){
            System.out.println(i);
        }
        
        

        return moviesAndBechdel; 
    }
    
    /**
     * byActor is a method that obtains all the movies that the inputted actor
     * played in by calling the method from MovieCollection, allMoviesOneActor().
     * 
     * @param actor the desired actor
     * 
     * @return allMoviesOneActor a vector of all the movies the inputted actor starred in
     */
    public Vector<String> byActor(String actor){
        Vector<String> allMoviesOneActor = movieAndInfo.allMoviesOneActor(actor);

        return allMoviesOneActor;
    }

    /**
     * byMovie is a method that obtains all actors that play in the inputted movie.
     * Does so by calling the allActorsOneMovie method from MovieCollection.
     * 
     * @param movie the name of the desired movie
     * 
     * @return byMovie a vector of all the actors in the inputted movie
     */
    public Vector<String> byMovie(String movie){
        Vector<String> byMovie = movieAndInfo.allActorsOneMovie(movie);

        return byMovie;
    }

    /**
     * toString() method that calls the saveTGF() method and saves
     * the graph as a .tgf file. 
     * 
     * @return s String representing the vertices and edges in this graph
     */
    public String toString(){
        String s ="";

        graph.saveTGF("testing.tgf");

        s += graph; //prints graph information

        return s; 
    }

    /**
     * Tester method. 
     */
    public static void main(){
        HollywoodApp smallCast = new HollywoodApp("small_castGender.txt");
        HollywoodApp nextBechdelCast = new HollywoodApp("nextBechdel_castGender.txt");

        //System.out.println(smallCast);
        //System.out.println(nextBechdelCast);
        
        

        System.out.println("\n---Testing with nextBechdel_castGender.txt---\n");
        System.out.println("Actor in question: Jennifer Lawrence");
        Vector<String> actorTest = nextBechdelCast.byActor("\"Jennifer Lawrence\"");
        System.out.println("Movies this actor is in: " + actorTest);

        System.out.println("-------------------------------");

        System.out.println("Movie in question: The Jungle Book");
        Vector<String> movieTest = nextBechdelCast.byMovie("\"The Jungle Book\"");
        System.out.println("Actors in this movie: " + movieTest);

        System.out.println("\n---Testing moviesAndBechdel()---");
        System.out.println("smallCast movies that pass bechdel test: \n" + smallCast.moviesAndBechdel());
        
        
        System.out.println("\nnextBechdelCast actors that pass: \n" + nextBechdelCast.moviesAndBechdel()); 
    }
}