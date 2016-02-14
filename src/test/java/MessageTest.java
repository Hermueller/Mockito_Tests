import at.htl.mockito.MessageList;
import at.htl.mockito.Messenger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class MessageTest {

    Messenger msg;

    @Mock
    MessageList ml;


    @Before
    public void setUp() {
        msg = new Messenger(ml);
    }

    @After
    public void tearDown() {
        msg = null;
        ml = null;
    }

    @Test
    public void t000_MessageListParameters() {

        //    PARAMETERS        TIMES()
        ml.addMessageAt(1, "Hello");
        ml.addMessageAt(1, "Hello");

        ml.addMessageAt(1, "Good Day");
        ml.addMessageAt(2, "Good Day");
        ml.addMessageAt(3, "Good Day");

        verify(ml, times(2)).addMessageAt(eq(1), eq("Hello"));
        verify(ml, times(3)).addMessageAt(anyInt(), eq("Good Day"));


        /*   STUBBER   */

        // because this is a mock, the method is empty.
        ml.printStaticMessages("Hello", "Madam");

        doCallRealMethod()
                .doNothing()
                .doNothing()
                .doCallRealMethod()
                .when(ml).printStaticMessages(anyString(), anyString());

        ml.printStaticMessages("Hello", "Mister");  //+
        ml.printStaticMessages("Guten", "Tag");     //-
        ml.printStaticMessages("Guten", "Tag");     //-
        ml.printStaticMessages("Guten", "Tag");     //+
    }

    @Test
    public void t101_SendMessage() {
        when(ml.pull()).thenReturn("Hello").thenReturn("World");

        assertEquals("Hello", msg.printMessage());
        assertEquals("World", msg.printMessage());
    }

    @Test
    public void t102_CountMessages() {
        // the MessageList was never used.
        verifyZeroInteractions(ml);

        when(ml.pull()).thenReturn("Hello");

        verify(ml, never()).pull();

        // send a bunch of messages.
        msg.printMessage();
        msg.printMessage();
        msg.printMessage();
        msg.printMessage();

        // check how often the method was called.
        verify(ml, times(4)).pull();
        verify(ml, atLeastOnce()).pull();
        verify(ml, atLeast(3)).pull();
        verify(ml, atMost(5)).pull();

        // this is often used for testing Threads !!
        // check if the method was called 4 times within 1 second.
        verify(ml, timeout(1000).times(4)).pull();

        // from this point onwards the mock will never be used.
        verifyNoMoreInteractions(ml);
    }

    @Test
    public void t103_VerifyParameters() {
        ml.addMessageAt(1, "Hello");
        ml.addMessageAt(1, "Hello");
        ml.addMessageAt(1, "Good Day");
        ml.addMessageAt(2, "Good Day");
        ml.addMessageAt(3, "Good Day");

        // static parameters need the eq()-Function.
        verify(ml, times(2)).addMessageAt(eq(1), eq("Hello"));
        verify(ml, times(3)).addMessageAt(anyInt(), eq("Good Day"));
    }

    @Test
    public void t104_spy_list() {
        List<String> spy = spy(ml.getMessages());

        doReturn("Hello").when(spy).get(0);
        when(ml.getMessages()).thenReturn(spy);

        assertEquals("Hello", msg.getMl().getMessages().get(0));
    }

    @Test
    public void t105_stub_methods() {
        // because this is a mock, the method is empty.
        ml.printStaticMessages("Hello", "Madam");

        doCallRealMethod()
                .doNothing()
                .doNothing()
                .doCallRealMethod()
                    .when(ml).printStaticMessages(anyString(), anyString());

        ml.printStaticMessages("Hello", "Mister");
        ml.printStaticMessages("Guten", "Tag");
        ml.printStaticMessages("Guten", "Tag");
        ml.printStaticMessages("Guten", "Tag");
    }

    @Test
    public void t106_spy_usage() {
        Messenger spy = spy(msg);

        when(spy.getMl()).thenReturn(null);

        System.out.println(msg.getMl());

        verify(spy, never()).getMl();

        System.out.println(spy.getMl());
    }

}
