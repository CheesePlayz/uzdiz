package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.greske.SustavGresaka;
import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaPrijevoznaSredstva;
import foi.uzdiz.sbicak20.modeli.ZeljeznickeStanice;

import java.util.List;

public class InitSustava {
    CitacCSV csvcitac;
    private List<ZeljeznickeStanice> stanice;
    private List<ZeljeznickaPrijevoznaSredstva> vozila;
    private List<List<Kompozicija>> kompozicije;

    public InitSustava(String putanjaCsvStanice, String putanjaCsvPrijevoznaSredstva, String putanjaCsvKompozicije) throws Exception {
        csvcitac = new CitacCSV();
        stanice = CitacCSV.ucitajStaniceIzCSV(putanjaCsvStanice);
        vozila = CitacCSV.ucitajVozilaIzCSV(putanjaCsvPrijevoznaSredstva);
        kompozicije = CitacCSV.ucitajKompozicijeIzCSV(putanjaCsvKompozicije);

    }

    public List<ZeljeznickeStanice> getStanice() {
        return stanice;
    }

    public List<ZeljeznickaPrijevoznaSredstva> getVozila() {
        return vozila;
    }

    public List<List<Kompozicija>> getKompozicije() {
        return kompozicije;
    }
}
