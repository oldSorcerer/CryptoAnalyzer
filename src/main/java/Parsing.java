import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Parsing {

    private final Map<Character, Character> decrypted = new HashMap<>();

    public void parse() throws IOException {

        Util.writeMessage("Введите полный путь к файлу, для его расшифровки:");
        String pathEncrypted = Util.readString();

        Util.writeMessage("Введите полный путь к файлу, для набора статистики:");
        String pathStatistic = Util.readString();

        Path parsing = Util.buildFileName(pathEncrypted, "_parsing");

        List<Map.Entry<Character, Integer>> listEncrypted = mapToList(fillMapWithValues(pathEncrypted));
        List<Map.Entry<Character, Integer>> listStatistic = mapToList(fillMapWithValues(pathStatistic));

        if (listEncrypted.size() <= listStatistic.size()) {
            for (int i = 0; i < listEncrypted.size(); i++) {
                decrypted.put(listEncrypted.get(i).getKey(), listStatistic.get(i).getKey());
            }
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncrypted));
                 BufferedWriter writer = Files.newBufferedWriter(parsing)) {
                while (reader.ready()) {
                    StringBuilder builder = new StringBuilder();
                    String string = reader.readLine();
                    for (char encryptedChar : string.toCharArray()) {
                        Character decryptedChar = decrypted.get(encryptedChar);
                        builder.append(decryptedChar);
                    }
                    writer.write(builder.toString());
                    writer.newLine();
                }
            }
            Util.writeMessage("Содержимое файла расшифровано методом статистического анализа." + System.lineSeparator());
        } else {
            Util.writeMessage("Размер файла статистики недостаточен для расшифровки, необходим файл большей длины чем зашифрованный" + System.lineSeparator());
        }
    }

    private Map<Character, Integer> fillMapWithValues(String path) throws IOException {

        Map<Character, Integer> map = new HashMap<>();

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
