package foi.uzdiz.sbicak20;

import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.VozniRedPodaci;
import foi.uzdiz.sbicak20.modeli.ZeljeznickoPrijevoznoSredstvo;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;
import foi.uzdiz.sbicak20.modeli.composite.VozniRed;

import java.util.*;

import static java.lang.System.exit;

public class ZeljeznickiSustavSingleton {
    private static ZeljeznickiSustavSingleton instanca;

    private List<ZeljeznickaStanica> stanice;
    private List<ZeljeznickoPrijevoznoSredstvo> vozila;
    private List<List<Kompozicija>> kompozicije;
    private List<VozniRedPodaci> vozniRed;

    private ZeljeznickiSustavSingleton(List<ZeljeznickaStanica> stanice, List<ZeljeznickoPrijevoznoSredstvo> vozila, List<List<Kompozicija>> kompozicije, List<VozniRedPodaci> vr) {
        this.stanice = stanice;
        this.vozila = vozila;
        this.kompozicije = kompozicije;
        this.vozniRed = vr;
    }

    public static ZeljeznickiSustavSingleton getInstanca(List<ZeljeznickaStanica> stanice, List<ZeljeznickoPrijevoznoSredstvo> vozila, List<List<Kompozicija>> kompozicije, List<VozniRedPodaci> vr) {
        if (instanca == null) {
            instanca = new ZeljeznickiSustavSingleton(stanice, vozila, kompozicije, vr);
        }
        return instanca;
    }

    public void IzvrsiKomandu(String komanda) {

        if (komanda.startsWith("IK")) {
            String[] dioKomande = komanda.split(" ");
            if (dioKomande.length != 2) {
                System.out.println("Nepoznata komanda ili pogrešna sintaksa !!!");
                return;
            }

            String oznakaKompozicije = dioKomande[1];
            IspisVozilaIzKompozicije(oznakaKompozicije);
        } else if (komanda.startsWith("ISP")) {
            String[] dioKomande = komanda.split(" ");
            if (dioKomande.length != 3) {
                System.out.println("Nepoznata komanda ili pogrešna sintaksa !!!");
                return;
            }

            String oznakaPruge = dioKomande[1];;
            boolean obrnutRedoslijed;
            if (Objects.equals(dioKomande[2], "N")) {
                obrnutRedoslijed = false;
            } else if (Objects.equals(dioKomande[2], "O")) {
                obrnutRedoslijed = true;
            } else {
                System.out.println("Nepoznata komanda ili pogrešna sintaksa !!!");
                return;
            }

            IspisPregledStanicaNaOdredenojPruzi(stanice, oznakaPruge, obrnutRedoslijed);
        }
        else if (komanda.startsWith("ISI2S")){
            String[] dioKomande = komanda.split(" ");
            if (dioKomande.length != 4) {
                System.out.println("Nepoznata komanda ili pogrešna sintaksa !!!");
                return;
            }
            Isi2s(stanice, dioKomande[1], dioKomande[3]);
        }
        else {
            switch (komanda) {
                case "Q": {
                    exit(0);
                    break;
                }
                case "IP": {
                    IspisPruge(stanice);
                    break;
                }
                default:
                    System.out.println("Nepoznata komanda !!!");
            }
        }
    }

    private void IspisPruge(List<ZeljeznickaStanica> stanice) {
        if (stanice == null || stanice.isEmpty()) {
            System.out.println("Nema dostupnih podataka o stanicama.");
            return;
        }

        Map<String, List<ZeljeznickaStanica>> prugeMap = new HashMap<>();
        int[] maxDuljine = {6, 15, 15, 18};


        for (ZeljeznickaStanica stanica : stanice) {
            String oznakaPruge = stanica.getOznakaPruge();
            prugeMap.putIfAbsent(oznakaPruge, new ArrayList<>());
            prugeMap.get(oznakaPruge).add(stanica);

            maxDuljine[0] = Math.max(maxDuljine[0], oznakaPruge.length());
        }

        for (Map.Entry<String, List<ZeljeznickaStanica>> entry : prugeMap.entrySet()) {
            List<ZeljeznickaStanica> stanicePruge = entry.getValue();
            if (stanicePruge.size() < 2) continue;


            String pocetnaStanica = stanicePruge.get(0).getStanica();
            String zavrsnaStanica = stanicePruge.get(stanicePruge.size() - 1).getStanica();

            maxDuljine[1] = Math.max(maxDuljine[1], pocetnaStanica.length());
            maxDuljine[2] = Math.max(maxDuljine[2], zavrsnaStanica.length());
        }


        StringBuilder formatBuilder = new StringBuilder("|");
        for (int duljina : maxDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        System.out.printf(format, "Oznaka", "Pocetna stanica", "Zavrsna stanica", "Ukupna kilometraza");

        for (Map.Entry<String, List<ZeljeznickaStanica>> entry : prugeMap.entrySet()) {
            String oznaka = entry.getKey();
            List<ZeljeznickaStanica> stanicePruge = entry.getValue();

            if (stanicePruge.size() < 2) {
                continue;
            }

            String pocetnaStanica = stanicePruge.get(0).getStanica();
            String zavrsnaStanica = stanicePruge.get(stanicePruge.size() - 1).getStanica();
            double ukupnaKilometraza = 0;

            for (ZeljeznickaStanica stanica : stanicePruge) {
                ukupnaKilometraza += stanica.getDuzina();
            }

            maxDuljine[2] = Math.max(maxDuljine[2], pocetnaStanica.length());
            maxDuljine[3] = Math.max(maxDuljine[3], zavrsnaStanica.length());

            System.out.printf(format, oznaka, pocetnaStanica, zavrsnaStanica, ukupnaKilometraza);
        }


        for (ZeljeznickaStanica stanica : stanice) {
            System.out.println(stanica.toString());
        }
    }


    private void IspisPregledStanicaNaOdredenojPruzi(List<ZeljeznickaStanica> stanice, String oznakaPruge, boolean obrnutRedoslijed) {
        if (stanice == null || stanice.isEmpty()) {
            System.out.println("Nema dostupnih podataka o stanicama.");
            return;
        }

        List<ZeljeznickaStanica> stanicePruge = new ArrayList<>();
        int[] maxDuljine = {13, 13, 18};

        for (ZeljeznickaStanica stanica : stanice) {
            if (stanica.getOznakaPruge().equals(oznakaPruge)) {
                stanicePruge.add(stanica);
                maxDuljine[0] = Math.max(maxDuljine[0], stanica.getStanica().length());
                maxDuljine[1] = Math.max(maxDuljine[1], stanica.getVrstaStanice().toString().length());
            }
        }

        if (stanicePruge.isEmpty()) {
            System.out.println("Nema stanica za prugu: " + oznakaPruge);
            return;
        }

        if (stanicePruge.size() < 2) {
            System.out.println("Pruga mora imati barem dvije stanice!");
            return;
        }

        StringBuilder formatBuilder = new StringBuilder("|");
        for (int duljina : maxDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        System.out.printf(format, "Naziv stanice", "Vrsta stanice", "Ukupna kilometraza");

        double ukupnaKilometraza = 0;


        if (obrnutRedoslijed) {
            int brojElemenataUnutarPruge = stanicePruge.size() - 1;
            for (int i = brojElemenataUnutarPruge; i >= 0; i--) {
                ZeljeznickaStanica stanica = stanicePruge.get(i);
                if (i == brojElemenataUnutarPruge) {
                    ukupnaKilometraza = 0;
                } else {
                    ukupnaKilometraza += stanicePruge.get(i + 1).getDuzina();
                }
                System.out.printf(format, stanica.getStanica(), stanica.getVrstaStanice(), String.format("%.1f km", ukupnaKilometraza));
            }
        } else {
            for (ZeljeznickaStanica stanica : stanicePruge) {
                ukupnaKilometraza += stanica.getDuzina();
                System.out.printf(format, stanica.getStanica(), stanica.getVrstaStanice(), String.format("%.1f km", ukupnaKilometraza));
            }
        }
    }


    private void Isi2s(List<ZeljeznickaStanica> stanice, String pocetnaStanica, String zavrsnaStanica) {
        // Koristenje grafa za modeliranje povezanosti stanica
        Map<String, List<ZeljeznickaStanica>> graf = new HashMap<>();

        // Izgradnja grafa s dvosmjernim vezama
        for (ZeljeznickaStanica stanica : stanice) {
            graf.putIfAbsent(stanica.getStanica(), new ArrayList<>());
            for (ZeljeznickaStanica susjed : stanice) {
                if (!stanica.getStanica().equals(susjed.getStanica()) && stanica.getOznakaPruge().equals(susjed.getOznakaPruge())) {
                    graf.get(stanica.getStanica()).add(susjed);
                    graf.putIfAbsent(susjed.getStanica(), new ArrayList<>());
                    graf.get(susjed.getStanica()).add(stanica); // Dodavanje obrnute veze
                }
            }
        }

        // DFS za pronalazak puta
        List<ZeljeznickaStanica> rezultat = new ArrayList<>();
        Set<String> posjeceni = new HashSet<>();
        ZeljeznickaStanica pocetna = stanice.stream().filter(s -> s.getStanica().equals(pocetnaStanica)).findFirst().orElse(null);
        String pocetnaPruge = pocetna != null ? pocetna.getOznakaPruge() : "";
        if (!dfs(graf, pocetnaStanica, zavrsnaStanica, posjeceni, rezultat, stanice, 0, pocetnaPruge)) {
            System.out.println("Put nije pronaden!");
            return;
        }

        // Ispis rezultata
        System.out.println(String.format("%-20s %-10s %-10s", "Naziv", "Vrsta", "Ukupna udaljenost"));
        int kumulativnaUdaljenost = 0;
        for (ZeljeznickaStanica stanica : rezultat) {
            kumulativnaUdaljenost += stanica.getDuzina();
            System.out.println(String.format("%-20s %-10s %-10d", stanica.getStanica(), stanica.getVrstaStanice(), kumulativnaUdaljenost));
        }
    }

    private boolean dfs(Map<String, List<ZeljeznickaStanica>> graf, String trenutni, String kraj, Set<String> posjeceni,
                        List<ZeljeznickaStanica> rezultat, List<ZeljeznickaStanica> stanice, int ukupnaUdaljenost, String trenutnaPruge) {
        if (posjeceni.contains(trenutni)) return false;
        posjeceni.add(trenutni);

        // Dodavanje trenutne stanice u rezultat
        ZeljeznickaStanica trenutnaStanica = stanice.stream().filter(s -> s.getStanica().equals(trenutni)).findFirst().orElse(null);
        if (trenutnaStanica != null) rezultat.add(trenutnaStanica);

        // Ako smo došli do kraja, vraćamo true
        if (trenutni.equals(kraj)) return true;

        // Pretraga susjeda
        for (ZeljeznickaStanica susjed : graf.getOrDefault(trenutni, new ArrayList<>())) {
            // Nastavi pretragu samo ako je ista pruga
            if (!posjeceni.contains(susjed.getStanica()) && susjed.getOznakaPruge().equals(trenutnaPruge)) {
                if (dfs(graf, susjed.getStanica(), kraj, posjeceni, rezultat, stanice, ukupnaUdaljenost + (int)susjed.getDuzina(), trenutnaPruge)) {
                    return true;
                }
            }
        }

        // Ako nismo našli put, uklonimo trenutnu stanicu iz rezultata i vratimo false
        rezultat.remove(rezultat.size() - 1);
        return false;
    }





    private void IspisVozilaIzKompozicije(String oznakaKompozicije) {
        boolean kompozicijaNadena = false;
        List<Kompozicija> trazenaKompozicija = null;

        for (List<Kompozicija> kompozicija : kompozicije) {
            if (kompozicija.get(0).getOznaka().equals(oznakaKompozicije)) {
                trazenaKompozicija = kompozicija;
                kompozicijaNadena = true;
                break;
            }
        }

        if (!kompozicijaNadena || trazenaKompozicija == null) {
            System.out.println("Kompozicija s oznakom " + oznakaKompozicije + " nije pronadena.");
            return;
        }

        int[] maxInitStupacaDuljine = {7, 5, 4, 6, 7, 12, 12};
        List<String[]> podaciZaIspis = new ArrayList<>();

        for (Kompozicija kompozicija : trazenaKompozicija) {
            String oznakaPrijevoznogSredstva = kompozicija.getOznakaPrijevoznogSredstva();
            ZeljeznickoPrijevoznoSredstvo sredstvo = null;
            for (ZeljeznickoPrijevoznoSredstvo ps : vozila) {
                if (ps.getOznaka().equals(oznakaPrijevoznogSredstva)) {
                    sredstvo = ps;
                    break;
                }
            }

            if (sredstvo != null) {
                String[] red = {
                        oznakaPrijevoznogSredstva,
                        String.valueOf(kompozicija.getUloga()),
                        sredstvo.getOpis(),
                        String.valueOf(sredstvo.getGodina()),
                        String.valueOf(sredstvo.getNamjena()),
                        String.valueOf(sredstvo.getVrstaPogona()),
                        sredstvo.getMaxBrzina() + " km/h"
                };
                podaciZaIspis.add(red);

                for (int i = 0; i < red.length; i++) {
                    maxInitStupacaDuljine[i] = Math.max(maxInitStupacaDuljine[i], red[i].length());
                }
            }
        }

        StringBuilder formatBuilder = new StringBuilder("|");
        for (int duljina : maxInitStupacaDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        System.out.printf(format, "Oznaka", "Uloga", "Opis", "Godina", "Namjena", "Vrsta pogona", "Maks. brzina");
        for (String[] red : podaciZaIspis) {
            System.out.printf(format, (Object[]) red);
        }
    }
}
