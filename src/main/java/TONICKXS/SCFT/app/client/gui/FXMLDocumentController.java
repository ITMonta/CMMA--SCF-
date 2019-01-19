package TONICKXS.SCFT.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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

public class FXMLDocumentController implements Initializable {
	@FXML
    private JFXButton v;	
	@FXML
	private ImageView close;
	@FXML
	private ImageView reduce;
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane parent;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		makeStageDrageable();
		
	} 
	 @FXML
	    void listvariante(ActionEvent event) throws IOException {
		   Parent root;
			Stage stage = (Stage) v.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("ListeVariantesFXML.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void listgamme(ActionEvent event) throws IOException {
	    	Parent root;
			Stage stage = (Stage) v.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("GammeList.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void nvlvariante(ActionEvent event) throws IOException {
	    	Parent root;
			Stage stage = (Stage) v.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("varianteAffichage.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }

	    @FXML
	    void nvlgamme(ActionEvent event) throws IOException {
	    	Parent root;
			Stage stage = (Stage) v.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("NewGamme1.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
	    @FXML
	    void listtype(ActionEvent event) throws IOException {
	    	Parent root;
			Stage stage = (Stage) v.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("ListType.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
	    @FXML
	    void nvltype(ActionEvent event) throws IOException {
	    	Parent root;
			Stage stage = (Stage) v.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("ListType.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();}
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
