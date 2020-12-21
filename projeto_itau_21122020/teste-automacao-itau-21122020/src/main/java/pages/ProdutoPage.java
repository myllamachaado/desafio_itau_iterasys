package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProdutoPage {
	
	private WebDriver driver;
	
	private By nomeProduto = By.cssSelector("h1[itemprop*=\"name\"]");
	private By nomeMarca = By.cssSelector("a[itemprop*=\"brand\"]");
	private By precoNormal = By.cssSelector("div[class*=\"price-current\"]");
	private By precoAssinantes = By.cssSelector("span[class*=\"price-subscriber\"]");
	private By adicionarNoCarrinho = By.cssSelector("button[id*=\"adicionarAoCarrinho\"]");
	
	public ProdutoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String Obter_NomeProduto(){
		return driver.findElement(nomeProduto).getText();
	}
	
	public String Obter_NomeMarca(){
		return driver.findElement(nomeMarca).getText();
	}
	
	public String Obter_PrecoNormal(){
		return driver.findElement(precoNormal).getText();
	}
	
	public String Obter_PrecoAssinante(){
		return driver.findElement(precoAssinantes).getText();
	}
	
	public void Clicar_AdicionarCarrinho(){
		driver.findElement(adicionarNoCarrinho).click();
	}
}
