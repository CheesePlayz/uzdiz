package foi.uzdiz.sbicak20.modeli;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistarKorisnika {
    static class Korisnik {
        String ime;
        String prezime;

        public Korisnik(String ime, String prezime) {
            this.ime = ime;
            this.prezime = prezime;
        }

        @Override
        public String toString() {
            return ime + " " + prezime;
        }
    }

    static class PracenjeVlaka {
        Korisnik korisnik;
        String oznakaVlaka;
        String stanica;

        public PracenjeVlaka(Korisnik korisnik, String oznakaVlaka, String stanica) {
            this.korisnik = korisnik;
            this.oznakaVlaka = oznakaVlaka;
            this.stanica = stanica;
        }

        @Override
        public String toString() {
            return korisnik + " - " + oznakaVlaka + (stanica != null ? " - " + stanica : "");
        }
    }

    private List<Korisnik> korisnici = new ArrayList<>();
    private List<PracenjeVlaka> pracenjaVlaka = new ArrayList<>();

    public void dodajKorisnika(String ime, String prezime) {
        Korisnik korisnik = new Korisnik(ime, prezime);
        korisnici.add(korisnik);
        System.out.println("Korisnik dodan u registar: " + korisnik);
    }

    public void ispisiKorisnike() {
        if (korisnici.isEmpty()) {
            System.out.println("Registar korisnika je prazan.");
        } else {
            System.out.println("Popis korisnika:");
            for (Korisnik korisnik : korisnici) {
                System.out.println("- " + korisnik);
            }
        }
    }

    public void dodajPracenjeVlaka(String ime, String prezime, String oznakaVlaka, String stanica) {
        Optional<Korisnik> korisnikOptional = korisnici.stream()
                .filter(korisnik -> korisnik.ime.equals(ime) && korisnik.prezime.equals(prezime))
                .findFirst();

        if (korisnikOptional.isPresent()) {
            PracenjeVlaka pracenje = new PracenjeVlaka(korisnikOptional.get(), oznakaVlaka, stanica);
            pracenjaVlaka.add(pracenje);
            System.out.println("Dodano praćenje: " + pracenje);
        } else {
            System.out.println("Korisnik " + ime + " " + prezime + " nije pronađen u registru.");
        }
    }
}
