package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.modeli.OznakaDana;
import foi.uzdiz.sbicak20.modeli.VozniRedPodaci;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;
import foi.uzdiz.sbicak20.modeli.composite.Etapa;
import foi.uzdiz.sbicak20.modeli.composite.Stanica;
import foi.uzdiz.sbicak20.modeli.composite.Vlak;
import foi.uzdiz.sbicak20.modeli.composite.VozniRed;

import java.time.LocalTime;
import java.util.*;

public class VozniRedPunjac {
    public VozniRed napuniVozniRed(List<ZeljeznickaStanica> stanice, List<VozniRedPodaci> vrpodaci, List<OznakaDana> oz) {
        VozniRed vr = new VozniRed();
        for (VozniRedPodaci podaci : vrpodaci) {
            vr.dodajKomponentu(napraviVlak(stanice, podaci, oz));
        }

        return vr;
    }

    public Vlak napraviVlak(List<ZeljeznickaStanica> stanice, VozniRedPodaci vozniRedPodaci, List<OznakaDana> oz) {
        Vlak vlak = new Vlak(vozniRedPodaci.getOznakaVlaka());
        vlak.dodajKomponentu(napraviEtapu(stanice, vozniRedPodaci, oz));
        return vlak;
    }

    public Etapa napraviEtapu(List<ZeljeznickaStanica> stanice, VozniRedPodaci vozniRedPodaci, List<OznakaDana> oz) {
        LocalTime vrijemeDolaska = LocalTime.parse(vozniRedPodaci.getVrijemePolaska() + vozniRedPodaci.getTrajanjeVoznje());
        String daniUTjednu = getDaneUTjednu(vozniRedPodaci, oz);
        ZeljeznickaStanica polaznaStanica = getPolaznaZeljeznickaStanica(vozniRedPodaci, stanice, vozniRedPodaci.getOznakaPruge());
        ZeljeznickaStanica odredisnaStanica = getOdredisnaZeljeznickaStanica(vozniRedPodaci, stanice, vozniRedPodaci.getOznakaPruge());
        Etapa etapa = new Etapa(vozniRedPodaci.getOznakaVlaka(), vozniRedPodaci.getOznakaPruge(), LocalTime.parse(vozniRedPodaci.getVrijemePolaska()), vrijemeDolaska, daniUTjednu);
        etapa.dodajKomponentu(new Stanica(polaznaStanica));
        etapa.dodajKomponentu(new Stanica(odredisnaStanica));
        etapa.setUkupnaKilometraza(izracunajKilometrazu(stanice, polaznaStanica, odredisnaStanica, vozniRedPodaci.getOznakaPruge()));
        return etapa;
    }

    private String getDaneUTjednu(VozniRedPodaci vozniRedPodaci, List<OznakaDana> oz) {
        String dani = null;
        for (OznakaDana oznaka : oz) {
            if (oznaka.getOznakaDana() == Integer.parseInt(vozniRedPodaci.getOznakaDana())) {
                dani = oznaka.getDani();
            }
        }
        return dani;
    }

    private ZeljeznickaStanica getPolaznaZeljeznickaStanica(VozniRedPodaci vrpodaci, List<ZeljeznickaStanica> stanice, String oznakaPrugeParam) {
        if (vrpodaci.getOdredisnaStanica().isEmpty()) {
            if (stanice == null || stanice.isEmpty()) {
                return null;
            }
            Map<String, List<ZeljeznickaStanica>> pruge = new HashMap<>();
            for (ZeljeznickaStanica stanica : stanice) {
                String oznakaPruge = stanica.getOznakaPruge();
                pruge.computeIfAbsent(oznakaPruge, k -> new ArrayList<>()).add(stanica);
            }

            List<ZeljeznickaStanica> staniceNaPrugi = pruge.get(oznakaPrugeParam);
            if (staniceNaPrugi != null && !staniceNaPrugi.isEmpty()) {
                return staniceNaPrugi.getFirst();
            }

            return null;
        } else {
            for (ZeljeznickaStanica stanica : stanice) {
                if (Objects.equals(stanica.getStanica(), vrpodaci.getOdredisnaStanica()) && stanica.getOznakaPruge().equals(vrpodaci.getOznakaPruge())) {
                    return stanica;
                }
            }
        }
        return null;
    }


    private ZeljeznickaStanica getOdredisnaZeljeznickaStanica(VozniRedPodaci vrpodaci, List<ZeljeznickaStanica> stanice, String oznakaPrugeParam) {
        if (vrpodaci.getOdredisnaStanica().isEmpty()) {
            if (stanice == null || stanice.isEmpty()) {
                return null;
            }
            Map<String, List<ZeljeznickaStanica>> pruge = new HashMap<>();
            for (ZeljeznickaStanica stanica : stanice) {
                String oznakaPruge = stanica.getOznakaPruge();
                pruge.computeIfAbsent(oznakaPruge, k -> new ArrayList<>()).add(stanica);
            }

            List<ZeljeznickaStanica> staniceNaPrugi = pruge.get(oznakaPrugeParam);
            if (staniceNaPrugi != null && !staniceNaPrugi.isEmpty()) {
                return staniceNaPrugi.getLast();
            }

            return null;
        } else {
            for (ZeljeznickaStanica stanica : stanice) {
                if (Objects.equals(stanica.getStanica(), vrpodaci.getOdredisnaStanica()) && stanica.getOznakaPruge().equals(vrpodaci.getOznakaPruge())) {
                    return stanica;
                }
            }
        }
        return null;
    }

    private double izracunajKilometrazu(List<ZeljeznickaStanica> stanice, ZeljeznickaStanica polazna, ZeljeznickaStanica odredisna, String oznakaPrugeParam) {
        Map<String, List<ZeljeznickaStanica>> pruge = new HashMap<>();
        for (ZeljeznickaStanica stanica : stanice) {
            String oznakaPruge = stanica.getOznakaPruge();
            pruge.computeIfAbsent(oznakaPruge, k -> new ArrayList<>()).add(stanica);
        }

        List<ZeljeznickaStanica> staniceNaPrugi = pruge.get(oznakaPrugeParam);
        if (staniceNaPrugi == null || staniceNaPrugi.isEmpty()) {
            return 0;
        }

        int indeksPolazne = staniceNaPrugi.indexOf(polazna);
        int indeksOdredisne = staniceNaPrugi.indexOf(odredisna);

        if (indeksPolazne == -1 || indeksOdredisne == -1) {
            throw new IllegalArgumentException("Polazna ili odredišna stanica nije pronađena na pruzi.");
        }

        double ukupnaKilometraza = 0;

        if (indeksPolazne <= indeksOdredisne) {
            for (int i = indeksPolazne; i < indeksOdredisne; i++) {
                ukupnaKilometraza += staniceNaPrugi.get(i + 1).getDuzina();
            }
        } else {
            for (int i = indeksPolazne; i > indeksOdredisne; i--) {
                ukupnaKilometraza += staniceNaPrugi.get(i).getDuzina();
            }
        }

        return ukupnaKilometraza;
    }

}
