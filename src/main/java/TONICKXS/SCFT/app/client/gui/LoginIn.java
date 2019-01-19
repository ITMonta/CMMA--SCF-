package TONICKXS.SCFT.app.client.gui;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import TONICKXS.SCFT.entities.User;
import TONICKXS.SCFT.services.UserService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginIn implements Initializable {
	@FXML
	private JFXTextField Username;
	@FXML
	private ImageView reduce;
	@FXML
	private ImageView close;
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane parent;
	@FXML
	private JFXPasswordField Passwd;
    @FXML
    private JFXButton logg;
	private static User loggedUser;
	private Stage stage;
	private UserService userService = new UserService();
	public static User getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(User loggedUser) {
		LoginIn.loggedUser = loggedUser;
	}

	@FXML
	void DoLogin(ActionEvent event)  {
		
			try {
		
		User user= null;
		String l = Username.getText();
		String pwd = Passwd.getText();
		user = userService.login(l, pwd);
		setLoggedUser(user);
		stage = (Stage) logg.getScene().getWindow();
		
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
		
			Scene scene = new Scene(root);
			stage.setScene(scene);
			//stage.setFullScreen (true);
			
			stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			
			}
			}
			catch (Exception ex) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning !!");
				alert.setHeaderText("Vérifier l'identifiant ou le mot de passe");

				alert.showAndWait();
			}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		makeStageDrageable();
		
	}
	public void makeStageDrageable() {

		parent.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Projet.stage.setX(event.getScreenX() - xOffset);
				Projet.stage.setY(event.getScreenY() - yOffset);
				Projet.stage.setOpacity(0.7f);
			}
		});
		parent.setOnDragDone((e) -> {
			Projet.stage.setOpacity(1.0f);
		});
		parent.setOnMouseReleased((e) -> {
			Projet.stage.setOpacity(1.0f);
		});
	}
	@FXML
    void close(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit confirmation !");
		alert.setHeaderText("Vous allez quitter l'application !");
		alert.setContentText("vous êtes sure ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Stage stage = (Stage) close.getScene().getWindow();
		    // do what you have to do
		    stage.close();
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
		
    }
	@FXML
	 void reduce (MouseEvent event) {
			
			Stage stage = (Stage) close.getScene().getWindow();
		    
		    stage.setIconified(true);
	
		
 }
}
