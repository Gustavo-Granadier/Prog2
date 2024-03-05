package util;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDate;
import modelo.Veiculo;
import modelo.Operador;
import modelo.UsoVeiculo;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import javafx.scene.control.Alert;
import javax.persistence.Query;


public class Dao<T extends Persistivel> {

    private final Class<T> classe;

    public Dao(Class<T> classe) {
        this.classe = classe;
    }

    public T alterar(T objeto) {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        
        try {
            tx.begin();
            objeto = manager.merge(objeto);
            tx.commit();
            return objeto;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            manager.close();
        }
    }

    public T buscarPorCodigo(Object id) {
        EntityManager manager = JpaUtil.getEntityManager();
        try {
            return manager.find(classe, id);
        } finally {
            manager.close();
        }
    }

    public void excluir(T objeto) throws ExclusaoException {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();
            T temp = manager.find(classe, objeto.getCodigo());
            manager.remove(temp);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new ExclusaoException();
        } finally {
            manager.close();
        }
    }

    public void inserir(T objeto) {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();
            manager.persist(objeto);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    public List<T> listarTodos() {
        EntityManager manager = JpaUtil.getEntityManager();

        try {
            CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(classe);
            query.select(query.from(classe));
            return manager.createQuery(query).getResultList();
        } finally {
            manager.close();
        }
    }

    public List<T> buscarPorVeiculoEData(Veiculo veiculo, LocalDate data) {
        EntityManager manager = JpaUtil.getEntityManager();

        try {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(classe);
            Root<T> root = query.from(classe);

            Predicate predicate = criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("veiculo"), veiculo),
                    criteriaBuilder.equal(root.get("dataRetirada"), data)
            );

            query.select(root).where(predicate);
            return manager.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            manager.close();
        }
    }

    public void excluirVeiculoComDependencias(Veiculo veiculo) {
        EntityManager manager = JpaUtil.getEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            List<UsoVeiculo> usos = manager.createQuery("SELECT u FROM UsoVeiculo u WHERE u.veiculo = :veiculo", UsoVeiculo.class)
                    .setParameter("veiculo", veiculo)
                    .getResultList();

            for (UsoVeiculo uso : usos) {
                manager.remove(uso);
            }

            manager.remove(veiculo);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

     public boolean verificarDisponibilidadeVeiculo(Veiculo veiculo) {
        EntityManager manager = JpaUtil.getEntityManager();

        try {
            Long count = manager.createQuery("SELECT COUNT(u) FROM UsoVeiculo u WHERE u.veiculo = :veiculo " +
                    "AND u.devolucao IS NULL", Long.class)
                    .setParameter("veiculo", veiculo)
                    .getSingleResult();

            return count == 0; 
        } finally {
            manager.close();
        }
    }
     
    public Operador buscarOperadorPorLogin(String login) {
        EntityManager manager = JpaUtil.getEntityManager();
        
        try {
            Query query = manager.createQuery("SELECT o FROM Operador o WHERE o.login = :login");
            query.setParameter("login", login);
            return (Operador) query.getSingleResult();
        } finally {
            manager.close();
        }
    }

    public void exibirAlertaErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
