package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.modeli.OznakaDana;
import foi.uzdiz.sbicak20.modeli.VozniRedPodaci;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;
import foi.uzdiz.sbicak20.modeli.composite.Etapa;
import foi.uzdiz.sbicak20.modeli.composite.Stanica;
import foi.uzdiz.sbicak20.modeli.composite.Vlak;
import foi.uzdiz.sbicak20.modeli.composite.VozniRed;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class VozniRedPunjac {
    public VozniRed napuniVozniRed(List<ZeljeznickaStanica> stanice, List<VozniRedPodaci> vrpodaci, List<OznakaDana> oz) {
        VozniRed vr = new VozniRed();

        for (VozniRedPodaci podaci : vrpodaci) {
            Vlak postojeciVlak = (Vlak) vr.getDjeca().stream()
                    .filter(komponenta -> komponenta instanceof Vlak &&
                            ((Vlak) komponenta).getOznakaVlaka().equals(podaci.getOznakaVlaka()))
                    .findFirst()
                    .orElse(null);

            if (postojeciVlak != null) {
                postojeciVlak.dodajKomponentu(napraviEtapu(stanice, podaci, oz));
            } else {
                vr.dodajKomponentu(napraviVlak(stanice, podaci, oz));
            }
        }

        return vr;
    }


    private Vlak napraviVlak(List<ZeljeznickaStanica> stanice, VozniRedPodaci vozniRedPodaci, List<OznakaDana> oz) {
        Vlak vlak = new Vlak(vozniRedPodaci.getOznakaVlaka());
        vlak.dodajKomponentu(napraviEtapu(stanice, vozniRedPodaci, oz));
        return vlak;
    }

    private Etapa napraviEtapu(List<ZeljeznickaStanica> stanice, VozniRedPodaci podaciVoznogReda, List<OznakaDana> oznakeDana) {
        String originalnoVrijemePolaska = podaciVoznogReda.getVrijemePolaska();
        String obradenVrijemePolaska = obradiVrijeme(originalnoVrijemePolaska);

        LocalTime vrijemePolaska = LocalTime.parse(obradenVrijemePolaska);
        LocalTime vrijemeDolaska = vrijemePolaska.plusMinutes(parsirajTrajanjeVoznje(podaciVoznogReda.getTrajanjeVoznje()));

        String daniUTjednu = getDaneUTjednu(podaciVoznogReda, oznakeDana);
        String polaznaStanica = getPolaznaZeljeznickaStanica(podaciVoznogReda, stanice, podaciVoznogReda.getOznakaPruge());
        String odredisnaStanica = getOdredisnaZeljeznickaStanica(podaciVoznogReda, stanice, podaciVoznogReda.getOznakaPruge());

        Etapa etapa = new Etapa(
                podaciVoznogReda.getOznakaVlaka(),
                podaciVoznogReda.getOznakaPruge(),
                vrijemePolaska,
                vrijemeDolaska,
                daniUTjednu
        );

        etapa.dodajKomponentu(new Stanica(polaznaStanica));
        etapa.dodajKomponentu(new Stanica(odredisnaStanica));
        etapa.setUkupnaKilometraza(izracunajKilometrazu(stanice, polaznaStanica, odredisnaStanica, podaciVoznogReda.getOznakaPruge()));
        return etapa;
    }

    private String obradiVrijeme(String originalnoVrijeme) {
        String ociscenoVrijeme = originalnoVrijeme.replaceAll("[^0-9:]", "");

        String[] dijeloviVremena = ociscenoVrijeme.split(":");

        if (dijeloviVremena.length < 2) {
            throw new IllegalArgumentException("Neispravan format vremena: " + originalnoVrijeme);
        }

        int sati = Integer.parseInt(dijeloviVremena[0]);
        int minute = Integer.parseInt(dijeloviVremena[1]);

        sati += minute / 60;
        minute = minute % 60;
        return String.format("%02d:%02d", sati, minute);
    }

    private long parsirajTrajanjeVoznje(String trajanjeVoznje) {
        String ociscenoTrajanje = trajanjeVoznje.replaceAll("[^0-9]", "");
        return Long.parseLong(ociscenoTrajanje);
    }


    private String getDaneUTjednu(VozniRedPodaci vozniRedPodaci, List<OznakaDana> oz) {
        String dani = "";

        String oznakaDana = vozniRedPodaci.getOznakaDana();
        if (oznakaDana == null || oznakaDana.isEmpty() || oznakaDana.equals("null")) {
            dani = "PoUSrČPeSuN";
        } else {
            try {
                int oznakaDanaInt = Integer.parseInt(oznakaDana);
                for (OznakaDana oznaka : oz) {
                    if (oznaka.getOznakaDana() == oznakaDanaInt) {
                        dani = oznaka.getDani();
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Greška pri parsiranju oznake dana: " + oznakaDana);
                dani = "PoUSrČPeSuN";
            }
        }
        return dani;
    }

    private String getPolaznaZeljeznickaStanica(VozniRedPodaci vrpodaci, List<ZeljeznickaStanica> stanice, String oznakaPrugeParam) {
        if (Objects.equals(vrpodaci.getPolaznaStanica(), "null") || vrpodaci.getPolaznaStanica().isEmpty()) {
            String nazivStanice = "";

            Map<String, List<ZeljeznickaStanica>> pruge = new HashMap<>();
            for (ZeljeznickaStanica stanica : stanice) {
                String oznakaPruge = stanica.getOznakaPruge();
                pruge.computeIfAbsent(oznakaPruge, k -> new ArrayList<>()).add(stanica);
            }

            List<ZeljeznickaStanica> staniceNaPrugi = pruge.get(oznakaPrugeParam);
            if (staniceNaPrugi != null && !staniceNaPrugi.isEmpty() && staniceNaPrugi.size() != 1) {
                if (Objects.equals(vrpodaci.getSmjer(), "N")) {
                    nazivStanice = staniceNaPrugi.get(0).getStanica();
                } else if (Objects.equals(vrpodaci.getSmjer(), "O")) {
                    nazivStanice = staniceNaPrugi.get(staniceNaPrugi.size() - 1).getStanica();
                }
            }
            return nazivStanice;
        } else {
            return vrpodaci.getPolaznaStanica();
        }
    }

    private String getOdredisnaZeljeznickaStanica(VozniRedPodaci vrpodaci, List<ZeljeznickaStanica> stanice, String oznakaPrugeParam) {
        if (Objects.equals(vrpodaci.getOdredisnaStanica(), "null") || Objects.equals(vrpodaci.getOdredisnaStanica(), "")) {
            String nazivStanica = "";
            if (stanice == null || stanice.isEmpty()) {
                return null;
            }
            Map<String, List<ZeljeznickaStanica>> pruge = new HashMap<>();
            for (ZeljeznickaStanica stanica : stanice) {
                String oznakaPruge = stanica.getOznakaPruge();
                pruge.computeIfAbsent(oznakaPruge, k -> new ArrayList<>()).add(stanica);
            }

            List<ZeljeznickaStanica> staniceNaPrugi = pruge.get(oznakaPrugeParam);
            if (Objects.equals(vrpodaci.getSmjer(), "N")){
                if (staniceNaPrugi != null && !staniceNaPrugi.isEmpty()) {
                    nazivStanica = staniceNaPrugi.get(staniceNaPrugi.size() - 1).getStanica();
                }
            }
            else if (Objects.equals(vrpodaci.getSmjer(), "O")){
                if (staniceNaPrugi != null && !staniceNaPrugi.isEmpty()) {
                    nazivStanica = staniceNaPrugi.get(0).getStanica();
                }
            }
            return nazivStanica;
        } else {
            return vrpodaci.getOdredisnaStanica();
        }
    }

    private double izracunajKilometrazu(List<ZeljeznickaStanica> stanice, String polazna, String odredisna, String oznakaPrugeParam) {
        Map<String, List<ZeljeznickaStanica>> pruge = new HashMap<>();
        for (ZeljeznickaStanica stanica : stanice) {
            String oznakaPruge = stanica.getOznakaPruge();
            pruge.computeIfAbsent(oznakaPruge, k -> new ArrayList<>()).add(stanica);
        }

        List<ZeljeznickaStanica> staniceNaPrugi = pruge.get(oznakaPrugeParam);
        if (staniceNaPrugi == null || staniceNaPrugi.isEmpty()) {
            return 0;
        }

        int indeksPolazne = -1, indeksOdredisne = -1;
        for (int i = 0; i < staniceNaPrugi.size(); i++) {
            if (staniceNaPrugi.get(i).getStanica().equals(polazna)) {
                indeksPolazne = i;
            }
            if (staniceNaPrugi.get(i).getStanica().equals(odredisna)) {
                indeksOdredisne = i;
            }
        }

        if (indeksPolazne == -1 || indeksOdredisne == -1) {
            return 0;
        }

        boolean obrnutRedoslijed = indeksPolazne > indeksOdredisne;

        double ukupnaKilometraza = 0;
        if (obrnutRedoslijed) {
            for (int i = indeksPolazne; i > indeksOdredisne; i--) {
                ukupnaKilometraza += staniceNaPrugi.get(i).getDuzina();
            }
        } else {
            for (int i = indeksPolazne; i < indeksOdredisne; i++) {
                ukupnaKilometraza += staniceNaPrugi.get(i + 1).getDuzina();
            }
        }

        return ukupnaKilometraza;
    }
}
