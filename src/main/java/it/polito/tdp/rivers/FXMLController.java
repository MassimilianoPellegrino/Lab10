/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCombo(ActionEvent event) {
    	River r = this.boxRiver.getValue();
    	this.txtStartDate.setText(model.getPrimaMisurazione(r).toString());
    	this.txtEndDate.setText(model.getUltimaMisurazione(r).toString());
    	this.txtNumMeasurements.setText(model.getNumeroMisurazioni(r).toString());
    	this.txtFMed.setText(String.format("%.2f", model.getFlussoMedio(r)));
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	this.txtResult.clear();
    	double k = 0;
    	River r = null;
    	try {
    		k = Double.parseDouble(this.txtK.getText());
    		r = this.boxRiver.getValue();
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Inserire un numero valido");
    		return;
    	}catch(NullPointerException e) {
    		this.txtResult.setText("Selezionare un fiume dal menu");
    		return;
    	}
    	
    	TreeMap<Integer, Double> mapSim = this.model.getSim(r, k);
    	int giorni = mapSim.firstKey();
    	double media = mapSim.get(giorni);
    	
    	this.txtResult.setText("Numero giorni erogazione insufficiente: "+giorni+"\nMedia contenuto cistera: "+String.format("%.0f", media)+" m^3");
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxRiver.getItems().addAll(model.getAllRivers());
    }
}
