package foi.uzdiz.sbicak20;

import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaPrijevoznaSredstva;
import foi.uzdiz.sbicak20.modeli.ZeljeznickeStanice;

import java.util.*;

import static java.lang.System.exit;

public class ZeljeznickiSustavSingleton {
    private static ZeljeznickiSustavSingleton instanca;

    private List<ZeljeznickeStanice> stanice;
    private List<ZeljeznickaPrijevoznaSredstva> vozila;
    private List<List<Kompozicija>> kompozicije;

    private ZeljeznickiSustavSingleton(List<ZeljeznickeStanice> stanice, List<ZeljeznickaPrijevoznaSredstva> vozila, List<List<Kompozicija>> kompozicije) {
        this.stanice = stanice;
        this.vozila = vozila;
        this.kompozicije = kompozicije;
    }

    public static ZeljeznickiSustavSingleton getInstanca(List<ZeljeznickeStanice> stanice, List<ZeljeznickaPrijevoznaSredstva> vozila, List<List<Kompozicija>> kompozicije) {
        if (instanca == null) {
            instanca = new ZeljeznickiSustavSingleton(stanice, vozila, kompozicije);
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
        } else {
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

    private void IspisPruge(List<ZeljeznickeStanice> stanice) {
        if (stanice == null || stanice.isEmpty()) {
            System.out.println("Nema dostupnih podataka o stanicama.");
            return;
        }

        Map<String, List<ZeljeznickeStanice>> prugeMap = new HashMap<>();
        int[] maxDuljine = {6, 15, 15, 18};


        for (ZeljeznickeStanice stanica : stanice) {
            String oznakaPruge = stanica.getOznakaPruge();
            prugeMap.putIfAbsent(oznakaPruge, new ArrayList<>());
            prugeMap.get(oznakaPruge).add(stanica);

            maxDuljine[0] = Math.max(maxDuljine[0], oznakaPruge.length());
        }

        for (Map.Entry<String, List<ZeljeznickeStanice>> entry : prugeMap.entrySet()) {
            List<ZeljeznickeStanice> stanicePruge = entry.getValue();
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

        for (Map.Entry<String, List<ZeljeznickeStanice>> entry : prugeMap.entrySet()) {
            String oznaka = entry.getKey();
            List<ZeljeznickeStanice> stanicePruge = entry.getValue();

            if (stanicePruge.size() < 2) {
                continue;
            }

            String pocetnaStanica = stanicePruge.get(0).getStanica();
            String zavrsnaStanica = stanicePruge.get(stanicePruge.size() - 1).getStanica();
            double ukupnaKilometraza = 0;

            for (ZeljeznickeStanice stanica : stanicePruge) {
                ukupnaKilometraza += stanica.getDuzina();
            }

            maxDuljine[2] = Math.max(maxDuljine[2], pocetnaStanica.length());
            maxDuljine[3] = Math.max(maxDuljine[3], zavrsnaStanica.length());

            System.out.printf(format, oznaka, pocetnaStanica, zavrsnaStanica, ukupnaKilometraza);
        }
    }


    private void IspisPregledStanicaNaOdredenojPruzi(List<ZeljeznickeStanice> stanice, String oznakaPruge, boolean obrnutRedoslijed) {
        if (stanice == null || stanice.isEmpty()) {
            System.out.println("Nema dostupnih podataka o stanicama.");
            return;
        }

        List<ZeljeznickeStanice> stanicePruge = new ArrayList<>();
        int[] maxDuljine = {13, 13, 18};

        for (ZeljeznickeStanice stanica : stanice) {
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
                ZeljeznickeStanice stanica = stanicePruge.get(i);
                if (i == brojElemenataUnutarPruge) {
                    ukupnaKilometraza = 0;
                } else {
                    ukupnaKilometraza += stanicePruge.get(i + 1).getDuzina();
                }
                System.out.printf(format, stanica.getStanica(), stanica.getVrstaStanice(), String.format("%.1f km", ukupnaKilometraza));
            }
        } else {
            for (ZeljeznickeStanice stanica : stanicePruge) {
                ukupnaKilometraza += stanica.getDuzina();
                System.out.printf(format, stanica.getStanica(), stanica.getVrstaStanice(), String.format("%.1f km", ukupnaKilometraza));
            }
        }
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
            ZeljeznickaPrijevoznaSredstva sredstvo = null;
            for (ZeljeznickaPrijevoznaSredstva ps : vozila) {
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
