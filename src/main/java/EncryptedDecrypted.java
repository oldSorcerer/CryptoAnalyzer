import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptedDecrypted {

    private final CaesarCipher cesarCipher = new CaesarCipher();

    public void encryptedDecrypted(boolean flag) throws IOException {

        ConsoleHelper.writeMessage("Введите путь к файлу для его " + (flag ? "зашифровки" : "расшифровки"));
        String path = ConsoleHelper.readString();

        System.out.println("Введите ключ:");
        int key = ConsoleHelper.readInt();

        Path newPath = PathHelper.buildFileName(path, flag ? "_encrypted" : "_decrypted");

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(newPath)) {
            while (reader.ready()) {
                String string = reader.readLine();
                String encryptedDecrypted = flag ? cesarCipher.encrypt(string, key) : cesarCipher.decrypt(string, key);
                writer.write(encryptedDecrypted + System.lineSeparator());
            }
        }
        ConsoleHelper.writeMessage("Содержимое файла " + newPath.getFileName() + (flag ? " зашифровано" : " расшифровано") +
                System.lineSeparator());
    }
}
