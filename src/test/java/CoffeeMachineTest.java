import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import at.htl.coffee.*;
import at.htl.coffee.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineTest {

    CoffeeMachine coffeeMachine;

    @Mock
    Container coffeeContainer;
    @Mock
    Container waterContainer;
    @Mock
    Coffee coffee;

    @Before
    public void setUp() {
        coffeeMachine = new CoffeeMachine(coffeeContainer, waterContainer);
    }

    @After
    public void tearDown() {
        coffeeContainer = null;
        waterContainer = null;
        coffeeMachine = null;
    }

    @Test
    public void t201_MakeCoffee() throws NotEnoughException {
        when(coffeeContainer.getPortion(Portion.LARGE)).thenReturn(true);
        when(waterContainer.getPortion(Portion.LARGE)).thenReturn(true);

        assertTrue(coffeeMachine.makeCoffee(Portion.LARGE));
    }

    @Test(expected=NotEnoughException.class)
    public void t202_NotEnoughException01() throws NotEnoughException {
        when(waterContainer.getPortion(Portion.MEDIUM)).thenReturn(true);
        doThrow(new NotEnoughException()).when(coffeeContainer).getPortion(Portion.MEDIUM);

        coffeeMachine.makeCoffee(Portion.MEDIUM);
    }

    @Test(expected=NotEnoughException.class)
    public void t203_NotEnoughException02() throws NotEnoughException {

        when(coffeeContainer.getPortion(Portion.LARGE))
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        when(waterContainer.getPortion(Portion.LARGE))
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        assertTrue(coffeeMachine.makeCoffee(Portion.LARGE));
        assertTrue(coffeeMachine.makeCoffee(Portion.LARGE));
        coffeeMachine.makeCoffee(Portion.LARGE);
    }

    @Test
    public void t204_CompareCoffee() {
        when(coffee.compareTo(anyChar())).thenReturn(-1);

        assertEquals(-1, coffee.compareTo(anyChar()));

        when(coffee.compareTo(anyBoolean())).thenReturn(1).thenReturn(-1);

        assertEquals(1, coffee.compareTo(true));
        assertEquals(-1, coffee.compareTo(true));

        when(coffee.compareTo(isA(Type.class))).thenReturn(0);
        when(coffee.compareTo(isA(Coffee.class))).thenReturn(-1);

        assertEquals(0, coffee.compareTo(Type.ESPRESSO));
        assertEquals(-1, coffee.compareTo(new Coffee(Type.ESPRESSO)));
    }

    @Test
    public void t205_CoffeInOrder() throws NotEnoughException {
        doNothing().doCallRealMethod().when(coffeeContainer).clear();
        doNothing().doCallRealMethod().when(waterContainer).clear();

        // specifies the mocks
        InOrder inOrder = inOrder(coffeeContainer, waterContainer);

        // some method calls
        coffeeMachine.cleanCoffeeMachine();
        waterContainer.clear();

        // check if coffeeContainer was cleared before the waterContainer
        inOrder.verify(coffeeContainer, atLeastOnce()).clear();
        inOrder.verify(waterContainer, times(2)).clear();
    }
}
