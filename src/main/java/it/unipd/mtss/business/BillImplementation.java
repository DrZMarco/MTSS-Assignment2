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
                getPerifPrice(map.get(EItem.itemType.MOUSE), map.get(EItem.itemType.TASTIERE))+
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
    double getPerifPrice(List<EItem> mouseList, List<EItem> keyboardList){
        ArrayList<EItem> Perif = new ArrayList<EItem>();
        if (mouseList != null){
            if (mouseList.size() > 10){
                mouseList.stream().min(Comparator.comparing((EItem x) -> x.getPrice())).get().discount(1.0);
                Perif.addAll(mouseList);
            } else{
                Perif.addAll(mouseList);
            }
        }
        if (keyboardList != null){
            Perif.addAll(keyboardList);
            if (mouseList.size() == keyboardList.size() && mouseList.size() != 0){
                Perif.stream().min(Comparator.comparing((EItem x) -> x.getPrice())).get().discount(1.0);
            }
        }
        return Perif.stream().map((x) -> x.getPrice()).reduce(0.0, (a,b) -> a+b);
    }
    double getMotherboardPrice(List<EItem> motherboardlist){
        if(motherboardlist == null || motherboardlist.size() == 0){
            return 0.0;
        }
        return motherboardlist.stream().map((x) -> x.getPrice()).reduce(0.0, (a,b) -> a+b);
    }

}