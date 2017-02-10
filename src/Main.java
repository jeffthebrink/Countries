import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<String> countryArrayList = new ArrayList<>();
        HashMap<String, ArrayList<String>> countryHashMap = new HashMap<>();

        System.out.println();
        System.out.println("Welcome to the country listing app!");
        System.out.println("------------------------------------------------------------------------------------");

        File f = new File("countries.txt");
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] columns = line.split("\\|");
            Country country = new Country(columns[1]);
            countryArrayList.add(country.name);
        }
        scanner.close();

        System.out.println("To create a list of all countries beginning with a certain letter, type that letter.");

        String userLetter;
        Scanner consoleScanner = new Scanner(System.in);
        userLetter = consoleScanner.nextLine().toLowerCase();

        ArrayList<String> resultArrayList = new ArrayList<>();
        if (userLetter.matches("^[a-z]$")) {
            if (userLetter.length() == 1) {
                for (String name : countryArrayList) {
                    if (name.startsWith(userLetter)) {
                        resultArrayList.add(name);
                    }
                }
            } else if (userLetter.length() > 1) {
                throw new Exception("Your input must only be one letter.");
            } else if (userLetter.isEmpty()) {
                throw new Exception("You must input one letter!");
            }
        }
        else throw new Exception("You must enter a letter!");

        countryHashMap.put(userLetter, resultArrayList);

        File f1 = new File(userLetter.toUpperCase() + "_countries.txt");
        FileWriter fw = new FileWriter(f1);
        fw.write("This file contains all countries that begin with the letter " + userLetter.toUpperCase() + "!\n");
        fw.write(String.valueOf(countryHashMap.values()));
        if (userLetter.equalsIgnoreCase("x")) {
            fw.write("There are no countries that begin with the letter X!");
        }
        fw.close();

        System.out.println("Your new file has been created with the name " + userLetter.toUpperCase() + "_countries.txt!");
    }
}