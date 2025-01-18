package foi.uzdiz.sbicak20.pomocnici.stanja;

import foi.uzdiz.sbicak20.modeli.Pruga;

public interface PrugaState {
    void promijeniStatus(Pruga pruga, String polazna, String odredisna);
    String dohvatiNazivStatusa();
}
