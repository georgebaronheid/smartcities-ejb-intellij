package br.com.fiap.smartcities.ejb;

import br.com.fiap.smartcities.domain.Estabelecimento;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * A anotação stateless indica que é o EJB que decidirá quantas instâncias deste serão criadas
 */

@Stateless
public class EstabelecimentosService {

    // PersistenceContext indica que o Container EJB injetará no EntityManager as configurações.
    @PersistenceContext
    private EntityManager entityManager;

    public List<String> pesquisar(String termo) {
        List<String> resultado = new ArrayList<String>();

        // Neste caso a consulta não pediria commit. Caso pedisse, EJB cuidria disso.
        List<Estabelecimento> estabelecimentos = this.entityManager
                .createQuery("select e from Estabelecimento e where e.nome like :nome")
                .setParameter("nome", "%" + termo + "%")
                .getResultList();

        for (Estabelecimento estabelecimento :
                estabelecimentos) {
            resultado.add(estabelecimento.getNome()
                    + " (" + estabelecimento.getTipoEstabelecimento().getNome() + ")");
        }
        return resultado;
    }
}
