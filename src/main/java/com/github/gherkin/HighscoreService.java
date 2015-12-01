package com.github.gherkin;

public interface HighscoreService {
    void save(Highscore highscore);
    Highscore get(int id);
}
