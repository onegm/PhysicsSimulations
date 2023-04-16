package oneDimensionalKinematics.viewModel;

        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.*;

class CarViewModelTest {
    private CarViewModel carViewModel;
    @BeforeEach
    void setUp() {
        this.carViewModel = new CarViewModel();
    }

    @Test
    void step_constSpeed() {
        carViewModel.getSpeed().setValue(5.0);
        carViewModel.step(3.0);
        assertEquals(15, carViewModel.getX().getValue());
    }

    @Test
    void step_increasingSpeed() {
        carViewModel.getSpeed().setValue(0.0);
        carViewModel.getAcc().setValue(2.0);
        carViewModel.step(3.0);
        assertEquals(6.0, carViewModel.getSpeed().getValue());
        assertEquals(9.0, carViewModel.getX().getValue());
    }

    @Test
    void currentPropertyListeningToInitialProperty() {
        carViewModel.getInitialX().setValue(7.5);
        carViewModel.getInitialSpeed().setValue(15.0);
        assertEquals(7.5, carViewModel.getX().getValue());
        assertEquals(15.0, carViewModel.getSpeed().getValue());
    }
}