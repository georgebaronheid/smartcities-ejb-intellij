package br.com.fiap.smartcities.servlets;

import br.com.fiap.smartcities.ejb.ContadorPesquisasService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Um servelet listener é um tipo especial de classe que escuta quando ocorrem
 * diferentes tipos de eventos na aplicação.
 * Ele escuturá os eventos de criação e encerramento de sessão.
 */
@WebListener
public class SmartcitiesListener implements HttpSessionListener {


    /**
     * Este método será invocado toda vez que um cliente acessar a aplicação via navegador.
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        try {
            /**
             * InitialContext solicita instâncias de EJB do projeto por meio do lookup().
             */
            InitialContext initialContext = new InitialContext();

            /**
             * Os nomes do lookup são definidos pelo WildFly. O padrão é "java:module/<Nome da Classe>"
             * Neste método os 3 beans existentes no projeto são solicitados. O lookup é chamado
             * pois não se sabe se xiste uma instância desse atributo já.
             */
            httpSessionEvent.getSession().setAttribute(
                    "estabelecimentosService",
                    initialContext.lookup(
                            "java:module/EstabelecimentosService!br.com.fiap.smartcities.Estabelecimentos" +
                                    "ServiceLocal")
            );  

            httpSessionEvent.getSession().setAttribute(
                    "historicoService","java:module/HistoricoPesquisasService"
            );

            // Ele é instanciado para invocar o método.
            ContadorPesquisasService contadorPesquisasService = (ContadorPesquisasService) initialContext
                    .lookup("java:module/ContadorPesquisasService");

            contadorPesquisasService.novoUsuario();

            httpSessionEvent.getSession().setAttribute(
                    "contadorService", contadorPesquisasService
            );
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método será invocado automaticamente sempre que uma sessão da aplicação for encerrada
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ContadorPesquisasService contadorPesquisasService = (ContadorPesquisasService) httpSessionEvent.getSession().getAttribute(
                "contadorService"
        );

        contadorPesquisasService.usuarioSaiu();
    }
}
