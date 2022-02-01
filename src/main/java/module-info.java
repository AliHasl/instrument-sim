module com.technicaltest.instrumentsim {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.technicaltest.instrumentsim to javafx.fxml;
    exports com.technicaltest.instrumentsim;
    exports com.technicaltest.instrumentsim.instrument;
    opens com.technicaltest.instrumentsim.instrument to javafx.fxml;
}