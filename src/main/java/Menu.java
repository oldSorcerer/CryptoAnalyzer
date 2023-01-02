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

                    Для выхода из программы введите exit""");

            String answer = reader.readLine();

            switch (answer) {
                case ("1") -> new ChoiceOne().choiceOne();
                case ("2") -> new ChoiceTwo().choiceTwo();
                case ("3") -> new ChoiceThree().choiceThree();
                case ("4") -> new ChoiceFour().choiceFour();
                case ("exit") -> {return;}
            }
        }
    }
}