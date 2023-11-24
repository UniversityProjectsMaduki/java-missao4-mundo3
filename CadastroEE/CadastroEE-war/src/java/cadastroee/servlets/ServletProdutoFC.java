package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;


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
                    destino = "ProdutoDados.jsp";
                    break;
                case "formAlterar":
                    int idAlterar = Integer.parseInt(request.getParameter("id"));
                    Produto produtoParaAlterar = produtoFacade.find(idAlterar);
                    request.setAttribute("produto", produtoParaAlterar);
                    destino = "ProdutoDados.jsp";
                    break;
                case "excluir":
                    int idExcluir = Integer.parseInt(request.getParameter("id"));
                    Produto produtoParaExcluir = produtoFacade.find(idExcluir);
                    produtoFacade.remove(produtoParaExcluir);
                    response.sendRedirect("ServletProdutoFC?acao=listar");
                    return;
                case "alterar":
                    int idParaAlterar = Integer.parseInt(request.getParameter("id"));
                    Produto produtoAlterar = produtoFacade.find(idParaAlterar);
                    // Atualize produtoAlterar com dados do request
                    produtoFacade.edit(produtoAlterar);
                    response.sendRedirect("ServletProdutoFC?acao=listar");
                    return;
                case "incluir":
                    Produto novoProduto = new Produto();
                    // Preencha novoProduto com dados do request
                    produtoFacade.create(novoProduto);
                    response.sendRedirect("ServletProdutoFC?acao=listar");
                    return;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Erro ao processar a ID do produto.");
            destino = "errorPage.jsp"; // Substitua pelo seu JSP de erro
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
        dispatcher.forward(request, response);
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
