package foi.uzdiz.sbicak20.modeli.composite;

import java.util.ArrayList;
import java.util.List;

public class Vlak extends VozniRedKomponenta {
    private final List<VozniRedKomponenta> etape = new ArrayList<>();
    private final String oznakaVlaka;

    public Vlak(String oznakaVlaka) {
        this.oznakaVlaka = oznakaVlaka;
    }

    public String getOznakaVlaka() {
        return oznakaVlaka;
    }

    @Override
    public void dodajKomponentu(VozniRedKomponenta komponenta) {
        etape.add(komponenta);
    }

    @Override
    public void ukloniKomponentu(VozniRedKomponenta komponenta) {
        etape.remove(komponenta);
    }

    @Override
    public List<VozniRedKomponenta> getDjeca() {
        return etape;
    }

    @Override
    public void prikaziDetalje() {

    }

    @Override
    public Object dohvatiObjekt() {
        return this;
    }


}
