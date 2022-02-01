package com.technicaltest.instrumentsim;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private VBox contentVBox;

    @FXML
    private TextArea resultsTextBox;

    @FXML
    private Button pollingButton;

    private static boolean polling = false;

    private static Sampler sampler = null;
    private static Instrument instrument = null;



    public HelloController()
    {
        instrument = new Instrument();
        instrument.start();
        //Thread instrumentThread = new Thread(instrument);
        //instrumentThread.start();

        sampler = new Sampler(instrument);
        sampler.start();
//        Thread samplerThread = new Thread(sampler);
//        samplerThread.start();

        sampler.getReadingIntegerProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                resultsTextBox.appendText(newValue.toString() +"\n");
            }
        });



    }

    @FXML
    protected void onHelloButtonClick() {

        if(!polling)
        {
            polling = true;
            sampler.startPolling();
            pollingButton.setText("Stop Polling");
        }
        else
        {
            polling = false;
            sampler.stopPolling();
            pollingButton.setText("Start Polling");
        }
    }
}