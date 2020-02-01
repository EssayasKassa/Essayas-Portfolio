# CS2910-2019-A3

Given the Movie database included in this project (as a zip file). Unzip the database and add the resulting txt file and add into an IntelliJ project. 

Open the txt file and ensure you understand the format. Each movie has lines describing: Name, Year, Directed By, Produced By and a List of the Actors

In each case use Streams within your application to the solve the following problems.

Task 0. Read the movie database from `movie.txt` into an ArrayList of Movie objects (or your own Movie aggregate class).

Task 1.create a method that prints out to screen the count of the total number of movies in the database

Task 2. Create a method that takes as input a Stream of all of the movies and prints out the year of the oldest movie and year of the most recent movie.

Task 3. Create a method that takes as input a Stream of all movies and prints out in the order of their release (earliest years first) the names of all movies with "Frankenstein" in their title.

Task 4. Did you know the original name for the Alien movie was Star Beast? Create a method that takes as input a Stream of all movies and changes the name of any movie containing the word "Alien" such that "Alien" becomes "Star Beast" in the title. Print out the names of any movies that had their titles changed out to screen.

Task 5. Create a method that takes as input a Stream of all movies and returns a Map associating the following directors with the names of the movies that they have directed. Directors: [Wes Craven, John Carpenter, Alfred Hitchcock] print this Map out to screen before returning.

Task 6. Did you know Michael Caine's real name is Maurice Micklewhite. Create a method that asks for two names from the user. One is the an actor's stage name and the other is the actor's real name. Your method will then create a stream of all movies and alter any movie so that the actor's name is changed from their stage name to their real name. For example Michael Caine's name in each of the movies in which he is an actor is changed to Maurice Micklewhite. Collect any movies which you alter into an ArrayList of movies. Print this ArrayList to screen.

Task 7. Of all of the movie's in the database that Woody Allen directed, what percentage has the actor Mia Farrow appeared in? What percentage has Diane Keaton appeared in? Use higher order function(s) to help with this task. Your method should allow the user to input a director and an actor name and your program will output the percentage of that Director's movies that the given actor appeared in. 

Task 8. Create a simple driver program that the grader may use to execute any of the above tasks. 


See below a basic Movie class which you are welcome to use to help with this assignment. 

```java
public class Movie 
{
   private String title;
   private int year;
   private List<String> directors;
   private List<String> producers;
   private List<String> actors;
		
   public Movie(String title, int year, List<String> directors,
         List<String> producers, List<String> actors) 
   {
      this.title = title;
      this.year = year;
      this.directors = directors;
      this.producers = producers;
      this.actors = actors;
   }

   public List<String> getActors() 
   {
      return actors;
   }

   public List<String> getDirectors() 
   {
      return directors;
   }

   public List<String> getProducers() 
   {
      return producers;
   }

   public String getTitle() 
   {
      return title;
   }

   public int getYear() 
   {
      return year;
   }
	
   public String toString() 
   {
      return "Name: " + title 
         + "\nYear: " + year
         + "\nDirected by: " + directors
         + "\nProduced by: " + producers
         + "\nActors: " + actors + "\n";
   }
}
```

# Grading: 

Code Readability: 3 points - use of methods, variables and proper naming conventions

Code Design: 2 points - there isn't too much code design here. but long methods should be avoided

Program Functionality - does your program produce the desired results. 

# Submission:
Check your code into the Github repository before 11:59pm on October 25, 2019


