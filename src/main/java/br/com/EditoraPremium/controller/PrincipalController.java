package br.com.EditoraPremium.controller;

import java.net.URL;


import java.util.ResourceBundle;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;


public class PrincipalController implements Initializable {

	@FXML
	private MenuItem mncadVenda;
	@FXML
	private MenuItem mncadFuncionario;
	@FXML
	private MenuItem mncadCidade;
	@FXML
	private MenuItem mncadEstado;
	@FXML
	private MenuItem mncadCurso;
	@FXML
	private MenuItem mncadMaterial;
	@FXML
	private MenuItem mncadDespesa;
	@FXML
	private MenuItem mncadLista;

	@FXML
	private TabPane tbJanelas;

	
	public void initialize(URL location, ResourceBundle resources) {

		mncadVenda.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				exibirTelaVendas();
			   
			}
		});
		
		mncadCidade.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				exibirTelaCidade();
			}
		});
	}
	
    
	private void exibirTelaVendas() {
		if (verificarAba("Clientes")) {
			CadastroVendaController cadVendaController =
					new CadastroVendaController();
			
			Tab tabClientes = new Tab("Clientes");
		//	tabClientes.setContent(CadastroVendaController.getNode());
		
			tbJanelas.getTabs().add(tabClientes);
			tbJanelas.getSelectionModel().select(tabClientes);
		}
	}
	
	
	private void exibirTelaCidade() {
		if (verificarAba("Cidades")) {
			Tab tabCidades = new Tab("Cidades");
		
			tbJanelas.getTabs().add(tabCidades);
			tbJanelas.getSelectionModel().select(tabCidades);
		}
	}
	
	private boolean verificarAba(String titulo) {
		if (!tbJanelas.getTabs().isEmpty()) {
			for (Tab tab : tbJanelas.getTabs()) {
				if (tab.getText().equals(titulo)) {
					return false;
				}
			}
		}
		
		return true;
	}

	
	}


