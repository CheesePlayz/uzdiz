package foi.uzdiz.sbicak20.modeli.composite;

import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;

import java.util.List;

public class Stanica extends VozniRedKomponenta{
    private ZeljeznickaStanica detaljiStanice;

    public Stanica(ZeljeznickaStanica detaljiStanice) {
        this.detaljiStanice = detaljiStanice;
    }

    public ZeljeznickaStanica getDetaljiStanice() {
        return detaljiStanice;
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
