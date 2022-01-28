import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ChoiceTwo {

    public static void choiceTwo() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите полный путь к файлу, для его расшифровки:");
        String pathEncryptedFile = scanner.nextLine();

        System.out.println("Введите ключ шифрования:");
        int key = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
        String pathNotEncryptedFile = scanner.nextLine();

        CaesarCipher caesarCipher = new CaesarCipher();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))
        ) {
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                String deEncryptString = caesarCipher.deEncrypt(string, key);
                bufferedWriter.write(deEncryptString + "\n");
            }
        }
        System.out.println("Содержимое файла расшифровано.");
    }
}
