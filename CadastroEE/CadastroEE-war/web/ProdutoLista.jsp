<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Produtos</title>
    <!-- Adicione aqui qualquer referência a CSS ou Bootstrap se necessário -->
</head>
<body>
    <h1>Lista de Produtos</h1>
    <a href="ServletProdutoFC?acao=formIncluir">Incluir Novo Produto</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Preço</th>
                <th>Ações</th>
            </tr>
        </thead>
        
            <c:forEach items="${produtos}" var="produto">
                <tr>
                    <td>${produto.id}</td>
                    <td>${produto.nome}</td>
                    <td>${produto.quantidade}</td>
                    <td>${produto.preco}</td>
                    <td>
                        <a href="ServletProdutoFC?acao=formAlterar&id=${produto.id}">Alterar</a>
                        |
                        <a href="ServletProdutoFC?acao=excluir&id=${produto.id}" onclick="return confirm('Tem certeza que deseja excluir este produto?');">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty produtos}">
                <tr>
                    <td colspan="5">Nenhum produto encontrado.</td>
                </tr>
            </c:if>
        
    </table>
</body>
</html>
