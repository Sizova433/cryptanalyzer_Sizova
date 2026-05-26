package service;
import validator.Validator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {

    public static void processFile(String srcPath, String destPath, int key) throws Exception {
        Validator.validate(srcPath);

        // try-with-resources автоматически закроет потоки даже при возникновении ошибок
        try (BufferedReader reader = Files.newBufferedReader(Path.of(srcPath));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(destPath))) {

            int symbol;
            while ((symbol = reader.read()) != -1) {
                char encryptedChar = CaesarCipher.encrypt((char) symbol, key);
                writer.write(encryptedChar);
            }
        }
    }
}
