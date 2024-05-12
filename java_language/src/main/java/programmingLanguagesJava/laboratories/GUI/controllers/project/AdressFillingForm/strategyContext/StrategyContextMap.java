package programmingLanguagesJava.laboratories.GUI.controllers.project.AdressFillingForm.strategyContext;

import com.sothawo.mapjfx.MapView;
import javafx.scene.control.TextField;

// для сокращения передаваемых аргументов
public record StrategyContextMap(MapView mapView, TextField adressTextField) {}
