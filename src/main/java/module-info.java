module com.technicaltest.instrumentsim {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.technicaltest.instrumentsim to javafx.fxml;
    exports com.technicaltest.instrumentsim;
}