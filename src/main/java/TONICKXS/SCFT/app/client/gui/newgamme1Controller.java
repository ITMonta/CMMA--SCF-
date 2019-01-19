package TONICKXS.SCFT.app.client.gui;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
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
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javassist.expr.NewArray;

public class newgamme1Controller implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane parent;
	@FXML
	private ImageView close;
	@FXML
	private ImageView reduce;
	public String imageUrl;
	@FXML
	private JFXComboBox<Type> type_article;

	@FXML
	private JFXTextField nom_article;
	@FXML
	private JFXButton valider;
	public Gamme gamme = new Gamme();
	public String PDFurl;

	public Gamme getGamme() {
		return gamme;
	}

	public void setGamme(Gamme gamme) {
		this.gamme = gamme;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		makeStageDrageable();
		TypeServices typeServices = new TypeServices();
		List<Type> lt = typeServices.findAll();
		for (int i = 0; i < lt.size(); i++) {
			type_article.getItems().add(lt.get(i));
		}
	}

	@FXML
	void validerGamme(ActionEvent event) throws IOException {
		
		if( !nom_article.getText().isEmpty() && !type_article.getSelectionModel().isEmpty())
		{
		String Url = getImageUrl();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("NewGamme2.fxml"));
		loader.load();
		gamme.setNom_Gamme(nom_article.getText());
		gamme.setType_gamme(type_article.getSelectionModel().getSelectedItem());
		gamme.setC_designurl(Url);
		gamme.setType_gamme(type_article.getSelectionModel().getSelectedItem());
		Image imago = new Image(Url);
		BufferedImage bImage = SwingFXUtils.fromFXImage(imago, null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", s);
		byte[] res = s.toByteArray();
		s.close(); // especially if you are using a different output stream.
		// gamme.setC_Design(res);
		newgamme2Controller gammecontroller = loader.getController();
		System.out.println(Url);
		
		gammecontroller.setGammo(gamme);
		gammecontroller.seturl(Url);
		Parent root = loader.getRoot();
		Stage secondStage = new Stage();
		secondStage.setScene(new Scene(root, 1201, 821));
		secondStage.show();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning !!");
			alert.setHeaderText(" Veuillez remplire tout les champs !!");
			alert.showAndWait();
		}

	}

	@FXML
	void downloadCD(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();
		// Set extension filter
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		fileChooser.getExtensionFilters().add(extFilterJPG);
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			try {
				setImageUrl(file.toURI().toURL().toExternalForm());
				Image image = new Image(imageUrl);
				// System.out.println(imageUrl);
				// gamme.setC_designurl(imageUrl);
				valider.setDisable(false);
			} catch (MalformedURLException ex) {
				throw new IllegalStateException(ex);
			}
		}

	}

	public static byte[] convertFileContentToBlob(String filePath) throws IOException {
		byte[] fileContent = null;
		// initialize string buffer to hold contents of file
		StringBuffer fileContentStr = new StringBuffer("");
		BufferedReader reader = null;
		try {
			// initialize buffered reader
			reader = new BufferedReader(new FileReader(filePath));
			String line = null;
			// read lines of file
			while ((line = reader.readLine()) != null) {
				// append line to string buffer
				fileContentStr.append(line).append("\n");
			}
			// convert string to byte array
			fileContent = fileContentStr.toString().trim().getBytes();
		} catch (IOException e) {
			throw new IOException("Unable to convert file to byte array. " + e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return fileContent;
	}

	public Gamme Getgamme() {
		return this.gamme;
	}

	public String GetImage() {
		return this.imageUrl;
	}

	@FXML
	void listgamme(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) valider.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("GammeList.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void listtype(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) valider.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ListType.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void nvlvariante(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) valider.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("varianteAffichage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void listvariante(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) valider.getScene().getWindow();
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
