package dev.foobarbaz.Ufo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;

public class UfoDataReader {
    private ArrayList<UfoDatum> data = new ArrayList<>();

    public UfoDataReader(String fileName) {
        System.out.println("Reading UFO data");
        try {
            Reader in = new FileReader(fileName);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                String city = record.get("city");
                String state = record.get("state/province");
                String description = record.get("description");
                String date = record.get("Date_time");
                UfoDatum ufoSighting = new UfoDatum(city, state, description, date);

                data.add(ufoSighting);
            }
        } catch (FileNotFoundException e) {
            System.err.println("UFO file not found");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO Exception");
            System.exit(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UfoDatum> getData() {
        return data;
    }
}
