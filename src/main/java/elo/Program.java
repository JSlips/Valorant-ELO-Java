package elo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kong.unirest.Cookies;

public class Program extends Application {
    public static  void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        String username = "";
        Cookies cookies = Authentication.getCookies();
        String accessToken = "Bearer " + Authentication.getAccessToken(cookies, username, "");
        String entitlementToken = Authentication.getEntitlement(accessToken);
        String userID = Authentication.getUserID(accessToken);

        Matches m = new Matches(accessToken, entitlementToken, userID);
        Rank rank = new Rank(m);

        Scene scene = Graphing.getLineChart(rank);
        stage.setScene(scene);
        stage.setTitle(String.format("%s | %s | RP: %d", username, rank.getRank(), rank.getCurrentRP()));
        stage.setResizable(false);
        stage.show();
    }
}
