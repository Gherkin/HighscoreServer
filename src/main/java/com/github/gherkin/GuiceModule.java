package com.github.gherkin;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiceModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(HighscoreResource.class);
        binder.bind(HighscoreService.class).to(HighscoreServiceMemoryImpl.class);
    }

    @Provides
    @Named("storage")
    @Singleton
    List<Highscore> provideStorage() {
        return Collections.synchronizedList(new ArrayList<>());
    }
}
