package database;

import kirjasto.Hint;

import java.util.List;

public interface HintDao {

    Hint getHint(int id);

    List<String> getAllHints();

    Integer addHint(Hint hint);

    void removeHint(int id);

    void addTags(int id, String tags);

    List<String> findTags(String tag);
}
