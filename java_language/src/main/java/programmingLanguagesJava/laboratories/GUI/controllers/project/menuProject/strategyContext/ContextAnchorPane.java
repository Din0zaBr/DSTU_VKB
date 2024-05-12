package programmingLanguagesJava.laboratories.GUI.controllers.project.menuProject.strategyContext;

import javafx.scene.layout.AnchorPane;
// record для хранения и написания сущностей. В данном случае, хранение
public record ContextAnchorPane(AnchorPane anchorPaneMovable,
                                AnchorPane registrationAnchorPane,
                                AnchorPane hiddenAnchorPane)  {}
