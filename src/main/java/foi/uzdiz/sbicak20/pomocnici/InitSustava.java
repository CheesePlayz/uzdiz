package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickoPrijevoznoSredstvo;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;
import foi.uzdiz.sbicak20.validatori.KompozicijaFactory;
import foi.uzdiz.sbicak20.validatori.PrijevoznoSredstvoFactory;
import foi.uzdiz.sbicak20.validatori.StanicaFactory;

import java.util.List;

public class InitSustava {
    CitacCSV csvcitac;
    private List<ZeljeznickaStanica> stanice;
    private List<ZeljeznickoPrijevoznoSredstvo> vozila;
    private List<List<Kompozicija>> kompozicije;

    public InitSustava(String putanjaCsvStanice, String putanjaCsvPrijevoznaSredstva, String putanjaCsvKompozicije) throws Exception {
        csvcitac = new CitacCSV();
        stanice = CitacCSV.ucitajStaniceIzCSV(putanjaCsvStanice, new StanicaFactory());
        vozila = CitacCSV.ucitajVozilaIzCSV(putanjaCsvPrijevoznaSredstva, new PrijevoznoSredstvoFactory());
        kompozicije = CitacCSV.ucitajKompozicijeIzCSV(putanjaCsvKompozicije, new KompozicijaFactory());

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
}
