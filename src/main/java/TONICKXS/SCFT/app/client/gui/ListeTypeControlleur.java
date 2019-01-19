package TONICKXS.SCFT.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.hibernate.type.ListType;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import TONICKXS.SCFT.entities.Module;
import TONICKXS.SCFT.entities.Operation;
import TONICKXS.SCFT.entities.Type;
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

public class ListeTypeControlleur implements Initializable {

	@FXML
	private TableView<Module> ListeModules;
	@FXML
	private ImageView close;
	@FXML
	private ImageView reduce;

	@FXML
	private JFXTextField NomTypes;
	@FXML
    private JFXTextField NomTypeajouter;
	@FXML
	private TableView<Type> ListeTypes;

	@FXML
	private JFXButton ModifierType;

	@FXML
	private TableColumn<Type, Integer> numerotype;
	@FXML
	private TableColumn<Type, String> nomtype;
	@FXML
	private JFXButton v;
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane parent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeStageDrageable();
		TypeServices typeServices = new TypeServices();
		List<Type> types = typeServices.findAll();

		ObservableList<Type> data = FXCollections.observableArrayList(types);
		numerotype.setCellValueFactory(new PropertyValueFactory<Type, Integer>("idType"));
		nomtype.setCellValueFactory(new PropertyValueFactory<Type, String>("Nom_Type"));

		ListeTypes.setItems((ObservableList<Type>) data);
		ListeTypes.refresh();

	}

	@FXML
	void addType(ActionEvent event) throws IOException {
		Type type1 = new Type();
		TypeServices typeServicesRemote = new TypeServices();
		String name = NomTypeajouter.getText();
		type1.setNom_Type(name);	
		typeServicesRemote.add(type1) ;
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(null);
		alert.setContentText("Type "+name+" ajouté avec succès !! ");

		alert.showAndWait();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ListType.fxml"));
		loader.load();
		Parent root = loader.getRoot();

		Stage stage = (Stage) v.getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void DeleteType(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Attention !!");
		alert.setHeaderText("Vous allez supprimer ce type et inclus tous les modules et les variantes associées !");
		alert.setContentText("Vous êtes sure ??");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Type type = ListeTypes.getSelectionModel().getSelectedItem();
			TypeServices typeServices = new TypeServices();
			typeServices.delete(type);
			Parent root;
			Stage stage = (Stage) v.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
		   
		}
		
	}

	@FXML
	void listvariante(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) v.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ListeVariantesFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void listgamme(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) v.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("GammeList.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void nvlvariante(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) v.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("varianteAffichage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	void Home(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) parent.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	void nvlgamme(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) v.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("NewGamme1.fxml"));
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
	void modifType(ActionEvent event) throws IOException {
		TypeServices typeServices = new TypeServices();
		Type type = ListeTypes.getSelectionModel().getSelectedItem();
		// type.setNom_Type(nom.getText());
		// type.setNumero(Integer.parseInt(numero.getText()));
		// typeServices.add(type);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ajouterType.fxml"));
		loader.load();
		// gamme.setNom_Gamme(nom_article.getText());
		ajouterTypeController ajouterTypeController = loader.getController();

		ajouterTypeController.settype(type);
		
		Parent root = loader.getRoot();

		Stage stage = (Stage) v.getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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
