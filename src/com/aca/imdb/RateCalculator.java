package com.aca.imdb;

public class RateCalculator {
    public static Double calculateRate(Double rating, Integer ratersCount) {
        return rating / ratersCount;
    }
}


