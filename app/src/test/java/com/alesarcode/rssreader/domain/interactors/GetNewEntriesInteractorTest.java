package com.alesarcode.rssreader.domain.interactors;

import com.alesarcode.rssreader.domain.repository.RSSRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;

import rx.Scheduler;
import rx.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Test for GetNewEntriesInteractor.
 *
 * @author Sarai Díaz García
 * @version %I%
 * @see GetNewEntriesInteractor
 */
public class GetNewEntriesInteractorTest {

    @Mock
    private RSSRepository mockRepository;

    private Scheduler testExecutorScheduler;
    private Scheduler testUiScheduler;

    private GetNewEntriesInteractor mInteractor;

    @Before
    public void setUp() throws MalformedURLException {
        MockitoAnnotations.initMocks(this);
        testExecutorScheduler = new TestScheduler();
        testUiScheduler = new TestScheduler();
        this.mInteractor = new GetNewEntriesInteractor(mockRepository, testExecutorScheduler,
                testUiScheduler);
    }

    @Test
    public void testShouldGetEntriesFromRepository() {
        this.mInteractor.buildInteractorObservable();
        verify(mockRepository).getNewEntries();
        verifyNoMoreInteractions(mockRepository);
    }

}
