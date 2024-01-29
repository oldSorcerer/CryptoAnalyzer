import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Bruteforce {

    private final CaesarCipher caesarCipher = new CaesarCipher();
    private static final int MAX_LENGTH_WORD = 28;

    public void bruteforce() throws IOException {

        ConsoleHelper.writeMessage("Введите полный путь к файлу, для его расшифровки:");
        String src = ConsoleHelper.readString();

        Path dst = PathHelper.buildFileName(src, "_bruteforce");

        try (BufferedReader reader = Files.newBufferedReader(Path.of(src));
             BufferedWriter writer = Files.newBufferedWriter(dst)) {

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
                    ConsoleHelper.writeMessage("Содержимое файла расшифровано, методом перебора ключей. Ключ расшифровки K = " + i);
                    break;
                }
            }
        }
    }

    private boolean isValidateText(String text) throws IOException {

        boolean isValidate = false;

        for (String word : text.split(" ")) {
            if (word.length() > MAX_LENGTH_WORD) {
                return false;
            }
        }

        if (text.contains(". ") || text.contains(", ") || text.contains("! ") || text.contains("? ")) {
//            substring.matches("[.,:!?]+\s");
            isValidate = true;
        }
        while (isValidate) {
            int indexStart = new Random().nextInt(text.length() / 2);
            String substring = text.substring(indexStart, indexStart + (int) Math.sqrt(text.length()));
            ConsoleHelper.writeMessage(substring + System.lineSeparator() + "Понятен ли вам этот текст? Y/N");

            String answer = ConsoleHelper.readString();

            if (answer.equalsIgnoreCase("Y")) {
                return true;
            } else if (answer.equalsIgnoreCase("N")) {
                isValidate = false;
            } else {
                ConsoleHelper.writeMessage("выберете только между Y/N");
            }
        }
        return false;
    }
}