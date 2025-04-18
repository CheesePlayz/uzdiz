package foi.uzdiz.sbicak20.modeli.composite;

import java.util.List;

public class Stanica extends VozniRedKomponenta {
    private final String naziv;

    public Stanica(String naziv) {
        this.naziv = naziv;
    }

    public String getStanica() {
        return naziv;
    }

    @Override
    public void dodajKomponentu(VozniRedKomponenta komponenta) {

    }

    @Override
    public void ukloniKomponentu(VozniRedKomponenta komponenta) {

    }

    @Override
    public List<VozniRedKomponenta> getDjeca() {
        return List.of();
    }

    @Override
    public void prikaziDetalje() {

    }

    @Override
    public Object dohvatiObjekt() {
        return null;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
