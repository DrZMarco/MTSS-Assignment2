////////////////////////////////////////////////////////////////////
// [MARCO] [BERNARDI] [2018528]
// [NICOLO] [TRINCA] [2011070]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EItemTest {
    EItem item = null;

    @Before
    public void beforeTests() {
        item = new EItem(EItem.itemType.PROCESSORI, 50.00, "Intel Celeron");
    }

    @Test
    public void testGetItemType() {
        assertEquals(EItem.itemType.PROCESSORI, item.getType());
    }

    @Test
    public void testGetName() {
        assertEquals("Intel Celeron", item.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(50.00, item.getPrice(), 1e-8);
    }

    @Test
    public void testNegativePrice() {
        try {
            item = new EItem(EItem.itemType.PROCESSORI, -5.00, "Intel Celeron");
        } catch(IllegalArgumentException exc) {
            assertEquals("Il prezzo deve essere positivo", exc.getMessage());
        }
    }
}