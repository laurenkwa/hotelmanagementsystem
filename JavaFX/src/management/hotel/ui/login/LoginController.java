package management.hotel.ui.login;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {
    public Label userId;
    public Label userPassword;
    public TextField userIdTextField;
    public TextField userPasswordTextField;
    public Text result;

    public void processing(ActionEvent actionEvent) {
        result.setText("Processing");
    }
}
