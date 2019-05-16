package com.aca.imdb.engine.dbmanagement;

import com.aca.imdb.engine.filemanagement.Repository;
import com.aca.imdb.engine.user.User;

public class UsersDB<T extends User> {
    Repository<Long, T> repository;
    Repository<String, Long> namesRepository;

    public UsersDB(Class<T> user) {
        repository = new Repository<>(user);
        namesRepository = new Repository<>(user.getSimpleName() + " names.txt");
    }

    public void add(T user) {
        repository.writeUnique(user.getId(), user);
        namesRepository.writeUnique(user.getUserName(), user.getId());
    }

    public T get(String name) {
        Long id = namesRepository.read(name);
        if (id == null) {
            return null;
        }
        return get(id);
    }

    public T get(Long id) {
        return repository.read(id);
    }

    public boolean exists(String name) {
        T user = get(name);
        return !(user == null);
    }
}
