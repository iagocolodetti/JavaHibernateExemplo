
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author iagocolodetti
 */
public interface ContatoDAO {

    public void add(Contato contato) throws HibernateException;

    public Contato getContato(int id) throws ContatoNaoExisteException;

    public List<Contato> getContatosPorNome(String nome) throws ContatoNaoExisteException, HibernateException;

    public List<Contato> getContatosPorTelefone(String telefone) throws ContatoNaoExisteException, HibernateException;

    public List<Contato> getContatosPorEmail(String email) throws ContatoNaoExisteException, HibernateException;

    public List<Contato> getContatos() throws ContatoNaoExisteException, HibernateException;

    public void update(Contato contato) throws ContatoNaoExisteException, HibernateException;

    public void delete(int id) throws ContatoNaoExisteException, HibernateException;
}
