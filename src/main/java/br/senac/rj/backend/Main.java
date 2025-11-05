package br.senac.rj.backend;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * 
 * @author reinaldo.jose
 * Classe que configura e inicia o servidor.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // Configura o servidor HTTP Jetty para escutar na port 8080
    	int port = 8080;
        Server server = new Server(port);

        // Configura um contexto (espaço) dentro do servidor onde os servlets irão operar, com suporte a sessões
        // Servlet é uma classe Java que responde a requisições HTTP em um servidor
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        // Define que o contexto irá atuar na raiz (/) do servidor
        context.setContextPath("/");

        // Adiciona o servlet Jersey ao contexto
        // Configura o Servlet Jersey para operar na rota /api a partir da raiz
        // Jersey implementa o padrão JAX-RS para criar APIs REST
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/api/*");

        // Aponta para a classe Application que configura o Servlet Jersey
        jerseyServlet.setInitParameter(
                "jakarta.ws.rs.Application",
                "br.senac.rj.backend.config.JaxRsApplication"
        );
        
        // Define o contexto que irá tratar as requisições que chegarem no servidor
        // O contexto contém os servlets
        server.setHandler(context);

        // Inicia o servidor
        server.start();
        System.out.println("Servidor iniciado em http://localhost:" + port + "/api");
        
        server.join();
    }
}
