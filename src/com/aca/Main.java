package com.aca;

import com.aca.imdb.Imdb;
import com.aca.imdb.ui.models.UIType;

public class Main {
    public static void main(String[] args) {
        Imdb imdb = new Imdb(UIType.TERMINAL);
        imdb.start();
    }
}
