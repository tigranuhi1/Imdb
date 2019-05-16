package com.aca.imdb.engine;

import java.io.Serializable;
import java.util.List;

public interface Readable <K, V extends Serializable>{
    V read(K key);
    List<V> readAll(K key);
}
