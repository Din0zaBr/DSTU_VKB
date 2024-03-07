/**
 * Контроллер для взаимодействия с адресом
 */

package programmingLanguagesJava.laboratories.GUI.controllers.project.AdressFillingForm;

import com.sothawo.mapjfx.MapView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import programmingLanguagesJava.laboratories.GUI.config.ComboboxConfigurator;
import programmingLanguagesJava.laboratories.GUI.controllers.BaseController;
import programmingLanguagesJava.laboratories.GUI.controllers.project.AdressFillingForm.addingNames.TextFieldAddController;
import programmingLanguagesJava.laboratories.GUI.controllers.project.AdressFillingForm.fileChooserInteraction.PhotoAutoCadFileChooser;
import programmingLanguagesJava.laboratories.GUI.controllers.project.AdressFillingForm.processingEventsOnMap.OpenStreetMap;
import programmingLanguagesJava.laboratories.GUI.controllers.project.AdressFillingForm.searchEngineField.TextFieldSearchController;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuAddress extends BaseController {

    @FXML private MapView mapView;
    @FXML private Button downloadFile, startSearch, addHuman, createDocument;
    @FXML private TextField addressField, fullNameField;
    @FXML private ComboBox<String> combobox;
    private final ComboboxConfigurator comboboxConfigurator = new ComboboxConfigurator();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        setUpMap();
        setUpFileChooser();
        setUpSearchEngine();
        setUpFullName();
        comboboxConfigurator.defaultConfiguration(combobox);
    }

    /**
     * Здесь запускается event, который обрабатывает изначальную инициализацию карты, добавляет event маркера
     */
    private void setUpMap() {
        var openStreetMapInstance = new OpenStreetMap(mapView);
        openStreetMapInstance.setAddressField(addressField);
        openStreetMapInstance.event();
    }

    /**
     * Здесь запускается event, который обрабатывает поиск через ввод данных с TextField.
     */
    private void setUpSearchEngine() {
        var textFieldSearchEngine = new TextFieldSearchController(addressField);
        textFieldSearchEngine.setMapView(mapView);
        buttonConfigurator.setupButtonEvent(startSearch, event -> textFieldSearchEngine.event());
    }


    /**
     * Здесь запускается event, который обрабатывает кнопку добавки файлов
     */
    private void setUpFileChooser() {
        var fileChooser = new PhotoAutoCadFileChooser();
        buttonConfigurator.setupButtonEvent(downloadFile, fileChooser::event);
    }

    /**
     * Здесь запускается event, который обрабатывает добавления ФИО в TextField.
     * После добавления хотя бы одного ФИО включается combobox.
     */
    private void setUpFullName() {
        var textFieldNamePerson = new TextFieldAddController(fullNameField);
        buttonConfigurator.setupButtonEvent(addHuman, event -> {
            textFieldNamePerson.event();
            comboboxConfigurator.setupComboboxEvent(combobox, textFieldNamePerson.getPersons());
        });
    }


    /**
     * Здесь описывается логика создания документа, который мы будем обрабатывать.
     */
    private void setUpCreateDocument() {

    }


}