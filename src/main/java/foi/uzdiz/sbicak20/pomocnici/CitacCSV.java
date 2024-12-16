package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.greske.NevaljaniFormatGreska;
import foi.uzdiz.sbicak20.greske.NullVrijednostGreska;
import foi.uzdiz.sbicak20.greske.SustavGresaka;
import foi.uzdiz.sbicak20.modeli.*;
import foi.uzdiz.sbicak20.validatori.IValidator;
import foi.uzdiz.sbicak20.validatori.ValidatorFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;

public class CitacCSV {

    static IValidator validator;

    public static List<ZeljeznickaStanica> ucitajStaniceIzCSV(String putanjaDatoteke, ValidatorFactory factory) throws Exception {
        if (putanjaDatoteke == null) {
            SustavGresaka.getInstance().prijaviGresku(new NullVrijednostGreska("Datoteka za stanice je null"));
            exit(1);
        }
        List<ZeljeznickaStanica> stanice = new ArrayList<>();
        int redakCSV = 1;
        validator = factory.napraviValidator();
        try (BufferedReader br = new BufferedReader(new FileReader(putanjaDatoteke))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                redakCSV++;
                String[] redovi = line.split(";");
                if (redovi[0].startsWith("#")) {
                    continue;
                }
                boolean ispravnaValidacija = validator.Validiraj(redovi, redakCSV, null);
                if (!ispravnaValidacija) {
                    continue;
                }

                ZeljeznickaStanica.StanicaBuilder builder = new ZeljeznickaStanica.StanicaBuilder()
                        .setStanica(redovi[0].trim())
                        .setOznakaPruge(redovi[1].trim())
                        .setVrstaStanice(redovi[2].trim())
                        .setStatusStanice(redovi[3].trim())
                        .setPutniciUlIz(redovi[4].trim())
                        .setRobaUtIst(redovi[5].trim())
                        .setKategorijaPruge(redovi[6].trim())
                        .setBrojPerona(Integer.parseInt(redovi[7].trim()))
                        .setVrstaPruge(redovi[8].trim())
                        .setBrojKolosjeka(Integer.parseInt(redovi[9].trim()))
                        .setDoPoOsovini(Double.parseDouble(redovi[10].trim().replace(",", ".")))
                        .setDoPoDuznomM(Double.parseDouble(redovi[11].trim().replace(",", ".")))
                        .setStatusPruge(redovi[12].trim())
                        .setDuzina(Integer.parseInt(redovi[13].trim()));

                if (redovi.length > 14 && redovi[14] != null && !redovi[14].trim().isEmpty()) {
                    builder.setVrijemeNormalniVlak(Integer.parseInt(redovi[14].trim()));
                }

                if (redovi.length > 15 && redovi[15] != null && !redovi[15].trim().isEmpty()) {
                    builder.setVrijemeUbrzaniVlak(Integer.parseInt(redovi[15].trim()));
                }

                if (redovi.length > 16 && redovi[16] != null && !redovi[16].trim().isEmpty()) {
                    builder.setVrijemeBrziVlak(Integer.parseInt(redovi[16].trim()));
                }

                ZeljeznickaStanica stanica = builder.build();

                stanice.add(stanica);
            }
        } catch (IOException e) {
            SustavGresaka.getInstance().prijaviGresku(e, "Greška pri učitavanju datoteke");
            exit(1);
        }

        return stanice;
    }

    public static List<ZeljeznickoPrijevoznoSredstvo> ucitajVozilaIzCSV(String putanjaDatoteke, ValidatorFactory factory) throws Exception {
        if (putanjaDatoteke == null) {
            SustavGresaka.getInstance().prijaviGresku(new NullVrijednostGreska("Datoteka za vozila je null"));
            exit(1);
        }
        List<ZeljeznickoPrijevoznoSredstvo> vozila = new ArrayList<ZeljeznickoPrijevoznoSredstvo>();
        int redakCSV = 1;
        validator = factory.napraviValidator();
        try (BufferedReader br = new BufferedReader(new FileReader(putanjaDatoteke))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                redakCSV++;
                String[] redovi = line.split(";");

                boolean ispravnaValidacija = validator.Validiraj(redovi, redakCSV, null);
                if (!ispravnaValidacija) {
                    continue;
                }

                ZeljeznickoPrijevoznoSredstvo vozilo = new ZeljeznickoPrijevoznoSredstvo.VoziloBuilder()
                        .setOznaka(redovi[0].trim())
                        .setOpis(redovi[1].trim())
                        .setProizvodac(redovi[2].trim())
                        .setGodina(Integer.parseInt(redovi[3].trim()))
                        .setNamjena(redovi[4].trim())
                        .setVrstaPrijevoza(redovi[5].trim())
                        .setVrstaPogona(redovi[6].trim())
                        .setMaxBrzina(Integer.parseInt(redovi[7].trim()))
                        .setMaxSnaga(Double.parseDouble(redovi[8].trim().replace(",", ".")))
                        .setBrojSjedecihMjesta(Integer.parseInt(redovi[9].trim()))
                        .setBrojStajucihMjesta(Integer.parseInt(redovi[10].trim()))
                        .setBrojBicikala(Integer.parseInt(redovi[11].trim()))
                        .setBrojKreveta(Integer.parseInt(redovi[12].trim()))
                        .setBrojAutomobila(Integer.parseInt(redovi[13].trim()))
                        .setNosivost(Double.parseDouble(redovi[14].trim().replace(",", ".")))
                        .setPovrsina(Double.parseDouble(redovi[15].trim().replace(",", ".")))
                        .setZapremina(Double.parseDouble(redovi[16].trim().replace(",", ".")))
                        .setStatus(redovi[17].trim())
                        .build();

                vozila.add(vozilo);
            }
        } catch (IOException e) {
            SustavGresaka.getInstance().prijaviGresku(e, "Greška pri učitavanju datoteke");
            exit(1);
        }

        return vozila;
    }


    public static List<List<Kompozicija>> ucitajKompozicijeIzCSV(String putanjaCsvKompozicije, ValidatorFactory factory) throws Exception {
        if (putanjaCsvKompozicije == null) {
            SustavGresaka.getInstance().prijaviGresku(new NullVrijednostGreska("Datoteka za kompozicije je null"));
            System.exit(1);
        }
        validator = factory.napraviValidator();
        List<List<Kompozicija>> listaKompozicija = new ArrayList<>();
        List<Kompozicija> trenutnaKompozicija = new ArrayList<>();
        String trenutnaOznaka = null;

        try (BufferedReader br = new BufferedReader(new FileReader(putanjaCsvKompozicije))) {
            String line;
            br.readLine();
            int redakCSV = 1;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                redakCSV++;

                if (line.equals(";;")) {
                    if (!trenutnaKompozicija.isEmpty()) {
                        if (validator.Validiraj(null, 0, trenutnaKompozicija)) {
                            listaKompozicija.add(new ArrayList<>(trenutnaKompozicija));
                        } else {
                            SustavGresaka.getInstance().prijaviGresku(
                                    new IllegalArgumentException("Kompozicija nije validna: mora imati barem jedan pogon, ispravan redoslijed i uloge."),
                                    SustavGresaka.getInstance().getPodrucjaGresaka().get(2),
                                    new String[]{"CSV Redak: " + redakCSV, "Kompozicija " + trenutnaOznaka}
                            );
                        }
                        trenutnaKompozicija.clear();
                        trenutnaOznaka = null;
                    }
                    continue;
                }

                String[] redovi = line.split(";");
                if (redovi.length != 3) {
                    continue;
                }

                String oznaka = redovi[0].trim();
                String oznakaPrijevoznogSredstva = redovi[1].trim();
                String uloga = redovi[2].trim();

                if (oznaka.isEmpty() || oznakaPrijevoznogSredstva.isEmpty() || uloga.isEmpty()) {
                    continue;
                }

                if (trenutnaOznaka == null) {
                    trenutnaOznaka = oznaka;
                }

                if (!oznaka.equals(trenutnaOznaka)) {
                    if (!trenutnaKompozicija.isEmpty()) {
                        if (validator.Validiraj(null, 0, trenutnaKompozicija)) {
                            listaKompozicija.add(new ArrayList<>(trenutnaKompozicija));
                        } else {
                            SustavGresaka.getInstance().prijaviGresku(
                                    new IllegalArgumentException("Kompozicija nije validna: mora imati barem jedan pogon, ispravan redoslijed i uloge"),
                                    SustavGresaka.getInstance().getPodrucjaGresaka().get(2),
                                    new String[]{"CSV Redak: " + redakCSV, "Kompozicija " + trenutnaOznaka}
                            );
                        }
                    }
                    trenutnaKompozicija.clear();
                    trenutnaOznaka = null;
                    listaKompozicija.removeLast();
                    continue;

                }

                try {
                    Kompozicija dioKompozicije = new Kompozicija(oznaka, oznakaPrijevoznogSredstva, uloga);
                    trenutnaKompozicija.add(dioKompozicije);
                } catch (IllegalArgumentException e) {
                    SustavGresaka.getInstance().prijaviGresku(e, "Pogrešan zapis kompozicije", new String[]{"CSV Redak: " + redakCSV, line});
                    trenutnaKompozicija.clear();
                    trenutnaOznaka = null;
                }
            }

            if (!trenutnaKompozicija.isEmpty()) {
                if (validator.Validiraj(null, 0, trenutnaKompozicija)) {
                    listaKompozicija.add(new ArrayList<>(trenutnaKompozicija));
                } else {
                    SustavGresaka.getInstance().prijaviGresku(
                            new IllegalArgumentException("Kompozicija nije validna: mora imati barem jedan pogon, ispravan redoslijed i uloge"),
                            SustavGresaka.getInstance().getPodrucjaGresaka().get(2),
                            new String[]{"CSV Redak: " + redakCSV, "Kompozicija " + trenutnaOznaka}
                    );
                }
            }

        } catch (IOException e) {
            SustavGresaka.getInstance().prijaviGresku(e, "Greška pri učitavanju datoteke");
            System.exit(1);
        }

        return listaKompozicija;
    }


    public static List<VozniRedPodaci> ucitajVozniRedIzCSV(String putanjaDatoteke, ValidatorFactory factory) throws Exception {
        if (putanjaDatoteke == null) {
            SustavGresaka.getInstance().prijaviGresku(new NullVrijednostGreska("Datoteka za vozni red je null"));
            System.exit(1);
        }

        List<VozniRedPodaci> vozniRedPodaciList = new ArrayList<>();
        int redakCSV = 1;
        IValidator validator = factory.napraviValidator();

        try (BufferedReader br = new BufferedReader(new FileReader(putanjaDatoteke))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                redakCSV++;
                String[] redovi = line.split(";");

                if (redovi.length < 9) {
                    String[] tempRedovi = new String[9];
                    Arrays.fill(tempRedovi, "");
                    System.arraycopy(redovi, 0, tempRedovi, 0, redovi.length);
                    redovi = tempRedovi;
                }

                boolean ispravnaValidacija = validator.Validiraj(redovi, redakCSV, null);
                if (!ispravnaValidacija) {
                    continue;
                }

                VozniRedPodaci podaci = new VozniRedPodaci.VozniRedPodaciBuilder()
                        .oznakaPruge(redovi[0].trim())
                        .smjer(redovi[1].trim())
                        .polaznaStanica(redovi[2].trim().isEmpty() ? "null" : redovi[2].trim())
                        .odredisnaStanica(redovi[3].trim().isEmpty() ? "null" : redovi[3].trim())
                        .oznakaVlaka(redovi[4].trim())
                        .vrstaVlaka(redovi[5].trim().isEmpty() ? "null" : redovi[5].trim())
                        .vrijemePolaska(redovi[6].trim())
                        .trajanjeVoznje(redovi[7].trim())
                        .oznakaDana(redovi[8].trim().isEmpty() ? "null" : redovi[8].trim())
                        .build();

                vozniRedPodaciList.add(podaci);
            }
        } catch (IOException e) {
            SustavGresaka.getInstance().prijaviGresku(e, "Greška pri učitavanju datoteke");
            System.exit(1);
        }


        return vozniRedPodaciList;
    }


    public static List<OznakaDana> ucitajOznakeDanaIzCSV(String putanjaOznaka) throws Exception {
        List<OznakaDana> oznakeDana = new ArrayList<>();
        List<String> validneKombinacije = Arrays.asList("Po", "U", "Sr", "Č", "Pe", "Su", "N");

        try (BufferedReader br = new BufferedReader(new FileReader(putanjaOznaka))) {
            String redak;
            br.readLine();

            int redakCSV = 0;
            while ((redak = br.readLine()) != null) {
                redakCSV++;

                String[] parts = redak.split(";");
                if (parts.length == 1){
                    parts = new String[]{parts[0], "PoUSrČPeSuN"};
                }
                if (parts.length == 2) {
                    OznakaDana oznaka = new OznakaDana();
                    try {
                        oznaka.setOznakaDana(Integer.parseInt(parts[0].trim()));
                    } catch (NumberFormatException e) {
                        SustavGresaka.getInstance().prijaviGresku(e,
                                SustavGresaka.getInstance().getPodrucjaGresaka().get(4),
                                new String[]{
                                        "CSV redak: " + redakCSV,
                                        "Trenutni zapis: " + redak.trim()
                                });
                        continue;
                    }
                    //oznaka.setDani(parts[1].trim());

                    String dani = parts[1].trim();
                    oznaka.setDani(dani);
                    List<String> daniList = new ArrayList<>();
                    int i = 0;

                    while (i < dani.length()) {
                        boolean validan = false;
                        for (String kombinacija : validneKombinacije) {
                            if (dani.startsWith(kombinacija, i)) {
                                daniList.add(kombinacija);
                                i += kombinacija.length();
                                validan = true;
                                break;
                            }
                        }
                        if (!validan) {
                            SustavGresaka.getInstance().prijaviGresku(new Exception("Nevalidan dan"),
                                    SustavGresaka.getInstance().getPodrucjaGresaka().get(4),
                                    new String[]{
                                            "CSV redak: " + redakCSV,
                                            "Trenutni zapis: " + redak.trim(),
                                            "Nevalidan unos u oznaci dana"
                                    });
                            break;
                        }
                    }

                    if (i == dani.length()) {
                        oznakeDana.add(oznaka);
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Greška pri čitanju CSV-a", e);
        }
        for(OznakaDana dan : oznakeDana){
            System.out.println(dan.getOznakaDana() + " " + dan.getDani());
        }

        return oznakeDana;
    }
}
