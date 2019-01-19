package TONICKXS.SCFT.app.client.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXComboBox;

import TONICKXS.SCFT.entities.Module;
import TONICKXS.SCFT.entities.Operation;
import TONICKXS.SCFT.entities.Type;

import TONICKXS.SCFT.services.OperationServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class varianteController implements Initializable {

	@FXML
	private ImageView img;

	@FXML
	private TableView<Operation> operations;
	
	@FXML
	private TableColumn<Operation, String> machine;

	@FXML
	private TableColumn<Operation, String> Operation;

	@FXML
	private TableColumn<Operation, String> time;
	
	@FXML
	private JFXComboBox<Module> module;

	@FXML
	private JFXComboBox<Type> type;

	private List<Type> Listetypes;
	private List<Operation> listeoperations;
	private List<Module> listeModules;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	
	}

	/* private List<Type> getTypes() throws NamingException {
		Context context = new InitialContext();
		TypeServicesRemote typeServicesRemote = (TypeServicesRemote) context
				.lookup("SCFT-ear/SCFT-service/TypeServices!TONICKXS.SCFT.services.TypeServicesRemote");
		Listetypes = typeServicesRemote.findAll();
		System.out.println("provided types");
		return Listetypes;

	}

	private List<Module> getModule() throws NamingException {
		Context context = new InitialContext();
		ModuleServicesRemote moduleServicesRemote = (ModuleServicesRemote) context
				.lookup("SCFT-ear/SCFT-service/ModuleServices!TONICKXS.SCFT.services.ModuleServicesRemote");
		listeModules = moduleServicesRemote.findAll();
		return listeModules;

	}
*/
	private List<Operation> getOperation()  {
		OperationServices operationServicesRemote = new OperationServices();
		listeoperations = operationServicesRemote.findAll();
		return listeoperations;

	}

	@FXML
	void Validate(ActionEvent event) {

	}

}
