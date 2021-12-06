package controller;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                System.out.println("Same files.");
                Log("Same files" + args[0] + " and " + args[1]);
            } else {
                System.out.println("Different files, copy this file? y/n");
                Log("Different files" + args[0] + " and " + args[1]);
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    Log("Say yes");
                    Copy(secondFile);
                } else {
                    System.out.println("Exit");
                    Log("Exit program");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Copy(FileInputStream nameSecondFile) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(nameSecondFile,200);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input new file name:");
        String copyFileName = scanner.nextLine();
        Log("New copped file name : " + copyFileName);
        try {
            try (FileOutputStream fileOutput = new FileOutputStream(copyFileName)) {
                int bit = bufferedInputStream.read();
                while (bit != -1) {
                    fileOutput.write(bit);
                    bit = bufferedInputStream.read();
                }
                System.out.println("File copped with name : " + copyFileName);
                Log("File copped without errors");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Log(String string) {
        LocalDateTime localDateTime = LocalDateTime.now();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write(string.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
