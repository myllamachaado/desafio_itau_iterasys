package homepage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.CarrinhoPage;
import pages.HomePage;
import pages.LeituraJSON;
import pages.ProdutoPage;

public class HomePageTestsJSON extends BaseTests {

	//Produto Page
	public String nomeProduto;
	public String marcaProduto;
	public String precoProduto;
	public String precoProdutoAssinantes;
		
	//Carrinho Page
	public String nomeProdutoCarrinho;
	public String precoProdutoCarrinho;
	public String precoTotalCarrinho;
	public String precoSubtotal2Carrinho;
	public String precoTotal2Carrinho;
		
	@Test
	public void AcessaPaginaInicial() {
		carregaPaginaInicial();
		assertTrue(driver.getCurrentUrl().contains("www.petz.com.br"));
	}
	
	@Test
	public void ConsultaProdutoSelecionarItem3Parametro(String busca) throws Exception {
		HomePage homePage = new HomePage(driver);
		AcessaPaginaInicial();
		Thread.sleep(1000);
		homePage.Inserir_Busca(busca);
		capturarTela("Consulta_Produto");
		Thread.sleep(1000);
		homePage.Selecionar_Item(3);
		capturarTela("Selecionar_Item_3");
//		System.out.println("Item buscado: " + homePage.itemBusca);
		
	}
	
	@Test
	public void GuardaDadosProdutoBuscadoParametro(String busca) throws Exception {
		HomePage homePage = new HomePage(driver);
		ProdutoPage produtoPage = new ProdutoPage(driver);
		ConsultaProdutoSelecionarItem3Parametro(busca);
		nomeProduto = produtoPage.Obter_NomeProduto();
		marcaProduto = produtoPage.Obter_NomeMarca();
		precoProduto = produtoPage.Obter_PrecoNormal();
		precoProdutoAssinantes = produtoPage.Obter_PrecoAssinante();
	}
	
	
	@Test
	public void AdicionarProdutoCarrinhoParametro(String busca) throws Exception {
		HomePage homePage = new HomePage(driver);
		ProdutoPage produtoPage = new ProdutoPage(driver);
		GuardaDadosProdutoBuscadoParametro(busca);
		produtoPage.Clicar_AdicionarCarrinho();
		capturarTela("Adiciona_Carrinho");
		Thread.sleep(1000);
	}
	
	@Test
	public void ValidaItensCarrinhoParametro(String busca) throws Exception {
		HomePage homePage = new HomePage(driver);
		ProdutoPage produtoPage = new ProdutoPage(driver);
		CarrinhoPage carrinhoPage = new CarrinhoPage(driver);
		LeituraJSON leituraJSON = new LeituraJSON();
		
		AdicionarProdutoCarrinhoParametro(busca);
		
		capturarTela("Valida_Item");
		nomeProdutoCarrinho = carrinhoPage.Obter_NomeProdutoCarrinho();
		precoProdutoCarrinho = carrinhoPage.Obter_PrecoNormalCarrinho();
		precoTotalCarrinho = carrinhoPage.Obter_PrecoTotalCarrinho();
		precoSubtotal2Carrinho = carrinhoPage.Obter_PrecoSubtotalCarrinho();
		precoTotal2Carrinho = carrinhoPage.Obter_PrecoTotal2Carrinho();
		assertTrue(nomeProdutoCarrinho.contains(nomeProduto));
		assertTrue(precoProdutoCarrinho.contains(precoProduto));
		assertTrue(nomeProdutoCarrinho.contains(nomeProduto));
		assertTrue(precoProdutoCarrinho.trim().equals(precoProduto.trim()));
		assertTrue(precoTotalCarrinho.trim().equals(precoProduto.trim()));
		assertTrue(precoSubtotal2Carrinho.trim().equals(precoProduto.trim()));
		assertTrue(precoTotal2Carrinho.trim().equals(precoProduto.trim()));
		carrinhoPage.Obter_ExcluirProduto();
		capturarTela("Exclui_Produto_Carrinho");
		Thread.sleep(2000);
	}
	
	@Test
	public void RealizaBuscasSiteParametro() throws Exception {
		List<String> listaBusca = LeituraJSON.LeituraJSON();
		for(String busca : listaBusca) {
			System.out.println("Item: " + busca);
			ValidaItensCarrinhoParametro(busca);
		}
			
	}
}
