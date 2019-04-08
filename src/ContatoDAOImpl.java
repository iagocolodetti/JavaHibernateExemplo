
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author iagocolodetti
 */
public class ContatoDAOImpl implements ContatoDAO {

    // <editor-fold defaultstate="collapsed" desc="Comando(s) de Criação/Escrita (CREATE)">
    @Override
    public void add(Contato contato) throws HibernateException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction tx = session.beginTransaction();
            session.save(contato);
            tx.commit();
            session.flush();
        } catch (HibernateException e) {
            throw new HibernateException("Não foi possível adicionar o contato.");
        } finally {
            HibernateUtil.closeConnection(session);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Comando(s) de Leitura/Busca (READ)">
    @Override
    public Contato getContato(int id) throws ContatoNaoExisteException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Contato contato = null;

        try {
            contato = (Contato) session.get(Contato.class, id);
            session.flush();
            if (contato == null) {
                throw new ContatoNaoExisteException("Não existe contato com esse ID.");
            }
        } catch (HibernateException e) {
            throw new HibernateException("Não foi possível buscar o contato.");
        } finally {
            HibernateUtil.closeConnection(session);
        }

        return contato;
    }

    @Override
    public List<Contato> getContatosPorNome(String nome) throws ContatoNaoExisteException, HibernateException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Contato> contatos = null;

        try {
            contatos = (List<Contato>) session.createSQLQuery("SELECT * FROM Contato WHERE nome LIKE '%" + nome + "%'").addEntity(Contato.class).list();
            session.flush();
            if (contatos == null || contatos.isEmpty()) {
                throw new ContatoNaoExisteException("Não existem contatos com esse nome ou parte dele.");
            }
        } catch (HibernateException e) {
            throw new HibernateException("Não foi possível buscar os contatos.");
        } finally {
            HibernateUtil.closeConnection(session);
        }

        return contatos;
    }

    @Override
    public List<Contato> getContatosPorTelefone(String telefone) throws ContatoNaoExisteException, HibernateException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Contato> contatos = null;

        try {
            contatos = (List<Contato>) session.createSQLQuery("SELECT * FROM Contato WHERE telefone LIKE '%" + telefone + "%'").addEntity(Contato.class).list();
            session.flush();
            if (contatos == null || contatos.isEmpty()) {
                throw new ContatoNaoExisteException("Não existem contatos com esse telefone ou parte dele.");
            }
        } catch (HibernateException e) {
            throw new HibernateException("Não foi possível buscar os contatos.");
        } finally {
            HibernateUtil.closeConnection(session);
        }

        return contatos;
    }

    @Override
    public List<Contato> getContatosPorEmail(String email) throws ContatoNaoExisteException, HibernateException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Contato> contatos = null;

        try {
            contatos = (List<Contato>) session.createSQLQuery("SELECT * FROM Contato WHERE email LIKE '%" + email + "%'").addEntity(Contato.class).list();
            session.flush();
            if (contatos == null || contatos.isEmpty()) {
                throw new ContatoNaoExisteException("Não existem contatos com esse e-mail ou parte dele.");
            }
        } catch (HibernateException e) {
            throw new HibernateException("Não foi possível buscar os contatos.");
        } finally {
            HibernateUtil.closeConnection(session);
        }

        return contatos;
    }

    @Override
    public List<Contato> getContatos() throws ContatoNaoExisteException, HibernateException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Contato> contatos = null;

        try {
            contatos = (List<Contato>) session.createSQLQuery("SELECT * FROM Contato").addEntity(Contato.class).list();
            session.flush();
            if (contatos == null || contatos.isEmpty()) {
                throw new ContatoNaoExisteException("Não existem contatos.");
            }
        } catch (HibernateException e) {
            throw new HibernateException("Não foi possível buscar os contatos.");
        } finally {
            HibernateUtil.closeConnection(session);
        }

        return contatos;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Comando(s) de Atualização/Alteração (UPDATE)">
    @Override
    public void update(Contato contato) throws ContatoNaoExisteException, HibernateException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            if (session.get(Contato.class, contato.getId()) == null) {
                throw new ContatoNaoExisteException("Não existe contato com esse ID.");
            }
            Transaction tx = session.beginTransaction();
            session.update(contato);
            tx.commit();
            session.flush();
        } catch (HibernateException e) {
            throw new HibernateException("Não foi possível alterar/atualizar o contato.");
        } finally {
            HibernateUtil.closeConnection(session);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Comando(s) de Exclusão/Remoção (DELETE)">
    @Override
    public void delete(int id) throws ContatoNaoExisteException, HibernateException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction tx = session.beginTransaction();
            session.delete(getContato(id));
            tx.commit();
            session.flush();
        } catch (ContatoNaoExisteException e) {
            throw new ContatoNaoExisteException(e.getMessage());
        } catch (HibernateException e) {
            throw new HibernateException("Não foi possível excluir/remover o contato.");
        } finally {
            HibernateUtil.closeConnection(session);
        }
    }
    // </editor-fold>
}
