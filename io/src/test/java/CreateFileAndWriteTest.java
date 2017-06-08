import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

class CreateFileAndWriteTest {

    @Test
    @DisplayName("JavaSE6-style file creation")
    @SneakyThrows
    void javaSE6StyleFileCreation() {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;
        try {
            // создание потокового объекта (открытие потока)
            fileWriter = new FileWriter("text.txt");

            // придание потоковому объекту требуемых свойств
            bufferedWriter = new BufferedWriter(fileWriter);

            // Декарирование классом с нужными высокоуровневыми операциями
            printWriter = new PrintWriter(bufferedWriter);

            // работа с потоком через потоковый объект
            printWriter.println("I'm a sentence in a text-file.");
        } finally {
            if (printWriter != null)
                try {
                    printWriter.close(); // закрытие потока
                } catch (Exception e1) {
                    try {
                        bufferedWriter.close();
                    } catch (Exception e2) {
                        fileWriter.close();
                    }
                }
            else if (bufferedWriter != null)
                try {
                    bufferedWriter.close();
                } catch (Exception e) {
                    fileWriter.close();
                }
            else if (fileWriter != null)
                fileWriter.close();
        }
    }

    @Test
    @DisplayName("JavaSE7-style file creation")
    @SneakyThrows
    void JavaSE7StyleFileCreation() {
        try (val fileWriter = new FileWriter("text.txt");
             val bufferedWriter = new BufferedWriter(fileWriter);
             val printWriter = new PrintWriter(bufferedWriter)) {
            // работа с потоком через потоковый объект
            printWriter.println("I'm a sentence in a text-file.");
        }
    }
}
