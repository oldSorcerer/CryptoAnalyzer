import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ChoiceThree {

    public static void choiceThree() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите полный путь к файлу, для его расшифровки:");
        String pathEncryptedFile = scanner.nextLine();

        System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
        String pathNotEncryptedFile = scanner.nextLine();

        CaesarCipher caesarCipher = new CaesarCipher();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))) {

            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<String> listStrings = new ArrayList<>();

            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                stringBuilder.append(string);
                listStrings.add(string);
            }
            for (int i = 0; i < caesarCipher.maxSize; i++) {
                String deEncrypt = caesarCipher.deEncrypt(stringBuilder.toString(), i);
                if (isValidText(deEncrypt)) {
                    for (String listString : listStrings) {
                        String encrypt = caesarCipher.deEncrypt(listString, i);
                        bufferedWriter.write(encrypt + System.lineSeparator());
                    }
                    System.out.println("Содержимое файла расшифровано методом перебора ключей. Ключ расшифровки K = " + i);
                    break;
                }
            }
        }
    }

    private static boolean isValidText(String text) {

        boolean flag = false;

        int stringStart = new Random().nextInt(text.length() / 2);
        String substring = text.substring(stringStart, stringStart + (int) Math.sqrt(text.length()));

        String[] strings = substring.split(" ");
        for (String string : strings) {
            if (string.length() > 24) {
                return false;
            }
        }
        if (substring.contains(". ") || substring.contains(", ") || substring.contains("! ") || substring.contains("? ")) {
            flag = true;
        }
        if (flag) {
            System.out.println(substring);
            System.out.println("Понятин ли вам этот текст? Y/N");

            Scanner scanner = new Scanner(System.in);
            String nextLine = scanner.nextLine();

            if (nextLine.equalsIgnoreCase("Y")) {
                return true;
            } else if (nextLine.equalsIgnoreCase("N")) {
                flag = false;
            }
        }
        return flag;
    }
}