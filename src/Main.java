import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<String> countryArrayList = new ArrayList<>();

        HashMap<String, ArrayList<String>> countryHashMap = new HashMap<>();

        System.out.println();
        System.out.println("Welcome to the country listing app!");
        System.out.println("------------------------------------------------------------------------------------");

        //read and parse countries.txt into hashmap
        File f = new File("countries.txt");
        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] columns = line.split("\\|");
            Country country = new Country(columns[1]);
            countryArrayList.add(country.name);
        }

        //prompt user to type a letter. be sure to include the exception

        System.out.println("To create a list of all countries beginning with a certain letter, type that letter.");

        String userLetter;
        Scanner consoleScanner = new Scanner(System.in);
        userLetter = consoleScanner.nextLine().toLowerCase();


        ArrayList<String> resultArrayList = new ArrayList<>();
        //make condition where letter input matches key to countries
        for (String name : countryArrayList) {
            if (name.startsWith(userLetter)) {
                resultArrayList.add(name);
            }
        }

        countryHashMap.put(userLetter, resultArrayList);

        //write that hashmap to a new file and name it "X_countries.txt" where X is the key letter

        //write new file with FileWriter class
        File f1 = new File(userLetter.toUpperCase() + "_countries.txt");
        FileWriter fw = new FileWriter(f1);
        fw.write("This file contains all countries that begin with the letter " + userLetter.toUpperCase() + "!\n");
        fw.write(String.valueOf(countryHashMap.values()));

        fw.close();

        System.out.println("Your new file has been created with the name " + userLetter.toUpperCase() + "_countries.txt!");
    }
}
