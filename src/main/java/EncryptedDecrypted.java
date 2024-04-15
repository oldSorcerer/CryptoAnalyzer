
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptedDecrypted {

    private final CaesarCipher caesar = new CaesarCipher();

    @SneakyThrows
    public void encryptedDecrypted(boolean flag) {

        ConsoleHelper.writeMessage("Введите путь к файлу для его " + (flag ? "зашифровки" : "расшифровки"));
        String src = ConsoleHelper.readString();

        System.out.println("Введите ключ:");
        int key = ConsoleHelper.readInt();

        Path dst = ConsoleHelper.buildFileName(src, flag ? "_encrypted" : "_decrypted");

        String content = Files.readString(Path.of(src));
        Files.writeString(dst, flag ? caesar.encrypt(content, key) : caesar.decrypt(content, key));

        ConsoleHelper.writeMessage("Содержимое файла " + dst + (flag ? " зашифровано" : " расшифровано") +
                                   System.lineSeparator());
    }
}