package uk.ac.soton.comp1206.scene;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.soton.comp1206.App;
import uk.ac.soton.comp1206.ui.GamePane;
import uk.ac.soton.comp1206.ui.GameWindow;

/**
 * The main menu of the game. Provides a gateway to the rest of the game.
 */
public class MenuScene extends BaseScene {

    private static final Logger logger = LogManager.getLogger(MenuScene.class);

    /**
     * Create a new menu scene
     *
     * @param gameWindow the Game Window this will be displayed in
     */
    public MenuScene(GameWindow gameWindow) {
        super(gameWindow);
        logger.info("Creating Menu Scene");
    }

    /**
     * Build the menu layout
     */
    @Override
    public void build() {
        logger.info("Building " + this.getClass().getName());

        root = new GamePane(gameWindow.getWidth(), gameWindow.getHeight());

        var menuPane = new StackPane();
        menuPane.setMaxWidth(gameWindow.getWidth());
        menuPane.setMaxHeight(gameWindow.getHeight());
        menuPane.getStyleClass().add("menu-background");
        root.getChildren().add(menuPane);

        var mainPane = new BorderPane();
        menuPane.getChildren().add(mainPane);

        // Logo
        Image image = new Image((getClass().getResource("/images/TetrECS.png").toExternalForm()));
        ImageView logo = new ImageView(image);

        logo.setFitHeight(130);
        logo.setPreserveRatio(true);
        mainPane.setCenter(logo);

        // Menu items
        var menu = new VBox(10);
        menu.setPadding(new Insets(15));
        menu.setAlignment(Pos.CENTER);
        mainPane.setBottom(menu);

        var local = new Text("Local");
        local.getStyleClass().add("menuItem");
        menu.getChildren().add(local);

        var online = new Text("Online");
        online.getStyleClass().add("menuItem");
        menu.getChildren().add(online);

        var instructions = new Text("Instructions");
        instructions.getStyleClass().add("menuItem");
        menu.getChildren().add(instructions);

        var settings = new Text("Settings");
        settings.getStyleClass().add("menuItem");
        menu.getChildren().add(settings);

        var quit = new Text("Quit");
        quit.getStyleClass().add("menuItem");
        quit.setOnMouseClicked((e) -> App.getInstance().shutdown());
        menu.getChildren().add(quit);

        //Bind the button action to the startGame method in the menu
        //local.setOnAction(this::startGame);
    }

    /**
     * Initialise the menu
     */
    @Override
    public void initialise() {

    }

    /**
     * Handle when the Start Game button is pressed
     *
     * @param event event
     */
    private void startGame(ActionEvent event) {
        gameWindow.startChallenge();
    }

}
