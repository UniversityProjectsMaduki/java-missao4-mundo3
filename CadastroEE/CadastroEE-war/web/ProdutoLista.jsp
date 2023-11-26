<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Produtos</title>
    <!-- Adicione aqui qualquer referência a CSS ou JS que você precisar -->
</head>
<body>
<h2>Lista de Produtos</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Quantidade</th>
        <th>Preço</th>
        <th>Ações</th>
    </tr>
    <%-- Supondo que "produtos" é uma lista de objetos Produto adicionada ao request --%>
    <c:forEach var="produto" items="${produtos}">
        <tr>
            <td>${produto.id}</td>
            <td>${produto.nome}</td>
            <td>${produto.quantidade}</td>
            <td>${produto.precoVenda}</td>
            <td>
                <a href="ServletProdutoFC?acao=formAlterar&id=${produto.id}">Alterar</a> |
                <a href="ServletProdutoFC?acao=excluir&id=${produto.id}">Excluir</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="ServletProdutoFC?acao=formIncluir">Incluir Novo Produto</a>
</body>
</html>