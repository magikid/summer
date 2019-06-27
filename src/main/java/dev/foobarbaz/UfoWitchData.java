package dev.foobarbaz;

import dev.foobarbaz.Ufo.UfoDatum;
import dev.foobarbaz.Witch.WitchDatum;

import java.security.cert.CollectionCertStoreParameters;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class UfoWitchData {
    private List<UfoDatum> ufoDatumList;
    private List<WitchDatum> witchDatumList;
    public enum UserSelection {UFO,WITCH};

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
    }
}
