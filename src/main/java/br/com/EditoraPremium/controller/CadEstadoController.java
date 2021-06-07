package br.com.EditoraPremium.controller;

import java.net.URL;

import java.sql.Connection;

import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import br.com.EditoraPremium.dao.EstadoDAO;
import br.com.EditoraPremium.framework.db.DatabaseConnectionFactory;
import br.com.EditoraPremium.util.AlertUtil;

import br.com.EditoraPremiun.vo.EstadoVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadEstadoController implements Initializable {

	public String tipo = "Vazio";

	private List<EstadoVO> listEstado;
	private ObservableList<EstadoVO> observableEstado;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnNew;

	@FXML
	private TableColumn<EstadoVO, String> clnEstado;

	@FXML
	private TableColumn<EstadoVO, Integer> clnId;

	@FXML
	private TableColumn<EstadoVO, String> clnSigla;

	@FXML
	private TableView<EstadoVO> tbEstado;

	@FXML
	private TextField txtEstado;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtSigla;

	// Conexão
	private final Connection conn = (Connection) new DatabaseConnectionFactory().getConnection();
	private final EstadoDAO estadoDAO = new EstadoDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		estadoDAO.setConnection(conn);
		carregaTable();

		tbEstado.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableCidade(newValue));

	}

	public void carregaTable() {

		System.out.println(tipo);
		btnSalvar.setDisable(true);
		// txtPesquisar.setDisable(false);
		txtEstado.setDisable(true);
		txtSigla.setDisable(true);
		txtId.setDisable(true);
		btnNew.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnCancelar.setDisable(false);
		
		clnId.setCellValueFactory(new PropertyValueFactory<>("idEstado"));
		clnEstado.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		clnSigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));

		listEstado = estadoDAO.selecionarTodos();

		observableEstado = FXCollections.observableArrayList(listEstado);
		tbEstado.setItems(observableEstado);

	}

	public void selecionarItemTableCidade(EstadoVO estado) {

		System.out.println("selecttab");
		if (estado != null && ("Vazio").equals(tipo)) {

			txtEstado.setDisable(true);
			txtSigla.setDisable(true);
			txtId.setDisable(true);
			btnSalvar.setDisable(true);
			txtId.setText(String.valueOf(estado.getIdEstado()));
			txtEstado.setText(estado.getDescricao());
			txtSigla.setText(String.valueOf(estado.getSigla()));
			btnEditar.setDisable(false);
			btnExcluir.setDisable(false);
			
			

		} else {

			txtId.setText("");
			txtSigla.setText("");
			txtEstado.setText("");
		}
	}

	@FXML
	public void ButtonSalvar() {
		
		if (("Novo").equals(tipo)) {

			EstadoVO estado = new EstadoVO();
			estado.setDescricao(txtEstado.getText());
			estado.setSigla(txtSigla.getText());

			if (AlertUtil.displayConfirmationMessage("Confirmar", "Confirmar Cadastro?", "Confirmar", "Cancelar")) {
				if (estado.getDescricao().isEmpty()) {
					// lblMensagemErro.setText("Erro - Campo Descrição Vazio");

				} else {
					try {

						estadoDAO.inserir(estado);
						btnNew.setDisable(false);
						btnSalvar.setDisable(true);
						btnCancelar.setDisable(true);
						txtEstado.setDisable(true);
						// lblMensagemErro.setText("");
						txtId.setText("-1");
						txtEstado.setText("");
						txtSigla.setText("");
						tipo = "Vazio";
						carregaTable();

					} catch (Exception e) {
						AlertUtil.displayErrorMessage("Falha", e);

					}
				}

			}

		} else if (("alterar").equals(tipo)) {
			
			
			EstadoVO estado = new EstadoVO();
			estado.setIdEstado(Integer.parseInt(txtId.getText()));
			estado.setDescricao(txtEstado.getText());
			estado.setSigla(txtSigla.getText());

			if (AlertUtil.displayConfirmationMessage("Confirmar", "Confirmar Alterar?", "Confirmar", "Cancelar")) {
				
				try {
						System.out.println("ENTROU NO EDIT");
						estadoDAO.alterar(estado);
						btnNew.setDisable(false);
						btnSalvar.setDisable(false);
						btnCancelar.setDisable(true);
						txtEstado.setDisable(true);						
						txtId.setText("-1");
						txtEstado.setText("");
						carregaTable();
						tipo = "Vazio";

					} catch (Exception e) {
						AlertUtil.displayErrorMessage("Falha", e);

					}
				}

			}

		}

	

	@FXML
	public void ButtonNew() {

		System.out.println("NEW");
		btnSalvar.setDisable(false);
		// txtPesquisar.setDisable(false);
		txtEstado.setDisable(false);
		txtSigla.setDisable(false);
		txtId.setDisable(true);
		btnNew.setDisable(true);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnCancelar.setDisable(false);
		

		txtId.setText("-1");
		txtEstado.setText("");
		txtSigla.setText("");
		tipo = "Novo";

	

	}

	@FXML
	public void ButtonExcluir(){
		
		System.out.println("Button Excluir");
		
		EstadoVO estado = new EstadoVO();
		estado.setIdEstado(Integer.parseInt(txtId.getText()));
		estado.setDescricao(txtEstado.getText());
		estado.setSigla(txtSigla.getText());
		if (AlertUtil.displayConfirmationMessage("Confirmar", "Deseja Remover "+estado.getDescricao() + "?", "Confirmar", "Cancelar")){
		try{
			  estadoDAO.remover(estado);
		      btnExcluir.setDisable(true);
		      btnSalvar.setDisable(true);
		      btnEditar.setDisable(true);
     	      txtId.setDisable(true);
		      txtEstado.setDisable(true);
		      txtSigla.setDisable(true);
		      btnNew.setDisable(false);
		      btnCancelar.setDisable(false);
		      carregaTable();
		      txtId.setText("");
		      txtEstado.setText("");
		      txtSigla.setText("");
		      
		      
		}catch (Exception e) {
			AlertUtil.displayErrorMessage("Falha", e);
		}
			
		}	
		
	}
	
	@FXML
	public void ButtonEditar(){
		
		 
		  btnSalvar.setDisable(false);
	      btnEditar.setDisable(true);
	      txtId.setDisable(true);
	      txtEstado.setDisable(false);
	      txtSigla.setDisable(false);
	      btnNew.setDisable(true);
	      btnCancelar.setDisable(false);
	      tipo = "alterar";
	}
	
	
	@FXML
	public void ButtonCancelar(){
				
		  System.out.println("Cancelar");
		  btnSalvar.setDisable(true);
	      btnEditar.setDisable(true);
	      txtId.setDisable(true);
	      txtEstado.setDisable(true);
	      txtSigla.setDisable(true);
	      btnNew.setDisable(false);
	      btnCancelar.setDisable(false);
	      btnExcluir.setDisable(true);
	      txtEstado.setText("");
	      txtSigla.setText("");
	      txtId.setText("");
	      tipo = "Vazio";
	    	
		
	}
}
