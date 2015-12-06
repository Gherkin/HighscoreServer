package com.github.gherkin;

import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HIghscoreServiceSqlImpl implements HighscoreService {
    @Inject
    private EntityManager entityManager;

    @Override
    public void save(Highscore highscore) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(highscore);
            transaction.commit();

        } catch (IllegalArgumentException exception) {
            transaction.rollback();
            exception.printStackTrace();
        }
    }

    @Override
    public List<Highscore> getTop(int mapId) {
        return getAll().stream()
                .filter(h -> h.getId() == mapId)
                .sorted((h1, h2) -> h2.getScore() - h1.getScore())
                .limit(5)
                .collect(Collectors.toList());
    }

    private List<Highscore> getAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        List result = new ArrayList<>();
        try {
            transaction.begin();

            Query query = entityManager.createQuery("SELECT h FROM Highscore h");
            result = query.getResultList();

            transaction.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
