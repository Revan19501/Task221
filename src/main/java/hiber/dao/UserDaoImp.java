package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        String hql = "select distinct u from User u left join fetch u.car";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        String hqlUpd = "SELECT u FROM User u JOIN FETCH u.car c WHERE c.model = :model AND c.series = :series";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hqlUpd, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);

        return query.getSingleResult();
    }

}
