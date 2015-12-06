package com.github.gherkin;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiceModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(HighscoreResource.class);
        binder.bind(HighscoreService.class).to(HighscoreServiceMemoryImpl.class);
        binder.bind(EntityManagerFactory.class).toInstance(Persistence.createEntityManagerFactory("sql"));
    }

    @Provides
    @Named("storage")
    @Singleton
    List<Highscore> provideStorage() {
        return Collections.synchronizedList(new ArrayList<>());
    }

    @Provides
    @Inject
    EntityManager provideEntityManager(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

}
