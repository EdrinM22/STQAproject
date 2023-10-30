module project.stqaprojectf {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.stqaprojectf to javafx.fxml;
    exports project.stqaprojectf;
}