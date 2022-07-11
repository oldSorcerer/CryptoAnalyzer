import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ChoiceTwo {
    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void choiceTwo() throws IOException {

        System.out.println("Введите полный путь к файлу, для его расшифровки:");
        String pathEncryptedFile = scanner.nextLine();

        System.out.println("Введите ключ шифрования:");
        int key = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
        String pathNotEncryptedFile = scanner.nextLine();

        try (var reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
             var writer = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))
        ) {
            while (reader.ready()) {
                String string = reader.readLine();
                String deEncryptString = caesarCipher.deEncrypt(string, key);
                writer.write(deEncryptString + System.lineSeparator());
            }
        }
        System.out.println("Содержимое файла расшифровано.");
    }
}