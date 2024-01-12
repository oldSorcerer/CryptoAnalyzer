import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class CaesarCipher {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            ".,\":!? +-*/\\@#$%^&(){}[];'|`~=_©«»—…" + "0123456789" + "\u00A0" + (char)10 + (char)13;

    public String encrypt(String message, int key) {
        StringBuilder builder = new StringBuilder();
        for (char aChar : message.toCharArray()) {
            int index = ALPHABET.indexOf(aChar);
            if (index >= 0) {
                int newIndex = (index + key) % ALPHABET.length();
                char charAt = ALPHABET.charAt(newIndex < 0 ? ALPHABET.length() + newIndex : newIndex);
                builder.append(charAt);
            }
        }
        return builder.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, key * (-1));
    }

    public int alphabetLength() {
        return ALPHABET.length();
    }

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/alphabet.properties", StandardCharsets.UTF_8));


        String alphabetEn = properties.getProperty("alphabet.en");
        String alphabetRu = properties.getProperty("alphabet.ru");
        String property = properties.getProperty("alphabet.symbols");
        String property2 = properties.getProperty("alphabet.digit");
        System.out.println(alphabetEn + alphabetEn.toUpperCase() + alphabetRu + alphabetRu.toUpperCase() + property + property2);
    }
}