////////////////////////////////////////////////////////////////////
// [MARCO] [BERNARDI] [2018528]
// [NICOLO] [TRINCA] [2011070]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.EItem.itemType;

@RunWith(JUnit4.class)
public class BillImplementationTest extends TestCase {

    public List<EItem> itemsOrdered;
    public BillImplementation bill;
    public User user;

    @Before
    public void before() {
        bill = new BillImplementation();
        user = new User("Nicolò", "Trinca", "nicolo@gmail.com", LocalDate.of(2001,2,1));
        itemsOrdered = new ArrayList<EItem>();
    }
    @org.junit.Test
    public void testSum() throws BillException {

        EItem Mouse = new EItem(itemType.MOUSE, 20, "Logitech");
        EItem Processor = new EItem(itemType.PROCESSORI, 200, "Intel I5 7600K");
        EItem MoBo = new EItem(itemType.MOTHERBOARD, 85, "Asrock B450");

        itemsOrdered.add(Mouse);
        itemsOrdered.add(Processor);
        itemsOrdered.add(MoBo);
        assertEquals(305.0, bill.getOrderPrice(itemsOrdered, user));
    }
    @org.junit.Test
    public void testEmptyOrder() throws BillException {
        assertEquals(0.0, bill.getOrderPrice(itemsOrdered, user));
    }
    @org.junit.Test
    public void testProcDiscount() throws BillException{
        for (int i=0;i<5;i++){
            itemsOrdered.add(new EItem(itemType.PROCESSORI, 150, "I3"));
        }
        itemsOrdered.add(new EItem(itemType.PROCESSORI, 70, "Intel Celeron"));
        assertEquals(785.0, bill.getOrderPrice(itemsOrdered, user));
    }
}