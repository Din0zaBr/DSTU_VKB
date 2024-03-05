/**
 * Данный класс описывает взаимодействие с картой, здесь у нас происходят все взаимодействия с картой.
 * Здесь есть логика нажатия на карту, установка маркеров
 */

package programmingLanguagesJava.laboratories.GUI.controllers.project.AdressFillingForm.MapInteraction;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapView;
import javafx.scene.control.TextField;

public class OpenStreetMap {
    private static final Coordinate DSTUCoords = new Coordinate(47.2371576587879, 39.711658338598745);
    private final MapView mapView;
    private TextField addressField = null;


    public OpenStreetMap(MapView mapView) {
        this.mapView = mapView;
    }

    public void setAddressField(TextField addressField) {
        this.addressField = addressField;
    }

    /**
     * Точка запуска обработки карты, здесь происходит первоначальная инициализация карты.
     * Добавляется обработчик событий на то, что при нажатии на карту добавляется маркер.
     */
    public void event() {
        defaultConfiguration(mapView);
        mapClickMarkerEvent(mapView, addressField);
    }

    /**
     * Первоначальная настройка карты.
     * Здесь я создаю карту, устанавливаю центр на Гагарина 1.
     * На глаз подобрал приближение для карты
     * @param mapView объект карты, который мы передаем для обработки.
     */
    private static void defaultConfiguration(MapView mapView) {
        mapView.initialize();
        mapView.setCenter(DSTUCoords);
        mapView.setZoom(17);
    }

    /**
     * Event, который отвечает за установку маркера на карту.
     * @param mapView карта, на которую мы хотим добавить данный event
     * @param addressField TextField, в который мы хотим записывать информацию.
     */
    private static void mapClickMarkerEvent(MapView mapView, TextField addressField) {
        var mapClickController = new MapClickController(mapView);

        if (addressField != null) {
            mapClickController.setAddressField(addressField);
        }

        mapClickController.event();
    }

    private static void textFieldEvent(MapView mapView, TextField addressField) {
        // Добавить поддержку ассинхронности в приложении, чтобы не было проблем с задержкой. 
    }

}
