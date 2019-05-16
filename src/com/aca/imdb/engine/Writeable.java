package com.aca.imdb.engine;

import java.io.Serializable;

public interface Writeable <K, V extends Serializable>{
    void writeUnique(K key, V value);
    void write(K key, V value);
}
