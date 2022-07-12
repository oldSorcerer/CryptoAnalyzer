import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ChoiceThree {
    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void choiceThree() throws IOException {

        System.out.println("Введите полный путь к файлу, для его расшифровки:");
        String pathEncryptedFile = scanner.nextLine();

        System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
        String pathNotEncryptedFile = scanner.nextLine();

        try (var reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
             var writer = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))) {

            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<String> listStrings = new ArrayList<>();

            while (reader.ready()) {
                String string = reader.readLine();
                stringBuilder.append(string);
                listStrings.add(string);
            }
            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String deEncrypt = caesarCipher.deEncrypt(stringBuilder.toString(), i);
                if (isValidateText(deEncrypt)) {
                    for (String listString : listStrings) {
                        String encrypt = caesarCipher.deEncrypt(listString, i);
                        writer.write(encrypt + System.lineSeparator());
                    }
                    System.out.println("Содержимое файла расшифровано методом перебора ключей. Ключ расшифровки K = " + i);
                    break;
                }
            }
        }
    }

    private static boolean isValidateText(String text) {

        boolean isValidate = false;

        int indexStart = new Random().nextInt(text.length() / 2);
        String substring = text.substring(indexStart, indexStart + (int) Math.sqrt(text.length()));

        String[] words = substring.split(" ");
        for (String word : words) {
            if (word.length() > 24) {
                return false;
            }
        }
        if (substring.contains(". ") || substring.contains(", ") || substring.contains("! ") || substring.contains("? ")) {
            isValidate = true;
        }
        while (isValidate) {
            System.out.println(substring);
            System.out.println("Понятин ли вам этот текст? Y/N");

            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("Y")) {
                return true;
            } else if (answer.equalsIgnoreCase("N")) {
                isValidate = false;
            } else {
                System.out.println("выберете только между Y/N");
            }
        }
        return false;
    }
}