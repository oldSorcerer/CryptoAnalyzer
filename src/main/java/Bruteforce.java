import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Bruteforce {

    private final CaesarCipher caesar = new CaesarCipher();
    private static final int MAX_LENGTH_WORD = 28;

    public void bruteforce() throws IOException {

        Util.writeMessage("Введите полный путь к файлу, для его расшифровки:");
        String path = Util.readString();

        Path bruteforce = Util.buildFileName(path, "_bruteforce");

        String content = Files.readString(Path.of(path));
        for (int i = 0; i < caesar.alphabetLength(); i++) {
            String decrypt = caesar.decrypt(content, i);
            if (isValidateText(decrypt)) {
                Files.writeString(bruteforce, decrypt);
                Util.writeMessage("Содержимое файла расшифровано, методом перебора ключей. Ключ расшифровки K = " + i);
                break;
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
            Util.writeMessage(substring + System.lineSeparator() + "Понятен ли вам этот текст? Y/N");

            String answer = Util.readString();

            if (answer.equalsIgnoreCase("Y")) {
                return true;
            } else if (answer.equalsIgnoreCase("N")) {
                isValidate = false;
            } else {
                Util.writeMessage("выберете только между Y/N");
            }
        }
        return false;
    }
}