package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextField txtFMed;

    @FXML
    private TextField txtK;

    @FXML
    private Button btnSimula;

    @FXML
    private TextArea txtResult;

    @FXML
    void doSimula(ActionEvent event) {
    	River r = null;
    	int k = -1;
    	try {
    		r = boxRiver.getValue();
    		if(r==null)
    			throw new IllegalStateException();
    		k = Integer.parseInt(txtK.getText());
    	} catch (IllegalStateException ie) {
    		ie.printStackTrace();
    		txtResult.setText("Seleziona un fiume");
    	} catch (NumberFormatException ne) {
    		ne.printStackTrace();
    		txtResult.setText("Inserisci un numero");
    	}
    	if(r!=null && k!=-1) {
    		model.simula(k, r);
    		txtResult.setText("Non è stato garantito il servizio " + model.getGiorniNoSimulator() + " giorni\n");
    		txtResult.appendText("La capienza media è " + model.getAvgSimulator() );
    	}
    }

    @FXML
    void selectRiver(ActionEvent event) {
    	River river = boxRiver.getValue();
    	model.setRiverInfo(river);
    	txtEndDate.setText(river.getDataMax().toString());
    	txtStartDate.setText(river.getDataMin().toString());
    	txtNumMeasurements.setText(river.getFlowNum() + "");
    	txtFMed.setText(river.getFlowAvg() + "");
    }

    @FXML
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
    	boxRiver.getItems().addAll(model.getAllRivers());
    }
}
