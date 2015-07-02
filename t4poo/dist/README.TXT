Gustavo Bouzaz Paixão - 8936947
Lucas Silva Loureiro - 8947184


O sistema é dividido em duas partes: uma aplicação cliente (client.jar) e outra servidor (server.jar).
Para executar a aplicação cliente, a servidor deve obrigatoriamente estar executando!
Ambas as aplicações estão localizadas na pasta dist!

Aplicação servidor:

->Para cadastrar novo produto: Basta clicar no botão "Register new product", no menu principal, preencher os campos
no menu que aparecer e clicar em register.
->Para listar todos os produtos: Clique em "List all products", no menu principal.
->Para atualizar o estoque de um produto: Clique em "Update a product", no menu principal e em seguida: selecione o produto
desejado, informe a nova quantidade e clique em "Update".
->Para gerar relaórios: clique em "Reports", e caso queira um relatório de acordo com as vendas em um dia, selecione um
na caixa de texto superior e clique em "Generate!" superior. Caso queira gerar de acordo com um mês de um ano, informe estes dados
nos campos inferiores e clique no botão "Generate!" inferior.
Obs: os relatórios são salvos na pasta dist do projeto.

Aplicação cliente:

->Para fazer login no sistema: Clique em "Login", informe o userID e sua respectiva senha e clique em "Go!". Caso este
usuário exista, este retornará para o menu principal do sistema. Caso contrário, limpará os campos para um novo login.
->Para registrar um novo usuário: Clique em "Register new client", preencha os campos  e clique em "Register".
->Para listar todos os produtos: Clique em "List all products". Uma tabela mostrará todos os produtos cadastrados.
->Para requisitar email de notificação de produtos em falta: Clique em "Request notification" no menu principal. 
Este botão apenas estará habilitado quando um usuário estiver logado no sistema.
->Para comprar um produto: Clique em "Buy our products" no menu principal, selecione o produto desejado, preencha os
campos requisitados e clique em "Buy!". Caso o estoque do produto requisitado seja positivo, este é comprado
e o estoque deste é atualizado.
