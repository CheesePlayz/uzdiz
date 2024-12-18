package foi.uzdiz.sbicak20;

import foi.uzdiz.sbicak20.modeli.*;
import foi.uzdiz.sbicak20.modeli.composite.Etapa;
import foi.uzdiz.sbicak20.modeli.composite.Vlak;
import foi.uzdiz.sbicak20.modeli.composite.VozniRed;
import foi.uzdiz.sbicak20.modeli.composite.VozniRedKomponenta;
import foi.uzdiz.sbicak20.pomocnici.VozniRedPunjac;
import foi.uzdiz.sbicak20.pomocnici.komande.*;

import java.util.*;

import static java.lang.System.exit;

public class ZeljeznickiSustavSingleton {
    private static ZeljeznickiSustavSingleton instanca;

    private RegistarKorisnika registarKorisnika;

    private List<ZeljeznickaStanica> stanice;
    private List<ZeljeznickoPrijevoznoSredstvo> vozila;
    private List<List<Kompozicija>> kompozicije;
    private List<VozniRedPodaci> vozniRedPodaci;
    private List<OznakaDana> oznakeDana;

    private VozniRed vozniRed;
    private VozniRedPunjac vozniRedPunjac;

    public static void setInstanca(ZeljeznickiSustavSingleton instanca) {
        ZeljeznickiSustavSingleton.instanca = instanca;
    }

    public void setRegistarKorisnika(RegistarKorisnika registarKorisnika) {
        this.registarKorisnika = registarKorisnika;
    }

    public void setStanice(List<ZeljeznickaStanica> stanice) {
        this.stanice = stanice;
    }

    public void setVozila(List<ZeljeznickoPrijevoznoSredstvo> vozila) {
        this.vozila = vozila;
    }

    public void setKompozicije(List<List<Kompozicija>> kompozicije) {
        this.kompozicije = kompozicije;
    }

    public void setVozniRedPodaci(List<VozniRedPodaci> vozniRedPodaci) {
        this.vozniRedPodaci = vozniRedPodaci;
    }

    public void setOznakeDana(List<OznakaDana> oznakeDana) {
        this.oznakeDana = oznakeDana;
    }

    public void setVozniRed(VozniRed vozniRed) {
        this.vozniRed = vozniRed;
    }

    public void setVozniRedPunjac(VozniRedPunjac vozniRedPunjac) {
        this.vozniRedPunjac = vozniRedPunjac;
    }

    public List<List<Kompozicija>> getKompozicije() {
        return kompozicije;
    }

    public RegistarKorisnika getRegistarKorisnika() {
        return registarKorisnika;
    }

    public VozniRed getVozniRed() {
        return vozniRed;
    }

    public VozniRedPunjac getVozniRedPunjac() {
        return vozniRedPunjac;
    }

    public List<OznakaDana> getOznakeDana() {
        return oznakeDana;
    }

    public List<ZeljeznickaStanica> getStanice() {
        return stanice;
    }

    public List<VozniRedPodaci> getVozniRedPodaci() {
        return vozniRedPodaci;
    }

    public List<ZeljeznickoPrijevoznoSredstvo> getVozila() {
        return vozila;
    }

    private ZeljeznickiSustavSingleton(List<ZeljeznickaStanica> stanice, List<ZeljeznickoPrijevoznoSredstvo> vozila, List<List<Kompozicija>> kompozicije, List<VozniRedPodaci> vr, List<OznakaDana> od) {
        this.stanice = stanice;
        this.vozila = vozila;
        this.kompozicije = kompozicije;
        this.vozniRedPodaci = vr;
        this.oznakeDana = od;
        registarKorisnika = new RegistarKorisnika();
        vozniRedPunjac = new VozniRedPunjac();
        vozniRed = vozniRedPunjac.napuniVozniRed(stanice, vr, od);
    }

    public static ZeljeznickiSustavSingleton getInstanca(){
        if (instanca != null){
            return instanca;
        }
        return null;
    }

    public static ZeljeznickiSustavSingleton getInstanca(List<ZeljeznickaStanica> stanice, List<ZeljeznickoPrijevoznoSredstvo> vozila, List<List<Kompozicija>> kompozicije, List<VozniRedPodaci> vr, List<OznakaDana> od) {
        if (instanca == null) {
            instanca = new ZeljeznickiSustavSingleton(stanice, vozila, kompozicije, vr, od);
        }
        return instanca;
    }

    public void IzvrsiKomanduVisitor(String komandaString) {
        Komanda komanda = parseKomandu(komandaString);

        if (komanda == null) {
            return;
        }

        KomandaVisitor visitor = new IzvrsiKomanduVisitor();
        komanda.prihvati(visitor);
    }

    private Komanda parseKomandu(String komandaString) {
        if (komandaString.equals("IP")) {
            return new IP(stanice);
        }

        if (komandaString.matches("^ISP \\S+ [NO]$")) {
            String[] dioKomande = komandaString.split(" ");
            boolean obrnut = dioKomande[2].equals("O");
            return new ISP(stanice, dioKomande[1], obrnut);
        }

        if (komandaString.matches("^ISI2S .+ - .+$")) {
            String[] dioKomande = komandaString.substring(6).trim().split(" - ");
            if (dioKomande.length == 2) {
                return new ISI2S(stanice, dioKomande[0].trim(), dioKomande[1].trim());
            }
        }

        if (komandaString.matches("^IK \\S+$")) {
            return new IK(kompozicije, vozila, komandaString.split(" ")[1]);
        }

        if (komandaString.equals("IV")) {
            return new IV(vozniRed);
        }

        if (komandaString.matches("^IEV .+$")) {
            String oznakaVlaka = komandaString.substring(4).trim();
            return new IEV(vozniRed, oznakaVlaka);
        }

        if (komandaString.matches("^IEVD .+$")) {
            String dani = komandaString.substring(5).trim();
            return new IEVD(vozniRed, dani);
        }

        if (komandaString.matches("^IVRV .+$")) {
            String oznakaVlaka = komandaString.substring(5).trim();
            return new IVRV(vozniRed, stanice, oznakaVlaka);
        }


        if (komandaString.matches("^DK \\S+ \\S+$")) {
            String[] dioKomande = komandaString.split(" ");
            return new DK(dioKomande[1], dioKomande[2]);
        }

        if (komandaString.equals("PK")) {
            return new PK();
        }

        if (komandaString.matches("^DPK \\S+ \\S+ - \\S+( - .+)?$")) {
            String[] dioKomande = komandaString.split(" - ");
            String imePrezimePart = dioKomande[0].substring(4).trim();
            String[] imePrezime = imePrezimePart.split(" ", 2);
            if (imePrezime.length != 2) {
                return null;
            }
            String ime = imePrezime[0];
            String prezime = imePrezime[1];
            String oznakaVlaka = dioKomande[1].trim();
            String stanica = dioKomande.length == 3 ? dioKomande[2].trim() : null;
            return new DPK(ime, prezime, oznakaVlaka, stanica);
        }

        if (komandaString.equals("Q")) {
            return new Q();
        }

        System.out.println("Nepoznata komanda ili pogrešna sintaksa: " + komandaString);
        return null;
    }

}
