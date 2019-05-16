package com.aca.imdb.engine.dbmanagement;

import com.aca.imdb.engine.filemanagement.Repository;
import com.aca.imdb.engine.moviepeople.MoviePeople;

public class MoviePeopleDB<T extends MoviePeople> {
    Repository<Long, T> moviePeople;
    Repository<String, Long> moviePeopleNames;

    public MoviePeopleDB(Class<T> moviePeople) {
        this.moviePeople = new Repository<>(moviePeople);
        moviePeopleNames = new Repository<>(moviePeople.getSimpleName() + " names.txt");
    }

    public void add(T moviePeople) {
        this.moviePeople.writeUnique(moviePeople.getId(), moviePeople);
        moviePeopleNames.writeUnique(moviePeople.getName(), moviePeople.getId());
    }

    public T get(String name) {
        Long id = moviePeopleNames.read(name);
        if (id == null) {
            return null;
        }
        return get(id);
    }

    public T get(Long id) {
        return moviePeople.read(id);
    }

    public boolean existsObject(String name) {
        T moviePeople = get(name);
        return !(moviePeople == null);
    }
}
