import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConsoleHelper {

    private static final BufferedReader CONSOLE = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return CONSOLE.readLine();
    }

    public static int readInt() throws IOException {
        return Integer.parseInt(readString().trim());
    }

    public static Path buildFileName(String path, String suffix) {
        Path fullPath = Path.of(path);
        Path parent = fullPath.getParent();
        String fileName = fullPath.getFileName().toString();
        String newFileName;
        if (fileName.contains(".")) {
            int index = fileName.lastIndexOf(".");
            newFileName = fileName.substring(0, index) + suffix + fileName.substring(index);
        } else {
            newFileName = fileName + suffix;
        }
        return parent.resolve(newFileName);
    }
}
