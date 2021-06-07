package br.com.EditoraPremium.controller;

import java.net.URL;



import java.util.Date;
import java.util.ResourceBundle;
import br.com.EditoraPremiun.vo.ClienteVO;
import br.com.EditoraPremiun.vo.EscolaridadeVO;
import br.com.EditoraPremiun.vo.EstadoVO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class CadastroVendaController implements Initializable {

	@FXML
	private Button btnCliente;
	@FXML
	private Button venda;
	@FXML
	private Button btnIndicacoes;
	@FXML
	private Button bntSalvar;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnRemover;
	@FXML
	private Button btnCancelar;
	@FXML
	private Button btnPesquisar;

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtEndereco;
	@FXML
	private TextField txtMunicipio;
	@FXML
	private TextField txtFone;
	@FXML
	private TextField txtFoneRecado;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtProfissao;
	@FXML
	private TextField txtNascimento;
	@FXML
	private TextField txtPesquisa;

	@FXML
	private ComboBox<EstadoVO> cbEstado;
	@FXML
	private ComboBox<EscolaridadeVO> cbEscolaridade;

	@FXML
	private TableView<ClienteVO> tblCursos;
   
    

	
	private ClienteVO cliente = null;
	private Node rootNode = null;
	
	public CadastroVendaController() {
		FXMLLoader loader = 
			new FXMLLoader(
				getClass().getResource(
					"fxml/CadVenda.fxml"));
		loader.setController(this);
		
		try {
			this.rootNode = (Node) loader.load();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public  Node getNode() {
		return this.rootNode;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("CadVenda");
		
		bntSalvar.setOnAction(new EventHandler<ActionEvent>() {
      		@Override
			public void handle(ActionEvent event) {
				Salvar();
			}
		});
		
		

	}

private void Salvar() {
	
	System.out.println("SalVAR");
	
	String nome = txtNome.getText();
	String cpf = txtCpf.getText();
	String endereco = txtEndereco.getText();
	int municipio = (int) txtMunicipio.getCaretPosition(); ////????????????????
	String fone = txtFone.getText();
	String foneRecado = txtFoneRecado.getText();
	String email = txtEmail.getText();
	String profissao = txtProfissao.getText();
	Date nascimento = (Date) txtNascimento.getCharacters();////////?????????????

	
	this.cliente.setNome(nome);
	this.cliente.setCpf(cpf);
	this.cliente.setEndereco(endereco);
	this.cliente.setCidadeFK(municipio);
	this.cliente.setFone(fone);
	this.cliente.setFoneRecado(foneRecado);
	this.cliente.setEmail(email);
	this.cliente.setProfissao(profissao);
	this.cliente.setNasCli(nascimento);
	
   }


}
