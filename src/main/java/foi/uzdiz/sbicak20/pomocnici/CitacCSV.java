package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.modeli.Kompozicija;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaPrijevoznaSredstva;
import foi.uzdiz.sbicak20.modeli.ZeljeznickeStanice;
import foi.uzdiz.sbicak20.validatori.IValidator;
import foi.uzdiz.sbicak20.validatori.ValidatorFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CitacCSV {

    static IValidator validator;
    public static List<ZeljeznickeStanice> ucitajStaniceIzCSV(String putanjaDatoteke) throws Exception {
        List<ZeljeznickeStanice> stanice = new ArrayList<>();
        validator = ValidatorFactory.napraviValidator(ZeljeznickeStanice.class);
        try (BufferedReader br = new BufferedReader(new FileReader(putanjaDatoteke))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] redovi = line.split(";");
                if (redovi.length != 14){
                    continue;
                }
                boolean ispravnaValidacija = validator.Validiraj(redovi);
                if (!ispravnaValidacija){
                    System.out.println("Neispravna stanica! Nastavljamo s radom");
                    continue;
                }

                ZeljeznickeStanice stanica = new ZeljeznickeStanice.StanicaBuilder()
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
                        .setDuzina(Integer.parseInt(redovi[13].trim()))
                        .build();

                stanice.add(stanica);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stanice;
    }

    public static List<ZeljeznickaPrijevoznaSredstva> ucitajVozilaIzCSV(String putanjaDatoteke) throws Exception {
        List<ZeljeznickaPrijevoznaSredstva> vozila = new ArrayList<ZeljeznickaPrijevoznaSredstva>();
        validator = ValidatorFactory.napraviValidator(ZeljeznickaPrijevoznaSredstva.class);
        try (BufferedReader br = new BufferedReader(new FileReader(putanjaDatoteke))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] redovi = line.split(";");
                if (redovi.length != 18){
                    continue;
                }
                boolean ispravnaValidacija = validator.Validiraj(redovi);
                if (!ispravnaValidacija){
                    System.out.println("Neispravno vozilo! Nastavljamo s radom");
                    continue;
                }

                ZeljeznickaPrijevoznaSredstva vozilo = new ZeljeznickaPrijevoznaSredstva.VoziloBuilder()
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
            e.printStackTrace();
        }

        return vozila;
    }

    public static List<Kompozicija> ucitajKompozicijeIzCSV(String putanjaCsvKompozicije) {
        List<Kompozicija> kompozicije = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(putanjaCsvKompozicije))) {
            String line;
            String trenutnaOznaka = null;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] redovi = line.split(";");


                if (redovi.length == 3) {
                    trenutnaOznaka = redovi[0].trim();
                    String oznakaPrijevoznogSredstva = redovi[1].trim();
                    String uloga = redovi[2].trim();

                    Kompozicija kompozicija = new Kompozicija(trenutnaOznaka, oznakaPrijevoznogSredstva, uloga);
                    kompozicije.add(kompozicija);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return kompozicije;
    }
}
