package search;

import search.strategy.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class SearchTemplate {
    private InvertedIndex index;
    private Scanner input;

    public SearchTemplate(String dataFile, Scanner input) throws FileNotFoundException{
        this.index = new InvertedIndex();
        loadEntries(dataFile);
        this.input = input;
    }

    public void run() {
        boolean done = false;
        while (!done) {
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");

            switch (input.nextLine()) {
                case "1":
                    askQuery();
                    break;
                case "2":
                    printEntries();
                    break;
                case "0":
                    System.out.println("Bye!");
                    done = true;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
    }

    private void loadEntries(String dataFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(dataFile));
        while (scanner.hasNext()) {
            index.addRecord(scanner.nextLine());
        }
    }

    private void askQuery() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strStrategy = input.nextLine();
        SearchStrategy strategy;
        switch (strStrategy) {
            case "ALL":
                strategy = new AllStrategy();
                break;
            case "ANY":
                strategy = new AnyStrategy();
                break;
            case "NONE":
                strategy = new NoneStrategy();
                break;
            default:
                System.out.println("Unknown strategy.");
                return;
        }

        System.out.println("Enter a name or email to search all suitable people:");
        String term = input.nextLine();
        List<String> results = index.search(term, strategy);

        if (results.isEmpty()) {
            System.out.println("No matching people found.");
            return;
        }

        System.out.println("Found people:");
        for (String result: results) {
            System.out.println(result);
        }
    }

    private void printEntries() {
        System.out.println("=== List of people ===");
        for (String r:index.records()) {
            System.out.println(r);
        }
    }
}
