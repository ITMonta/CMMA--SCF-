package TONICKXS.SCFT.app.client.gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

import TONICKXS.SCFT.entities.Module;
import TONICKXS.SCFT.entities.Operation;
import TONICKXS.SCFT.entities.Type;
import TONICKXS.SCFT.entities.Variante;
import TONICKXS.SCFT.services.ModuleServices;
import TONICKXS.SCFT.services.OperationServices;
import TONICKXS.SCFT.services.TypeServices;
import TONICKXS.SCFT.services.VarianteServices;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ListeVariantesController implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane parent;
	@FXML
	private ImageView reduce;
	@FXML
	private JFXTextField T11;
	@FXML
	private JFXTextField T12;
	@FXML
	private JFXTextField T13;
	@FXML
	private JFXTextField T14;
	@FXML
	private JFXTextField T21;
	@FXML
	private JFXTextField T22;
	@FXML
	private JFXTextField T23;
	@FXML
	private JFXTextField T24;
	@FXML
	private JFXTextField T31;
	@FXML
	private JFXTextField T32;
	@FXML
	private JFXTextField T33;
	@FXML
	private JFXTextField T34;

	@FXML
	private ImageView V32;

	@FXML
	private ImageView V21;

	@FXML
	private ImageView V31;

	@FXML
	private ImageView V34;

	@FXML
	private ImageView V23;

	@FXML
	private ImageView V12;

	@FXML
	private ImageView V33;

	@FXML
	private ImageView V22;

	@FXML
	private ImageView V11;

	@FXML
	private ImageView V24;

	@FXML
	private ImageView V13;
	@FXML
	private ImageView V14;
	@FXML
	private Rectangle R11;
	@FXML
	private Rectangle R12;
	@FXML
	private Rectangle R13;
	@FXML
	private Rectangle R14;
	@FXML
	private Rectangle R21;
	@FXML
	private Rectangle R22;
	@FXML
	private Rectangle R23;
	@FXML
	private Rectangle R24;
	@FXML
	private Rectangle R31;
	@FXML
	private Rectangle R32;
	@FXML
	private Rectangle R33;
	@FXML
	private Rectangle R34;
	@FXML
	private Text TT11;
	@FXML
	private Text TT12;
	@FXML
	private Text TT13;
	@FXML
	private Text TT14;
	@FXML
	private Text TT21;
	@FXML
	private Text TT22;
	@FXML
	private Text TT23;
	@FXML
	private Text TT24;
	@FXML
	private Text TT31;
	@FXML
	private Text TT32;
	@FXML
	private Text TT33;
	@FXML
	private Text TT34;
	@FXML
	private JFXTextField TP11;
	@FXML
	private JFXTextField TP12;
	@FXML
	private JFXTextField TP13;
	@FXML
	private JFXTextField TP14;
	@FXML
	private JFXTextField TP21;
	@FXML
	private JFXTextField TP22;
	@FXML
	private JFXTextField TP23;
	@FXML
	private JFXTextField TP24;
	@FXML
	private JFXTextField TP31;
	@FXML
	private JFXTextField TP32;
	@FXML
	private JFXTextField TP33;
	@FXML
	private JFXTextField TP34;
	@FXML
	private JFXComboBox<Type> Types;
	@FXML
	private ImageView prec;
	@FXML
	private ImageView suiv;
	@FXML
	private JFXComboBox<Module> Modules;
	@FXML
	private JFXButton duplic;
	@FXML
	private JFXButton v;
	@FXML
	private JFXButton dele;
	@FXML
	private ImageView close;
	private List<Variante> next;
	private int a;
	int sizeof;
	int editable;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		makeStageDrageable();
		prec.setVisible(false);
		suiv.setVisible(false);
		dele.setDisable(true);
		V11.setVisible(false);
		V12.setVisible(false);
		V13.setVisible(false);
		V14.setVisible(false);
		V21.setVisible(false);
		V22.setVisible(false);
		V23.setVisible(false);
		V24.setVisible(false);
		V31.setVisible(false);
		V32.setVisible(false);
		V33.setVisible(false);
		V34.setVisible(false);
		v.setDisable(true);
		duplic.setDisable(true);
		next = new ArrayList<Variante>();
		editable = 0;
		sizeof = 0;

		TypeServices typeServicesRemote = new TypeServices();
		List<Type> t = typeServicesRemote.findAll();

		List<Module> m2 = null;
		for (int i = 0; i < t.size(); i++) {
			Types.getItems().add(t.get(i));
		}

	}

	@FXML
	void NextVariantes(MouseEvent event) {
		prec.setVisible(true);
		V11.imageProperty().set(null);
		V12.imageProperty().set(null);
		V13.imageProperty().set(null);
		V14.imageProperty().set(null);
		V21.imageProperty().set(null);
		V22.imageProperty().set(null);
		V23.imageProperty().set(null);
		V24.imageProperty().set(null);
		V31.imageProperty().set(null);
		V32.imageProperty().set(null);
		V33.imageProperty().set(null);
		V34.imageProperty().set(null);
		V11.setVisible(false);
		V12.setVisible(false);
		V13.setVisible(false);
		V14.setVisible(false);
		V21.setVisible(false);
		V22.setVisible(false);
		V23.setVisible(false);
		V24.setVisible(false);
		V31.setVisible(false);
		V32.setVisible(false);
		V33.setVisible(false);
		V34.setVisible(false);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		TT11.setVisible(false);
		TT12.setVisible(false);
		TT13.setVisible(false);
		TT14.setVisible(false);
		TT21.setVisible(false);
		TT22.setVisible(false);
		TT23.setVisible(false);
		TT24.setVisible(false);
		TT31.setVisible(false);
		TT32.setVisible(false);
		TT33.setVisible(false);
		TT34.setVisible(false);
		TP11.setVisible(false);
		TP12.setVisible(false);
		TP13.setVisible(false);
		TP14.setVisible(false);
		TP21.setVisible(false);
		TP22.setVisible(false);
		TP23.setVisible(false);
		TP24.setVisible(false);
		TP31.setVisible(false);
		TP32.setVisible(false);
		TP33.setVisible(false);
		TP34.setVisible(false);
		sizeof += 12;
		imageviewmanage(next, sizeof);

		if ((a > 24)) {

			suiv.setVisible(true);
			a = a - 12;

		} else {
			suiv.setVisible(false);
		}
	}

	@FXML
	void PrecedVariantes(MouseEvent event) {

		suiv.setVisible(true);
		V11.imageProperty().set(null);
		V12.imageProperty().set(null);
		V13.imageProperty().set(null);
		V14.imageProperty().set(null);
		V21.imageProperty().set(null);
		V22.imageProperty().set(null);
		V23.imageProperty().set(null);
		V24.imageProperty().set(null);
		V31.imageProperty().set(null);
		V32.imageProperty().set(null);
		V33.imageProperty().set(null);
		V34.imageProperty().set(null);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		TT11.setVisible(false);
		TT12.setVisible(false);
		TT13.setVisible(false);
		TT14.setVisible(false);
		TT21.setVisible(false);
		TT22.setVisible(false);
		TT23.setVisible(false);
		TT24.setVisible(false);
		TT31.setVisible(false);
		TT32.setVisible(false);
		TT33.setVisible(false);
		TT34.setVisible(false);
		TP11.setVisible(false);
		TP12.setVisible(false);
		TP13.setVisible(false);
		TP14.setVisible(false);
		TP21.setVisible(false);
		TP22.setVisible(false);
		TP23.setVisible(false);
		TP24.setVisible(false);
		TP31.setVisible(false);
		TP32.setVisible(false);
		TP33.setVisible(false);
		TP34.setVisible(false);
		sizeof -= 12;
		imageviewmanage(next, sizeof);

		if ((sizeof > 0)) {

			prec.setVisible(true);
			a = a + 12;

		} else {
			prec.setVisible(false);
		}

	}

	@FXML
	void DisplayVariantes(ActionEvent event) {
		suiv.setVisible(false);
		prec.setVisible(false);
		V11.imageProperty().set(null);
		V12.imageProperty().set(null);
		V13.imageProperty().set(null);
		V14.imageProperty().set(null);
		V21.imageProperty().set(null);
		V22.imageProperty().set(null);
		V23.imageProperty().set(null);
		V24.imageProperty().set(null);
		V31.imageProperty().set(null);
		V32.imageProperty().set(null);
		V33.imageProperty().set(null);
		V34.imageProperty().set(null);
		V11.setVisible(false);
		V12.setVisible(false);
		V13.setVisible(false);
		V14.setVisible(false);
		V21.setVisible(false);
		V22.setVisible(false);
		V23.setVisible(false);
		V24.setVisible(false);
		V31.setVisible(false);
		V32.setVisible(false);
		V33.setVisible(false);
		V34.setVisible(false);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		TT11.setVisible(false);
		TT12.setVisible(false);
		TT13.setVisible(false);
		TT14.setVisible(false);
		TT21.setVisible(false);
		TT22.setVisible(false);
		TT23.setVisible(false);
		TT24.setVisible(false);
		TT31.setVisible(false);
		TT32.setVisible(false);
		TT33.setVisible(false);
		TT34.setVisible(false);
		TP11.setVisible(false);
		TP12.setVisible(false);
		TP13.setVisible(false);
		TP14.setVisible(false);
		TP21.setVisible(false);
		TP22.setVisible(false);
		TP23.setVisible(false);
		TP24.setVisible(false);
		TP31.setVisible(false);
		TP32.setVisible(false);
		TP33.setVisible(false);
		TP34.setVisible(false);
		VarianteServices varianteServicesRemote = new VarianteServices();
		List<Variante> variantes = varianteServicesRemote.findAll();
		imageviewmanage(variantes, 0);
		next = new ArrayList<>();
		for (int i = 0; i < variantes.size(); i++) {
			if ((variantes.get(i).getVariante_module().getIdModule() == Modules.getSelectionModel().getSelectedItem()
					.getIdModule())
					&& (variantes.get(i).getVariante_module().getModule_type().getIdType() == Types.getSelectionModel()
							.getSelectedItem().getIdType())) {
				next.add(variantes.get(i));
				
			}
		}
		imageviewmanage(next, 0);
		a = next.size();
		if (a > 12) {
			suiv.setVisible(true);
		}

	}

	@FXML
	void populateModules(ActionEvent event) {
		Modules.getItems().clear();

		ModuleServices moduleServicesRemote = new ModuleServices();
		List<Module> m = moduleServicesRemote.findAll();
		for (int i = 0; i < m.size(); i++) {
			if (m.get(i).getModule_type().getIdType() == ((Types.getSelectionModel().getSelectedItem().getIdType()))) {
				Modules.getItems().add(m.get(i));

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

	private void imageviewmanage(List<Variante> v, int size) {
		int add = 0;
		for (int i = size; i < v.size(); i++) {
			if ((v.get(i).getVariante_module().getIdModule() == Modules.getSelectionModel().getSelectedItem()
					.getIdModule())
					&& (v.get(i).getVariante_module().getModule_type().getIdType() == Types.getSelectionModel()
							.getSelectedItem().getIdType())) {

				if (add > 12) {
					suiv.setVisible(true);

				}

				add++;
				if (add == 1) {
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V11.setVisible(true);
					V11.setImage(convertToJavaFXImage1);
					TT11.setVisible(true);
					TP11.setVisible(true);
					TP11.setText("  " + v.get(i).getNom_variante());
					T11.setText(v.get(i).getIdVariante() + "");
				}
				if (add == 2) {
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V12.setVisible(true);
					V12.setImage(convertToJavaFXImage1);
					TT12.setVisible(true);
					TP12.setVisible(true);
					TP12.setText("  " + v.get(i).getNom_variante());
					T12.setText(v.get(i).getIdVariante() + "");
				}
				if (add == 3) {
					V13.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V13.setImage(convertToJavaFXImage1);
					TT13.setVisible(true);
					TP13.setVisible(true);
					TP13.setText("  " + v.get(i).getNom_variante());
					T13.setText(v.get(i).getIdVariante() + "");
				}
				if (add == 4) {
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V14.setVisible(true);
					V14.setImage(convertToJavaFXImage1);
					TT14.setVisible(true);
					TP14.setVisible(true);
					TP14.setText("  " + v.get(i).getNom_variante());
					T14.setText(v.get(i).getIdVariante() + "");
				}
				if (add == 5) {
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V21.setVisible(true);
					V21.setImage(convertToJavaFXImage1);
					TT21.setVisible(true);
					TP21.setVisible(true);
					TP21.setText("  " + v.get(i).getNom_variante());
					T21.setText(v.get(i).getIdVariante() + "");
				}
				if (add == 6) {
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V22.setVisible(true);
					V22.setImage(convertToJavaFXImage1);
					TT22.setVisible(true);
					TP22.setVisible(true);
					TP22.setText("  " + v.get(i).getNom_variante());
					T22.setText(v.get(i).getIdVariante() + "");
				}
				if (add == 7) {
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V23.setVisible(true);
					V23.setImage(convertToJavaFXImage1);
					TT23.setVisible(true);
					TP23.setVisible(true);
					TP23.setText("  " + v.get(i).getNom_variante());
					T23.setText(v.get(i).getIdVariante() + "");
				}
				if (add == 8) {
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V24.setVisible(true);
					V24.setImage(convertToJavaFXImage1);
					TT24.setVisible(true);
					TP24.setVisible(true);
					TP24.setText("  " + v.get(i).getNom_variante());
					T24.setText(v.get(i).getIdVariante() + "");
				}
				if (add == 9) {
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V31.setVisible(true);
					V31.setImage(convertToJavaFXImage1);
					TT31.setVisible(true);
					TP31.setVisible(true);
					TP31.setText("  " + v.get(i).getNom_variante());
					T31.setText(v.get(i).getIdVariante() + "");
				}
				if (add == 10) {
					V32.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V32.setImage(convertToJavaFXImage1);
					TT32.setVisible(true);
					TP32.setVisible(true);
					TP32.setText("  " + v.get(i).getNom_variante());
					T32.setText(v.get(i).getIdVariante() + "");
				}
				if (add == 11) {
					V33.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V33.setImage(convertToJavaFXImage1);
					TT33.setVisible(true);
					TP33.setVisible(true);
					TP33.setText("  " + v.get(i).getNom_variante());
					T33.setText(v.get(i).getIdVariante() + "");
				}
				if (add == 12) {
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					V34.setVisible(true);
					V34.setImage(convertToJavaFXImage1);
					TT34.setVisible(true);
					TP34.setVisible(true);
					TP34.setText("  " + v.get(i).getNom_variante());
					T34.setText(v.get(i).getIdVariante() + "");
				}

			}

		}

	}

	@FXML
	void deletevariante(ActionEvent event) throws IOException {
		try {
			VarianteServices varianteServicesRemote = new VarianteServices();
			Variante v = varianteServicesRemote.find(editable);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Attention !!");
			alert.setHeaderText("Vous allez supprimer la variante :" + v.getNom_variante() + "");
			alert.setContentText("Vous êtes sure?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				System.out.println(v.toString());
				varianteServicesRemote.delete(v);
				// varianteServicesRemote.delete2(editable);
				V11.imageProperty().set(null);
				V12.imageProperty().set(null);
				V13.imageProperty().set(null);
				V14.imageProperty().set(null);
				V21.imageProperty().set(null);
				V22.imageProperty().set(null);
				V23.imageProperty().set(null);
				V24.imageProperty().set(null);
				V31.imageProperty().set(null);
				V32.imageProperty().set(null);
				V33.imageProperty().set(null);
				V34.imageProperty().set(null);
				V11.setVisible(false);
				V12.setVisible(false);
				V13.setVisible(false);
				V14.setVisible(false);
				V21.setVisible(false);
				V22.setVisible(false);
				V23.setVisible(false);
				V24.setVisible(false);
				V31.setVisible(false);
				V32.setVisible(false);
				V33.setVisible(false);
				V34.setVisible(false);
				R11.setVisible(false);
				R12.setVisible(false);
				R13.setVisible(false);
				R14.setVisible(false);
				R21.setVisible(false);
				R22.setVisible(false);
				R23.setVisible(false);
				R24.setVisible(false);
				R31.setVisible(false);
				R32.setVisible(false);
				R33.setVisible(false);
				R34.setVisible(false);
				TT11.setVisible(false);
				TT12.setVisible(false);
				TT13.setVisible(false);
				TT14.setVisible(false);
				TT21.setVisible(false);
				TT22.setVisible(false);
				TT23.setVisible(false);
				TT24.setVisible(false);
				TT31.setVisible(false);
				TT32.setVisible(false);
				TT33.setVisible(false);
				TT34.setVisible(false);
				TP11.setVisible(false);
				TP12.setVisible(false);
				TP13.setVisible(false);
				TP14.setVisible(false);
				TP21.setVisible(false);
				TP22.setVisible(false);
				TP23.setVisible(false);
				TP24.setVisible(false);
				TP31.setVisible(false);
				TP32.setVisible(false);
				TP33.setVisible(false);
				TP34.setVisible(false);
				imageviewmanage(varianteServicesRemote.findAll(), 0);
				dele.setDisable(true);
				duplic.setDisable(true);
				Parent root;
				Stage stage = (Stage) dele.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("ListeVariantesFXML.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} else {
				Parent root;
				Stage stage = (Stage) dele.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("ListeVariantesFXML.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning message");
			alert.setHeaderText("Impossible de supprimer ");
			alert.setContentText("variante existe dans une gamme !! ");

			alert.showAndWait();
			Parent root;
			Stage stage = (Stage) dele.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("ListeVariantesFXML.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}

	}

	@FXML
	void duplicatevariante(ActionEvent event) throws IOException {

		VarianteServices varianteServicesRemote = new VarianteServices();
		VarianteServices varianteServicesRemote2 = new VarianteServices();
		List<Variante> variantes1 = varianteServicesRemote.findAll();
		Variante v = varianteServicesRemote.find(editable);
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation de duplication");
		alert.setHeaderText("Vous allez dupliquer une variante");
		alert.setContentText("êtes vous sure?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			v.setIdVariante(variantes1.size() + 2);
			OperationServices moduleServices = new OperationServices();
			List<Operation> modules12345 = moduleServices.findAll();
			List<Operation> omp = new ArrayList<>();
			for(int j=0;j<modules12345.size();j++)
			{
				if( modules12345.get(j).getVariante_operation().getIdVariante() == editable)
				{
					Operation ops = new Operation();
					ops.setDescription(modules12345.get(j).getDescription());
					ops.setMachine(modules12345.get(j).getMachine());
					ops.setNumero(modules12345.get(j).getNumero());
					ops.setTime(modules12345.get(j).getTime());
					ops.setVariante_operation(v);
					omp.add(ops);
				}
			}
			v.setOperations(omp);
			varianteServicesRemote2.add(v);
			
			

		} else {

		}

		V11.imageProperty().set(null);
		V12.imageProperty().set(null);
		V13.imageProperty().set(null);
		V14.imageProperty().set(null);
		V21.imageProperty().set(null);
		V22.imageProperty().set(null);
		V23.imageProperty().set(null);
		V24.imageProperty().set(null);
		V31.imageProperty().set(null);
		V32.imageProperty().set(null);
		V33.imageProperty().set(null);
		V34.imageProperty().set(null);
		V11.setVisible(false);
		V12.setVisible(false);
		V13.setVisible(false);
		V14.setVisible(false);
		V21.setVisible(false);
		V22.setVisible(false);
		V23.setVisible(false);
		V24.setVisible(false);
		V31.setVisible(false);
		V32.setVisible(false);
		V33.setVisible(false);
		V34.setVisible(false);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		TT11.setVisible(false);
		TT12.setVisible(false);
		TT13.setVisible(false);
		TT14.setVisible(false);
		TT21.setVisible(false);
		TT22.setVisible(false);
		TT23.setVisible(false);
		TT24.setVisible(false);
		TT31.setVisible(false);
		TT32.setVisible(false);
		TT33.setVisible(false);
		TT34.setVisible(false);
		TP11.setVisible(false);
		TP12.setVisible(false);
		TP13.setVisible(false);
		TP14.setVisible(false);
		TP21.setVisible(false);
		TP22.setVisible(false);
		TP23.setVisible(false);
		TP24.setVisible(false);
		TP31.setVisible(false);
		TP32.setVisible(false);
		TP33.setVisible(false);
		TP34.setVisible(false);
		imageviewmanage(varianteServicesRemote.findAll(), 0);
		dele.setDisable(true);
		duplic.setDisable(true);

		Parent root;
		Stage stage = (Stage) duplic.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ListeVariantesFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void V31clicked(MouseEvent event) {
		dele.setDisable(false);
		R31.setVisible(true);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		duplic.setDisable(false);
		v.setDisable(false);
		editable = Integer.parseInt(T31.getText());
	}

	@FXML
	void V32clicked(MouseEvent event) {
		dele.setDisable(false);
		R32.setVisible(true);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		duplic.setDisable(false);
		v.setDisable(false);
		editable = Integer.parseInt(T32.getText());
	}

	@FXML
	void V33clicked(MouseEvent event) {
		dele.setDisable(false);
		R33.setVisible(true);
		duplic.setDisable(false);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R34.setVisible(false);
		v.setDisable(false);
		editable = Integer.parseInt(T33.getText());
	}

	@FXML
	void V34clicked(MouseEvent event) {
		dele.setDisable(false);
		R34.setVisible(true);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		duplic.setDisable(false);
		v.setDisable(false);
		editable = Integer.parseInt(T34.getText());
	}

	@FXML
	void V21clicked(MouseEvent event) {
		dele.setDisable(false);
		R21.setVisible(true);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		duplic.setDisable(false);
		v.setDisable(false);
		editable = Integer.parseInt(T21.getText());
	}

	@FXML
	void V22clicked(MouseEvent event) {
		dele.setDisable(false);
		R22.setVisible(true);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		duplic.setDisable(false);
		v.setDisable(false);
		editable = Integer.parseInt(T22.getText());
	}

	@FXML
	void V23clicked(MouseEvent event) {
		dele.setDisable(false);
		R23.setVisible(true);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		duplic.setDisable(false);
		v.setDisable(false);
		editable = Integer.parseInt(T23.getText());
	}

	@FXML
	void V24clicked(MouseEvent event) {
		dele.setDisable(false);
		R24.setVisible(true);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		duplic.setDisable(false);
		v.setDisable(false);
		editable = Integer.parseInt(T24.getText());
	}

	@FXML
	void V14clicked(MouseEvent event) {
		dele.setDisable(false);
		R14.setVisible(true);
		R11.setVisible(false);
		R12.setVisible(false);
		R13.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		duplic.setDisable(false);
		v.setDisable(false);
		editable = Integer.parseInt(T14.getText());
	}

	@FXML
	void V13clicked(MouseEvent event) {
		dele.setDisable(false);
		duplic.setDisable(false);
		R13.setVisible(true);
		R11.setVisible(false);
		R12.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		v.setDisable(false);
		editable = Integer.parseInt(T13.getText());
	}

	@FXML
	void V12clicked(MouseEvent event) {
		dele.setDisable(false);
		duplic.setDisable(false);
		R12.setVisible(true);
		R11.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		v.setDisable(false);
		editable = Integer.parseInt(T12.getText());
	}

	@FXML
	void V11clicked(MouseEvent event) {
		dele.setDisable(false);
		duplic.setDisable(false);
		v.setDisable(false);
		R11.setVisible(true);
		R12.setVisible(false);
		R13.setVisible(false);
		R14.setVisible(false);
		R21.setVisible(false);
		R22.setVisible(false);
		R23.setVisible(false);
		R24.setVisible(false);
		R31.setVisible(false);
		R32.setVisible(false);
		R33.setVisible(false);
		R34.setVisible(false);
		editable = Integer.parseInt(T11.getText());
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
	void listgamme(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) duplic.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("GammeList.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void listtype(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) duplic.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("ListType.fxml"));
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
	void nvlvariante(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) duplic.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("varianteAffichage.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void nvlgamme(MouseEvent event) throws IOException {
		Parent root;
		Stage stage = (Stage) duplic.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource("NewGamme1.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void Modif(ActionEvent event) throws IOException {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Modification");
		alert.setHeaderText("Vous allez modifier une variante");
		alert.setContentText("êtes vous sure ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			VarianteServices varianteServicesRemote = new VarianteServices();
			VarianteServices varianteServicesRemote2 = new VarianteServices();
			List<Variante> variantes1 = varianteServicesRemote.findAll();
			Variante v = varianteServicesRemote.find(editable);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("varianteAffichage.fxml"));
			loader.load();
			varianteaffichageController controller = loader.getController();
			controller.setVariante(v);
			Parent root = loader.getRoot();

			Stage stage = (Stage) duplic.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			Parent root;
			Stage stage = (Stage) duplic.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("ListeVariantesFXML.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
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
