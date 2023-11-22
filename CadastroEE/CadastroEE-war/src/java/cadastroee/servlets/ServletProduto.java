package cadastroee.servlets;

import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServletProduto extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal produtoFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de Produtos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de Produtos</h1>");

            // Busca a lista de produtos usando o facade
            List<Produto> listaProdutos = produtoFacade.findAll();

            // Verifica se a lista não está vazia antes de tentar usá-la
            if (listaProdutos != null) {
                out.println("<ul>");
                for (Produto produto : listaProdutos) {
                    out.println("<li>" + produto.getNome() + " - " + produto.getPrecoVenda() + "</li>");
                }
                out.println("</ul>");
            } else {
                out.println("<p>Não foram encontrados produtos.</p>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para listar produtos";
    }
}
