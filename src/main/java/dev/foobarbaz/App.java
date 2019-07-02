/*
 * summer
 * Copyright (C) 2019 cjone0102
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package dev.foobarbaz;

import dev.foobarbaz.Ufo.UfoDataReader;
import dev.foobarbaz.Ufo.UfoDatum;
import dev.foobarbaz.UfoWitchData;
import dev.foobarbaz.Witch.WitchDataReader;
import dev.foobarbaz.Witch.WitchDatum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        System.out.println("Starting up");
        List<UfoDatum> ufoData = new UfoDataReader("ufo_sighting_data.csv").getData();
        List<WitchDatum> witchData = new WitchDataReader("Accused-Witches-Data-set.csv").getData();
        UfoWitchData.UserSelection selection = getUserInput();
        UfoWitchData merged = new UfoWitchData(ufoData, witchData, selection);
    }

    private static UfoWitchData.UserSelection getUserInput() {
        System.out.println("UfoWitch Data Collator");
        System.out.println("Copyright (C) 2019 cjone0102");
        System.out.println("This program is free software: you can redistribute it and/or modify");
        System.out.println("it under the terms of the GNU General Public License as published by");
        System.out.println("the Free Software Foundation, either version 3 of the License, or");
        System.out.println("(at your option) any later version.");
        System.out.println();
        System.out.println("This program is distributed in the hope that it will be useful,");
        System.out.println("but WITHOUT ANY WARRANTY; without even the implied warranty of");
        System.out.println("MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the");
        System.out.println("GNU General Public License for more details.");
        System.out.println();
        System.out.println("You should have received a copy of the GNU General Public License");
        System.out.println("along with this program.  If not, see <http://www.gnu.org/licenses/>.");
        System.out.println();
        System.out.println("Enter the number corresponding the the data you would like:");
        System.out.println("1. Witches accused in the same month a UFO was spotted");
        System.out.println("2. UFO sightings in the same city where a witch was accused");
        System.out.println("3. Exit");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                return UfoWitchData.UserSelection.WITCH;
            case 2:
                return UfoWitchData.UserSelection.UFO;
            default:
                System.out.println("Exiting");
                System.exit(0);
        }

        return UfoWitchData.UserSelection.UFO;
    }
}
