package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class Main {
    
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        //Option 1 is basic
        //Option 2 has support for displaying multiple columns
        Option2();
        sc.close();
    }

    private static void Option1()
    {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/resources/VideosUpdated.csv")).build()) {
            String[] nextLine = reader.readNext();
            for (int i = 0; i < nextLine.length; i++) {
                System.out.println("Column #" + i + "\t" + nextLine[i]);
            }
            System.out.println("----------------------------------");
            int ReadColumn = Integer.parseInt(UserInput1("Enter Column # To Read: "));
            int count = 2;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                System.out.println("Line " + count + ":\t" + nextLine[ReadColumn]);
                count++;
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }
        
    private static String UserInput1(String x)
    {
        System.out.print(x);
        String Input = sc.nextLine();
        return Input;
    }

    private static void Option2()
    {
        System.out.println("Enter directory of file to read: ");
        String file = sc.nextLine();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(file)).build()) {
            String[] nextLine = reader.readNext();
            for (int i = 0; i < nextLine.length; i++) {
                System.out.println("Column #" + i + "\t" + nextLine[i]);
            }
            System.out.println("----------------------------------");
            System.out.print("Enter Column(s) # To Read: ");
            String Input = sc.nextLine();
            String[] InputArray = Input.split(" ");
            int count = 2;
            while ((nextLine = reader.readNext()) != null) {
                System.out.print("Line " + count + ": \t");
                for (String item : InputArray) {
                    System.out.print(nextLine[Integer.parseInt(item)] + " \t");
                }
                System.out.println();
                count++;
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }
}