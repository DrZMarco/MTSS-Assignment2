////////////////////////////////////////////////////////////////////
// [MARCO] [BERNARDI] [2018528]
// [NICOLO] [TRINCA] [2011070]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;
import it.unipd.mtss.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Giveaway {
    Random rand;
    List<User> users;

    public Giveaway() {
        rand = new Random();
        rand.setSeed(10);
        users = new ArrayList<>();
    }

    public boolean checkRequirements(User user, LocalTime timeStamp) {
        if(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears() < 18) {
            if(timeStamp.isAfter(LocalTime.of(18, 0)) && timeStamp.isBefore(LocalTime.of(19, 0))) {
                if(users.size() < 10) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean giftOrder(User user, LocalTime timeStamp) {
        if(user == null) {
            throw new IllegalArgumentException("User null");
        }
        if(timeStamp == null) {
            throw new IllegalArgumentException("Timestamp null");
        }
        if(checkRequirements(user, timeStamp) && rand.nextInt(100) <= 25) {
            users.add(user);
            return true;
        }
        return false;
    }
}