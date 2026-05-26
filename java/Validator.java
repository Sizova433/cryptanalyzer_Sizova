package Vall;
import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {

    public static void validate(String pathStr) {
        Path path = Path.of(pathStr);

        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Файл не найден по указанному пути: " + pathStr);
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("Указанный путь ведет не к файлу: " + pathStr);
        }
    }
}
