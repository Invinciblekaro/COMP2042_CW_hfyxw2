module com.cw {
    requires javafx.controls;
    requires javafx.fxml;
    requires TinySound;
    requires fastjson;
    requires java.sql;

    opens com.cw to javafx.fxml;
    exports com.cw;

    opens com.cw.controller to javafx.fxml;
    exports com.cw.controller;

    opens com.cw.commandLine to javafx.fxml;
    exports com.cw.commandLine;

    opens com.cw.game.settings to fastjson;
    exports com.cw.game.settings;

    opens com.cw.game.leaderboard to fastjson;
    exports com.cw.game.leaderboard;
}