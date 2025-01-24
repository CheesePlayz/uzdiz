package foi.uzdiz.sbicak20.pomocnici.kupovanje;

import foi.uzdiz.sbicak20.modeli.Karta;

import java.util.Date;

public interface NacinKupovanjaStrategy {
    Karta kupiKartu(String oznakaVlaka, String polaznaStanica, String odredisnaStanica, Date datum);
}
