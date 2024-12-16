package foi.uzdiz.sbicak20.modeli.composite;

import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;

import java.util.ArrayList;
import java.util.List;

public class Etapa extends VozniRedKomponenta{

    private List<VozniRedKomponenta> stanice = new ArrayList<VozniRedKomponenta>();
    private String oznakaVlaka;
    private String oznakaPruge;
    private String vrijemePolaska;
    private String vrijemeDolaska;

    public Etapa(String oznakaVlaka, String vrijemePolaska, String vrijemeDolaska, String oznakaPruge) {
        this.oznakaVlaka = oznakaVlaka;
        this.oznakaPruge = oznakaPruge;
        this.vrijemePolaska = vrijemePolaska;
        this.vrijemeDolaska = vrijemeDolaska;
    }

    public String getOznakaVlaka() {
        return oznakaVlaka;
    }
    public String getVrijemePolaska(){
        return vrijemePolaska;
    }
    private String getVrijemeDolaska(){
        return vrijemeDolaska;
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
