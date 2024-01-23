import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Decrypted {
    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void decrypted() throws IOException {

        System.out.println("Введите полный путь к файлу, для его расшифровки:");
        String src = scanner.nextLine();

        System.out.println("Введите ключ шифрования:");
        int key = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
        String dst = scanner.nextLine();

        try (var reader = Files.newBufferedReader(Paths.get(src));
             var writer = Files.newBufferedWriter(Paths.get(dst))
        ) {
            while (reader.ready()) {
                String string = reader.readLine();
                String decryptString = caesarCipher.decrypt(string, key);
                writer.write(decryptString + System.lineSeparator());
            }
        }
        System.out.println("Содержимое файла расшифровано." + System.lineSeparator());
    }
}