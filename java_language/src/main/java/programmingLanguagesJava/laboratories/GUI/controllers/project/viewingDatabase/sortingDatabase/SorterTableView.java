package programmingLanguagesJava.laboratories.GUI.controllers.project.viewingDatabase.sortingDatabase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import lombok.RequiredArgsConstructor;
import programmingLanguagesJava.laboratories.GUI.config.ButtonConfigurator;
import programmingLanguagesJava.laboratories.GUI.controllers.project.database.utils.PersonInfo;
import programmingLanguagesJava.laboratories.GUI.controllers.project.viewingDatabase.ElementDatabaseView;

import java.util.Comparator;
import java.util.function.Function;

@RequiredArgsConstructor
public class SorterTableView implements ElementDatabaseView {

    private final ButtonConfigurator buttonConfigurator = ButtonConfigurator.getInstance();

    private final RadioButton lastNameRadioButton, firstNameRadioButton, patronymicRadioButton;
    private final ObservableList<PersonInfo> personInfoList;


    @Override
    public void event() {
        setupSortingRadioButton(lastNameRadioButton, personInfoList, PersonInfo::getLastName);
        setupSortingRadioButton(firstNameRadioButton, personInfoList, PersonInfo::getFirstName);
        setupSortingRadioButton(patronymicRadioButton, personInfoList, PersonInfo::getPatronymic);
    }

    private void setupSortingRadioButton(RadioButton radioButton,
                                         ObservableList<PersonInfo> data,
                                         Function<PersonInfo, String> valueExtractor) {

        buttonConfigurator.setupButtonEvent(
                radioButton,
                event -> FXCollections.sort(data, Comparator.comparing(valueExtractor, String.CASE_INSENSITIVE_ORDER))
        );
    }



}
