import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PathHelper {

    public static Path buildFileName(String path, String suffix) {
        Path fullPath = Path.of(path);
        Path directory = fullPath.getParent();
        String fileName = fullPath.getFileName().toString();
        String newFileName;
        if (fileName.contains(".")) {
            int index = fileName.lastIndexOf(".");
            newFileName = fileName.substring(0, index) + suffix + fileName.substring(index);
        } else {
            newFileName = fileName + suffix;
        }
        return directory.resolve(Path.of(newFileName));
    }
}