import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptedDecrypted {

    private final CaesarCipher caesar = new CaesarCipher();

    public void encryptedDecrypted(boolean flag) throws IOException {

        Util.writeMessage("Введите путь к файлу для его " + (flag ? "зашифровки" : "расшифровки"));
        String path = Util.readString();

        System.out.println("Введите ключ:");
        int key = Util.readInt();

        Path newPath = Util.buildFileName(path, flag ? "_encrypted" : "_decrypted");

        String content = Files.readString(Path.of(path));
        Files.writeString(newPath, flag ? caesar.encrypt(content, key) : caesar.decrypt(content, key));

        Util.writeMessage("Содержимое файла " + newPath.getFileName() + (flag ? " зашифровано" : " расшифровано") +
                System.lineSeparator());
    }
}
