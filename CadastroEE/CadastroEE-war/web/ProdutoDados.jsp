<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dados do Produto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body class="container">
<h1>Dados do Produto</h1>
<form action="ServletProdutoFC" method="post" class="form">
    <input type="hidden" name="acao" value="${produto == null ? 'incluir' : 'alterar'}">
    <input type="hidden" name="idProduto" value="${produto != null ? produto.idProduto : ''}">
    <div class="mb-3">
        <label for="nome" class="form-label">Nome:</label>
        <input class="form-control" type="text" name="nome" id="nome" value="${produto != null ? produto.nome : ''}">
    </div>
    <div class="mb-3">
        <label for="quantidade" class="form-label">Quantidade:</label>
        <input class="form-control"  type="number" name="quantidade" id="quantidade" value="${produto != null ? produto.quantidade : ''}">
    </div>
    <div class="mb-3">
        <label for="precoVenda" class="form-label">Preço de Venda:</label>
        <input class="form-control"  type="number" name="precoVenda" id="precoVenda" min="0" step="0.01" value="${produto != null ? produto.precoVenda : ''}">
    </div>
    <button type="submit" class="btn btn-primary">${produto == null ? 'Adicionar' : 'Alterar'} Produto</button>
</form>
</body>
</html>