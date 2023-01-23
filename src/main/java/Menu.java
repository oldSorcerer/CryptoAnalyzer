import java.io.*;

public class Menu {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("""
                    Выберете действие, введя его номер:\s
                    1. Зашифровать текст в файле с помощью ключа.\s
                    2. Расшифровать текст в файле с помощью ключа.\s
                    3. Подобрать ключ к зашифрованному тексу в файле (brute force).\s
                    4. Расшифровать текст в файле методом статического перебора.\s
                    5. Выхода из программы""");

            String answer = reader.readLine();

            switch (answer) {
                case ("1") -> new Encrypted().encrypted();
                case ("2") -> new Decrypted().decrypted();
                case ("3") -> new ChoiceThree().choiceThree();
                case ("4") -> new Parsing().parse();
                case ("5") -> {return;}
            }
        }
    }
}