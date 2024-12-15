package foi.uzdiz.sbicak20.modeli.composite;

import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;

import java.util.ArrayList;
import java.util.List;

public class Etapa extends VozniRedKomponenta{

    private List<VozniRedKomponenta> stanice = new ArrayList<VozniRedKomponenta>();
    private String oznakaVlaka;
    private String vrijemePolaska;
    private String vrijemeDolaska;

    public Etapa(String oznakaVlaka, String vrijemePolaska, String vrijemeDolaska) {
        this.oznakaVlaka = oznakaVlaka;
        this.vrijemePolaska = vrijemePolaska;
        this.vrijemeDolaska = vrijemeDolaska;
    }
    @Override
    public void dodajKomponentu(VozniRedKomponenta komponenta) {
        stanice.add(komponenta);
    }

    @Override
    public void ukloniKomponentu(VozniRedKomponenta komponenta) {
        stanice.remove(komponenta);
    }

    @Override
    public List<VozniRedKomponenta> getDjeca() {
        return stanice;
    }

    @Override
    public void prikaziDetalje() {

    }
}
