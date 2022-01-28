import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ChoiceThree {

    public static void choiceThree() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите полный путь к файлу, для его расшифровки:");
        String pathEncryptedFile = scanner.nextLine();

        System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
        String pathNotEncryptedFile = scanner.nextLine();

        int key = 0;
        CaesarCipher caesarCipher = new CaesarCipher();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))) {

            StringBuilder stringBuilder = new StringBuilder();

            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                stringBuilder.append(string).append("\n");
            }
            // подумать как собрать статистику для правельных подборов ключа

            for (int i = 0; i <= caesarCipher.maxSize; i++) {
                String deEncrypt = caesarCipher.deEncrypt(stringBuilder.toString(), i);
                if (deEncrypt.contains(". ") || deEncrypt.contains(", ") || deEncrypt.contains("! ")
                        || deEncrypt.contains("/? ")) {
                    System.out.println(deEncrypt);
                    System.out.println("Похоже на то что нужно ? Y/N");
                    String nextLine = scanner.nextLine();
                    if (nextLine.equals("Y") || nextLine.equals("y")) {
                        key = i;
                        break;
                    } else if (nextLine.equals("N") || nextLine.equals("n")){
                        i++;
                    }
                }
                System.out.println(deEncrypt);
            }
            String deEncryptString = caesarCipher.deEncrypt(stringBuilder.toString(), key);
            bufferedWriter.write(deEncryptString + "\n");
        }
        System.out.println("Содержимое файла расшифровано методом перебора ключей. Ключ расшифровки K = " + key );
    }

}
