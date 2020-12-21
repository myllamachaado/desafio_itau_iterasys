package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarrinhoPage {
	private WebDriver driver;
	
	private By nomeProdutoCarrinho = By.cssSelector("td[class*=\"td-resumo\"]");
	private By precoNormalCarrinho = By.cssSelector("td[class*=\"preco\"]");
	private By precoTotalCarrinho = By.xpath("/html/body/div[8]/main/div/div/div[2]/div[2]/div[1]/div/div[1]/div[2]");
	private By precoSubtotalCarrinho = By.xpath("/html/body/div[8]/main/div/div/div[2]/div[2]/div[1]/div/div[1]/div[9]");
	private By precoTotal2Carrinho = By.xpath("/html/body/div[8]/main/div/div/div[2]/div[2]/div[1]/div/div[1]/div[9]");
	private By botaoExcluirProduto = By.cssSelector("i[class*=\"delete-item-pedido fa fa-times\"]");
	
	public CarrinhoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String Obter_NomeProdutoCarrinho(){
		return driver.findElement(nomeProdutoCarrinho).getText();
	}
	
	public String Obter_PrecoNormalCarrinho(){
		return driver.findElement(precoNormalCarrinho).getText();
	}
	
	public String Obter_PrecoTotalCarrinho(){
		return driver.findElement(precoTotalCarrinho).getText();
	}
	
	public String Obter_PrecoSubtotalCarrinho(){
		return driver.findElement(precoSubtotalCarrinho).getText();
	}
	
	public String Obter_PrecoTotal2Carrinho(){
		return driver.findElement(precoTotal2Carrinho).getText();
	}
	
	public void Obter_ExcluirProduto(){
		driver.findElement(botaoExcluirProduto).click();
	}
	
	
	
}
