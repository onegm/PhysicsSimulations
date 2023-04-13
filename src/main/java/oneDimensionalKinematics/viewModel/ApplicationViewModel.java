package oneDimensionalKinematics.viewModel;

import java.util.LinkedList;
import java.util.List;

public class ApplicationViewModel {
    private ApplicationState state;
    private final List<SimpleChangeListener<ApplicationState>> appStateListeners;


    public ApplicationViewModel() {
        this.state = ApplicationState.READY;
        appStateListeners = new LinkedList<>();
    }

    public void listenToAppState(SimpleChangeListener<ApplicationState> listener){
        this.appStateListeners.add(listener);
    }

    public ApplicationState getState() {
        return state;
    }

    public void setState(ApplicationState newState) {
        if(this.state == newState){
            return;
        }
        System.out.println("State Changed: " + newState);
        this.state = newState;
        notifyAppStateListeners();
    }

    private void notifyAppStateListeners() {
        for (SimpleChangeListener<ApplicationState> appStateListener : appStateListeners) {
            appStateListener.valueChanged(this.state);
        }
    }
}
