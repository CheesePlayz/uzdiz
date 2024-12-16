package foi.uzdiz.sbicak20.modeli.composite;

import java.util.ArrayList;
import java.util.List;

public class VozniRed extends VozniRedKomponenta{
    private List<VozniRedKomponenta> vlakovi = new ArrayList<>();

    @Override
    public void dodajKomponentu(VozniRedKomponenta komponenta) {
        vlakovi.add(komponenta);
    }

    @Override
    public void ukloniKomponentu(VozniRedKomponenta komponenta) {
        vlakovi.remove(komponenta);
    }

    @Override
    public List<VozniRedKomponenta> getDjeca() {
        return vlakovi;
    }

    @Override
    public void prikaziDetalje() {

    }


}
