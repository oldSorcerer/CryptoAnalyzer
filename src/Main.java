import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("\n"+ "Выберете действие, введя его номер: \n" +
                    "0. Воспользоваться Терморектальным криптоанализатором. \n" +
                    "1. Зашифровать текст в файле с помощью ключа. \n" +
                    "2. Расшифровать текст в файле с помощью ключа. \n" +
                    "3. Подобрать ключ к зашифрованому тексу в файле (brute force). \n" +
                    "4. Расшифровать текст в файле методом статического перебора. \n" +
                    "\n" +
                    "Для выхода из программы введите exit");

            String stringSwitch = reader.readLine();

            switch (stringSwitch) {
                case ("0") -> ChoiceZero.choiceZero();
                case ("1") -> ChoiceOne.choiceOne();
                case ("2") -> ChoiceTwo.choiceTwo();
                case ("3") -> ChoiceThree.choiceThree();
                case ("4") -> ChoiceFour.choiceFour();
            }
            if (stringSwitch.equals("exit")) {
                break;
            }
        }
    }
}
