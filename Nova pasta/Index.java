package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessao")
public class SessaoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Cria ou recupera a sessão
        HttpSession session = request.getSession(true);

        // Obtém o ID da sessão
        String idSessao = session.getId();

        // Obtém a data e hora da criação da sessão
        Date dataCriacaoSessao = session.getCreationTime();

        // Obtém a data e hora do último acesso à sessão
        Date dataUltimoAcessoSessao = session.getLastAccessedTime();

        // Verifica se é a 5ª sessão
        int contagem = session.getAttribute("contador") != null ? (int) session.getAttribute("contador") : 0;
        if (contagem == 5) {
            session.setAttribute("contador", contagem + 1);
            response.setContentType("text/html");
            try (PrintWriter out = response.getWriter()) {
                out.println("<h1>Sessão 5</h1>");
            }
            return;
        }

        // Exibe as informações da sessão
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>Informações da sessão</h1>");
            out.println("<p>ID da sessão: " + idSessao + "</p>");
            out.println("<p>Data e hora da criação: " + dataCriacaoSessao + "</p>");
            out.println("<p>Data e hora do último acesso: " + dataUltimoAcessoSessao + "</p>");
        }
    }
}