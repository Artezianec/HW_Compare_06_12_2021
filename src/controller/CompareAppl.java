package controller;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class CompareAppl {
    private static final File file = new File("log.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (args.length != 2) {
            System.out.println("Error with arguments");
            return;
        }
        try (FileInputStream firstFile = new FileInputStream(args[0]); FileInputStream secondFile = new FileInputStream(args[1])) {
            if (firstFile.available() == secondFile.available()) {
                int f1 = 0, f2 = 0;
                while (f1 != -1 && f2 != -1) {
                    f1 = firstFile.read();
                    f2 = secondFile.read();
                }
                {
                    System.out.println("Same files.");
                    Log("Same files" + args[0] + " and " + args[1]);
                }
            } else {
                System.out.println("Different files, copy this file? y/n");
                Log("Different files" + args[0] + " and " + args[1]);
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    Log("Say yes");
                    Copy(firstFile);
                } else {
                    System.out.println("Exit");
                    Log("Exit program");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Copy(FileInputStream nameFirstFile) {
        //todo BufferedInputStream bufferedInputStream = new BufferedInputStream(nameFirstFile, 200);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input new file name:");
        String copyFileName = scanner.nextLine();
        Log("New copped file name : " + copyFileName);
        try {
            try (FileOutputStream fileOutput = new FileOutputStream(copyFileName)) {
                Date dateBefore = new Date();
                int bit = nameFirstFile.read();
                while (bit != -1) {
                    fileOutput.write(bit);
                    bit = nameFirstFile.read();
                }
                Date dateAfter = new Date();
                System.out.println("File copped with name : " + copyFileName);
                System.out.println(dateAfter.getTime() - dateBefore.getTime());
                Log("File copped without errors");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Log(String string) {
        //todo LocalDateTime localDateTime = LocalDateTime.now();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(string.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
