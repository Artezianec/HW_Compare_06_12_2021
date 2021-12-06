package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CompareAppl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (args.length != 2) {
            System.out.println("Error with arguments");
            return;
        }
        try (FileInputStream firstFile = new FileInputStream(args[0]); FileInputStream secondFile = new FileInputStream(args[1])) {
            if (firstFile.available() == secondFile.available()) {
                System.out.println("Same files.");
            } else {
                System.out.println("Different files, copy this file? y/n");
                String copy = scanner.nextLine();
                if (copy.equals("y")) {
                    System.out.println("Input new file name:");
                    String copyFileName = scanner.nextLine();
                    try (FileOutputStream fileOutput = new FileOutputStream(copyFileName)) {
                        int bit = secondFile.read();
                        while (bit != -1) {
                            fileOutput.write(bit);
                            bit = secondFile.read();
                        }
                        System.out.println("File copped with name : " + copyFileName);
                    }
                } else {
                    System.out.println("Exit");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
