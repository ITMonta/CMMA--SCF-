package TONICKXS.SCFT.app.client.gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import TONICKXS.SCFT.entities.Gamme;
import TONICKXS.SCFT.entities.Operation;
import TONICKXS.SCFT.entities.Variante;
import TONICKXS.SCFT.entities.Variante_Gamme;
import TONICKXS.SCFT.services.GammeServices;
import TONICKXS.SCFT.services.OperationServices;
import TONICKXS.SCFT.services.VarianteGammeServices;
import TONICKXS.SCFT.services.VarianteServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
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
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class VarianteGammeController implements Initializable {

	@FXML
	private ImageView prec;

	@FXML
	private JFXTextField TotalTime;
	@FXML
	private Rectangle R1;
	@FXML
	private Rectangle R2;
	@FXML
	private Rectangle R3;
	@FXML
	private ImageView suiv;
	@FXML
	private TableView<Operation> V1_L1;
	@FXML
	private TableColumn<Operation, String> machine1;
	@FXML
	private TableColumn<Operation, String> time1;
	@FXML
	private TableColumn<Operation, String> operation1;
	@FXML
	private TableColumn<Operation, String> machine2;
	@FXML
	private TableColumn<Operation, String> time2;
	@FXML
	private TableColumn<Operation, String> operation2;
	@FXML
	private TableColumn<Operation, String> machine3;
	@FXML
	private TableColumn<Operation, String> time3;
	@FXML
	private TableColumn<Operation, String> operation3;
	@FXML
	private ImageView V1;
	private boolean b=false;
	@FXML
	private ImageView V2;
	@FXML
    private JFXTextField TTP3;

    @FXML
    private JFXTextField TTP2;

    @FXML
    private JFXTextField TTP1;

	@FXML
	private TableView<Operation> V2_L2;
	@FXML
	private JFXButton eng;
	@FXML
	private TableView<Operation> V3_L3;

	@FXML
	private ImageView V3;
	private int increment;
	List<Variante> variantes = new ArrayList<>();
	private List<Variante> next = new ArrayList<>();
	private int a;
	private float temp;
	int sizeof;
	int editable;
	DecimalFormat df = new DecimalFormat ( ) ; 
	private Gamme gamme;
	String T11, T12, T13;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		temp = 0;
		df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules 
		df.setMinimumFractionDigits ( 2 ) ; 
		df.setDecimalSeparatorAlwaysShown ( true ) ; 
		increment = 0;
		V1_L1.setVisible(false);
		V2_L2.setVisible(false);
		V3_L3.setVisible(false);
	}

	@FXML
	void Validate(ActionEvent event) throws IOException {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation !!");
		alert.setHeaderText("Vous allez enregistrer cette gamme ");
		alert.setContentText("Vous êtes sure ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			GammeServices gammeServices = new GammeServices();
			gamme.setTemps(Float.parseFloat(TotalTime.getText()));
			List<Variante_Gamme> variantee = new ArrayList<>();
			VarianteGammeServices vgs = new VarianteGammeServices();
			List<Variante_Gamme> lvg = vgs.findAll();
			for (int j = 0; j < lvg.size(); j++) {
				if (lvg.get(j).getVariante_gamme().getIdGamme() == gamme.getIdGamme()) {
					vgs.delete(lvg.get(j));
				}
			}
			for (int i = 0; i < variantes.size(); i++) {
				Variante_Gamme vg = new Variante_Gamme();
				vg.setGamme_variantes(variantes.get(i));
				vg.setVariante_gamme(gamme);
				variantee.add(vg);
			}
			gamme.setVariantes(variantee);
			List<Gamme> gammesr = gammeServices.findAll();
			for(int k=0;k<gammesr.size();k++)
			{
				if(gammesr.get(k).getIdGamme() == gamme.getIdGamme())
				{
					gammeServices.update(gamme);
					b=true;
					Alert alert1 = new Alert(AlertType.INFORMATION);
					alert1.setTitle("success");
					alert1.setHeaderText(null);
					alert1.setContentText("Gamme modifié avec succés !");

					alert1.showAndWait();
				}
			}
			if(!b)
			{
			gammeServices.add(gamme);
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setTitle("success");
			alert1.setHeaderText(null);
			alert1.setContentText("Gamme ajouté avec succés !");

			alert1.showAndWait();
			}
			
			Stage stage = (Stage) V3.getScene().getWindow();
			stage.close();
		} else {
			Stage stage = (Stage) V3.getScene().getWindow();
			stage.close();
		}
		

	}

	@FXML
	void desc(MouseEvent event) {
		prec.setVisible(true);
		V1.imageProperty().set(null);
		V2.imageProperty().set(null);
		V3.imageProperty().set(null);
		TTP1.setVisible(false);
		TTP2.setVisible(false);
		TTP3.setVisible(false);
		V1_L1.setItems(null);
		V2_L2.setItems(null);
		V3_L3.setItems(null);
		V1_L1.setVisible(false);
		V2_L2.setVisible(false);
		V3_L3.setVisible(false);
		R1.setVisible(false);
		R2.setVisible(false);
		R3.setVisible(false);
		sizeof += 3;
		imageviewmanage(next, sizeof);

		if ((a > 6)) {

			suiv.setVisible(true);
			a = a - 3;

		} else {
			suiv.setVisible(false);
		}
	}

	@FXML
	void asc(MouseEvent event) {
		suiv.setVisible(true);
		V1.imageProperty().set(null);
		V2.imageProperty().set(null);
		V3.imageProperty().set(null);
		TTP1.setVisible(false);
		TTP2.setVisible(false);
		TTP3.setVisible(false);
		V1_L1.setItems(null);
		V2_L2.setItems(null);
		V3_L3.setItems(null);
		V1_L1.setVisible(false);
		V2_L2.setVisible(false);
		V3_L3.setVisible(false);
		R1.setVisible(false);
		R2.setVisible(false);
		R3.setVisible(false);

		sizeof -= 3;
		imageviewmanage(next, sizeof);

		if ((sizeof > 0)) {

			prec.setVisible(true);
			a = a + 3;

		} else {
			prec.setVisible(false);
		}
	}

	void setVariantes(List<Variante> vario) {
		this.variantes = vario;

	}

	public void setListeVariantes(List<Variante> listvariantes) {
		this.variantes = listvariantes;
		double temp=0.0;
		View(variantes);
		for (int i = 0; i < variantes.size(); i++) {
			List<Operation> operations1 = getoperations((variantes.get(i).getIdVariante()));
			for (int j = 0; j < operations1.size(); j++) {
				temp += Double.parseDouble(df.format(Float.parseFloat(operations1.get(j).getTime())));
			}
		}
		TotalTime.setText(temp + "");
	}

	public void View(List<Variante> variantes) {

		for (int i = 0; i < variantes.size(); i++) {
			System.out.println(variantes.get(i).toString());
			next.add(variantes.get(i));

		}
		a = next.size();
		imageviewmanage(next, 0);
		if (a > 3) {
			suiv.setVisible(true);
		}

	}

	private void imageviewmanage(List<Variante> v, int size) {
		int add = 0;
		for (int i = size; i < v.size(); i++) {

			if (add > 3) {
				suiv.setVisible(true);

			}

			add++;

			if (add == 1) {
				Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
				V1.setImage(convertToJavaFXImage1);
				V1_L1.setVisible(true);
				TTP1.setVisible(true);
				double timetotal=0.0;
				OperationServices operationServices = new OperationServices();
				List<Operation> operationss1 = operationServices.findAll();
				for (int j = 0; j < operationss1.size(); j++) {
					if (operationss1.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
						timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss1.get(j).getTime())));
					} else {
						System.out.println("vide");
					}
				}
				TTP1.setText("  "+timetotal +" minutes");
				
				T11 = ((size + add - 1) + "");
				List<Operation> operationss = getoperations((v.get(i).getIdVariante()));

				ObservableList<Operation> data = FXCollections.observableArrayList(operationss);
				operation1.setCellValueFactory(new PropertyValueFactory<Operation, String>("description"));
				time1.setCellValueFactory(new PropertyValueFactory<Operation, String>("time"));
				machine1.setCellValueFactory(new PropertyValueFactory<Operation, String>("machine"));

				V1_L1.setItems((ObservableList<Operation>) data);

			}
			if (add == 2) {
				Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
				V2.setImage(convertToJavaFXImage1);
				V2_L2.setVisible(true);
				TTP2.setVisible(true);
				double timetotal=0.0;
				OperationServices operationServices = new OperationServices();
				List<Operation> operationss1 = operationServices.findAll();
				for (int j = 0; j < operationss1.size(); j++) {
					if (operationss1.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
						timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss1.get(j).getTime())));
					} else {
						System.out.println("vide");
					}
				}
				TTP2.setText("  "+timetotal +" minutes");
				T12 = ((size + add - 1) + "");
				List<Operation> operationss = getoperations((v.get(i).getIdVariante()));

				ObservableList<Operation> data2 = FXCollections.observableArrayList(operationss);
				operation2.setCellValueFactory(new PropertyValueFactory<Operation, String>("description"));
				time2.setCellValueFactory(new PropertyValueFactory<Operation, String>("time"));
				machine2.setCellValueFactory(new PropertyValueFactory<Operation, String>("machine"));

				V2_L2.setItems((ObservableList<Operation>) data2);
			}
			if (add == 3) {
				Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
				V3_L3.setVisible(true);
				TTP3.setVisible(true);
				double timetotal=0.0;
				OperationServices operationServices = new OperationServices();
				List<Operation> operationss1 = operationServices.findAll();
				for (int j = 0; j < operationss1.size(); j++) {
					if (operationss1.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
						timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss1.get(j).getTime())));
					} else {
						System.out.println("vide");
					}
				}
				TTP3.setText("  "+timetotal +" minutes");
				V3.setImage(convertToJavaFXImage1);
				T13 = ((size + add - 1) + "");
				List<Operation> operationss = getoperations((v.get(i).getIdVariante()));

				ObservableList<Operation> data3 = FXCollections.observableArrayList(operationss);
				operation3.setCellValueFactory(new PropertyValueFactory<Operation, String>("description"));
				time3.setCellValueFactory(new PropertyValueFactory<Operation, String>("time"));
				machine3.setCellValueFactory(new PropertyValueFactory<Operation, String>("machine"));

				V3_L3.setItems((ObservableList<Operation>) data3);
			}

		}

	}

	private static Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
		WritableImage image = new WritableImage(width, height);
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(raw);
			BufferedImage read = ImageIO.read(bis);
			image = SwingFXUtils.toFXImage(read, null);
		} catch (IOException ex) {

		}
		return image;
	}

	private List<Operation> getoperations(int idvariante) {
		List<Operation> operations = new ArrayList<>();
		OperationServices op = new OperationServices();
		List<Operation> operationsall = op.findAll();
		for (int i = 0; i < operationsall.size(); i++) {
			if ((operationsall.get(i).getVariante_operation().getIdVariante() == idvariante)) {
				operations.add(operationsall.get(i));
			}
		}
		return operations;

	}

	public void setGamme(Gamme gammo) {
		this.gamme = gammo;

	}

	@FXML
	void actualiser(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("variantesnewgamme.fxml"));
		loader.load();
		// gamme.setNom_Gamme(nom_article.getText());
		VarianteGammeController gammecontroller = loader.getController();

		gammecontroller.setListeVariantes(variantes);
		gammecontroller.setGamme(gamme);
		Parent root;
		Stage stage = (Stage) eng.getScene().getWindow();
		root = loader.getRoot();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void V1action(MouseEvent event) {
		editable = Integer.parseInt(T11);
		R1.setVisible(true);
		R2.setVisible(false);
		R3.setVisible(false);
	}

	@FXML
	void V2action(MouseEvent event) {
		editable = Integer.parseInt(T12);
		R2.setVisible(true);
		R1.setVisible(false);
		R3.setVisible(false);
	}

	@FXML
	void V3action(MouseEvent event) {
		editable = Integer.parseInt(T13);
		R3.setVisible(true);
		R1.setVisible(false);
		R2.setVisible(false);
	}

	@FXML
	void deletevariante(ActionEvent event) throws IOException {
		Variante obj = variantes.get(editable);
		variantes.remove(editable);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("variantesnewgamme.fxml"));
		loader.load();
		// gamme.setNom_Gamme(nom_article.getText());
		VarianteGammeController gammecontroller = loader.getController();

		gammecontroller.setListeVariantes(variantes);
		gammecontroller.setGamme(gamme);
		Parent root;
		Stage stage = (Stage) eng.getScene().getWindow();
		root = loader.getRoot();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	@FXML
	void Home(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) prec.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
