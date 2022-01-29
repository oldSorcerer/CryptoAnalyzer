import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class ChoiceThree {

    public static void choiceThree() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите полный путь к файлу, для его расшифровки:");
        String pathEncryptedFile = scanner.nextLine();

        System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
        String pathNotEncryptedFile = scanner.nextLine();

        int key;
        CaesarCipher caesarCipher = new CaesarCipher();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))) {

            StringBuilder stringBuilder = new StringBuilder();

            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                stringBuilder.append(string).append("\n");
            }
            for (int i = 0; i <= caesarCipher.maxSize; i++) {
                String deEncrypt = caesarCipher.deEncrypt(stringBuilder.toString(), i);
                if (isValidText(deEncrypt)) {
                    key = i;
                    bufferedWriter.write(deEncrypt);
                    System.out.println("Содержимое файла расшифровано методом перебора ключей. Ключ расшифровки K = " + key);
                    break;
                }
            }
        }
        System.out.println("Содержимое файла не расшифровано!");
    }

    private static boolean isValidText(String text) {

        boolean flag = false;

        String[] strings = text.split(" ");
        for (String string : strings) {
            if (string.length() > 24) {
                return false;
            }
        }
        if (text.contains(". ") || text.contains(", ") || text.contains("! ")
                || text.contains("/? ") || text.contains(" - ") || text.contains(" — ")) {
            flag = true;
        }

        int stringStart = new Random().nextInt(text.length() / 2);

        System.out.println(text.substring(stringStart, stringStart + (int)Math.sqrt(text.length())));
        System.out.println("Понятин ли вам этот текст? Y/N");

        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();
        if (nextLine.equals("Y") || nextLine.equals("y")) {
            flag = true;
        } else if (nextLine.equals("N") || nextLine.equals("n")){
            return false;
        }
        return flag;
    }
}
