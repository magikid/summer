package dev.foobarbaz.Witch;

import java.text.DateFormatSymbols;

public class WitchDatum {
    private String city;
    private String state;
    private String name;
    private int monthAccused;

    public WitchDatum(String city, String state, String name, String monthAccused) {
        this.city = city;
        this.state = state;
        this.name = name;
        this.monthAccused = Integer.parseInt(monthAccused);
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public int getMonthAccused() {
        return monthAccused;
    }

    @Override
    public String toString() {
        return "Witch accused named " + name + " in " + new DateFormatSymbols().getMonths()[monthAccused-1] + " in " + city + "," + state;
    }
}
