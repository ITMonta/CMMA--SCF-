package TONICKXS.SCFT.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import TONICKXS.SCFT.entities.Gamme;
import TONICKXS.SCFT.entities.Type;
import TONICKXS.SCFT.services.GammeServices;
import TONICKXS.SCFT.services.TypeServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class gammelisteController implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane parent;
	@FXML
	private ImageView reduce;
	@FXML
	private ImageView close;
	@FXML
	private JFXButton duplicategamme;

	@FXML
	private JFXButton updategamme;

	@FXML
	private JFXTextField name;

	@FXML
	private JFXButton deletegamme;

	@FXML
	private TableView<Gamme> listegammes;
	@FXML
	private TableColumn<Gamme, String> type_gam;

	@FXML
	private TableColumn<Gamme, String> Nom_gam;

	@FXML
	private TableColumn<Gamme, Float> temp_gam;
	@FXML
	private JFXButton ok;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		makeStageDrageable();
		Nom_gam.setEditable(true);
		temp_gam.setEditable(true);
		GammeServices gammeServices = new GammeServices();
		List<Gamme> liste = new ArrayList<>();
				liste=gammeServices.findAll();
		if(!liste.isEmpty())
		{
		ObservableList<Gamme> data = FXCollections.observableArrayList(liste);
		Nom_gam.setCellValueFactory(new PropertyValueFactory<Gamme, String>("Nom_Gamme"));

		type_gam.setCellValueFactory(new PropertyValueFactory<Gamme, String>("Type_gamme"));
		temp_gam.setCellValueFactory(new PropertyValueFactory<Gamme, Float>("Temps"));

		listegammes.setItems((ObservableList<Gamme>) data);
		listegammes.refresh();
		}
    }
		
		/*
		 * // Define rendering of selected value shown in ComboBox.
		 * types.setConverter(new StringConverter<Type>() {
		 * 
		 * @Override public String toString(Type type) { if (type == null) { return
		 * null; } else { return type.getNom_Type() + " "; } }
		 * 
		 * @Override public Type fromString(String typeString) { return null; // No
		 * conversion fromString needed. } });
		 */

	

	

	@FXML
	void update(ActionEvent event) throws IOException {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Modification");
		alert.setHeaderText("Vous allez modifier la gamme :"+listegammes.getSelectionModel().getSelectedItem().getNom_Gamme()+"");
		alert.setContentText("Vous êtes sure ??");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("NewGamme2.fxml"));
			loader.load();
			newgamme2Controller gammecontroller = loader.getController();
			
			Gamme gamme=listegammes.getSelectionModel().getSelectedItem();
			gammecontroller.setGammo2(gamme);
			gammecontroller.seturl(gamme.getC_designurl());
			Parent root = loader.getRoot();
			Stage secondStage = new Stage();
			secondStage.setScene(new Scene(root, 1201, 821));
			secondStage.show();
		} else {
		   
		}
		
	}

	@FXML
	void delete(ActionEvent event) throws IOException {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Attention !!");
		alert.setHeaderText("Vous allez supprimer la gamme :"+listegammes.getSelectionModel().getSelectedItem().getNom_Gamme()+"");
		alert.setContentText("Vous êtes sure ??");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			GammeServices gammeServices = new GammeServices();
			String gamme_name = name.getText();
			Gamme gamme=listegammes.getSelectionModel().getSelectedItem();
			GammeServices gammeServices1=new GammeServices();
			gammeServices1.delete(gamme);
			Parent root;
			Stage stage = (Stage) updategamme.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("GammeList.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
		
	}

	

	@FXML
	void listvariante(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) updategamme.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ListeVariantesFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void listtype(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) updategamme.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ListType.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void nvlvariante(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) updategamme.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("varianteAffichage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void nvlgamme(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) updategamme.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("NewGamme1.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void Home(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) updategamme.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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
	    void recherchegamme(ActionEvent event) {
		 	GammeServices gammeServicesRemote = new GammeServices();
		 	List<Gamme> liste = gammeServicesRemote.findAll();
		 	List<Gamme> endliste = new ArrayList<Gamme>();
			String gamme_name = name.getText();
			System.out.println(gamme_name);
			for (int i = 0; i < liste.size(); i++) {

				if ((liste.get(i).getNom_Gamme().equals(gamme_name))) {

					endliste.add(liste.get(i));
					
				}

			}
			ObservableList<Gamme> data = FXCollections.observableArrayList(endliste);
			Nom_gam.setCellValueFactory(new PropertyValueFactory<Gamme, String>("Nom_Gamme"));

			type_gam.setCellValueFactory(new PropertyValueFactory<Gamme, String>("Type_gamme"));
			temp_gam.setCellValueFactory(new PropertyValueFactory<Gamme, Float>("Temps"));

			listegammes.setItems((ObservableList<Gamme>) data);
			listegammes.refresh();
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
