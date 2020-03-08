package br.com.fiap.smartcities.servlets;

import br.com.fiap.smartcities.ejb.EstabelecimentosService;
import br.com.fiap.smartcities.ejb.HistoricoPesquisasService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

// Endpoint para pesquisas de estabelecimentos
@WebServlet(value = "/estabelecimentos")
public class EstabelecimentosServlet extends HttpServlet {

    /**
     * Método que será utilziado quando solicitado o endpoit. São instanciados o service de estabelecimentos
     * e de historico de pesquisa. Ambos com os atributos do Listener. É setado o paramentro 'termo' vindo
     * da url. Após isso a pesquisa é realizada passando o termo, a requisicao e o service de estabelecimento.
     * O histórico é registrado com o termo, req e seus service.
     * Após isso um dispatcher é instanciado com o request dispatcher com o nome da jsp.
     * Logo após, um dispatch com a req e a resp.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EstabelecimentosService estabelecimentosService = (EstabelecimentosService) req
                .getSession().getAttribute("estabelecimentosService");

        HistoricoPesquisasService historicoPesquisasService = (HistoricoPesquisasService) req
                .getSession().getAttribute("historicoService");

        String termo = req.getParameter("termo");

        this.pesquisar(termo, req, estabelecimentosService);
        this.registrarHistorico(termo,req,historicoPesquisasService);

        RequestDispatcher dispatcher = req
                .getRequestDispatcher("estabelecimentos.jsp");
        dispatcher.forward(req, resp);

    }

    /**
     * Recebendo os argumentos {@param estabelecimentosService}, {@param termo} e {@param req} é realizada
     * uma pesquisa retornando uma list utilizando o método de pesquisar do service de estabelecimentos, passando o
     * termo. Um String builder é criado para gerar as li do resultado
     *
     */
    private void pesquisar (String termo, HttpServletRequest req, EstabelecimentosService estabelecimentosService) {

        List<String> resultadoPesquisa = estabelecimentosService.pesquisar(termo);

        StringBuilder stringBuilder = new StringBuilder();
        for (String resultado :
                resultadoPesquisa) {
            stringBuilder.append("<li>").append(resultado).append("</li>");
        }

        req.setAttribute("termo", termo);
        req.setAttribute("encontrados", resultadoPesquisa.size());
        req.setAttribute("resultado", stringBuilder.toString());
    }

    private void registrarHistorico(String termo, HttpServletRequest req, HistoricoPesquisasService historicoService){

        historicoService.registrarPesquisa(termo);
        Set<String> pesquisas = historicoService.getHistorico();

        StringBuilder stringBuilderHistorico = new StringBuilder();
        for (String pesquisa :
                pesquisas) {
            stringBuilderHistorico.append("<li><i>").append(pesquisa).append("</i></li>");
        }

        req.setAttribute("historico", stringBuilderHistorico);
    }
}
