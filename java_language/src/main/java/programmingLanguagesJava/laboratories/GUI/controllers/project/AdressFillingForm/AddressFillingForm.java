/**
 * Контроллер для взаимодействия с адресом
 */

package programmingLanguagesJava.laboratories.GUI.controllers.project.AdressFillingForm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import programmingLanguagesJava.laboratories.GUI.controllers.BaseController;
import programmingLanguagesJava.laboratories.GUI.controllers.project.AdressFillingForm.fileChooserInteraction.FileChooserController;
import programmingLanguagesJava.laboratories.GUI.controllers.project.AdressFillingForm.observers.FormObserver;
import programmingLanguagesJava.laboratories.GUI.controllers.project.AdressFillingForm.strategy.*;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.stream.Stream;
// Запускает все абстракции по иерархии от AddressFillingForm до strategy включительно
/**
 * Контроллер для окна для ввода данных от оператора
 */
public class AddressFillingForm extends BaseController {


    @FXML
    private Button downloadFile, addHuman, createDocument, addDataToDB;
    @FXML
    private TextField addressField, fullNameField;
    @FXML
    private ComboBox<String> combobox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        var fileChooser = new FileChooserController();
        var jsonData = new HashMap<String, String>();


        Stream.of(
                new TextFieldAddControllerActionFillingForm(fullNameField, addHuman, combobox),
                new FileChooserActionFillingForm(fileChooser, downloadFile),
                new AddDataToDatabaseActionFillingForm(jsonData, addDataToDB),
                new CreateDocumentActionFillingForm(fileChooser, jsonData, createDocument, addressField, combobox)
        ).forEach(ActionFillingForm::execute);


        new FormObserver(addressField, combobox, Arrays.asList(createDocument, addDataToDB)).listen();
    }
}