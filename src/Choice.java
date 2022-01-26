import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Choice {

    public static void choiceOne() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите полный путь к файлу, для его зашифровки:");
        String pathNotEncryptedFile = scanner.nextLine();

        System.out.println("Введите ключ шифрования:");
        int key = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите полный путь к файлу, в который записать зашифрованый текст:");
        String pathEncryptedFile = scanner.nextLine();

        CaesarCipher caesarCipher = new CaesarCipher();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(pathNotEncryptedFile));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(pathEncryptedFile))
        ) {
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                String encryptString = caesarCipher.encrypt(string, key);
                bufferedWriter.write(encryptString + "\n");
            }
        }
        System.out.println("Содержимое файла зашифровано.");
    }
    public static void choiceTwo() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите полный путь к файлу, для его расшифровки:");
        String pathEncryptedFile = scanner.nextLine();

        System.out.println("Введите ключ шифрования:");
        int key = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
        String pathNotEncryptedFile = scanner.nextLine();

        CaesarCipher caesarCipher = new CaesarCipher();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))
        ) {
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                String deEncryptString = caesarCipher.deEncrypt(string, key);
                bufferedWriter.write(deEncryptString + "\n");
            }
        }
        System.out.println("Содержимое файла расшифровано.");
    }
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
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                for (int i = 0; i <= caesarCipher.maxSize; i++) {
                    String deEncrypt = caesarCipher.deEncrypt(string, i);
                    if (deEncrypt.contains(". ") || deEncrypt.contains(", ")
                            || deEncrypt.contains("! ") || deEncrypt.contains("/? ")) {
                        key = i;
                        break;
                    }
                }
                String deEncryptString = caesarCipher.deEncrypt(string, key);
                bufferedWriter.write(deEncryptString + "\n");
            }
        }
        System.out.println("Содержимое файла расшифровано методом перебора ключей. Ключ расшифровки K = " + key );
    }
    public static void choiceFour() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите полный путь к файлу, для его расшифровки:");
        String pathEncryptedFile = scanner.nextLine();

        System.out.println("Введите полный путь к файлу, для набора статистики:");
        String pathStatisticFile  = scanner.nextLine();

        /*System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
        String pathNotEncryptedFile = scanner.nextLine();*/

        Map<Character, Integer> mapEncryptedFile = new HashMap<>();
        Map<Character, Integer> mapStatisticFile = new HashMap<>();

        fillMapValues(mapEncryptedFile, pathEncryptedFile);
        fillMapValues(mapStatisticFile, pathStatisticFile);

        HashMap<Character, Character> enc = new HashMap<>();

        for (Map.Entry<Character, Integer> entryEncryptedFile : mapEncryptedFile.entrySet()) {
            for (Map.Entry<Character, Integer> entryStatisticFile : mapStatisticFile.entrySet()) {

                Integer valueE = entryEncryptedFile.getValue();
                Integer valueS = entryStatisticFile.getValue();
                if ((valueE * 1.0/valueS >= 0.95) && (valueE * 1.0 / valueS <= 1.05)) {
                    enc.put(entryEncryptedFile.getKey(), entryStatisticFile.getKey());
                }
            }
        }

        System.out.println(enc.toString());











        System.out.println("Содержимое файла расшифровано методом статистического анализа.");

    }


    private static void fillMapValues(Map<Character, Integer> map, String path) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path))) {
            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                stringBuilder.append(string);
            }
            String bigString = stringBuilder.toString();
            for (int i = 0; i < bigString.length(); i++) {
                char charAt = bigString.charAt(i);
                if (!map.containsKey(charAt)) {
                    map.put(charAt, 1);
                } else {
                    map.put(charAt, map.get(charAt) + 1);
                }
            }
            map.replaceAll((k, v) -> (v / bigString.length()) * 10_000);

        }
    }



}
