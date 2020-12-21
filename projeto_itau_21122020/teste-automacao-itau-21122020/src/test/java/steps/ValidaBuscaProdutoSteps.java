package steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CarrinhoPage;
import pages.HomePage;
import pages.LeituraJSON;
import pages.ProdutoPage;

public class ValidaBuscaProdutoSteps {

	private static WebDriver driver;
	protected static HomePage homePage = new HomePage(driver);
	
	//Produto Page
	public String nomeProduto;
	public String marcaProduto;
	public String precoProduto;
	public String precoProdutoAssinantes;
		
	//Carrinho Page
	public String nomeProdutoCarrinho;
	public String precoProdutoCarrinho;
	
	List<String> listaBusca;
	
	@BeforeEach
	public void carregaPaginaInicial() {
		System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver\\86\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.petz.com.br");
		homePage = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}
	
	@After
	public static void finalizar() {
		driver.quit();
	}
	
	//----------------------------------------------------------------------------------------------------------------------
	
	@Given("estou na pagina inicial do site Petz")
	public void estou_na_pagina_inicial_do_site_petz() throws Exception {
		HomePage homePage = new HomePage(driver);
		carregaPaginaInicial();
		assertTrue(driver.getCurrentUrl().contains("www.petz.com.br"));
	}

	@And("digito o produto racao na barra de busca")
	public void digito_o_produto_racao_na_barra_de_busca() throws Exception {
		HomePage homePage = new HomePage(driver);
		homePage.Inserir_Busca("ração");
	}
	
	@When("seleciono o terceiro produto retornado pela busca")
	public void seleciono_o_terceiro_produto_retornado_pela_busca() throws Exception {
		HomePage homePage = new HomePage(driver);
		ProdutoPage produtoPage = new ProdutoPage(driver);
		homePage.Selecionar_Item(3);
		nomeProduto = produtoPage.Obter_NomeProduto();
		marcaProduto = produtoPage.Obter_NomeMarca();
		precoProduto = produtoPage.Obter_PrecoNormal();
		precoProdutoAssinantes = produtoPage.Obter_PrecoAssinante();
	}

	@And("adiciono o item no carrinho sem ter um cadastro assinante")
	public void adiciono_o_item_no_carrinho_sem_ter_um_cadastro_assinante() throws Exception {
		HomePage homePage = new HomePage(driver);
		ProdutoPage produtoPage = new ProdutoPage(driver);
		produtoPage.Clicar_AdicionarCarrinho();
		Thread.sleep(10000);
	}

	@Then("vejo que o preco normal e aplicado no carrinho")
	public void vejo_que_o_preco_normal_e_aplicado_no_carrinho() {
		HomePage homePage = new HomePage(driver);
		CarrinhoPage carrinhoPage = new CarrinhoPage(driver);
		nomeProdutoCarrinho = carrinhoPage.Obter_NomeProdutoCarrinho();
		precoProdutoCarrinho = carrinhoPage.Obter_PrecoNormalCarrinho();
		assertTrue(nomeProdutoCarrinho.contains(nomeProduto));
		assertTrue(precoProdutoCarrinho.contains(precoProduto));
		//Como na página do carrinho não tem informações da marca busquei a marca no nome do produto
		assertTrue(nomeProdutoCarrinho.contains(marcaProduto));
	}

	//---------------------------------------------------------------------------------------------------------------------

	@Given("carrego os produtos a serem buscados no site")
	public void carrego_os_produtos_a_serem_buscados_no_site() throws Exception {
		 listaBusca = LeituraJSON.LeituraJSON();
	}

	@Then("para todos os produtos da lista realizo a busca validando nome preco e valor de cada item")
	public void para_todos_os_produtos_da_lista_realizo_a_busca_validando_nome_preco_e_valor_de_cada_item() throws Exception {
		
		for(String busca : listaBusca) {
//			System.out.println("Item: " + busca);
			HomePage homePage = new HomePage(driver);
			homePage.Inserir_Busca(busca);
			
			ProdutoPage produtoPage = new ProdutoPage(driver);
			homePage.Selecionar_Item(3);
			nomeProduto = produtoPage.Obter_NomeProduto();
			marcaProduto = produtoPage.Obter_NomeMarca();
			precoProduto = produtoPage.Obter_PrecoNormal();
			precoProdutoAssinantes = produtoPage.Obter_PrecoAssinante();
			
			produtoPage.Clicar_AdicionarCarrinho();
			Thread.sleep(1000);
			
			CarrinhoPage carrinhoPage = new CarrinhoPage(driver);
			nomeProdutoCarrinho = carrinhoPage.Obter_NomeProdutoCarrinho();
			precoProdutoCarrinho = carrinhoPage.Obter_PrecoNormalCarrinho();
			assertTrue(nomeProdutoCarrinho.contains(nomeProduto));
			assertTrue(precoProdutoCarrinho.contains(precoProduto));
			
			carrinhoPage.Obter_ExcluirProduto();
		}
	}

}
