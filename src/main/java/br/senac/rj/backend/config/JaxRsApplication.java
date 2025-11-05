package br.senac.rj.backend.config;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * 
 * @author reinaldo.jose
 * Classe que faz a configuração principal da API REST com Jakarta (JAX-RS). 
 * Ela registra manualmente quais classes da sua aplicação serão expostas como endpoints REST.
 */
public class JaxRsApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        // adiciona recursos REST
        resources.add(br.senac.rj.backend.controller.AlunoController.class);
        resources.add(br.senac.rj.backend.controller.TurmaController.class);
        resources.add(br.senac.rj.backend.controller.UsuarioController.class);
        resources.add(br.senac.rj.backend.filter.AuthFilter.class);
        return resources;
    }
}
