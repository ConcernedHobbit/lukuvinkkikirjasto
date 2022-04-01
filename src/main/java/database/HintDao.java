package database;

import kirjasto.Hint;

import java.util.List;

public interface HintDao {

    Hint getHint(int id);

    List<String> getAllHints();

    void addHint(Hint hint);

    void removeHint(int id);

}
