import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Bruteforce {

    private final CaesarCipher caesar = new CaesarCipher();
    private static final int MAX_WORD_LENGTH = 28;

    @SneakyThrows
    public void bruteforce()  {

        Util.writeMessage("Введите полный путь к файлу, для его расшифровки:");
        String src = Util.readString();

        Path dst = Util.buildFileName(src, "_bruteforce");

        String content = Files.readString(Path.of(src));
        for (int i = 0; i < caesar.alphabetLength(); i++) {
            String decrypt = caesar.decrypt(content, i);
            if (isValidateText(decrypt)) {
                Files.writeString(dst, decrypt);
                Util.writeMessage("Содержимое расшифровано и сохранено в файл " + dst.getFileName());
                Util.writeMessage("Ключ расшифровки: " + i + System.lineSeparator());
                break;
            }
        }
    }

    private boolean isValidateText(String text) {

        boolean isValidate = false;

        for (String word : text.split(" ")) {
            if (word.length() > MAX_WORD_LENGTH) {
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