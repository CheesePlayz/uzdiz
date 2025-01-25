package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;
import foi.uzdiz.sbicak20.modeli.Karta;
import foi.uzdiz.sbicak20.modeli.memento.Caretaker;
import foi.uzdiz.sbicak20.modeli.memento.Memento;

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
        Caretaker caretaker = ZeljeznickiSustavSingleton.getInstanca().getKartaCaretaker();

        if (caretaker == null || caretaker.velicinaMemento() == 0) {
            System.out.println("Nema spremljenih karata za ispis.");
            return;
        }

        System.out.printf(
                "| %-15s | %-18s | %-18s | %-12s | %-17s | %-17s | %-13s | %-11s | %-14s | %-15s | %-18s |\n",
                "Oznaka Vlaka", "Polazna Stanica", "Odredišna Stanica", "Datum", "Vrijeme Kretanja",
                "Vrijeme Dolaska", "Izv. Cijena", "Popust (%)", "Konačna Cijena", "Način Kupovine", "Datum Kupovine"
        );

        if (index == -1) {
            for (int i = 0; i < caretaker.velicinaMemento(); i++) {
                Memento memento = caretaker.dohvatiMemento(i);
                if (memento != null) {
                    ispisiKartu(memento.getSacuvanoStanjeKarta());
                } else {
                    System.out.println("Ne postoji Memento na tom indexu!");
                }
            }
        } else {
            if (index-1 < 0 || index-1 >= caretaker.velicinaMemento()) {
                System.out.println("Neispravan indeks. Unesite valjani indeks karte.");
                return;
            }

            Memento memento = caretaker.dohvatiMemento(index-1);
            if (memento != null) {
                ispisiKartu(memento.getSacuvanoStanjeKarta());
            } else {
                System.out.println("Ne postoji Memento na tom indexu!");
            }
        }
    }


    private void ispisiKartu(Karta karta) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm");

        System.out.printf(
                "| %-15s | %-18s | %-18s | %-12s | %-17s | %-17s | %-13.2f | %-11.2f | %-14.2f | %-15s | %-18s |\n",
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
