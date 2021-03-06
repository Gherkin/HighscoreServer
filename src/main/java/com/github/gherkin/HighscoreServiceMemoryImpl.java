package com.github.gherkin;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.List;
import java.util.stream.Collectors;

public class HighscoreServiceMemoryImpl implements HighscoreService {
    @Inject @Named("storage")
    List<Highscore> storage;

    public void save(Highscore highscore) {
        storage.add(highscore);
    }

    public List<Highscore> getTop(final int mapId) {
        synchronized(storage) {
            return storage.stream()
                    .filter(h -> h.getId() == mapId)
                    .sorted((h1, h2) -> h2.getScore() - h1.getScore())
                    .limit(5)
                    .collect(Collectors.toList());
        }
    }
}
