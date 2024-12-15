package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.greske.NullVrijednostGreska;
import foi.uzdiz.sbicak20.greske.SustavGresaka;
import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.VozniRedPodaci;
import foi.uzdiz.sbicak20.modeli.ZeljeznickoPrijevoznoSredstvo;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;
import foi.uzdiz.sbicak20.validatori.IValidator;
import foi.uzdiz.sbicak20.validatori.ValidatorFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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


    public static List<VozniRedPodaci> ucitajVozniRed(String putanjaCsvVozniRed, ValidatorFactory factory) throws Exception {
        if (putanjaCsvVozniRed == null) {
            SustavGresaka.getInstance().prijaviGresku(new NullVrijednostGreska("Datoteka za vozni red je null"));
            System.exit(1);
        }

        List<VozniRedPodaci> listaPodataka = new ArrayList<>();
        int redakCSV = 1;
        IValidator validator = factory.napraviValidator();

        try (BufferedReader br = new BufferedReader(new FileReader(putanjaCsvVozniRed))) {
            String line;
            br.readLine(); // Preskoci header liniju

            while ((line = br.readLine()) != null) {
                redakCSV++;
                String[] redovi = line.split(";");

                if (redovi.length > 9) {
                    System.err.println("Redak " + redakCSV + ": Previše stupaca.");
                    continue;
                }

                boolean ispravnaValidacija = validator.Validiraj(redovi, redakCSV, null);
                if (!ispravnaValidacija) {
                    continue;
                }

                String oznakaPruge = redovi[0] == null ? "" : redovi[0].trim();
                String smjer = redovi[1] == null ? "" : redovi[1].trim();
                String polaznaStanica = redovi[2] == null || redovi[2].trim().isEmpty() ? "Prva stanica" : redovi[2].trim();
                String odredisnaStanica = redovi[3] == null || redovi[3].trim().isEmpty() ? "Zadnja stanica" : redovi[3].trim();
                String oznakaVlaka = redovi[4] == null ? "" : redovi[4].trim();
                String vrstaVlaka = redovi[5] == null || redovi[5].trim().isEmpty() ? "N" : redovi[5].trim();
                String vrijemePolaska = redovi[6] == null ? "" : redovi[6].trim();
                String trajanjeVoznje = redovi[7] == null ? "" : redovi[7].trim();
                String oznakaDana = redovi[8] == null ? "" : redovi[8].trim();

                VozniRedPodaci podaci = new VozniRedPodaci.VozniRedPodaciBuilder()
                        .oznakaPruge(oznakaPruge)
                        .smjer(smjer)
                        .polaznaStanica(polaznaStanica)
                        .odredisnaStanica(odredisnaStanica)
                        .oznakaVlaka(oznakaVlaka)
                        .vrstaVlaka(vrstaVlaka)
                        .vrijemePolaska(vrijemePolaska)
                        .trajanjeVoznje(trajanjeVoznje)
                        .oznakaDana(oznakaDana)
                        .build();

                listaPodataka.add(podaci);
            }
        } catch (IOException e) {
            SustavGresaka.getInstance().prijaviGresku(e, "Greška pri učitavanju datoteke");
            System.exit(1);
        }

        for (VozniRedPodaci podaci : listaPodataka) {
            System.out.println(podaci.toString());
        }
        return listaPodataka;
    }
}
