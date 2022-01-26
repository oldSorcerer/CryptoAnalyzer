
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("\n"+ "Выберете действие, введя его номер: \n" +
                    "1. Зашифровать текст в файле с помощью ключа. \n" +
                    "2. Расшифровать текст в файле с помощью ключа. \n" +
                    "3. Подобрать ключ к зашифрованому тексу в файле (brute force). \n" +
                    "4. Расшифровать текст в файле методом статического перебора. \n" +
                    "\n" +
                    "Для выхода из программы введите exit");

            String stringSwitch = reader.readLine();

            switch (stringSwitch) {
                case ("1") -> Choice.choiceOne();
                case ("2") -> Choice.choiceTwo();
                case ("3") -> Choice.choiceThree();
                case ("4") -> Choice.choiceFour();
            }
            if (stringSwitch.equals("exit")) {
                break;
            }
        }
    }
}
