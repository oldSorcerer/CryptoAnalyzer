import lombok.SneakyThrows;

import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Parsing {

    @SneakyThrows
    public void parse() {

        ConsoleHelper.writeMessage("Введите полный путь к файлу, для его расшифровки:");
        String src = ConsoleHelper.readString();

        ConsoleHelper.writeMessage("Введите полный путь к файлу, для набора статистики:");
        String pathStatistic = ConsoleHelper.readString();

        Path dst = ConsoleHelper.buildFileName(src, "_parsing");

        List<Map.Entry<String, Long>> listEncrypted = convertToList(src);
        List<Map.Entry<String, Long>> listStatistic = convertToList(pathStatistic);

        if (listEncrypted.size() <= listStatistic.size()) {

            Map<String, String> decrypted = IntStream.range(0, listEncrypted.size())
                    .boxed()
                    .collect(Collectors.toMap(i -> listEncrypted.get(i).getKey(), i -> listStatistic.get(i).getKey()));

            Files.writeString(dst, Arrays.stream(Files.readString(Path.of(src)).split(""))
                    .map(decrypted::get)
                    .collect(Collectors.joining()));

//            Map<Character, Character> decrypted = new HashMap<>();
//            for (int i = 0; i < listEncrypted.size(); i++) {
//                decrypted.put(listEncrypted.get(i).getKey(), listStatistic.get(i).getKey());
//            }
//
//            String content = Files.readString(Path.of(src));
//            StringBuilder builder = new StringBuilder();
//            for (char encryptedChar : content.toCharArray()) {
//                builder.append(decrypted.get(encryptedChar));
//            }
//            Files.writeString(dst, builder);

            ConsoleHelper.writeMessage("Содержимое файла расшифровано методом статистического анализа." + System.lineSeparator());

        } else {
            ConsoleHelper.writeMessage("Размер файла статистики недостаточен для расшифровки, необходим файл большей длины чем зашифрованный" + System.lineSeparator());
        }
    }

    @SneakyThrows
    private List<Map.Entry<String, Long>> convertToList(String path) {

        return Arrays.stream(Files.readString(Path.of(path)).split(""))
                .collect(Collectors.groupingBy(str -> str, Collectors.counting())).entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .toList();

//        Map<Character, Integer> map = new HashMap<>();
//
//        for (char aChar : Files.readString(Path.of(path)).toCharArray()) {
//            map.merge(aChar, 1, Integer::sum);
//        }
//        return map.entrySet().stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()).toList();
    }
}