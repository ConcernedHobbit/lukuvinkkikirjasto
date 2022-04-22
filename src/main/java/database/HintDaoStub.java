package database;

import kirjasto.*;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.DbUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HintDaoStub implements HintDao {

    private ArrayList<BookHint> hints;

    @Override

    public BookHint getBookHint(int id) {
        return hints.get(id);

    }

    @Override
    public VideoHint getVideoHint(int id) {
        return null;
    }

    @Override
    public PodcastHint getPodcastHint(int id) {
        return null;
    }

    @Override
    public BlogHint getBlogHint(int id) {
        return null;
    }

    @Override
    public List<String> getAllHints() {
        return null;
    }

    @Override
    public List<String> findWithHeader(String header) {
        return null;
    }

    @Override
    public List<String> findWithType(HintType type) {
        return null;
    }

    @Override
    public Integer addBookHint(BookHint hint) {
        return null;
    }

    @Override
    public Integer addVideoHint(VideoHint hint) {
        return null;
    }

    @Override
    public Integer addPodcastHint(PodcastHint hint) {
        return null;
    }

    @Override
    public Integer addBlogHint(BlogHint hint) {
        return null;
    }

    @Override
    public void removeHint(int id) {

    }

    @Override
    public void addTags(int id, String tags) {

    }

    @Override
    public List<String> findTags(String tag) {
        return null;
    }

    @Override
    public HintType getHintType(int id) {
        return null;
    }
}