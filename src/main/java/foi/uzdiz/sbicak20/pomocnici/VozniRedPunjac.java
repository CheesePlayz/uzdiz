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

import static java.lang.System.exit;

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

    private Etapa napraviEtapu(List<ZeljeznickaStanica> stanice, VozniRedPodaci vozniRedPodaci, List<OznakaDana> oz) {
        // Preprocess the time string to handle invalid formats
        String originalVrijemePolaska = vozniRedPodaci.getVrijemePolaska();
        String processedVrijemePolaska = processTime(originalVrijemePolaska);

        LocalTime vrijemePolaska = LocalTime.parse(processedVrijemePolaska);
        LocalTime vrijemeDolaska = vrijemePolaska.plusMinutes(parseTrajanjeVoznje(vozniRedPodaci.getTrajanjeVoznje()));

        String daniUTjednu = getDaneUTjednu(vozniRedPodaci, oz);
        String polaznaStanica = getPolaznaZeljeznickaStanica(vozniRedPodaci, stanice, vozniRedPodaci.getOznakaPruge());
        String odredisnaStanica = getOdredisnaZeljeznickaStanica(vozniRedPodaci, stanice, vozniRedPodaci.getOznakaPruge());

        // Format times in HH:mm format
        String formattedVrijemePolaska = vrijemePolaska.format(DateTimeFormatter.ofPattern("HH:mm"));
        String formattedVrijemeDolaska = vrijemeDolaska.format(DateTimeFormatter.ofPattern("HH:mm"));

        Etapa etapa = new Etapa(
                vozniRedPodaci.getOznakaVlaka(),
                vozniRedPodaci.getOznakaPruge(),
                vrijemePolaska,
                vrijemeDolaska,
                daniUTjednu
        );

        etapa.dodajKomponentu(new Stanica(polaznaStanica));
        etapa.dodajKomponentu(new Stanica(odredisnaStanica));
        etapa.setUkupnaKilometraza(izracunajKilometrazu(stanice, polaznaStanica, odredisnaStanica, vozniRedPodaci.getOznakaPruge()));
        return etapa;
    }

    // Helper method to process potentially malformed time strings
    private String processTime(String originalTime) {
        // Remove any extra colons or unexpected characters
        String cleanedTime = originalTime.replaceAll("[^0-9:]", "");

        // Split the time parts
        String[] timeParts = cleanedTime.split(":");

        if (timeParts.length < 2) {
            throw new IllegalArgumentException("Invalid time format: " + originalTime);
        }

        // Ensure hours and minutes are properly formatted
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        // Normalize hours and minutes
        hours += minutes / 60;
        minutes = minutes % 60;

        // Format back to HH:mm
        return String.format("%02d:%02d", hours, minutes);
    }

    // Helper method to parse duration
    private long parseTrajanjeVoznje(String trajanjeVoznje) {
        // Remove any non-digit characters and parse
        String cleanedDuration = trajanjeVoznje.replaceAll("[^0-9]", "");
        return Long.parseLong(cleanedDuration);
    }

    private String getDaneUTjednu(VozniRedPodaci vozniRedPodaci, List<OznakaDana> oz) {
        String dani = "";

        // Proverite da li je oznaka dana prazna ili null pre nego što radite sa njom
        String oznakaDana = vozniRedPodaci.getOznakaDana();
        if (oznakaDana == null || oznakaDana.isEmpty() || oznakaDana.equals("null")) {
            // Ako je oznaka dana prazna ili null, postavite podrazumevanu vrednost
            dani = "PoUSrČPeSuN";
        } else {
            try {
                // Pokušajte parsirati oznaku dana kao broj
                int oznakaDanaInt = Integer.parseInt(oznakaDana);

                // Pretražujemo listu oznaka dana za odgovarajući unos
                for (OznakaDana oznaka : oz) {
                    if (oznaka.getOznakaDana() == oznakaDanaInt) {
                        dani = oznaka.getDani();
                        break;  // Prestanite sa pretrazivanjem kada nađete odgovarajući unos
                    }
                }
            } catch (NumberFormatException e) {
                // Ako dođe do greške pri parsiranju, možete baciti izuzetak ili se ponašati na drugi način
                System.out.println("Greška pri parsiranju oznake dana: " + oznakaDana);
                dani = "PoUSrČPeSuN"; // Postavite podrazumevanu vrednost u slučaju greške
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

        int indeksPolazne = staniceNaPrugi.indexOf(polazna);
        int indeksOdredisne = staniceNaPrugi.indexOf(odredisna);

        if (indeksPolazne == -1 || indeksOdredisne == -1) {
            //System.out.println("Polazna ili odredišna stanica nije pronađena na pruzi.");
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
