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

//package br.senac.rj.backend;
//
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.servlet.ServletContextHandler;
//import org.eclipse.jetty.servlet.ServletHolder;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.servlet.ServletContainer;
//import org.glassfish.jersey.jackson.JacksonFeature;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
//
//public class Main {
//    public static void main(String[] args) throws Exception {
//        int port = 8080;
//        Server server = new Server(port);
//
//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.setContextPath("/");
//
//        // Configuração do Jersey com Jackson
//        ResourceConfig rc = new ResourceConfig()
//            .packages("br.senac.rj.backend.controller", "br.senac.rj.backend.filter")
//            .register(org.glassfish.jersey.jackson.JacksonFeature.class);
//
//        // Cria e configura o ObjectMapper
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        
//        // Registra o provider com esse ObjectMapper
//        rc.register(new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
//
//        // Usa o ResourceConfig no ServletContainer
//        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(rc));
//        context.addServlet(jerseyServlet, "/api/*");
//
//        server.setHandler(context);
//        server.start();
//        System.out.println("Servidor iniciado em http://localhost:" + port + "/api");
//        server.join();
//    }
//}
