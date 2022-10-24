module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;

    opens game to javafx.fxml;
    exports game;
    exports game.traps;
    opens game.traps to javafx.fxml;
}