package kirjasto;

import database.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;


public class MockDbTest {

    HintDaoJdbc mockDao;
    Hint testBook;
    Hint testPod;
    Hint testVideo;
    Hint testBlog;

    @Before

    public void setUp() {
        testBook = new BookHint("testbook", HintType.BOOK,
                "Lord Kelvin", "Elsevier", 1850);
        testPod = new Hint("testpod", HintType.PODCAST);
        testVideo = new Hint("testvid", HintType.VIDEO);
        testBlog = new Hint("testblog", HintType.BLOGPOST);

        mockDao = mock(HintDaoJdbc.class);

    }

    @Test

    public void getBookHintReturnBookHint() {

        when(mockDao.getBookHint(1)).thenReturn((BookHint) testBook);
        mockDao.getBookHint(1);

        verify(mockDao, times(1)).getBookHint(1);



    }
}
