package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaPrijevoznaSredstva;
import foi.uzdiz.sbicak20.modeli.ZeljeznickeStanice;

import java.util.ArrayList;
import java.util.List;

public class InitSustava {
    CitajCSV csvcitac;
    private List<ZeljeznickeStanice> stanice;
    private List<ZeljeznickaPrijevoznaSredstva> vozila;
    private List<Kompozicija> kompozicije;

    public InitSustava(String putanjaCsvStanice, String putanjaCsvPrijevoznaSredstva, String putanjaCsvKompozicije){
        csvcitac = new CitajCSV();
        stanice = CitajCSV.ucitajStaniceIzCSV(putanjaCsvStanice);
        vozila = CitajCSV.ucitajVozilaIzCSV(putanjaCsvPrijevoznaSredstva);
        kompozicije = CitajCSV.ucitajKompozicijeIzCSV(putanjaCsvKompozicije);
    }

    public List<ZeljeznickeStanice> getStanice(){
        return stanice;
    }

    public List<ZeljeznickaPrijevoznaSredstva> getVozila(){
        return vozila;
    }

    public List<Kompozicija> getKompozicije(){
        return kompozicije;
    }
}
