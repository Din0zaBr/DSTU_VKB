/*
Точка запуска программы, здесь создаются окна нашего приложения (Scene) и происходит конфигурация приложения (Stage)
 */

package programmingLanguagesJava.laboratories.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    protected Stage primaryStage;
    private double xOffset = 0, yOffset = 0;

    /**
     * Данный класс является точкой входа в нашу программу, здесь происходит запуск приложения
     *
     * @param stage сущность нашего приложения.
     * @throws IOException данная пометка стоит, так как у нас initMenu может выбросить ошибку.
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = initStage(stage);
        this.primaryStage.setScene(initMenu());
        this.primaryStage.show();
    }

    /**
     * Установка параметров на все приложение
     */
    private Stage initStage(Stage primaryStage) {
        // Название приложения
        primaryStage.setTitle("Ковалев Данил ВКБ22");

        // Установка неизменяемости по размеру, так как я немного криво располагаю элементы.
        // Не умею в масштабируемое приложение, поэтому так.
        primaryStage.setResizable(false);

        // Установка изображения для приложения
        primaryStage.getIcons().add(new Image("/menuFiles/desktop.png"));

        // Определяю так, чтобы не было системных Windows компонентов, так как с ними выглядит ужасно.
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        return primaryStage;
    }

    /**
     * Инициализация меню приложения.
     * @throws IOException Java просит перманентно обрабатывать файлы через try catch, при считывании может возникнуть такая ошибка.
     */
    private Scene initMenu() throws IOException {
        Parent menuFile = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/menuFiles/menu.fxml")));

        movableWindow(menuFile);

        var menu = new Scene(menuFile);
        // Костыль, чтобы нормально работали скругленные края.
        menu.setFill(Color.TRANSPARENT);

        return menu;
    }


    private void movableWindow(Parent scene) {

        scene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            this.primaryStage.setX(event.getScreenX() - xOffset);
            this.primaryStage.setY(event.getScreenY() - yOffset);
        });
    }

}
