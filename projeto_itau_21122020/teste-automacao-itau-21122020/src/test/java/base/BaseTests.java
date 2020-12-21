package base;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.common.io.Files;

import pages.HomePage;

public class BaseTests {
	public static WebDriver driver;
	
	protected static HomePage homePage;
	protected static String data_hora;
	protected static boolean diretorio;
//	protected LoginPage loginPage;
//	protected TasksPage tasksPage;
	
	@BeforeAll
	public static void iniciar() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-ss");
		data_hora = formatter.format(date).toString().replace(":", "-");
		diretorio = (new File("C:\\Users\\Ludmila - W3erp\\Projeto_ITAU\\teste-automacao-itau\\"
				+ "src\\test\\resources\\screenshots\\" + data_hora).mkdir());
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver\\86\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}
	
	@BeforeEach
	public void carregaPaginaInicial() {
		driver.get("https://www.petz.com.br");
		homePage = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}
	
	public void capturarTela(String nomeTeste){
		String path = "C:\\Users\\Ludmila - W3erp\\Projeto_ITAU\\teste-automacao-itau\\src\\test\\resources\\screenshots\\";
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-ss");
		System.out.println(path + data_hora + "\\");
		
		var camera = (TakesScreenshot) driver;
		File capturaDeTela = camera.getScreenshotAs(OutputType.FILE);
		try {
			Files.move(capturaDeTela, new File(path + data_hora + "\\" + nomeTeste + "_" + 
							formatter.format(date).toString().replace(":", "-") + ".png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterAll
	public static void finalizar() {
		driver.quit();
	}
}
