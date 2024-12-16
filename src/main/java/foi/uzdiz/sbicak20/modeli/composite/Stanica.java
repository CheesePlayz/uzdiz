package foi.uzdiz.sbicak20.modeli.composite;

import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;

import java.util.List;

public class Stanica extends VozniRedKomponenta {
    private ZeljeznickaStanica stanica ;

    public Stanica(ZeljeznickaStanica detaljiStanice) {
        this.stanica = detaljiStanice;
    }

    public ZeljeznickaStanica getStanica() {
        return stanica;
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
}
