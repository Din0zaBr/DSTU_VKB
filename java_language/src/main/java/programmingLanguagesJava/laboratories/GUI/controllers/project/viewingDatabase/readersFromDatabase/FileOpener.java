package programmingLanguagesJava.laboratories.GUI.controllers.project.viewingDatabase.readersFromDatabase;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import programmingLanguagesJava.laboratories.GUI.config.ButtonConfigurator;
import programmingLanguagesJava.laboratories.GUI.controllers.project.database.utils.PersonInfo;
import programmingLanguagesJava.laboratories.GUI.controllers.project.viewingDatabase.ElementDatabaseView;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;

/**
 * Абстрактный класс, который позволяет настроить открытия файлов из БД, расширяет возможности открытия файлов.
 */
@RequiredArgsConstructor
public abstract class FileOpener implements ElementDatabaseView {

    protected final TableView<PersonInfo> customersTableView;
    protected final Button button;
    protected final ButtonConfigurator buttonConfigurator = ButtonConfigurator.getInstance();


    protected void openFile(byte[] fileContent, String extension) {
        try {
            Path tempPath = Files.createTempFile("document", extension);
            File tempFile = tempPath.toFile();
            tempFile.deleteOnExit();

            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(fileContent);
            }

            Desktop.getDesktop().open(tempFile);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при открытии файла", e);
        }
    }
}
