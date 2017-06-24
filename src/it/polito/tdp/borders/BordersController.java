/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import it.polito.tdp.borders.model.RisultatiSimulazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {
	
	private Model model;
	int UltimoAnnoPerCuiHaiCreatoGrafo;
	
    public void setModel(Model model) {
		this.model = model;
		boxAnno.getItems().addAll(model.getAnni());
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxNazione"
    private ComboBox<Country> boxNazione; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    
    	
    	if(boxAnno.getValue()!=null){
    	UltimoAnnoPerCuiHaiCreatoGrafo=boxAnno.getValue();
    	model.CreaGrafo(UltimoAnnoPerCuiHaiCreatoGrafo);
    	txtResult.appendText(model.classifica()+"\n");
    	boxNazione.getItems().clear();
    	boxNazione.getItems().addAll(model.getVerticiGrafoOridinatiPerNome());
    	
    	}else{
    	txtResult.appendText("selezionare un anno \n");
    	}	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	if(boxAnno.getValue()!=null && boxAnno.getValue()==UltimoAnnoPerCuiHaiCreatoGrafo){
    		if(boxNazione.getValue()!=null){
    		RisultatiSimulazione rs=model.simula(boxNazione.getValue());
    		txtResult.appendText(rs.getImmigratiStanziatiInQuestoStato()+"\n");
    		txtResult.appendText(rs.classificaOrdinata()+"\n");
    		txtResult.appendText("Simulati: "+rs.getMaxIstanteSimulato()+" passi \n");
    		}else{
            	txtResult.appendText("prima di simulare devi sciegliere una città \n");
            }
    	}else{
        	txtResult.appendText("prima di simulare devi creare il grafo l anno che vuoi simulare\n");
        }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert boxNazione != null : "fx:id=\"boxNazione\" was not injected: check your FXML file 'Borders.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";

    }
}
