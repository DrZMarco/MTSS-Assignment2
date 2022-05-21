////////////////////////////////////////////////////////////////////
// [MARCO] [BERNARDI] [2018528]
// [NICOLO] [TRINCA] [2011070]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItem {

    public enum itemType {
        PROCESSORI, MOTHERBOARD, MOUSE, TASTIERE
    }

    private itemType tipo;
    private Double prezzo;
    private String nome;

    public EItem(itemType tipo, double prezzo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
        if (prezzo < 0){
            throw new IllegalArgumentException("Il prezzo deve essere positivo");
        }
        this.prezzo = prezzo;

    }

    public double getPrice() {
        return prezzo;
    }

    public void discount(double sconto) {
        this.prezzo *= (1-sconto);
    }

    public itemType getType() {
        return tipo;
    }

    public String getName(){
        return nome;
    }
}