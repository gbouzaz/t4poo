Gustavo Bouzaz Paix�o - 8936947
Lucas Silva Loureiro - 8947184


O sistema � dividido em duas partes: uma aplica��o cliente (client.jar) e outra servidor (server.jar).
Para executar a aplica��o cliente, a servidor deve obrigatoriamente estar executando!
Ambas as aplica��es est�o localizadas na pasta dist!

Aplica��o servidor:

->Para cadastrar novo produto: Basta clicar no bot�o "Register new product", no menu principal, preencher os campos
no menu que aparecer e clicar em register.
->Para listar todos os produtos: Clique em "List all products", no menu principal.
->Para atualizar o estoque de um produto: Clique em "Update a product", no menu principal e em seguida: selecione o produto
desejado, informe a nova quantidade e clique em "Update".
->Para gerar rela�rios: clique em "Reports", e caso queira um relat�rio de acordo com as vendas em um dia, selecione um
na caixa de texto superior e clique em "Generate!" superior. Caso queira gerar de acordo com um m�s de um ano, informe estes dados
nos campos inferiores e clique no bot�o "Generate!" inferior.
Obs: os relat�rios s�o salvos na pasta dist do projeto.

Aplica��o cliente:

->Para fazer login no sistema: Clique em "Login", informe o userID e sua respectiva senha e clique em "Go!". Caso este
usu�rio exista, este retornar� para o menu principal do sistema. Caso contr�rio, limpar� os campos para um novo login.
->Para registrar um novo usu�rio: Clique em "Register new client", preencha os campos  e clique em "Register".
->Para listar todos os produtos: Clique em "List all products". Uma tabela mostrar� todos os produtos cadastrados.
->Para requisitar email de notifica��o de produtos em falta: Clique em "Request notification" no menu principal. 
Este bot�o apenas estar� habilitado quando um usu�rio estiver logado no sistema.
->Para comprar um produto: Clique em "Buy our products" no menu principal, selecione o produto desejado, preencha os
campos requisitados e clique em "Buy!". Caso o estoque do produto requisitado seja positivo, este � comprado
e o estoque deste � atualizado.
