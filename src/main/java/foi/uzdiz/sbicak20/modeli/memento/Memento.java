package foi.uzdiz.sbicak20.modeli.memento;

import foi.uzdiz.sbicak20.modeli.Karta;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Memento {
    private Karta sacuvanoStanjeKarta;

    public Memento(Karta karta) {
        try {
            this.sacuvanoStanjeKarta = new Karta.KartaBuilder()
                    .oznakaVlaka(karta.getOznakaVlaka())
                    .polaznaStanica(karta.getPolaznaStanica())
                    .odredisnaStanica(karta.getOdredisnaStanica())
                    .datum(new SimpleDateFormat("dd.MM.yyyy.").format(karta.getDatum()))
                    .vrijemeKretanja(karta.getVrijemeKretanja())
                    .vrijemeDolaska(karta.getVrijemeDolaska())
                    .izvornaCijena(karta.getIzvornaCijena())
                    .popust(karta.getPopust())
                    .konacnaCijena(karta.getKonacnaCijena())
                    .nacinKupovanja(karta.getNacinKupovanja())
                    .datumVrijemeKupovine(new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss").format(karta.getDatumVrijemeKupovine()))
                    .build();
        } catch (Exception e) {
            System.out.println("Greška kod spremanja stanja karte u Memento.");
        }
    }

    public Karta getSacuvanoStanjeKarta() {
        return sacuvanoStanjeKarta;
    }
}
