package org.aharon.dictionary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TouroDictionary dict = new TouroDictionary();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dictionary ready!");
        System.out.println("Enter a word to lookup (or 'quit' to exit):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            String result = dict.lookup(input);
            System.out.println(result);
        }

        scanner.close();
    }
}