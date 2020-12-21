package homepage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.CarrinhoPage;
import pages.HomePage;
import pages.LeituraJSON;
import pages.ProdutoPage;

public class HomePageTests extends BaseTests{
	
	//Produto Page
	public String nomeProduto;
	public String marcaProduto;
	public String precoProduto;
	public String precoProdutoAssinantes;
	public String precoTotalCarrinho;
	public String precoSubtotal2Carrinho;
	public String precoTotal2Carrinho;
	
	//Carrinho Page
	public String nomeProdutoCarrinho;
	public String precoProdutoCarrinho;
	
	@Test
	public void AcessaPaginaInicial() {
		carregaPaginaInicial();
		assertTrue(driver.getCurrentUrl().contains("www.petz.com.br"));
	}
	
	@Test
	public void ConsultaProdutoRacaoSelecionarItem3() throws Exception {
		HomePage homePage = new HomePage(driver);
		AcessaPaginaInicial();
		homePage.Inserir_Busca("ração");
		homePage.Selecionar_Item(3);
	}
	
	@Test
	public void GuardaDadosProdutoBuscado() throws Exception {
		HomePage homePage = new HomePage(driver);
		ProdutoPage produtoPage = new ProdutoPage(driver);
		ConsultaProdutoRacaoSelecionarItem3();
		nomeProduto = produtoPage.Obter_NomeProduto();
		marcaProduto = produtoPage.Obter_NomeMarca();
		precoProduto = produtoPage.Obter_PrecoNormal();
		precoProdutoAssinantes = produtoPage.Obter_PrecoAssinante();
	}
	
	@Test
	public void AdicionarProdutoCarrinho() throws Exception {
		HomePage homePage = new HomePage(driver);
		ProdutoPage produtoPage = new ProdutoPage(driver);
		GuardaDadosProdutoBuscado();
		produtoPage.Clicar_AdicionarCarrinho();
		Thread.sleep(1000);
	}
	
	@Test
	public void ValidaItensCarrinho() throws Exception {
		CarrinhoPage carrinhoPage = new CarrinhoPage(driver);
		
		AdicionarProdutoCarrinho();
		
		nomeProdutoCarrinho = carrinhoPage.Obter_NomeProdutoCarrinho();
		precoProdutoCarrinho = carrinhoPage.Obter_PrecoNormalCarrinho();
		precoTotalCarrinho = carrinhoPage.Obter_PrecoTotalCarrinho();
		precoSubtotal2Carrinho = carrinhoPage.Obter_PrecoSubtotalCarrinho();
		precoTotal2Carrinho = carrinhoPage.Obter_PrecoTotal2Carrinho();
		
		Thread.sleep(10000);
		
		assertTrue(nomeProdutoCarrinho.contains(nomeProduto));
		assertTrue(precoProdutoCarrinho.trim().equals(precoProduto.trim()));
		assertTrue(precoTotalCarrinho.trim().equals(precoProduto.trim()));
		assertTrue(precoSubtotal2Carrinho.trim().equals(precoProduto.trim()));
		assertTrue(precoTotal2Carrinho.trim().equals(precoProduto.trim()));
		
	}
}
