import java.io.*;

public class Menu {

    public static void main(String[] args) throws IOException {

        while (true) {
            Util.writeMessage("""
                    Выберете действие, введя его номер:\s
                    1. Зашифровать текст в файле с помощью ключа.\s
                    2. Расшифровать текст в файле с помощью ключа.\s
                    3. Подобрать ключ к зашифрованному тексу в файле (brute force).\s
                    4. Расшифровать текст в файле методом статического перебора.\s
                    5. Выхода из программы""");

            String answer = Util.readString();

            switch (answer) {
                case ("1") -> new EncryptedDecrypted().encryptedDecrypted(true);
                case ("2") -> new EncryptedDecrypted().encryptedDecrypted(false);
                case ("3") -> new Bruteforce().bruteforce();
                case ("4") -> new Parsing().parse();
                case ("5") -> {return;}
            }
        }
    }
}