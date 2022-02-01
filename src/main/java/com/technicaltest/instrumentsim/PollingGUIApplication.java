package com.technicaltest.instrumentsim;

import com.technicaltest.instrumentsim.instrument.Instrument;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PollingGUIApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(PollingGUIApplication.class.getResource("pollingGUI-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Polling Application");
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            Instrument.stopThread();
            Sampler.stopThread();
        });

        stage.show();
    }
}