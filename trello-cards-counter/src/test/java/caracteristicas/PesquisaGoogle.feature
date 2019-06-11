# language: pt
@PesquisaGoogle
Funcionalidade: Realizar uma pesquisa no Google

  Cenario: Realizar uma pesquisa no Google
    Dado o endereco do Google "https://www.google.com.br/"
    E pesquisar o valor "Linkedin"
    E clicar no botao pesquisa
    Entao verificar se o primerio resultado e a pagina do Linkedin
