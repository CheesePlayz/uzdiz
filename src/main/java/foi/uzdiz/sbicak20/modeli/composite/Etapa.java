package foi.uzdiz.sbicak20.modeli.composite;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Etapa extends VozniRedKomponenta{

    private List<VozniRedKomponenta> stanice = new ArrayList<VozniRedKomponenta>();
    private String oznakaVlaka;
    private String oznakaPruge;
    private String daniUTjednu;
    private LocalTime vrijemePolaska;
    private LocalTime vrijemeDolaska;

    public Etapa(String oznakaVlaka, String oznakaPruge, LocalTime vrijemePolaska, LocalTime vrijemeDolaska, String daniUTjednu) {
        this.oznakaVlaka = oznakaVlaka;
        this.oznakaPruge = oznakaPruge;
        this.vrijemePolaska = vrijemePolaska;
        this.vrijemeDolaska = vrijemeDolaska;
        this.daniUTjednu = daniUTjednu;
    }

    public String getOznakaVlaka() {
        return oznakaVlaka;
    }
    public String getOznakaPruge() { return oznakaPruge; }
    public LocalTime getVrijemePolaska(){
        return vrijemePolaska;
    }
    public LocalTime getVrijemeDolaska(){
        return vrijemeDolaska;
    }
    public String getDaniUTjednu() {
        return daniUTjednu;
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
