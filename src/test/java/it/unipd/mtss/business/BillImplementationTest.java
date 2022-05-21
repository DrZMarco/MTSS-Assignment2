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
    @org.junit.Test
    public void testMouseDiscount() throws BillException{
        for (int i=0;i<10;i++){
            itemsOrdered.add(new EItem(itemType.MOUSE, 15, "Mouse logitech"));
        }
        itemsOrdered.add(new EItem(itemType.MOUSE, 50, "Corsair Mouse"));
        itemsOrdered.add(new EItem(itemType.MOUSE, 5, "Aliexpress Mouse"));
        assertEquals(200.0, bill.getOrderPrice(itemsOrdered, user));
    }
    @org.junit.Test
    public void testKeyboardMouseDiscount() throws BillException{
        for (int i=0;i<10;i++){
            itemsOrdered.add(new EItem(itemType.MOUSE, 15, "Mouse logitech"));
            itemsOrdered.add(new EItem(itemType.TASTIERE, 35, "Tastiera logitech"));
        }
    }
    @org.junit.Test
    public void testBigOrder() throws BillException{
        for (int i=0;i<10;i++){
            itemsOrdered.add(new EItem(itemType.MOUSE, 15, "Mouse logitech"));
            itemsOrdered.add(new EItem(itemType.TASTIERE, 35, "Tastiera logitech"));
        }
        itemsOrdered.add(new EItem(itemType.PROCESSORI, 200, "Intel I5 7600K"));
        itemsOrdered.add(new EItem(itemType.PROCESSORI, 350, "Intel I7 10700"));
        itemsOrdered.add(new EItem(itemType.MOTHERBOARD, 150, "MSI Z390"));
        assertEquals(1066.5, bill.getOrderPrice(itemsOrdered, user));
    }
    @org.junit.Test
    public void testTooManyItems() {
        for(int i=0; i<31; i++) {
            itemsOrdered.add(new EItem(itemType.PROCESSORI, 200, "Intel I5 7600K"));
        }
        try {
            bill.getOrderPrice(itemsOrdered, user);
        } catch(BillException e) {
            assertEquals("Ordine superiore a 30pz", e.getMessage());
        }
    }
    @org.junit.Test
    public void testLowAmountOrder() throws BillException {
        itemsOrdered.add(new EItem(itemType.MOUSE, 7, "Wish Mouse"));
        assertEquals(9.0, bill.getOrderPrice(itemsOrdered, user));
    }
}