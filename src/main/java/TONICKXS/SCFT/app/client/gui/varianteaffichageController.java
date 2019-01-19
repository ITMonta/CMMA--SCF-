package TONICKXS.SCFT.app.client.gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import TONICKXS.SCFT.entities.Gamme;
import TONICKXS.SCFT.entities.Machine;
import TONICKXS.SCFT.entities.Module;
import TONICKXS.SCFT.entities.Operation;
import TONICKXS.SCFT.entities.Type;
import TONICKXS.SCFT.entities.Variante;
import TONICKXS.SCFT.services.MachineServices;
import TONICKXS.SCFT.services.ModuleServices;
import TONICKXS.SCFT.services.OperationServices;
import TONICKXS.SCFT.services.TypeServices;
import TONICKXS.SCFT.services.VarianteServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class varianteaffichageController implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane parent;
	@FXML
    private JFXTextField TTotal;
	@FXML
	private ImageView close;
	@FXML
	private ImageView reduce;
	@FXML
	private JFXTextField Opr;
	@FXML
	private JFXButton deleteop;
	@FXML
	private JFXTextField Tm;
	@FXML
	private JFXComboBox<Machine> Mach;
	@FXML
	private JFXTextField Nom_Variantes;

	@FXML
	private TableView<Operation> Liste_Operation;

	@FXML
	private JFXComboBox<Type> type;
	@FXML
	private JFXButton addop;
	@FXML
	private JFXButton addop1;
	@FXML
	private JFXComboBox<Module> Module;
	@FXML
	private TableColumn<Operation, String> machine;
	@FXML
	private TableColumn<Operation, String> numero;
	@FXML
	private TableColumn<Operation, String> time;
	@FXML
	private TableColumn<Operation, String> operation;
	@FXML
	private ImageView img_var;
	@FXML
	private JFXButton ajouter;
	@FXML
	private JFXButton modif;
	@FXML
	private JFXTextField Num;
	@FXML
    private JFXButton deleteop1;
	private List<Type> Listetypes;
	private Variante vr;
	private Variante vrt;
	private List<Operation> listeoperations;
	private List<Module> listeModules;
	private List<Machine> listeMachines;
	private List<Machine> listeMachines2;
	private String imageUrl;
	private double tems=0.0;

	ObservableList<Operation> data = FXCollections.observableArrayList();
	ObservableList<Machine> data2 = FXCollections.observableArrayList();
	private List<Operation> operlist = new ArrayList<>();
	DecimalFormat df = new DecimalFormat ( ) ; 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TypeServices typeServices = new TypeServices();
		ModuleServices moduleServices = new ModuleServices();
		MachineServices machineServices = new MachineServices();
		List<Type> t = typeServices.findAll();
		modif.setVisible(false);
		addop1.setVisible(false);
		deleteop.setVisible(false);
		
		df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules 
		df.setMinimumFractionDigits ( 2 ) ; 
		df.setDecimalSeparatorAlwaysShown ( true ) ; 
		List<Machine> m = machineServices.findAll();

		for (int i = 0; i < m.size(); i++) {
			Mach.getItems().add(m.get(i));
		}
		for (int i = 0; i < t.size(); i++) {
			type.getItems().add(t.get(i));
		}
		makeStageDrageable();

	}

	@FXML
	void Add_Variante(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation !!");
		alert.setHeaderText("Vous allez ajouter une variante ");
		alert.setContentText("Vous êtes sure ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			if (!type.getSelectionModel().isEmpty() && !Module.getSelectionModel().isEmpty()
					&& !Nom_Variantes.getText().isEmpty()) {
				Variante v = ajouteryzeby();
				System.out.println(v.getIdVariante());
				OperationServices oprs = new OperationServices();
				for (int i = 0; i < operlist.size(); i++) {
					Operation op = operlist.get(i);
					// op.setIdvariante(v.getIdVariante());
					op.setVariante_operation(v);
					oprs.add(op);
				}
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle("success !");
				alert1.setHeaderText(null);
				alert1.setContentText("Variante " + v.getNom_variante() + " ajouter avec succès");

				alert1.showAndWait();
				Parent root;
				Stage stage = (Stage) ajouter.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} else {
				Alert alert1 = new Alert(AlertType.WARNING);
				alert1.setTitle("Warning !!");
				alert1.setHeaderText(" Veuillez remplire tout les champs !!");
				alert1.showAndWait();
			}

		} else {
			Parent root;
			Stage stage = (Stage) ajouter.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

	}

	@FXML
	void Download_image(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		// Set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			try {
				imageUrl = file.toURI().toURL().toExternalForm();
				Image image = new Image(imageUrl);
				img_var.setImage(image);
			} catch (MalformedURLException ex) {
				throw new IllegalStateException(ex);
			}
		}
	}

	private List<Type> getTypes() {
		TypeServices typeServicesRemote = new TypeServices();
		Listetypes = typeServicesRemote.findAll();
		return Listetypes;

	}

	private List<Module> getModule() {

		ModuleServices moduleServicesRemote = new ModuleServices();
		listeModules = moduleServicesRemote.findAll();
		return listeModules;

	}

	private List<Operation> getOperation() {

		OperationServices operationServicesRemote = new OperationServices();
		listeoperations = operationServicesRemote.findAll();
		return listeoperations;

	}

	private List<Machine> getMachines() {
		MachineServices machineServicesRemote = new MachineServices();
		listeMachines = machineServicesRemote.findAll();
		return listeMachines;

	}

	@FXML
	void Add_Opp(ActionEvent event) throws IOException {
		if (!Opr.getText().isEmpty() && !Mach.getSelectionModel().isEmpty() && !Tm.getText().isEmpty()
				&& !Num.getText().isEmpty()) {
			Operation op = new Operation();
			OperationServices operationServices = new OperationServices();
			op.setDescription(Opr.getText());
			op.setMachine(Mach.getSelectionModel().getSelectedItem().getNomMachine());
			op.setTime(Tm.getText());
			op.setNumero(Num.getText());
			// op.setVariante_operation(this.vr);
			// operationServices.add(op);
			operlist.add(op);
			tems+=Double.parseDouble(df.format(Float.parseFloat(op.getTime())));
		
			TTotal.setText(tems+" Min");
			data.add(op);

			machine.setCellValueFactory(new PropertyValueFactory<Operation, String>("machine"));
			time.setCellValueFactory(new PropertyValueFactory<Operation, String>("time"));
			operation.setCellValueFactory(new PropertyValueFactory<Operation, String>("description"));
			numero.setCellValueFactory(new PropertyValueFactory<Operation, String>("numero"));
			Liste_Operation.setItems((ObservableList<Operation>) data);
			Liste_Operation.refresh();
			Opr.setText(null);
			Mach.setValue(null);
			Tm.setText(null);
			Num.setText(null);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning !!");
			alert.setHeaderText(" Veuillez remplire tout les champs !!");
			alert.showAndWait();
		}

	}

	@FXML
	void listgamme(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) ajouter.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("GammeList.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void listtype(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) ajouter.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ListType.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void listvariante(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) ajouter.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ListeVariantesFXML.fxml"));
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
		Stage stage = (Stage) ajouter.getScene().getWindow();
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
	void populateModules(ActionEvent event) {
		Module.getItems().clear();

		ModuleServices moduleServicesRemote = new ModuleServices();
		List<Module> m = moduleServicesRemote.findAll();
		for (int i = 0; i < m.size(); i++) {
			if (m.get(i).getModule_type().getIdType() == ((type.getSelectionModel().getSelectedItem().getIdType()))) {
				Module.getItems().add(m.get(i));

			}
		}
	}

	public Variante ajouteryzeby() throws IOException {
		VarianteServices varianteServicesRemote = new VarianteServices();
		vr = new Variante();
		vr.setNom_variante(Nom_Variantes.getText()); // Name
		BufferedImage bImage = SwingFXUtils.fromFXImage(img_var.getImage(), null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", s);
		byte[] res = s.toByteArray();
		s.close(); // especially if you are using a different output stream.

		vr.setImage(imageUrl);
		vr.setNumero(vr.getIdVariante());
		vr.setVariante_module(Module.getSelectionModel().getSelectedItem());
		vr.setPicture(res);
		// vr.setOperations(operlist);
		varianteServicesRemote.add(vr);
		List<Variante> vrs = varianteServicesRemote.findAll();
		return vrs.get(vrs.size() - 1);

	}

	public void setVariante(Variante v) {
		this.vrt = v;
		ajouter.setVisible(false);
		modif.setVisible(true);
		addop1.setVisible(true);
		deleteop.setVisible(true);
		deleteop1.setVisible(false);
		addop.setVisible(false);
		
		imageUrl = v.getImage();
		
		Nom_Variantes.setText(v.getNom_variante());
		Image image = new Image(v.getImage());
		img_var.setImage(image);
		OperationServices operationServices = new OperationServices();
		List<Operation> operationss1 = new ArrayList<>();
		List<Operation> operationss = operationServices.findAll();
		for (int i = 0; i < operationss.size(); i++) {
			if (operationss.get(i).getVariante_operation().getIdVariante() == v.getIdVariante()) {
				operationss1.add(operationss.get(i));
				tems+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(i).getTime())));
				
			} else {
				System.out.println("vide");
			}
		}
		TTotal.setText(tems+" Min");
		ObservableList<Operation> data = FXCollections.observableArrayList(operationss1);

		machine.setCellValueFactory(new PropertyValueFactory<Operation, String>("machine"));
		time.setCellValueFactory(new PropertyValueFactory<Operation, String>("time"));
		operation.setCellValueFactory(new PropertyValueFactory<Operation, String>("description"));
		numero.setCellValueFactory(new PropertyValueFactory<Operation, String>("numero"));
		Liste_Operation.setItems((ObservableList<Operation>) data);
		Liste_Operation.refresh();

	}

	@FXML
	void ModifVariante(ActionEvent event) throws IOException {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Attention !");
		alert.setHeaderText("Vous allez modifier cette variante");
		alert.setContentText("Vous êtes sure ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			if (!type.getSelectionModel().isEmpty() && !Module.getSelectionModel().isEmpty()
					&& !Nom_Variantes.getText().isEmpty()) {

				VarianteServices varianteServicesRemote = new VarianteServices();
				vr = new Variante();
				vr.setNom_variante(Nom_Variantes.getText()); // Name
				BufferedImage bImage = SwingFXUtils.fromFXImage(img_var.getImage(), null);
				ByteArrayOutputStream s = new ByteArrayOutputStream();
				ImageIO.write(bImage, "jpg", s);
				byte[] res = s.toByteArray();
				s.close(); // especially if you are using a different output stream.

				vr.setImage(imageUrl);
				vr.setNumero(vr.getIdVariante());
				vr.setVariante_module(Module.getSelectionModel().getSelectedItem());
				vr.setPicture(res);
				// vr.setOperations(operlist);

				varianteServicesRemote.update(vr, getVrt().getIdVariante());
				Parent root;
				Stage stage = (Stage) ajouter.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} else {
				Alert alert1 = new Alert(AlertType.WARNING);
				alert1.setTitle("Warning !!");
				alert1.setHeaderText(" Veuillez remplire tout les champs !!");
				alert1.showAndWait();
			}
		} else {
			Parent root;
			Stage stage = (Stage) ajouter.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

	}

	@FXML
	void Add_Opp1(ActionEvent event) {

		if (!Opr.getText().isEmpty() && !Mach.getSelectionModel().isEmpty() && !Tm.getText().isEmpty()
				&& !Num.getText().isEmpty()) {

			Operation op = new Operation();
			OperationServices operationServices = new OperationServices();
			op.setDescription(Opr.getText());
			op.setMachine(Mach.getSelectionModel().getSelectedItem().getNomMachine());
			op.setTime(Tm.getText());
			op.setNumero(Num.getText());
			op.setVariante_operation(this.vrt);
			operationServices.add(op);
			tems+=Double.parseDouble(df.format(Float.parseFloat(op.getTime())));
			TTotal.setText(tems+" Min");
			// operlist.add(op);
			List<Operation> operationss1 = new ArrayList<>();
			List<Operation> operationss = operationServices.findAll();
			for (int i = 0; i < operationss.size(); i++) {
				if (operationss.get(i).getVariante_operation().getIdVariante() == getVrt().getIdVariante()) {
					operationss1.add(operationss.get(i));
				} else {
					System.out.println("vide");
				}
			}
			ObservableList<Operation> data = FXCollections.observableArrayList(operationss1);

			machine.setCellValueFactory(new PropertyValueFactory<Operation, String>("machine"));
			time.setCellValueFactory(new PropertyValueFactory<Operation, String>("time"));
			operation.setCellValueFactory(new PropertyValueFactory<Operation, String>("description"));
			numero.setCellValueFactory(new PropertyValueFactory<Operation, String>("numero"));
			Liste_Operation.setItems((ObservableList<Operation>) data);
			Liste_Operation.refresh();
			Opr.setText(null);
			Mach.setValue(null);
			Tm.setText(null);
			Num.setText(null);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning !!");
			alert.setHeaderText(" Veuillez remplire tout les champs !!");
			alert.showAndWait();
		}

	}
	@FXML
	void deleteoperation(ActionEvent event) {
		OperationServices operationServices = new OperationServices();
		Operation op = Liste_Operation.getSelectionModel().getSelectedItem();
		tems-=Double.parseDouble(df.format(Float.parseFloat(op.getTime())));
		TTotal.setText(tems+" Min");
		operationServices.delete(op);
		List<Operation> operationss1 = new ArrayList<>();
		List<Operation> operationss = operationServices.findAll();
		for (int i = 0; i < operationss.size(); i++) {
			if (operationss.get(i).getVariante_operation().getIdVariante() == getVrt().getIdVariante()) {
				operationss1.add(operationss.get(i));
			} else {
				System.out.println("vide");
			}
		}
		ObservableList<Operation> data = FXCollections.observableArrayList(operationss1);

		machine.setCellValueFactory(new PropertyValueFactory<Operation, String>("machine"));
		time.setCellValueFactory(new PropertyValueFactory<Operation, String>("time"));
		operation.setCellValueFactory(new PropertyValueFactory<Operation, String>("description"));
		numero.setCellValueFactory(new PropertyValueFactory<Operation, String>("numero"));
		Liste_Operation.setItems((ObservableList<Operation>) data);
		Liste_Operation.refresh();

	}

	public Variante getVrt() {
		return vrt;
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
    void deleteoperation1(ActionEvent event) {
		
		Operation op = Liste_Operation.getSelectionModel().getSelectedItem();
		operlist.remove(op);
		data.remove(op);
		tems-=Double.parseDouble(df.format(Float.parseFloat(op.getTime())));
		TTotal.setText(tems+" Min");
		System.out.println(operlist.size());
		ObservableList<Operation> data1 = FXCollections.observableArrayList(operlist);

		machine.setCellValueFactory(new PropertyValueFactory<Operation, String>("machine"));
		time.setCellValueFactory(new PropertyValueFactory<Operation, String>("time"));
		operation.setCellValueFactory(new PropertyValueFactory<Operation, String>("description"));
		numero.setCellValueFactory(new PropertyValueFactory<Operation, String>("numero"));
		Liste_Operation.setItems((ObservableList<Operation>) data1);
		Liste_Operation.refresh();
    }
	@FXML
	 void reduce (MouseEvent event) {
			
			Stage stage = (Stage) close.getScene().getWindow();
		    
		    stage.setIconified(true);
	
		
 }
}
