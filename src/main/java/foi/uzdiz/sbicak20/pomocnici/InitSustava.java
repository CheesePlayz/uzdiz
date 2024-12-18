package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.modeli.*;
import foi.uzdiz.sbicak20.validatori.KompozicijaFactory;
import foi.uzdiz.sbicak20.validatori.PrijevoznoSredstvoFactory;
import foi.uzdiz.sbicak20.validatori.StanicaFactory;
import foi.uzdiz.sbicak20.validatori.VozniRedFactory;

import java.util.List;

public class InitSustava {
    CitacCSV csvcitac;
    private final List<ZeljeznickaStanica> stanice;
    private final List<ZeljeznickoPrijevoznoSredstvo> vozila;
    private final List<List<Kompozicija>> kompozicije;
    private final List<VozniRedPodaci> vozniRed;
    private final List<OznakaDana> oznakeDana;

    public InitSustava(String putanjaCsvStanice, String putanjaCsvPrijevoznaSredstva, String putanjaCsvKompozicije, String putanjaCsvVozniRed, String putanjaCsvOznakeDana) throws Exception {
        csvcitac = new CitacCSV();
        stanice = CitacCSV.ucitajStaniceIzCSV(putanjaCsvStanice, new StanicaFactory());
        vozila = CitacCSV.ucitajVozilaIzCSV(putanjaCsvPrijevoznaSredstva, new PrijevoznoSredstvoFactory());
        kompozicije = CitacCSV.ucitajKompozicijeIzCSV(putanjaCsvKompozicije, new KompozicijaFactory());
        vozniRed = CitacCSV.ucitajVozniRedIzCSV(putanjaCsvVozniRed, new VozniRedFactory());
        oznakeDana = CitacCSV.ucitajOznakeDanaIzCSV(putanjaCsvOznakeDana);

    }

    public List<ZeljeznickaStanica> getStanice() {
        return stanice;
    }

    public List<ZeljeznickoPrijevoznoSredstvo> getVozila() {
        return vozila;
    }

    public List<List<Kompozicija>> getKompozicije() {
        return kompozicije;
    }

    public List<VozniRedPodaci> getVozniRed() {
        return vozniRed;
    }

    public List<OznakaDana> getOznakeDana() {
        return oznakeDana;
    }
}
