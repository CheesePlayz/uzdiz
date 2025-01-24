package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.modeli.Karta;
import foi.uzdiz.sbicak20.pomocnici.kupovanje.NacinKupovanjaStrategy;

import java.util.Date;

public class KKPV2S implements Komanda{

    private String oznakaVlaka;
    private String polaznaStanica;
    private String odlaznaStanica;
    private Date datum;
    private NacinKupovanjaStrategy nacinKupovanja;


    public KKPV2S(String oznakaVlaka, String polaznaStanica, String odlaznaStanica, Date datum, NacinKupovanjaStrategy nacinKupovanja){
        this.oznakaVlaka = oznakaVlaka;
        this.polaznaStanica = polaznaStanica;
        this.odlaznaStanica = odlaznaStanica;
        this.datum = datum;
        this.nacinKupovanja = nacinKupovanja;
    }

    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        Karta novaKarta = nacinKupovanja.kupiKartu(oznakaVlaka, polaznaStanica, odlaznaStanica, datum);
        // posremi kartu u memento
    }
}
