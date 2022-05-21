package it.unipd.mtss.business;

import it.unipd.mtss.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class GiveawayTest {
    Giveaway gift = null;
    User user = null;

    @Before
    public void beforeTests() {
        user = new User("Nicolò", "Trinca", "nicolo@gmail.com", LocalDate.of(2009,2,1));
        gift = new Giveaway();
    }

    @Test
    public void testRequirements() {
        assertTrue(gift.giftOrder(user, LocalTime.of(18, 30)));
    }

    @Test
    public void testRequirements_WrongAge() {
        user = new User("Nicolò", "Trinca", "nicolo@gmail.com", LocalDate.of(2001,2,1));
        assertFalse(gift.giftOrder(user, LocalTime.of(18, 30)));
    }

    @Test
    public void testRequirements_WrongTimestamp() {
        user =new User("Nicolò", "Trinca", "nicolo@gmail.com", LocalDate.of(2009,2,1));
        assertFalse(gift.giftOrder(user, LocalTime.of(10, 30)));
    }

    @Test
    public void testRequirements_TooManyUsers() throws IllegalArgumentException {
        for(int i=0; i<10; i++) {
            gift.users.add(user);
        }
        assertFalse(gift.giftOrder(user, LocalTime.of(18, 30)));
    }

    @Test
    public void testGiftOrder() throws IllegalArgumentException{
        assertTrue(gift.giftOrder(user, LocalTime.of(18, 30)));
    }

    @Test
    public void testGiftOrder_NullUser() {
        user = null;
        try {
            gift.giftOrder(user, LocalTime.of(18, 30));
        } catch(IllegalArgumentException e) {
            assertEquals("User null", e.getMessage());
        }
    }

    @Test
    public void testGiftOrder_NullTimestamp() {
        try {
            gift.giftOrder(user, null);
        } catch(IllegalArgumentException e) {
            assertEquals("Timestamp null", e.getMessage());
        }
    }
}