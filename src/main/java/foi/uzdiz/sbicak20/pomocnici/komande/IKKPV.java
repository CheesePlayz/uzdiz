package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;
import foi.uzdiz.sbicak20.modeli.Karta;
import foi.uzdiz.sbicak20.modeli.memento.Caretaker;

import java.text.SimpleDateFormat;

public class IKKPV implements Komanda{

    private int index;

    public IKKPV(int index){
        this.index = index;
    }
    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        assert ZeljeznickiSustavSingleton.getInstanca() != null;
        Caretaker caretaker= ZeljeznickiSustavSingleton.getInstanca().getKartaCaretaker();
        System.out.printf(
                "| %-15s | %-15s | %-15s | %-15s | %-10s | %-10s | %-10s | %-10s | %-10s | %-15s | %-20s |\n",
                "Oznaka Vlaka", "Polazna Stanica", "Odredišna Stanica", "Datum", "Vrijeme Kretanja",
                "Vrijeme Dolaska", "Izv. Cijena", "Popust", "Konačna Cijena", "Način Kupovine", "Datum Kupovine"
        );
        if (index == -1){
            for(int i = 0; i < caretaker.velicinaMemento(); i++){
                Karta sacuvanaKarta = caretaker.dohvatiMemento(i).getSacuvanoStanjeKarta();
                ispisiKartu(sacuvanaKarta);
            }
        }
        else {
            Karta sacuvanaKarta = caretaker.dohvatiMemento(index).getSacuvanoStanjeKarta();
            ispisiKartu(sacuvanaKarta);
        };
    }

    private void ispisiKartu(Karta karta) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");

        System.out.printf(
                "| %-15s | %-15s | %-15s | %-15s | %-10s | %-10s | %-10.2f | %-10.2f | %-10.2f | %-15s | %-20s |\n",
                karta.getOznakaVlaka(),
                karta.getPolaznaStanica(),
                karta.getOdredisnaStanica(),
                dateFormat.format(karta.getDatum()),
                karta.getVrijemeKretanja(),
                karta.getVrijemeDolaska(),
                karta.getIzvornaCijena(),
                karta.getPopust(),
                karta.getKonacnaCijena(),
                karta.getNacinKupovanja(),
                dateTimeFormat.format(karta.getDatumVrijemeKupovine())
        );
    }

}
