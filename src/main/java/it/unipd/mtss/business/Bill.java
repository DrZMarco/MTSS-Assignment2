////////////////////////////////////////////////////////////////////
// [MARCO] [BERNARDI] [2018528]
// [NICOLO] [TRINCA] [2011070]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.business.exception.BillException;

public interface Bill {
    double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException;

}