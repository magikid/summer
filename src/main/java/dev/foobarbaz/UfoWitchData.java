package dev.foobarbaz;

import dev.foobarbaz.Ufo.UfoDatum;
import dev.foobarbaz.Witch.WitchDatum;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class UfoWitchData {
    private List<UfoDatum> ufoDatumList;
    private List<WitchDatum> witchDatumList;
    public enum UserSelection {UFO,WITCH};
    private static final String OUTPUT_LOCATION = "output.csv";

    public UfoWitchData(List<UfoDatum> ufoDatumList, List<WitchDatum> witchDatumList, UserSelection selection) {
        this.ufoDatumList = ufoDatumList;
        this.witchDatumList = witchDatumList;
        this.printData(selection);
    }

    private void printData(UserSelection selection) {
        if (selection == UserSelection.UFO) {
            printUfoData();
        } else if (selection == UserSelection.WITCH) {
            printWitchData();
        } else {
            printUfoData();
        }
    }

    private void printUfoData() {
        var witchCities = witchDatumList.stream()
                .map(WitchDatum::getCity)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        var filteredUfo = ufoDatumList.stream()
                .filter(u -> u.getState().equals("ma"))
                .filter(ufoDatum -> {
                    return witchCities.contains(ufoDatum.getCity());
                })
                .collect(Collectors.toList());

        System.out.println("UFO sighted in the same town a witch was accused in");
        System.out.println("========================================================");
        System.out.println("Number found: " + filteredUfo.toArray().length);
        filteredUfo.forEach(System.out::println);
        System.out.println("Writing data");
        writeUfoData(filteredUfo);
    }

    private void printWitchData() {
        var ufoMonths = ufoDatumList.stream()
                .map(u -> u.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue())
                .collect(Collectors.toList());
        var witchMonths = witchDatumList.stream()
                .filter(w -> {
                    return ufoMonths.contains(w.getMonthAccused());
                })
                .collect(Collectors.toList());

        System.out.println("Witches accused in the same month that a UFO was sighted");
        System.out.println("========================================================");
        System.out.println("Number found: " + witchMonths.toArray().length);
        witchMonths.forEach(System.out::println);
        System.out.println("Writing data");
        writeWitchData(witchMonths);
    }

    private void writeWitchData(Collection<WitchDatum> data) {
        try {
            var writer = Files.newBufferedWriter(Paths.get(OUTPUT_LOCATION));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("city", "month", "name"));
            for (var datum : data) {
                csvPrinter.printRecord(datum.getCity(), datum.getMonthAccused(), datum.getName());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            System.err.println("Error writing output");
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    private void writeUfoData(Collection<UfoDatum> data) {
        try {
            var writer = Files.newBufferedWriter(Paths.get(OUTPUT_LOCATION));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("city", "desc", "date"));
            for (var datum : data) {
                csvPrinter.printRecord(datum.getCity(), datum.getDescription(), datum.getDate());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            System.err.println("Error writing output");
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
