import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class DataBase {
    public ArrayList<Movie> dataBase;
    private String FILE_NAME = "movies.txt";

    public DataBase() {
        dataBase = new ArrayList<>();
    }

    public void load() {
        try {
            File movies = new File(FILE_NAME);
            Scanner in = new Scanner(movies);
            while (in.hasNextLine()) {
                String name = removeFirstWord(in.nextLine());
                int year = Integer.parseInt(removeFirstWord(in.nextLine()));
                String dirBy = removeFirstWord(in.nextLine());
                String prdBy = removeFirstWord(in.nextLine());
                String actors = removeFirstWord(in.nextLine());
                Movie nextMovie = new Movie(name, year, makeList(dirBy), makeList(prdBy), makeList(actors));
                dataBase.add(nextMovie);

            }

        } catch (FileNotFoundException e) {
        }

    }
    //helper method to get the line and get only the important information
    public String removeFirstWord(String word) {
        String[] result = word.split(":");
        return result[1].trim();

    }
    //helper method to get the list of information in one group for example all the actors
    public ArrayList<String> makeList(String line) {
        ArrayList<String> result = new ArrayList<>();
        Scanner in = new Scanner(line);
        in.useDelimiter(",");
        while (in.hasNext()) {
            result.add(in.next().trim());
        }
        return result;
    }

    //1
    public void printCount() {
        System.out.println("Total number of Movies is "+dataBase.size());

    }

    //2
    public void printOldestAndRecent(Stream<Movie> list) {
        List<Movie> result = list.sorted((s, t) -> s.getYear() - t.getYear())
                .collect(toList());

        System.out.println("The oldest movie is: " + result.get(0));
        System.out.println("The most recent movie is; " + result.get(result.size() - 1));
    }

    //3
    public void printByOrderOfReleaseForFrankenstein(Stream<Movie> list) {
        list.filter(w -> w.getTitle().contains("Frankenstein"))
                .sorted((s, t) -> s.getYear() - t.getYear())
                .forEach(w -> System.out.println(w.getTitle()));

    }

    //4
    public void printChangeName(Stream<Movie> list) {
         list.filter(w -> w.getTitle().contains("Alien"))
                .map(w -> w.getTitle().replace("Alien", "Star Beast"))
                 .forEach(System.out::println);


    }

    //5
    public Map<String, Set<String>> associatingDirectorsToMovies(Stream<Movie> list) {
        String[] names = {"Wes Craven", "John Carpenter", "Alfred Hitchcock"};
        Map<String, Set<String>> result = list.filter(directoryName(names))
                .collect(Collectors.groupingBy(w->w.getDirectors().get(0),Collectors.mapping(w->w.getTitle(),Collectors.toSet())));

        result.keySet();
        for(String i :result.keySet()){
            System.out.println("Name "+i+"All the movies directed  "+result.get(i));
        }

        return result;
    }
    //high order helper method if give just one name
    public Predicate<Movie> directoryName(String name) {
        return s -> s.getDirectors().contains(name);
    }
    //or an array of strings
    public Predicate<Movie> directoryName(String[] list) {
        return s -> s.getDirectors().contains(list[0]) || s.getDirectors().contains(list[1]) || s.getDirectors().contains(list[2]);
    }



    //6
    public boolean checkIfNameExits(String stageName,List<Movie>list){
        System.out.println("yes");
        return list.stream().anyMatch(w->w.getActors().contains(stageName));

    }
    public ArrayList<Movie> alterName(String stageName, String realName) {
        Stream<Movie> list = dataBase.stream();
        ArrayList<Movie> altterMovies = new ArrayList<Movie>();
        list.filter(w -> w.getActors().contains(stageName))
                .forEach(w -> {
                    ListIterator<String> ln = w.getActors().listIterator();
                    while (ln.hasNext()) {
                        if (ln.next().equals(stageName)) {
                            ln.set(realName);
                        }
                    }
                    altterMovies.add(w);
                });
        System.out.println(altterMovies);

        return altterMovies;

    }

    public void print(Stream<Movie> list) {
        list.filter(w -> w.getDirectors().contains("Woody Allen")).map(w -> w.getActors()).forEach(w -> System.out.println(w));
        System.out.println("=============");
    }



    //7
    public void calculatePercentage() {
        System.out.println("Mia Farrow appeared in "+calculateRatio("Woody Allen", "Mia Farrow")+"%");
        System.out.println("Diane Keaton appeared in "+calculateRatio("Woody Allen", "Diane Keaton")+"%");


    }

    //high order function that helps calculate
    public int calculateRatio(String director, String actorsName) {
        double numberOfMoviesDirected =(double) dataBase.stream().filter(w -> w.getDirectors().contains(director))
                .count();

        double numberOfMoviesActed = (double) dataBase.stream().filter(w -> w.getDirectors().contains(director))
                .filter(w -> w.getActors().contains(actorsName)).count();
        return (int)((numberOfMoviesActed / numberOfMoviesDirected)*100);
    }

}











