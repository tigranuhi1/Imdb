package com.aca.imdb.engine.movie;

import com.aca.imdb.engine.modules.MoviePeopleType;
import com.aca.imdb.engine.moviepeople.MoviePeople;
import com.aca.imdb.engine.moviepeople.MoviePeopleFactory;

import java.time.LocalDate;
import java.util.Arrays;

public class MovieTest {
    public static void main(String[] args) {
        MovieTest test = new MovieTest();
        Movie movie = test.createMovie();
        test.testTitle(movie);
        test.testDescription(movie);
        test.testPremiereDate(movie);
        test.testActors(movie);
        test.testDirectors(movie);
        test.testWriters(movie);
        test.testRate(movie);
    }

    private void testRate(Movie movie) {
        movie.rate(5d);
        movie.rate(4d);
        movie.rate(3d);

        assert movie.getRating().equals(4d);
    }

    private Movie createMovie() {
        MoviePeopleFactory factory = new MoviePeopleFactory();
        Movie movie = new Drama("Truman show",
                "An insurance salesman discovers his whole life is actually a reality TV show.",
                LocalDate.of(1998, 6, 5));
        return movie;
    }

    private void testTitle(Movie movie){
        assert movie.getTitle().equals("Truman show");
    }

    private void testDescription(Movie movie){
        assert movie.getDescription().equals("An insurance salesman discovers his whole life is actually a reality TV show.");
    }

    private void testPremiereDate(Movie movie){
        assert movie.getPremierDate().equals(LocalDate.of(1998,6,5));
    }

    private void testActors(Movie movie){
        MoviePeopleFactory factory = new MoviePeopleFactory();
        MoviePeople actor = factory.createMoviePeople(MoviePeopleType.ACTOR,
                "Jim Carrey",
                "James Eugene Carrey was born on January 17, 1962 in Newmarket, Ontario, Canada");
        movie.addActor(actor);

        actor = factory.createMoviePeople(MoviePeopleType.ACTOR,
                "Ed Harris",
                "Born\tNovember 28, 1950 in Tenafly, New Jersey, USA");
        movie.addActor(actor);

        assert Arrays.toString(movie.getStaff().get("Actors").toArray()).equals("[Name:\tJim Carrey\n" +
                "Biography:\tJames Eugene Carrey was born on January 17, 1962 in Newmarket, Ontario, Canada\n" +
                ", Name:\tEd Harris\n" +
                "Biography:\tBorn\tNovember 28, 1950 in Tenafly, New Jersey, USA\n" +
                "]");
    }

    private void testDirectors(Movie movie){
        MoviePeopleFactory factory = new MoviePeopleFactory();
        MoviePeople director = factory.createMoviePeople(MoviePeopleType.DIRECTOR,
                "Peter Weir",
                "Born	August 21, 1944 in Sydney, New South Wales, Australia");
        movie.addDirector(director);
        assert Arrays.toString(movie.getStaff().get("Directors").toArray()).equals("[Name:\tPeter Weir\n" +
                "Biography:\tBorn\tAugust 21, 1944 in Sydney, New South Wales, Australia\n" +
                "]");
    }

    private void testWriters(Movie movie){
        MoviePeopleFactory factory = new MoviePeopleFactory();

        MoviePeople writer = factory.createMoviePeople(MoviePeopleType.WRITER,
                "Andrew Niccol",
                "Born\tJune 10, 1964 in Paraparaumu, New Zealand");
        movie.addWriter(writer);

        assert Arrays.toString(movie.getStaff().get("Writers").toArray()).equals("[Name:\tAndrew Niccol\n" +
                "Biography:\tBorn\tJune 10, 1964 in Paraparaumu, New Zealand\n" +
                "]");
    }
}
