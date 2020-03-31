package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static String dataFile;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        processArgs(args);
        SearchTemplate search = new SearchTemplate(dataFile, input);
        search.run();
    }

    private static void processArgs(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "--data":
                    dataFile = args[i+1];
                    break;
                default:
                    System.out.println("Error: Unknown option '" + args[i] + "'");
                    return;
            }
        }
    }
}
