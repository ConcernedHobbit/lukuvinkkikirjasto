package database;

import kirjasto.BookHint;
import kirjasto.HintType;
import kirjasto.VideoHint;

import java.util.List;

public interface HintDao {

    BookHint getBookHint(int id);

    VideoHint getVideoHint(int id);

    List<String> getAllHints();

    Integer addBookHint(BookHint hint);

    Integer addVideoHint(VideoHint hint);

    void removeHint(int id);

    void addTags(int id, String tags);

    List<String> findTags(String tag);

    HintType getHintType(int id);
}
