////////////////////////////////////////////////////////////////////
// [MARCO] [BERNARDI] [2018528]
// [NICOLO] [TRINCA] [2011070]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserTest {User user;

    @Before
    public void initialize() {
        user = new User("Nicolò", "Trinca", "nicolo@gmail.com", LocalDate.of(2001,2,1));
    }

    @Test
    public void testGetEmail() {
        assertEquals("nicolo@gmail.com", user.getEmail());
    }

    @Test
    public void testGetName() {
        assertEquals("Nicolò", user.getName());
    }

    @Test
    public void testGetSurname() {
        assertEquals("Trinca", user.getSurname());
    }

    @Test
    public void testGetDateOfBirth() {
        assertEquals(LocalDate.of(2001, 2, 1), user.getDateOfBirth());
    }

    @Test
    public void testNullDateOfBirth() {
        try {
            new User("Nicolò", "Trinca", "nicolo@gmail.com", null);
        }catch(IllegalArgumentException exc) {
            assertEquals("Data di nascita non valida", exc.getMessage());
        }
    }

    @Test
    public void testFutureDateOfBirth() {
        try {
            new User("Nicolò", "Trinca", "nicolo@gmail.com", LocalDate.of(2051,2,1));
        } catch (IllegalArgumentException e) {
            assertEquals("Data di nascita futura", e.getMessage());
        }
    }
}