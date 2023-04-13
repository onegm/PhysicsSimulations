package oneDimensionalKinematics.viewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationViewModelTest {

    private ApplicationViewModel applicationViewModel;

    @BeforeEach
    void setUp() {
        applicationViewModel = new ApplicationViewModel();
    }

    @Test
    void setApplicationState_setToNewState() {
        TestAppStateListener listener = new TestAppStateListener();
        applicationViewModel.listenToAppState(listener);

        applicationViewModel.setState(ApplicationState.PAUSED);

        assertTrue(listener.appStateUpdated);
        assertEquals(ApplicationState.PAUSED, listener.state);
    }

    @Test
    void setApplicationState_setToSameState() {
        TestAppStateListener listener = new TestAppStateListener();
        applicationViewModel.listenToAppState(listener);

        applicationViewModel.setState(applicationViewModel.getState());

        assertFalse(listener.appStateUpdated);
        assertNull(listener.state);
    }

    private static class TestAppStateListener implements SimpleChangeListener<ApplicationState>{
        private boolean appStateUpdated = false;
        private ApplicationState state = null;
        @Override
        public void valueChanged(ApplicationState newState) {
            appStateUpdated = true;
            state = newState;
        }
    }
}