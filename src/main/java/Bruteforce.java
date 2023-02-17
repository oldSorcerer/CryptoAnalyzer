import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Bruteforce {
    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void bruteforce() throws IOException {

        System.out.println("Введите полный путь к файлу, для его расшифровки:");
        String path = scanner.nextLine();

        Path bruteforce = PathHelper.buildFileName(path, "_bruteforce");

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(bruteforce)) {

            StringBuilder builder = new StringBuilder();
            List<String> list = new ArrayList<>();

            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string).append(System.lineSeparator());
                list.add(string);
            }
            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String decrypt = caesarCipher.decrypt(builder.toString(), i);
                if (isValidateText(decrypt)) {
                    for (String string : list) {
                        String encrypt = caesarCipher.decrypt(string, i);
                        writer.write(encrypt + System.lineSeparator());
                    }
                    System.out.println("Содержимое файла расшифровано методом перебора ключей. Ключ расшифровки K = " + i);
                    break;
                }
            }
        }
    }

    private boolean isValidateText(String text) {

        boolean isValidate = false;

        int indexStart = new Random().nextInt(text.length() / 2);
        String substring = text.substring(indexStart, indexStart + (int) Math.sqrt(text.length()));

        for (String word : substring.split(" ")) {
            if (word.length() > 28) {
                return false;
            }
        }

        if (substring.contains(". ") || substring.contains(", ") || substring.contains("! ") || substring.contains("? ")) {
//            substring.matches("[.,:!?]+\s");
            isValidate = true;
        }
        while (isValidate) {
            System.out.println(substring + System.lineSeparator() + "Понятин ли вам этот текст? Y/N");

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