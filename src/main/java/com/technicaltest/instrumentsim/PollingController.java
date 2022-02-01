package com.technicaltest.instrumentsim;

import com.technicaltest.instrumentsim.instrument.Instrument;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class PollingController {

    @FXML
    private TextArea resultsTextBox;
    @FXML
    private Button pollingButton;

    private static boolean polling = false;
    private static Sampler sampler = null;

    public PollingController() {
        Instrument instrument = new Instrument();
        instrument.start();

        sampler = new Sampler(instrument);
        sampler.start();

        sampler.getReadingIntegerProperty().addListener((observable, oldValue, newValue) ->
                resultsTextBox.appendText(newValue.toString() + "\n"));
    }

    @FXML
    protected void onPollingButtonClick() {
        if (!polling) {
            polling = true;
            sampler.startPolling();
            pollingButton.setText("Stop Polling");
        } else {
            polling = false;
            sampler.stopPolling();
            pollingButton.setText("Start Polling");
        }
    }
}