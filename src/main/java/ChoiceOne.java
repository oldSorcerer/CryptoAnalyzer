package main.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ChoiceOne {
    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void choiceOne() throws IOException {

        System.out.println("Введите полный путь к файлу, для его зашифровки:");
        String pathNotEncryptedFile = scanner.nextLine();

        System.out.println("Введите ключ шифрования:");
        int key = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите полный путь к файлу, в который записать зашифрованый текст:");
        String pathEncryptedFile = scanner.nextLine();

        try (var reader = Files.newBufferedReader(Paths.get(pathNotEncryptedFile));
             var writer = Files.newBufferedWriter(Paths.get(pathEncryptedFile))
        ) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encryptString = caesarCipher.encrypt(string, key);
                writer.write(encryptString + System.lineSeparator());
            }
        }
        System.out.println("Содержимое файла зашифровано.");
    }
}
