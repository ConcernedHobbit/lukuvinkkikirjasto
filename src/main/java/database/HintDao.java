package database;

import kirjasto.*;

import java.util.List;

public interface HintDao {

    BookHint getBookHint(int id);

    VideoHint getVideoHint(int id);

    PodcastHint getPodcastHint(int id);

    BlogHint getBlogHint(int id);

    List<String> getAllHints();

    List<String> findWithHeader(String header);

    List<String> findWithType(HintType type);

    Integer addBookHint(BookHint hint);

    Integer addVideoHint(VideoHint hint);

    Integer addPodcastHint(PodcastHint hint);

    Integer addBlogHint(BlogHint hint);

    void removeHint(int id);

    void addTags(int id, String tags);

    List<String> findTags(String tag);

    HintType getHintType(int id);
}
