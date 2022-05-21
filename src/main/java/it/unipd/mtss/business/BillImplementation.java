////////////////////////////////////////////////////////////////////
// [MARCO] [BERNARDI] [2018528]
// [NICOLO] [TRINCA] [2011070]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

public class BillImplementation implements Bill {

    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
        HashMap<EItem.itemType,ArrayList<EItem>> map = new HashMap<EItem.itemType,ArrayList<EItem>>();
        for (EItem eItem : itemsOrdered) {
            if (map.get(eItem.getType()) == null){
                ArrayList<EItem> listE = new ArrayList<EItem>();
                listE.add(eItem);
                map.put(eItem.getType(), listE);
            }else{
                map.get(eItem.getType()).add(eItem);
            }
        }
        return getProcessorPrice(map.get(EItem.itemType.PROCESSORI)) +
                getKeyboardPrice(map.get(EItem.itemType.TASTIERE)) +
                getMousePrice(map.get(EItem.itemType.MOUSE)) +
                getMotherboardPrice(map.get(EItem.itemType.MOTHERBOARD));
    }

    double getProcessorPrice(List<EItem> processList){
        if(processList == null || processList.size() == 0){
            return 0.0;
        }
        if (processList.size() > 5){
            processList.stream().min(Comparator.comparing((EItem x) -> x.getPrice())).get().discount(0.5);
        }
        return processList.stream().map((x) -> x.getPrice()).reduce(0.0, (a,b) -> a+b);
    }

    double getKeyboardPrice(List<EItem> keyboardlist){
        if(keyboardlist == null || keyboardlist.size() == 0){
            return 0.0;
        }
        return keyboardlist.stream().map((x) -> x.getPrice()).reduce(0.0, (a,b) -> a+b);
    }

    double getMousePrice(List<EItem> mouselist){
        if(mouselist == null || mouselist.size() == 0){
            return 0.0;
        }
        return mouselist.stream().map((x) -> x.getPrice()).reduce(0.0, (a,b) -> a+b);
    }

    double getMotherboardPrice(List<EItem> motherboardlist){
        if(motherboardlist == null || motherboardlist.size() == 0){
            return 0.0;
        }
        return motherboardlist.stream().map((x) -> x.getPrice()).reduce(0.0, (a,b) -> a+b);
    }

}