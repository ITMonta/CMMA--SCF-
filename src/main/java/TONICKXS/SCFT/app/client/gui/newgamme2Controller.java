package TONICKXS.SCFT.app.client.gui;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;

import TONICKXS.SCFT.entities.Gamme;
import TONICKXS.SCFT.entities.Module;
import TONICKXS.SCFT.entities.Operation;
import TONICKXS.SCFT.entities.Type;
import TONICKXS.SCFT.entities.Variante;
import TONICKXS.SCFT.entities.Variante_Gamme;
import TONICKXS.SCFT.services.ModuleServices;
import TONICKXS.SCFT.services.OperationServices;
import TONICKXS.SCFT.services.VarianteGammeServices;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class newgamme2Controller implements Initializable {
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private AnchorPane parent;
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
	private ImageView Vr41;

	@FXML
	private ImageView Vr42;

	@FXML
	private ImageView Vr31;

	@FXML
	private ImageView Vr21;

	@FXML
	private ImageView Vr32;

	@FXML
	private ImageView Vr11;

	@FXML
	private ImageView Vr22;

	@FXML
	private ImageView Vr33;

	@FXML
	private ImageView Vr12;

	@FXML
	private ImageView Vr23;

	@FXML
	private ImageView Vr34;

	@FXML
	private ImageView Vr13;

	@FXML
	private ImageView Vr24;
	@FXML
	private ImageView suiv;
	@FXML
	private ImageView prec;
	@FXML
	private ImageView Vr14;
	String T11, T12, T13, T14, T21, T22, T23, T24, T31, T32, T33, T34;
	@FXML
	private TableColumn<Module, String> bommodule;
	private List<Variante> next;
	private int a;
	int sizeof;
	int editable;
	private Gamme gammo;
	List<Variante> vario = new ArrayList<>();
	private String ImageUrl;
	private Type type123;
	private List<Module> myresult123 = new ArrayList<Module>();
	DecimalFormat df = new DecimalFormat ( ) ; 
	@FXML
	private TableView<Module> listeModules;

	@FXML
	private ImageView Pdfimagefile;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// Gamme gamme123 = getGammo();
		makeStageDrageable();
		
		df.setMaximumFractionDigits ( 2 ) ; //arrondi à 2 chiffres apres la virgules 
		df.setMinimumFractionDigits ( 2 ) ; 
		df.setDecimalSeparatorAlwaysShown ( true ) ; 
		System.out.println(ImageUrl);
		ModuleServices moduleServicesRemote = new ModuleServices();
		Vr11.setVisible(false);
		Vr12.setVisible(false);
		Vr13.setVisible(false);
		Vr14.setVisible(false);
		Vr21.setVisible(false);
		Vr22.setVisible(false);
		Vr23.setVisible(false);
		Vr24.setVisible(false);
		Vr31.setVisible(false);
		Vr32.setVisible(false);
		Vr33.setVisible(false);
		Vr34.setVisible(false);
		next = new ArrayList<Variante>();
		prec.setVisible(false);
		suiv.setVisible(false);
		editable = 0;
		sizeof = 0;
	}

	@FXML
	void selectModule(ActionEvent event) throws IOException {
		Vr11.imageProperty().set(null);
		Vr12.imageProperty().set(null);
		Vr13.imageProperty().set(null);
		Vr14.imageProperty().set(null);
		Vr21.imageProperty().set(null);
		Vr22.imageProperty().set(null);
		Vr23.imageProperty().set(null);
		Vr24.imageProperty().set(null);
		Vr31.imageProperty().set(null);
		Vr32.imageProperty().set(null);
		Vr33.imageProperty().set(null);
		Vr34.imageProperty().set(null);
		Vr11.setVisible(false);
		Vr12.setVisible(false);
		Vr13.setVisible(false);
		Vr14.setVisible(false);
		Vr21.setVisible(false);
		Vr22.setVisible(false);
		Vr23.setVisible(false);
		Vr24.setVisible(false);
		Vr31.setVisible(false);
		Vr32.setVisible(false);
		Vr33.setVisible(false);
		Vr34.setVisible(false);
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

		List<Variante> variantes = new ArrayList<Variante>();
		next.clear();
		VarianteServices varianteServicesRemote = new VarianteServices();
		variantes = varianteServicesRemote.findAll();

		for (int i = 0; i < variantes.size(); i++) {
			if ((variantes.get(i).getVariante_module().getIdModule() == listeModules.getSelectionModel()
					.getSelectedItem().getIdModule())) {
				next.add(variantes.get(i));
			}
		}
		a = next.size();
		imageviewmanage(next, 0);
		if (a > 12) {
			suiv.setVisible(true);
		}

	}

	@FXML
	void Prec_Var(MouseEvent event) {
		suiv.setVisible(true);
		Vr11.imageProperty().set(null);
		Vr12.imageProperty().set(null);
		Vr13.imageProperty().set(null);
		Vr14.imageProperty().set(null);
		Vr21.imageProperty().set(null);
		Vr22.imageProperty().set(null);
		Vr23.imageProperty().set(null);
		Vr24.imageProperty().set(null);
		Vr31.imageProperty().set(null);
		Vr32.imageProperty().set(null);
		Vr33.imageProperty().set(null);
		Vr34.imageProperty().set(null);
		Vr11.setVisible(false);
		Vr12.setVisible(false);
		Vr13.setVisible(false);
		Vr14.setVisible(false);
		Vr21.setVisible(false);
		Vr22.setVisible(false);
		Vr23.setVisible(false);
		Vr24.setVisible(false);
		Vr31.setVisible(false);
		Vr32.setVisible(false);
		Vr33.setVisible(false);
		Vr34.setVisible(false);
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
	void Suiv_Var(MouseEvent event) {
		prec.setVisible(true);
		Vr11.imageProperty().set(null);
		Vr12.imageProperty().set(null);
		Vr13.imageProperty().set(null);
		Vr14.imageProperty().set(null);
		Vr21.imageProperty().set(null);
		Vr22.imageProperty().set(null);
		Vr23.imageProperty().set(null);
		Vr24.imageProperty().set(null);
		Vr31.imageProperty().set(null);
		Vr32.imageProperty().set(null);
		Vr33.imageProperty().set(null);
		Vr34.imageProperty().set(null);
		Vr11.setVisible(false);
		Vr12.setVisible(false);
		Vr13.setVisible(false);
		Vr14.setVisible(false);
		Vr21.setVisible(false);
		Vr22.setVisible(false);
		Vr23.setVisible(false);
		Vr24.setVisible(false);
		Vr31.setVisible(false);
		Vr32.setVisible(false);
		Vr33.setVisible(false);
		Vr34.setVisible(false);
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

	private void imageviewmanage(List<Variante> v, int size) {
		int add = 0;
		for (int i = size; i < v.size(); i++) {
			if (v.get(i).getVariante_module().getIdModule() == listeModules.getSelectionModel().getSelectedItem()
					.getIdModule()) {

				if (add > 12) {
					suiv.setVisible(true);

				}

				add++;
				if (add == 1) {
					Vr11.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr11.setImage(convertToJavaFXImage1);
					TT11.setVisible(true);
					TP11.setVisible(true);
					double timetotal=0.0;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP11.setText("  "+timetotal +" minutes");
					T11 = (v.get(i).getIdVariante() + "");
				}
				if (add == 2) {
					Vr12.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr12.setImage(convertToJavaFXImage1);
					TT12.setVisible(true);
					TP12.setVisible(true);
					double timetotal=0.0;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP12.setText("  "+timetotal +" minutes");
					T12 = (v.get(i).getIdVariante() + "");
				}
				if (add == 3) {
					Vr13.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr13.setImage(convertToJavaFXImage1);
					TT13.setVisible(true);
					TP13.setVisible(true);
					double timetotal=0.0;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP13.setText("  "+timetotal +" minutes");
					T13 = (v.get(i).getIdVariante() + "");
				}
				if (add == 4) {
					Vr14.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr14.setImage(convertToJavaFXImage1);
					TT14.setVisible(true);
					TP14.setVisible(true);
					double timetotal=0.0;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP14.setText("  "+timetotal +" minutes");
					T14 = (v.get(i).getIdVariante() + "");
				}
				if (add == 5) {
					Vr21.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr21.setImage(convertToJavaFXImage1);
					TT21.setVisible(true);
					TP21.setVisible(true);
					double timetotal=0.0;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP21.setText("  "+timetotal +" minutes");
					T21 = (v.get(i).getIdVariante() + "");
				}
				if (add == 6) {
					Vr22.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr22.setImage(convertToJavaFXImage1);
					TT22.setVisible(true);
					TP22.setVisible(true);
					double timetotal=0.0;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP22.setText("  "+timetotal +" minutes");
					T22 = (v.get(i).getIdVariante() + "");
				}
				if (add == 7) {
					Vr23.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr23.setImage(convertToJavaFXImage1);
					TT23.setVisible(true);
					TP23.setVisible(true);
					double timetotal=0.0;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP23.setText("  "+timetotal +" minutes");
					T23 = (v.get(i).getIdVariante() + "");
				}
				if (add == 8) {
					Vr24.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr24.setImage(convertToJavaFXImage1);
					TT24.setVisible(true);
					TP24.setVisible(true);
					double timetotal=0.0;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP24.setText("  "+timetotal +" minutes");
					T24 = (v.get(i).getIdVariante() + "");
				}
				if (add == 9) {
					Vr31.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr31.setImage(convertToJavaFXImage1);
					TT31.setVisible(true);
					TP31.setVisible(true);
					double timetotal=0f;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP31.setText("  "+timetotal +" minutes");
					T31 = (v.get(i).getIdVariante() + "");
				}
				if (add == 10) {
					Vr32.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr32.setImage(convertToJavaFXImage1);
					TT32.setVisible(true);
					TP32.setVisible(true);
					double timetotal=0.0;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP32.setText("  "+timetotal +" minutes");
					T32 = (v.get(i).getIdVariante() + "");
				}
				if (add == 11) {
					Vr33.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr33.setImage(convertToJavaFXImage1);
					TT33.setVisible(true);
					TP33.setVisible(true);
					double timetotal=0.0;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP33.setText("  "+timetotal +" minutes");
					T33 = (v.get(i).getIdVariante() + "");
				}
				if (add == 12) {
					Vr34.setVisible(true);
					Image convertToJavaFXImage1 = convertToJavaFXImage(v.get(i).getPicture(), 175, 139);
					Vr34.setImage(convertToJavaFXImage1);
					TT34.setVisible(true);
					TP34.setVisible(true);
					double timetotal=0.0;
					OperationServices operationServices = new OperationServices();
					List<Operation> operationss = operationServices.findAll();
					for (int j = 0; j < operationss.size(); j++) {
						if (operationss.get(j).getVariante_operation().getIdVariante() == v.get(i).getIdVariante()) {
							timetotal+=Double.parseDouble(df.format(Float.parseFloat(operationss.get(j).getTime())));
						} else {
							System.out.println("vide");
						}
					}
					TP34.setText("  "+timetotal +" minutes");
					T34 = (v.get(i).getIdVariante() + "");
				}

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

	@FXML
	void G11(MouseEvent event) {

		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = (varianteServicesRemote.find(Integer.parseInt(T11)));
		R11.setVisible(true);
		vario.add(variante);
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
	}

	@FXML
	void G12(MouseEvent event) {
		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = (varianteServicesRemote.find(Integer.parseInt(T12)));
		R12.setVisible(true);
		vario.add(variante);
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
	}

	@FXML
	void G13(MouseEvent event) {
		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = (varianteServicesRemote.find(Integer.parseInt(T13)));
		R13.setVisible(true);
		vario.add(variante);
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
	}

	@FXML
	void G14(MouseEvent event) {
		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = (varianteServicesRemote.find(Integer.parseInt(T14)));
		R14.setVisible(true);
		vario.add(variante);
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
	}

	@FXML
	void G24(MouseEvent event) {
		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = (varianteServicesRemote.find(Integer.parseInt(T24)));
		R24.setVisible(true);
		vario.add(variante);
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
	}

	@FXML
	void G23(MouseEvent event) {
		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = (varianteServicesRemote.find(Integer.parseInt(T23)));
		R23.setVisible(true);
		vario.add(variante);
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

	}

	@FXML
	void G22(MouseEvent event) {
		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = varianteServicesRemote.find(Integer.parseInt(T22));
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
		vario.add(variante);

	}

	@FXML
	void G21(MouseEvent event) {
		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = (varianteServicesRemote.find(Integer.parseInt(T21)));
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
		vario.add(variante);
	}

	@FXML
	void G34(MouseEvent event) {
		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = (varianteServicesRemote.find(Integer.parseInt(T34)));
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
		vario.add(variante);
	}

	@FXML
	void G33(MouseEvent event) {
		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = (varianteServicesRemote.find(Integer.parseInt(T33)));
		R33.setVisible(true);
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
		vario.add(variante);
	}

	@FXML
	void G32(MouseEvent event) {
		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = (varianteServicesRemote.find(Integer.parseInt(T32)));
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
		vario.add(variante);
	}

	@FXML
	void G31(MouseEvent event) {
		VarianteServices varianteServicesRemote = new VarianteServices();
		Variante variante = (varianteServicesRemote.find(Integer.parseInt(T31)));
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
		vario.add(variante);
	}

	@FXML
	void showotherwindow(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("variantesnewgamme.fxml"));
		loader.load();
		// gamme.setNom_Gamme(nom_article.getText());
		VarianteGammeController gammecontroller = loader.getController();
		System.out.println(vario.size());

		gammecontroller.setListeVariantes(vario);
		gammecontroller.setGamme(gammo);
		Parent root = loader.getRoot();
		Stage secondStage = new Stage();
		secondStage.setScene(new Scene(root, 1201, 821));
		secondStage.show();

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

	public void seturl(String url) {
		this.ImageUrl = url;
		Image img = new Image(url);
		Pdfimagefile.setImage(img);
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public Gamme getGammo() {
		return gammo;
	}

	public void setGammo(Gamme gammo) {
		this.gammo = gammo;
		ModuleServices moduleServicesRemote = new ModuleServices();
		List<Module> myresult1 = moduleServicesRemote.findAll();
		for (int i = 0; i < myresult1.size(); i++) {
			if (gammo.getType_gamme().getIdType() == (myresult1.get(i).getModule_type().getIdType())) {
				myresult123.add(myresult1.get(i));
				System.out.println(myresult123);
			}

		}

		ObservableList<Module> data = FXCollections.observableArrayList(myresult123);
		bommodule.setCellValueFactory(new PropertyValueFactory<Module, String>("Nom_Module"));
		listeModules.setItems((ObservableList<Module>) data);
	}

	public void setGammo2(Gamme gammo) {
		this.gammo = gammo;
		ModuleServices moduleServicesRemote = new ModuleServices();
		List<Module> myresult1 = moduleServicesRemote.findAll();
		for (int i = 0; i < myresult1.size(); i++) {
			if (gammo.getType_gamme().getIdType() == (myresult1.get(i).getModule_type().getIdType())) {
				myresult123.add(myresult1.get(i));
				System.out.println(myresult123);
			}
			}
		ObservableList<Module> data = FXCollections.observableArrayList(myresult123);
		bommodule.setCellValueFactory(new PropertyValueFactory<Module, String>("Nom_Module"));
		listeModules.setItems((ObservableList<Module>) data);
			for (int j = 0; j < gammo.getVariantes().size(); j++) {
				vario.add(gammo.getVariantes().get(j).getGamme_variantes());
			}

		
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

}
