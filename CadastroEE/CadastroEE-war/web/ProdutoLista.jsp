<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Produto</title>
    </head>
        <body>

        <%-- Verifique se há alguma mensagem de erro no request e exiba-a --%>
        <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) {
            out.println("<p style='color: red;'>" + errorMessage + "</p>");
        }
        %>

        <%-- Se o produto existe, estamos editando. Se não, estamos criando um novo. --%>
        <%
        Produto produto = (Produto) request.getAttribute("produto");
        String formAction = produto != null ? "ServletProdutoFC?acao=alterar" : "ServletProdutoFC?acao=incluir";
        %>

        <form action="<%= formAction %>" method="post">
            Nome do Produto: <input type="text" name="nome" value="<%= produto != null ? produto.getNome() : "" %>"><br>
            Quantidade: <input type="number" name="quantidade" value="<%= produto != null ? produto.getQuantidade() : "" %>"><br>
            Preço de Venda: <input type="text" name="precoVenda" value="<%= produto != null ? produto.getPrecoVenda().toString() : "" %>"><br>
            <% if (produto != null) { %>
                <input type="hidden" name="id" value="<%= produto.getIdProduto() %>">
            <% } %>
            <input type="submit" value="Salvar">
        </form>

    </body>
    </html>
