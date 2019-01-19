package TONICKXS.SCFT.app.client.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import TONICKXS.SCFT.entities.Module;
import TONICKXS.SCFT.entities.Operation;
import TONICKXS.SCFT.entities.Type;
import TONICKXS.SCFT.services.ModuleServices;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ajouterTypeController implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane parent;
	@FXML
	private ImageView close;
	@FXML
	private ImageView reduce;
	@FXML
	private TableView<Module> ListeModules;
	   @FXML
	    private JFXButton vt;
	@FXML
	private JFXTextField NomType;
	@FXML
	private JFXTextField NomModule;
	@FXML
	private TableColumn<Module, Integer> idModule;
	@FXML
	private TableColumn<Module, String> Nom_Module;
	@FXML
	private TableColumn<Module, Integer> nbrvariantes;

	private Type type;
	private List<Module> moduless = new ArrayList<>();
	ObservableList<Module> data = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeStageDrageable();

		

	}

	@FXML
	void AddType(ActionEvent event) throws IOException {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation !");
		alert.setHeaderText("Vous allez Modifier le  type  :"+NomType.getText());
		alert.setContentText("Vous êtes  sure ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		Type type1 = new Type();
		TypeServices typeServicesRemote = new TypeServices();
		String name = NomType.getText();
		type1.setNom_Type(name);	
		type1.setModules(moduless);
		typeServicesRemote.update(type1 ,this.type.getIdType() );
		List<Type> lt = typeServicesRemote.findAll();
		Parent root;
		Stage stage = (Stage) vt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		} else {
			Parent root;
			Stage stage = (Stage) vt.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	
				
		}
		
		
	

	@FXML
	void listvariante(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) vt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ListeVariantesFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void listgamme(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) vt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("GammeList.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void nvlvariante(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) vt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("varianteAffichage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void nvlgamme(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) vt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("NewGamme1.fxml"));
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

	public void settype(Type type) {
		this.type = type;
		NomType.setText(type.getNom_Type());
		ModuleServices moduleServices = new ModuleServices();
		List<Module> modules1 = moduleServices.findAll();
		for(int i=0;i<modules1.size();i++)
		{
			if (modules1.get(i).getModule_type().getIdType() == type.getIdType())
			{
				moduless.add(modules1.get(i));
			}
			else {
				System.out.println("vide");
			}
		}
		ObservableList<Module> data = FXCollections.observableArrayList(moduless);

		idModule.setCellValueFactory(new PropertyValueFactory<Module, Integer>("idModule"));
		Nom_Module.setCellValueFactory(new PropertyValueFactory<Module, String>("Nom_Module"));
		nbrvariantes.setCellValueFactory(new PropertyValueFactory<Module, Integer>("nbrVariantes"));
		ListeModules.setItems((ObservableList<Module>) data);

	}

	@FXML
	void AddMod(ActionEvent event) {
		Module m = new Module();
		ModuleServices mods = new ModuleServices();
		m.setNom_Module(NomModule.getText());
		m.setModule_type(this.type);
		mods.add(m);
		List<Module> moduless1 = new ArrayList<>();
		List<Module> modules1 = mods.findAll();
		for(int i=0;i<modules1.size();i++)
		{
			if (modules1.get(i).getModule_type().getIdType() == type.getIdType())
			{
				moduless1.add(modules1.get(i));
			}
			else {
				System.out.println("vide");
			}
		}
		ObservableList<Module> data = FXCollections.observableArrayList(moduless1);

		idModule.setCellValueFactory(new PropertyValueFactory<Module, Integer>("idModule"));
		Nom_Module.setCellValueFactory(new PropertyValueFactory<Module, String>("Nom_Module"));
		nbrvariantes.setCellValueFactory(new PropertyValueFactory<Module, Integer>("nbrVariantes"));
		ListeModules.setItems((ObservableList<Module>) data);
		ListeModules.refresh();
		
	}

	@FXML
	void deleteModule(ActionEvent event) {
		ModuleServices moduleServices = new ModuleServices();
		Module mx = ListeModules.getSelectionModel().getSelectedItem();
		moduleServices.delete(mx);
		List<Module> moduless1 = new ArrayList<>();
		List<Module> modules1 = moduleServices.findAll();
		for(int i=0;i<modules1.size();i++)
		{
			if (modules1.get(i).getModule_type().getIdType() == type.getIdType())
			{
				moduless1.add(modules1.get(i));
			}
			else {
				System.out.println("vide");
			}
		}
		ObservableList<Module> data = FXCollections.observableArrayList(moduless1);

		idModule.setCellValueFactory(new PropertyValueFactory<Module, Integer>("idModule"));
		Nom_Module.setCellValueFactory(new PropertyValueFactory<Module, String>("Nom_Module"));
		nbrvariantes.setCellValueFactory(new PropertyValueFactory<Module, Integer>("nbrVariantes"));
		ListeModules.setItems((ObservableList<Module>) data);
		ListeModules.refresh();
	

	}

	
	@FXML
	void AddType1(ActionEvent event) throws IOException {
			
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation !");
		alert.setHeaderText("Vous allez Modifier le Nom du type par :"+NomType.getText());
		alert.setContentText("Vous êtes  sure ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Type type1 = new Type();
			TypeServices typeServicesRemote = new TypeServices();
			String name = NomType.getText();
			type1.setNom_Type(name);	
			type1.setModules(moduless);
			typeServicesRemote.update(type1 ,this.type.getIdType() );
			List<Type> lt = typeServicesRemote.findAll();
			Parent root;
			Stage stage = (Stage) vt.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
		  
		}
		
			
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
