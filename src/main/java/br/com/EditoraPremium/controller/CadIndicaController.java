package br.com.EditoraPremium.controller;

import java.net.URL;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import br.com.EditoraPremium.dao.IndicacoesDAO;
import br.com.EditoraPremium.framework.db.DatabaseConnectionFactory;
import br.com.EditoraPremium.util.AlertUtil;
import br.com.EditoraPremiun.vo.ClienteVO;
import br.com.EditoraPremiun.vo.IndicacoesVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadIndicaController implements Initializable {

	public String tipo = "Vazio";

	private List<IndicacoesVO> listIndica;
	private ObservableList<IndicacoesVO> observableIndica;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnCliente;

	@FXML
	private Button btnEditar;

	@FXML
	private Button btnIndica;

	@FXML
	private Button btnNew;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnVenda;

	@FXML
	private TableView<IndicacoesVO> tbIndica;
	@FXML
	private TableColumn<IndicacoesVO, Integer> clnId;

	@FXML
	private TableColumn<IndicacoesVO, String> clnIndicado;

	@FXML
	private TableColumn<IndicacoesVO, String> clnParentesco;

	@FXML
	private TableColumn<ClienteVO, String> clnReferencia;

	@FXML
	private TableColumn<IndicacoesVO, String> clnTelefone;

	@FXML
	private TextField txtBuscar;

	@FXML
	private TextField txtGrau;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtReferencia;

	@FXML
	private TextField txtTelefone;

	private final Connection conn = (Connection) new DatabaseConnectionFactory().getConnection();
	private final IndicacoesDAO indicaDAO = new IndicacoesDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		indicaDAO.setConnection(conn);
		carregaTable();

		tbIndica.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> selecionarItemTableCidade(newValue));

	}

	public void carregaTable() {

		System.out.println(tipo);
		btnSalvar.setDisable(true);
		// txtPesquisar.setDisable(false);
		txtId.setDisable(true);
		txtNome.setDisable(true);
		txtReferencia.setDisable(true);
		txtTelefone.setDisable(true);
		txtGrau.setDisable(true);
		btnNew.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnCancelar.setDisable(false);

		clnId.setCellValueFactory(new PropertyValueFactory<>("idIndica"));
		clnIndicado.setCellValueFactory(new PropertyValueFactory<>("nome"));
		clnReferencia.setCellValueFactory(new PropertyValueFactory<>("ClienteFK"));
		clnTelefone.setCellValueFactory(new PropertyValueFactory<>("fone"));
		clnParentesco.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		listIndica = indicaDAO.selecionarTodos();

		observableIndica = FXCollections.observableArrayList(listIndica);
		tbIndica.setItems(observableIndica);

	}

	public void selecionarItemTableCidade(IndicacoesVO indica) {

		System.out.println("selecttab");
		if (indica != null && ("Vazio").equals(tipo)) {

			txtId.setDisable(true);
			txtNome.setDisable(true);
			txtReferencia.setDisable(true);
			txtTelefone.setDisable(true);
			txtGrau.setDisable(true);
			btnSalvar.setDisable(true);
			btnEditar.setDisable(false);
			btnExcluir.setDisable(false);
			txtId.setText(String.valueOf(indica.getIdIndica()));
			txtNome.setText(indica.getNome());
			txtReferencia.setText(String.valueOf(indica.getClienteFK()));
			txtTelefone.setText(indica.getFone());
			txtGrau.setText(indica.getDescricao());
			

		} else {

			txtId.setText("");
			txtNome.setText("");
			txtReferencia.setText("");
			txtTelefone.setText("");
			txtGrau.setText("");

		}
	}

	@FXML
	public void ButtonSalvar() {

		System.out.println(tipo);
		if (("Novo").equals(tipo)) {

			System.out.println("SALVAR");
			IndicacoesVO indica = new IndicacoesVO();
			indica.setNome(txtNome.getText());
			indica.setClienteFK(Integer.parseInt(txtReferencia.getText()));
			indica.setFone(txtTelefone.getText());
			indica.setDescricao(txtGrau.getText());

			if (AlertUtil.displayConfirmationMessage("Confirmar", "Cadastrar "+ indica.getNome()+"?", "Sim", "Não")) {
				if (indica.getDescricao().isEmpty()) {
					// lblMensagemErro.setText("Erro - Campo Descrição Vazio");

				} else {
					try {

						indicaDAO.inserir(indica);
						btnNew.setDisable(false);
						btnSalvar.setDisable(true);
						btnCancelar.setDisable(true);

						txtId.setDisable(true);
						txtNome.setDisable(true);
						txtReferencia.setDisable(true);
						txtTelefone.setDisable(true);
						txtGrau.setDisable(true);

						txtId.setText("-1");
						txtNome.setText("");
						txtReferencia.setText("");
						txtTelefone.setText("");
						txtGrau.setText("");
						tipo = "Vazio";
						carregaTable();

					} catch (Exception e) {
						AlertUtil.displayErrorMessage("Falha", e);

					}
				}

			}

		} else if (("alterar").equals(tipo)) {

			IndicacoesVO indica = new IndicacoesVO();
			// indica.setIdIndica(Integer.parseInt(txtId.getText()));
			indica.setNome(txtNome.getText());
			indica.setClienteFK(Integer.parseInt(txtReferencia.getText()));
			indica.setFone(txtTelefone.getText());
			indica.setDescricao(txtGrau.getText());

			if (AlertUtil.displayConfirmationMessage("Confirmar", "Deseja alterar?", "Sim", "Não")) {

				try {
					System.out.println("ENTROU NO EDIT");
					indicaDAO.alterar(indica);
					btnNew.setDisable(false);
					btnSalvar.setDisable(false);
					btnCancelar.setDisable(true);

					txtId.setDisable(true);
					txtNome.setDisable(true);
					txtReferencia.setDisable(true);
					txtTelefone.setDisable(true);
					txtGrau.setDisable(true);

					txtId.setText("-1");
					txtNome.setText("");
					txtReferencia.setText("");
					txtTelefone.setText("");
					txtGrau.setText("");
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

		btnSalvar.setDisable(false);
		txtId.setDisable(true);
		txtNome.setDisable(false);
		txtReferencia.setDisable(false);
		txtTelefone.setDisable(false);
		txtGrau.setDisable(false);

		btnNew.setDisable(true);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnCancelar.setDisable(false);

		txtId.setText("-1");
		tipo = "Novo";

		System.out.println(tipo);

	}

	@FXML
	public void ButtonExcluir() {

		System.out.println("Button Excluir");

		IndicacoesVO indica = new IndicacoesVO();
		indica.setIdIndica(Integer.parseInt(txtId.getText()));
		indica.setDescricao(txtGrau.getText());
		indica.setFone(txtTelefone.getText());
		indica.setClienteFK(Integer.parseInt(txtReferencia.getText()));
		
		if (AlertUtil.displayConfirmationMessage("Confirmar", "Deseja Remover?", "Sim",	"Não")) {
			
			try {
				indicaDAO.remover(indica);
				btnExcluir.setDisable(true);
				btnSalvar.setDisable(true);
				btnEditar.setDisable(true);

				txtId.setDisable(true);
				txtNome.setDisable(true);
				txtReferencia.setDisable(true);
				txtTelefone.setDisable(true);
				txtGrau.setDisable(true);

				txtNome.setText("");
				txtReferencia.setText("");
				txtTelefone.setText("");
				txtGrau.setText("");

				btnNew.setDisable(false);
				btnCancelar.setDisable(false);
				carregaTable();
				txtId.setText("");

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
		btnExcluir.setDisable(true);
		btnEditar.setDisable(true);
	
		txtId.setDisable(true);
		txtNome.setDisable(true);
		txtReferencia.setDisable(true);
		txtTelefone.setDisable(true);
		txtGrau.setDisable(true);
		//carregaCombo();
		//cbEstado.setDisable(true);

		txtId.setText("");
		txtNome.setText("");
		txtReferencia.setText("");
		txtTelefone.setText("");
		txtGrau.setText("");
		txtId.setText("-1");
		tipo = "Vazio";
		
		carregaTable();
	}
	
	@FXML
	public void ButtonAlterar() {

		System.out.println("BOTAO ALTERAR");
		txtId.setDisable(true);
		btnCancelar.setDisable(false);
		btnSalvar.setDisable(false);
		
		txtId.setDisable(true);
		txtNome.setDisable(false);
		txtReferencia.setDisable(false);
		txtTelefone.setDisable(false);
		txtGrau.setDisable(false);
		
		btnEditar.setDisable(true);
		tipo = "alterar";

	}


}
