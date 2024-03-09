package dao;

import model.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import persistence.NewHibernateUtil;

public class UsuarioDaoImplement implements UsuarioDao {
    
    @Override
    public ArrayList<Usuario> getUsuarios() {
        Session session = null;
        ArrayList<Usuario> usuarios = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Usuario");
            usuarios = (ArrayList<Usuario>) query.list();
        } catch (HibernateException HE) {
            HE.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return usuarios;
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (HibernateException HE) {
            if (transaction != null) {
                transaction.rollback();
            }
            HE.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void editarUsuario(Usuario usuario) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
        } catch (HibernateException HE) {
            if (transaction != null) {
                transaction.rollback();
            }
            HE.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void borrarUsuario(Usuario usuario) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(usuario);
            transaction.commit();
        } catch (HibernateException HE) {
            if (transaction != null) {
                transaction.rollback();
            }
            HE.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    @Override
    public Usuario getUsuarioPorCredenciales(String identificador, String password) {
        Session session = null;
        Usuario usuario = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Usuario where id = :identificador and password = :password");
            query.setParameter("identificador", identificador);
            query.setParameter("password", password);
            usuario = (Usuario) query.uniqueResult();
        } catch (HibernateException HE) {
            HE.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return usuario;
    }

}
