import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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

        System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
        String pathNotEncryptedFile = scanner.nextLine();

        Map<Character, Integer> mapEncryptedFile = new HashMap<>();
        Map<Character, Integer> mapStatisticFile = new HashMap<>();

        List<Map.Entry<Character, Integer>> listEncryptedFile = fillMapValues(mapEncryptedFile, pathEncryptedFile);
        List<Map.Entry<Character, Integer>> listStatisticFile = fillMapValues(mapStatisticFile, pathStatisticFile);

        HashMap<Character, Character> mapDeEncrypted = new HashMap<>();

        if (listEncryptedFile.size() <= listStatisticFile.size() ) {
            for (int i = 0; i < listEncryptedFile.size(); i++) {
                mapDeEncrypted.put(listEncryptedFile.get(i).getKey(), listStatisticFile.get(i).getKey());
            }
            try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
                BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))) {
                while (bufferedReader.ready()) {
                    String string = bufferedReader.readLine();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < string.length(); i++) {
                        char encryptedChar = string.charAt(i);
                        Character deEncryptedChar = mapDeEncrypted.get(encryptedChar);
                        stringBuilder.append(deEncryptedChar);
                    }
                    bufferedWriter.write(stringBuilder + "\n");
                }
            }
            System.out.println("Содержимое файла расшифровано методом статистического анализа.");
        } else {
            System.out.println("Размер файла статистики недостаточен для расшифровки, необходим файл большей длины чем зашифрованный");
        }
    }

    private static List<Map.Entry<Character, Integer>> fillMapValues(Map<Character, Integer> map, String path) throws IOException {

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

            List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());

            Comparator<Map.Entry<Character, Integer>> comparator = Map.Entry.comparingByValue();

            list.sort(comparator.reversed());

            return list;
        }
    }
}
