package br.com.fiap.smartcities.servlets;

import br.com.fiap.smartcities.ejb.ContadorPesquisasService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/contador")
public class ContadorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ContadorPesquisasService contadorPesquisasService =
                (ContadorPesquisasService) req.getSession().getAttribute("contadorService");

        req.setAttribute("usuarios", contadorPesquisasService.getUsuarios());
        req.setAttribute("pesquisas", contadorPesquisasService.getPesquisas());

        RequestDispatcher dispatcher = req.getRequestDispatcher("contador.jsp");

        dispatcher.forward(req, resp);


    }
}
