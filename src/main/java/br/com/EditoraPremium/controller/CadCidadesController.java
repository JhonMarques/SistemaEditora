package br.com.EditoraPremium.controller;

import java.net.URL;



import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import br.com.EditoraPremium.dao.CidadeDAO;
import br.com.EditoraPremium.dao.EstadoDAO;
import br.com.EditoraPremium.framework.db.DatabaseConnectionFactory;
import br.com.EditoraPremium.util.AlertUtil;
import br.com.EditoraPremiun.vo.CidadeVO;
import br.com.EditoraPremiun.vo.EstadoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



public class CadCidadesController implements Initializable {

	public String tipo = "Vazio";

	// List's

	private List<CidadeVO> listCidades;
	private ObservableList<CidadeVO> observableListCidade;

	private List<EstadoVO> listEstado;
	private ObservableList<EstadoVO> observableListEstado;

	@FXML
	private TableView<CidadeVO> tbCidade;
	@FXML
	private TableColumn<CidadeVO, Integer> columId;
	@FXML
	private TableColumn<CidadeVO, String> columCid;
	@FXML
	private TableColumn<EstadoVO, String> columEstado;

	// Atributos Para Conecção
	private final Connection conn = (Connection) new DatabaseConnectionFactory().getConnection();
	private final CidadeDAO cidadeDAO = new CidadeDAO();	
	private final EstadoDAO estadoDAO = new EstadoDAO();

	// TextField
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtDescricao;
	@FXML
	private ComboBox<EstadoVO> cbEstado;
	@FXML
	private TextField txtPesquisar;

	// Label
	@FXML
	private Label lblMensagemErro;

	// Button
	
	
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnDeletar;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnPesquisar;
	@FXML
	private Button btnRemover;
	@FXML
	private Button btnNew;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Initi");
		cidadeDAO.setConnection(conn);
		carregarTable();
		carregaCombo();

		tbCidade.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableCidade(newValue));

	}

	
	public void carregaCombo(){
		
		listEstado = estadoDAO.selecionarTodos();
		observableListEstado = FXCollections.observableArrayList(listEstado);
		cbEstado.setItems(observableListEstado);
		
	}
	public void carregarTable() {
		System.out.println(tipo);

		if ((tipo).equals("Vazio")) {

			cbEstado.setDisable(true);
			txtDescricao.setDisable(true);
			txtId.setDisable(true);
			btnNew.setDisable(false);
			btnCancelar.setDisable(false);
			btnSalvar.setDisable(true);
			btnEditar.setDisable(true);
			btnDeletar.setDisable(true);
			// btnDeletar.setDisable(true);
			txtPesquisar.setDisable(false);

		}

		columId.setCellValueFactory(new PropertyValueFactory<>("idCidade"));
		columCid.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columEstado.setCellValueFactory(new PropertyValueFactory<>("estadJoin"));

		listCidades = cidadeDAO.SelecionarTodos();

		observableListCidade = FXCollections.observableArrayList(listCidades);
		tbCidade.setItems(observableListCidade);

	}

	public void selecionarItemTableCidade(CidadeVO cidade) {

		System.out.println("selecionou a tabela");
		if (cidade != null && ("Vazio").equals(tipo)) {

			cbEstado.setDisable(false);
			txtDescricao.setDisable(false);
			txtId.setDisable(true);
			txtDescricao.setDisable(true);
			cbEstado.setDisable(true);
			txtId.setText(String.valueOf(cidade.getIdCidade()));
			txtDescricao.setText(cidade.getDescricao());
			btnEditar.setDisable(false);
			btnDeletar.setDisable(false);

		} else {

			txtId.setText("");
			txtDescricao.setText("");
			
		}
	}

	@FXML
	public void ButtonSalvar() {


		if(("novo").equals(tipo)){
			
			CidadeVO cidade = new CidadeVO();
			cidade.setDescricao(txtDescricao.getText());
			cidade.setEstadoFK(cbEstado.getValue().getIdEstado());
			carregaCombo();
			
			if(AlertUtil.displayConfirmationMessage("Confirmar", "Confirmar Cadastro?", "Confirmar", "Cancelar")){
				if(cidade.getDescricao().isEmpty()){
					lblMensagemErro.setText("Erro - Campo Cidade Vazio");
		}else{
				try {
					
					System.out.println("SALVOU");
					cidadeDAO.inserir(cidade);
					btnNew.setDisable(false);					
					btnSalvar.setDisable(true);
					btnCancelar.setDisable(true);
					txtDescricao.setDisable(true);
					lblMensagemErro.setText("");
					txtId.setText("-1");
					txtDescricao.setText("");
					tipo = "Vazio";
					carregarTable();
					cbEstado.setDisable(true);
					
					
					
				} catch (Exception e) {
					AlertUtil.displayErrorMessage("Falha", e);
					
				
				}
		}
	}
					
		
	} else if(("alterar").equals(tipo)){
		
		CidadeVO cidade = new CidadeVO();
		cidade.setIdCidade(Integer.parseInt(txtId.getText()));
		cidade.setDescricao(txtDescricao.getText());
		cidade.setEstadoFK(cbEstado.getValue().getIdEstado());
		
		if(AlertUtil.displayConfirmationMessage("Confirmar", "Deseja alterar " + cidade.getDescricao()+"?", "Confirmar", "Cancelar")){
		
			
		{
			try {
				
				System.out.println("ALTEROU");
				cidadeDAO.alterar(cidade);
				btnNew.setDisable(false);
				btnSalvar.setDisable(true);
				btnCancelar.setDisable(true);
				txtDescricao.setDisable(true);
				cbEstado.setDisable(true);
				tipo = "Vazio";
				carregarTable();
				carregaCombo();
				txtId.setText("-1");
				txtDescricao.setText("");
				lblMensagemErro.setText("");
				
				
			} catch (Exception e) {
				AlertUtil.displayErrorMessage("Falha", e);
				
			
			}
		}
	  }
	}	

}

	@FXML
	public void ButtonAlterar() {

		System.out.println("BOTAO ALTERAR");
		txtId.setDisable(true);
		cbEstado.setDisable(false);
		btnCancelar.setDisable(false);
		btnSalvar.setDisable(false);
		txtDescricao.setDisable(false);
		btnDeletar.setDisable(true);
		btnEditar.setDisable(true);
		tipo = "alterar";

	}

	@FXML
	public void ButtonDeletar() {

		System.out.println("BOTAO DELETAR");

		CidadeVO cidade = new CidadeVO();
		cidade.setIdCidade(Integer.parseInt(txtId.getText()));
		cidade.setDescricao(txtDescricao.getText());
		

		if (AlertUtil.displayConfirmationMessage("Confirmar", "Deseja Deletar " + cidade.getDescricao() + "?", "Confirmar",
				"Cancelar")) {

			try {

				cidadeDAO.remover(cidade);
				btnNew.setDisable(false);
				btnSalvar.setDisable(true);
				btnCancelar.setDisable(false);
				txtDescricao.setDisable(true);
				btnDeletar.setDisable(true);
				btnEditar.setDisable(true);
				carregarTable();
				txtId.setText("-1");
				txtDescricao.setText("");
				lblMensagemErro.setText("");
				tipo = "Vazio";

			} catch (Exception e) {
				AlertUtil.displayErrorMessage("Falha", e);
			}
		}

	}

	@FXML
	public void ButtonCancelar() {

		System.out.println("BOTAO CANCELAR");
		btnSalvar.setDisable(true);
		btnNew.setDisable(false);
		btnCancelar.setDisable(false);
		btnDeletar.setDisable(true);
		btnEditar.setDisable(true);
		txtPesquisar.setDisable(false);
		txtDescricao.setDisable(true);
		carregaCombo();
		cbEstado.setDisable(true);
		txtId.setDisable(true);
		txtDescricao.setText("");
		txtId.setText("-1");
		tipo = "Vazio";
	}

	@FXML
	public void ButtonNew() {

		btnSalvar.setDisable(false);
		txtPesquisar.setDisable(false);
		cbEstado.setDisable(false);
		txtDescricao.setDisable(false);
		txtId.setDisable(true);
		btnNew.setDisable(true);
		btnEditar.setDisable(true);
		btnDeletar.setDisable(true);
		btnCancelar.setDisable(false);

		txtId.setText("-1");
		tipo = "novo";

		System.out.println(tipo);

	}

	/*@FXML
	public void ButtonBuscar(){
		
			
				String cidade;
				cidade = txtPesquisar.getText();
			
				columId.setCellValueFactory(new PropertyValueFactory<>("idCidade"));
				columCid.setCellValueFactory(new PropertyValueFactory<>("descricao"));
			
				listCidades = cidadeDAO.buscarDescricao(cidade);
			
				observableListCategoria = FXCollections.observableArrayList(listCategoria);
				tableViewCategoria.setItems(observableListCategoria);
		}
	}*/
}