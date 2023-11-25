package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal produtoFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String destino = "index.jsp";

        try {
            switch (acao) {
                case "listar":
                    List<Produto> produtos = produtoFacade.findAll();
                    request.setAttribute("produtos", produtos);
                    destino = "ProdutoLista.jsp";
                    break;
                case "formIncluir":
                    destino = "ProdutoForm.jsp"; // Nome da página JSP para inclusão
                    break;
                case "formAlterar":
                    // Verificação de parâmetro nulo ou vazio e conversão segura para int
                    String idStr = request.getParameter("id");
                    if (idStr != null && !idStr.isEmpty()) {
                        int idAlterar = Integer.parseInt(idStr);
                        Produto produtoParaAlterar = produtoFacade.find(idAlterar);
                        if (produtoParaAlterar != null) {
                            request.setAttribute("produto", produtoParaAlterar);
                            destino = "ProdutoForm.jsp"; // Nome da página JSP para alteração
                        } else {
                            request.setAttribute("errorMessage", "Produto não encontrado para ID: " + idAlterar);
                            destino = "errorPage.jsp";
                        }
                    } else {
                        request.setAttribute("errorMessage", "ID para alteração não fornecido.");
                        destino = "errorPage.jsp";
                    }
                    break;
                case "excluir":
                    // Mesma verificação de parâmetro nulo ou vazio e conversão segura para int
                    String idDelStr = request.getParameter("id");
                    if (idDelStr != null && !idDelStr.isEmpty()) {
                        int idExcluir = Integer.parseInt(idDelStr);
                        Produto produtoParaExcluir = produtoFacade.find(idExcluir);
                        if (produtoParaExcluir != null) {
                            produtoFacade.remove(produtoParaExcluir);
                        } else {
                            request.setAttribute("errorMessage", "Não é possível excluir, produto não encontrado para ID: " + idExcluir);
                            destino = "errorPage.jsp";
                        }
                    } else {
                        request.setAttribute("errorMessage", "ID para exclusão não fornecido.");
                        destino = "errorPage.jsp";
                    }
                    response.sendRedirect("ServletProdutoFC?acao=listar");
                    return;
                case "alterar":
                    // Atualização do produto existente com verificação de nulidade
                    // A verificação dos parâmetros e a conversão para os tipos corretos são feitas aqui
                    String nome = request.getParameter("nome");
                    String quantidadeStr = request.getParameter("quantidade");
                    String precoVendaStr = request.getParameter("precoVenda");
                    String idAltStr = request.getParameter("id");

                    if (nome != null && quantidadeStr != null && precoVendaStr != null && idAltStr != null) {
                        int idParaAlterar = Integer.parseInt(idAltStr);
                        int quantidade = Integer.parseInt(quantidadeStr);
                        BigDecimal precoVenda = new BigDecimal(precoVendaStr);

                        Produto produtoAlterar = produtoFacade.find(idParaAlterar);
                        if (produtoAlterar != null) {
                            produtoAlterar.setNome(nome);
                            produtoAlterar.setQuantidade(quantidade);
                            produtoAlterar.setPrecoVenda(precoVenda);
                            produtoFacade.edit(produtoAlterar);
                        } else {
                            request.setAttribute("errorMessage", "Produto para alterar não encontrado.");
                            destino = "errorPage.jsp";
                        }
                    } else {
                        request.setAttribute("errorMessage", "Dados inválidos para alteração.");
                        destino = "errorPage.jsp";
                    }
                    response.sendRedirect("ServletProdutoFC?acao=listar");
                    return;
                case "incluir":
                    // Inclusão de um novo produto com verificação de nulidade
                    // A verificação dos parâmetros e a conversão para os tipos corretos são feitas aqui
                    String novoNome = request.getParameter("nome");
                    String novaQuantidadeStr = request.getParameter("quantidade");
                    String novoPrecoVendaStr = request.getParameter("precoVenda");

                    if (novoNome != null && !novoNome.isEmpty()
                            && novaQuantidadeStr != null && !novaQuantidadeStr.isEmpty()
                            && novoPrecoVendaStr != null && !novoPrecoVendaStr.isEmpty()) {

                        try {
                            int novaQuantidade = Integer.parseInt(novaQuantidadeStr);
                            BigDecimal novoPrecoVenda = new BigDecimal(novoPrecoVendaStr);

                            Produto novoProduto = new Produto();
                            novoProduto.setNome(novoNome);
                            novoProduto.setQuantidade(novaQuantidade);
                            novoProduto.setPrecoVenda(novoPrecoVenda);


                            produtoFacade.create(novoProduto);
                            response.sendRedirect("ServletProdutoFC?acao=listar");
                        } catch (NumberFormatException e) {
                            request.setAttribute("errorMessage", "Erro ao converter quantidade ou preço de venda: " + e.getMessage());
                            destino = "errorPage.jsp";
                            RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
                            dispatcher.forward(request, response);
                        }

                    } else {
                        request.setAttribute("errorMessage", "Todos os campos devem ser preenchidos para incluir um novo produto.");
                        destino = "errorPage.jsp";
                        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
                        dispatcher.forward(request, response);
                    }
                    return;
                default:
                    request.setAttribute("errorMessage", "Ação não reconhecida: " + acao);
                    destino = "errorPage.jsp";
                    break;
            }

            if (!response.isCommitted()) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erro ao processar a solicitação: " + e.getMessage());
            destino = "errorPage.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet ProdutoFC que implementa Front Controller para operações de produtos";
    }
}