import lombok.SneakyThrows;

import java.nio.file.*;
import java.util.*;

public class Parsing {

    private final Map<Character, Integer> encrypted = new HashMap<>();
    private final Map<Character, Integer> statistic = new HashMap<>();
    private final Map<Character, Character> decrypted = new HashMap<>();

    @SneakyThrows
    public void parse()  {

        Util.writeMessage("Введите полный путь к файлу, для его расшифровки:");
        String src = Util.readString();

        Util.writeMessage("Введите полный путь к файлу, для набора статистики:");
        String pathStatistic = Util.readString();

        Path dst = Util.buildFileName(src, "_parsing");

        List<Map.Entry<Character, Integer>> listEncrypted = fillMapAndConvertToList(encrypted, src);
        List<Map.Entry<Character, Integer>> listStatistic = fillMapAndConvertToList(statistic, pathStatistic);

        if (listEncrypted.size() <= listStatistic.size()) {
            for (int i = 0; i < listEncrypted.size(); i++) {
                decrypted.put(listEncrypted.get(i).getKey(), listStatistic.get(i).getKey());
            }
        } else {
            Util.writeMessage("Размер файла статистики недостаточен для расшифровки, необходим файл большей длины чем зашифрованный" + System.lineSeparator());
        }

        String content = Files.readString(Path.of(src));
        StringBuilder builder = new StringBuilder();
        for (char encryptedChar : content.toCharArray()) {
            builder.append(decrypted.get(encryptedChar));
        }
        Files.writeString(dst, builder);

        Util.writeMessage("Содержимое файла расшифровано методом статистического анализа." + System.lineSeparator());
    }

    @SneakyThrows
    private List<Map.Entry<Character, Integer>> fillMapAndConvertToList(Map<Character, Integer> map, String path) {

        for (char aChar : Files.readString(Path.of(path)).toCharArray()) {
            map.merge(aChar, 1, Integer::sum);
        }
        return map.entrySet().stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()).toList();
    }
}