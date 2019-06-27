package dev.foobarbaz.Witch;

import dev.foobarbaz.Ufo.UfoDatum;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class WitchDataReader {
    private ArrayList<WitchDatum> data = new ArrayList<>();

    public WitchDataReader(String fileName) {
        System.out.println("Reading witch data");
        try {
            Reader in = new FileReader(fileName);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                String name = record.get("Accused Witch").trim();
                String city = record.get("Residence").trim();
                String monthAccused = record.get("Month of Accusation").trim();
                WitchDatum witchAccused = new WitchDatum(city, "MA", name, monthAccused);

                data.add(witchAccused);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Witch file not found");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO Exception");
            System.exit(1);
        }
    }

    public ArrayList<WitchDatum> getData() {
        return data;
    }
}
