package pages;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;

public class LeituraJSON {

	public static List<String> LeituraJSON()  throws Exception{
		String path = "C:\\Users\\Ludmila - W3erp\\Projeto_ITAU\\teste-automacao-itau\\src\\test\\resources\\files\\";
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(path + "pesquisa.json");
		Object obj = jsonParser.parse(reader);
		String JSONtexto = obj.toString();
		String[] textoSeparado = JSONtexto.split("\"");
		List<String> buscas = new ArrayList();
		 
		for(int i=0; i<textoSeparado.length; i++) {
			if((textoSeparado[i].contains("{"))||(textoSeparado[i].contains("}"))||
				(textoSeparado[i].contains("nome"))||(textoSeparado[i].contains(":"))||
				(textoSeparado[i].contains("itens"))||(textoSeparado[i].contains("item"))) {
//				 System.out.println("Item não entra pra lista!");
			 }
			 else {
				 buscas.add(textoSeparado[i]);

			 }
		 }
		return buscas;
	}
	
}
