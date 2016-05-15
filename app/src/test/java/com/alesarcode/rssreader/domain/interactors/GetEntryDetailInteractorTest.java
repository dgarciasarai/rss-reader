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
 * Test for GetEntryDetailInteractor.
 *
 * @author Sarai Díaz García
 * @version %I%
 * @see GetEntryDetailInteractor
 */
public class GetEntryDetailInteractorTest {

    @Mock
    private RSSRepository mockRepository;

    private Scheduler testExecutorScheduler;
    private Scheduler testUiScheduler;

    private GetEntryDetailInteractor mInteractor;

    private int itemId = 1;

    @Before
    public void setUp() throws MalformedURLException {
        MockitoAnnotations.initMocks(this);
        testExecutorScheduler = new TestScheduler();
        testUiScheduler = new TestScheduler();
        this.mInteractor = new GetEntryDetailInteractor(itemId, mockRepository, testExecutorScheduler,
                testUiScheduler);
    }

    @Test
    public void testShouldGetEntryFromRepository() {
        this.mInteractor.buildInteractorObservable();
        verify(mockRepository).getEntry(itemId);
        verifyNoMoreInteractions(mockRepository);
    }
}
