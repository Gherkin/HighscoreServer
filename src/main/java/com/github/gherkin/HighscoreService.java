package com.github.gherkin;

import java.util.List;

public interface HighscoreService {
    void save(Highscore highscore);
    List<Highscore> getTop(int mapId);

}
