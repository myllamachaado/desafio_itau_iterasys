#Author: ludmila machado
@tag
Feature: Busca do produto ração no site Petz

	@tag1
	Scenario: Busca do produto racao no site Petz
		Given estou na pagina inicial do site Petz
		  And digito o produto racao na barra de busca
		 When seleciono o terceiro produto retornado pela busca
		  And adiciono o item no carrinho sem ter um cadastro assinante
		 Then vejo que o preco normal e aplicado no carrinho
