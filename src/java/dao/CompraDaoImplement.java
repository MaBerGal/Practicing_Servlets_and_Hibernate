package dao;

import model.Compra;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import persistence.NewHibernateUtil;

public class CompraDaoImplement implements CompraDao {

    @Override
    public Compra getCompraPorId(int id) {
        Session session = null;
        Compra compra = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            compra = (Compra) session.get(Compra.class, id);
        } catch (HibernateException HE) {
            HE.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return compra;
    }

    @Override
    public ArrayList<Compra> getCompras() {
        Session session = null;
        ArrayList<Compra> compras = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Compra");
            compras = (ArrayList<Compra>) query.list();
        } catch (HibernateException HE) {
            HE.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return compras;
    }

    @Override
    public ArrayList<Compra> getComprasPorUsuario(String id) {
        Session session = null;
        ArrayList<Compra> compras = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Compra where ID_Usuario = :id");
            query.setParameter("id", id);
            compras = (ArrayList<Compra>) query.list();
        } catch (HibernateException HE) {
            HE.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return compras;
    }

    @Override
    public void insertarCompra(Compra compra) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(compra);
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
    public void editarCompra(Compra compra) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(compra);
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
    public void borrarCompra(Compra compra) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(compra);
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
    public int getSiguienteID() {
        Session session = null;
        int nextCode = 0;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("select max(id) from Compra");
            Integer maxId = (Integer) query.uniqueResult();

            if (maxId != null) {
                nextCode = maxId + 1;
            } else {
                nextCode = 1; // Si no hay registros, empieza desde 1
            }
        } catch (HibernateException HE) {
            HE.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return nextCode;
    }
    
    @Override
    public ArrayList<Compra> getComprasOrdenadas(String idUsuario, String criterioOrden) {
        Session session = null;
        ArrayList<Compra> compras = null;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Compra.class);
            criteria.add(Restrictions.eq("idUsuario", idUsuario));

            // Aplicar orden según el criterio
            if ("fechaAsc".equals(criterioOrden)) {
                criteria.addOrder(Order.asc("fecha"));
            } else if ("fechaDesc".equals(criterioOrden)) {
                criteria.addOrder(Order.desc("fecha"));
            } else if ("masCaro".equals(criterioOrden)) {
                criteria.addOrder(Order.desc("precio")); // Ordenar por precio para obtener el más reciente
            } else if ("masBarato".equals(criterioOrden)) {
                criteria.addOrder(Order.asc("precio"));  // Ordenar por precio para obtener el más antiguo
            }

            compras = (ArrayList<Compra>) criteria.list();
        } catch (HibernateException HE) {
            HE.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return compras;
    }

}
