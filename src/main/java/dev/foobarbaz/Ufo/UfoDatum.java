package dev.foobarbaz.Ufo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UfoDatum {
    private String city;
    private String state;
    private String description;
    private DateFormat df;
    private Date date;

    UfoDatum(String city, String state, String description, String date) throws ParseException {
        this.city = city;
        this.state = state;
        this.description = description.replace("&#44", ",")
                .replace("&#39", "'")
                .replace("&quot;", "\"");

        this.df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        this.date = df.parse(date);
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "UFO Sighting on " + df.format(date) + " in " + city + "," + state + ": " + description;
    }

    public String getDescription() {
        return description;
    }
}
