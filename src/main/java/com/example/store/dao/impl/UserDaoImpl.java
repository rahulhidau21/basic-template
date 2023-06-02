package com.example.store.dao.impl;

import com.example.store.dao.UserDao;
import com.example.store.data.Authority;
import com.example.store.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getAll(String query) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Join<User, Authority> authorityJoin = root.join("authorities", JoinType.LEFT);

        criteriaQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if (query != null && !query.isEmpty()) {
            predicates.add(builder.like(root.get("name"), "%" + query + "%"));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        List<Order> orderList = new ArrayList<>();
        orderList.add(builder.desc(root.get("id")));
        criteriaQuery.orderBy(orderList);

        return entityManager.createQuery(criteriaQuery).setFirstResult(0).setMaxResults(10).getResultList();
    }
}
