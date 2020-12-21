package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage {
	// Driver
	private WebDriver driver;
	//Componentes da Homepage relacionados a Login
	private By barraBusca = By.cssSelector("input[id*=\"search\"]");
	private By item3 = By.cssSelector("div[data-index*=\"3\"]");
	
	//Variáveis 
	public String itemBusca;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void Inserir_Busca(String busca) throws Exception{
		driver.findElement(barraBusca).sendKeys(busca);
		Thread.sleep(3000);
		itemBusca = Obter_Busca();
	}
	
	public String Obter_Busca() {
		return driver.findElement(item3).getText();
	}
	
	public String Item_Buscado() {
		return itemBusca;
	}
	
	public void Selecionar_Item(int id_item) throws Exception {
		for(int i=0; i<id_item; i++) {
			driver.findElement(barraBusca).sendKeys(Keys.DOWN);
		}
		Thread.sleep(1000);
		driver.findElement(barraBusca).sendKeys(Keys.ENTER);
	}
	
	
	
}