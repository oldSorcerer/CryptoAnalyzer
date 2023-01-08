import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class ChoiceFour {
    private final Scanner scanner = new Scanner(System.in);

    private final Map<Character, Integer> mapEncryptedFile = new HashMap<>();
    private final Map<Character, Integer> mapStatisticFile = new HashMap<>();
    private final Map<Character, Character> mapDeEncrypted = new HashMap<>();

    public void choiceFour() throws IOException {

        System.out.println("Введите полный путь к файлу, для его расшифровки:");
        String pathEncryptedFile = scanner.nextLine();

        System.out.println("Введите полный путь к файлу, для набора статистики:");
        String pathStatisticFile = scanner.nextLine();

        System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
        String pathNotEncryptedFile = scanner.nextLine();

        List<Map.Entry<Character, Integer>> listEncryptedFile = mapToList(fillMapValues(mapEncryptedFile, pathEncryptedFile));
        List<Map.Entry<Character, Integer>> listStatisticFile = mapToList(fillMapValues(mapStatisticFile, pathStatisticFile));

        if (listEncryptedFile.size() <= listStatisticFile.size()) {
            for (int i = 0; i < listEncryptedFile.size(); i++) {
                mapDeEncrypted.put(listEncryptedFile.get(i).getKey(), listStatisticFile.get(i).getKey());
            }
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
                 BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))) { // decryption
                while (reader.ready()) {
                    StringBuilder builder = new StringBuilder();
                    String string = reader.readLine();
                    for (char encryptedChar : string.toCharArray()) {
                        Character deEncryptedChar = mapDeEncrypted.get(encryptedChar);
                        builder.append(deEncryptedChar);
                    }
                    writer.write(builder + System.lineSeparator());
                }
            }
            System.out.println("Содержимое файла расшифровано методом статистического анализа." + System.lineSeparator());
        } else {
            System.out.println("Размер файла статистики недостаточен для расшифровки, необходим файл большей длины чем зашифрованный" + System.lineSeparator());
        }
    }

    private Map<Character, Integer> fillMapValues(Map<Character, Integer> map, String path) throws IOException {

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string);
            }
            for (char aChar : builder.toString().toCharArray()) {
                if (!map.containsKey(aChar)) {
                    map.put(aChar, 1);
                } else {
                    map.put(aChar, map.get(aChar) + 1);
                } //map.merge(aChar, 1, Integer::sum);
            }
            return map;
        }
    }

    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());

        Comparator<Map.Entry<Character, Integer>> comparator = Map.Entry.comparingByValue();

        list.sort(comparator.reversed());

        return list;
//        map.entrySet().stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()).forEach(System.out::println);
    }
}
