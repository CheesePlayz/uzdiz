package foi.uzdiz.sbicak20.greske;

import java.util.ArrayList;
import java.util.List;

public class SustavGresakaSingleton {
    private static SustavGresakaSingleton instance;
    private int brojacGresakaGlobalno = 0;
    private int brojacGresakaStanice = 0;
    private int brojacGresakaVozila = 0;
    private int brojacGresakaKompozicije = 0;
    private int brojacGresakaVozniRed = 0;
    private int brojacGresakaOznakeDana = 0;
    private StringBuilder logGresaka = new StringBuilder();
    private StringBuilder trenutnaGreska = new StringBuilder();
    private List<String> podrucjaGresaka = new ArrayList<>();

    private SustavGresakaSingleton() {
        podrucjaGresaka.add("Ucitavanje CSV-a Stanica");
        podrucjaGresaka.add("Ucitavanje CSV-a Vozila");
        podrucjaGresaka.add("Ucitavanje CSV-a Kompozicije");
        podrucjaGresaka.add("Ucitavanje CSV-a Vozni Red");
        podrucjaGresaka.add("Ucitavanje CSV-a Oznaka Dana");
    }

    public static SustavGresakaSingleton getInstance() {
        if (instance == null) {
            instance = new SustavGresakaSingleton();
        }
        return instance;
    }

    public static void setInstance(SustavGresakaSingleton instance) {
        SustavGresakaSingleton.instance = instance;
    }

    public List<String> getPodrucjaGresaka() {
        return podrucjaGresaka;
    }

    public void setPodrucjaGresaka(List<String> podrucjaGresaka) {
        this.podrucjaGresaka = podrucjaGresaka;
    }

    public void prijaviGresku(Exception e) {
        brojacGresakaGlobalno++;
        logGresaka.append("Greška #").append(brojacGresakaGlobalno).append(" - ")
                .append(e.getMessage());
        trenutnaGreska.setLength(0);
        trenutnaGreska.append("Greška #").append(brojacGresakaGlobalno).append(" - ")
                .append(e.getMessage());
        ispisiTrenutnuGresku(trenutnaGreska);
    }

    public void prijaviGresku(Exception e, String podrucjeGreske) {
        brojacGresakaGlobalno++;
        povecajBrojacGresakaPrimjene(podrucjeGreske);
        logGresaka.append("Greška #").append(brojacGresakaGlobalno).append(" - ").append(podrucjeGreske).append(" - ")
                .append(e.getMessage());
        trenutnaGreska.setLength(0);
        trenutnaGreska.append("Greška #").append(brojacGresakaGlobalno).append(" - ").append(podrucjeGreske).append(" - ")
                .append(e.getMessage());
        ispisiTrenutnuGresku(trenutnaGreska);
    }

    public void prijaviGresku(Exception e, String podrucjeGreske, String dodatniAtribut) {
        brojacGresakaGlobalno++;
        povecajBrojacGresakaPrimjene(podrucjeGreske);
        logGresaka.append("Greška #").append(brojacGresakaGlobalno).append(" - ").append(podrucjeGreske).append(" - ").append(dodatniAtribut).append(" - ")
                .append(e.getMessage());
        trenutnaGreska.setLength(0);
        trenutnaGreska.append("Greška #").append(brojacGresakaGlobalno).append(" - ").append(podrucjeGreske).append(" - ").append(dodatniAtribut).append(" - ")
                .append(e.getMessage());
        ispisiTrenutnuGresku(trenutnaGreska);
    }

    public void prijaviGresku(Exception e, String podrucjeGreske, String[] dodatniAtributi) {
        brojacGresakaGlobalno++;
        povecajBrojacGresakaPrimjene(podrucjeGreske);
        trenutnaGreska.setLength(0);

        logGresaka.append("Greška #").append(brojacGresakaGlobalno).append(" - ").append(podrucjeGreske).append(" - ");
        trenutnaGreska.append("Greška #").append(brojacGresakaGlobalno).append(" - ").append(podrucjeGreske).append(" - ");
        for (String atribut : dodatniAtributi) {
            logGresaka.append(atribut).append(" - ");
            trenutnaGreska.append(atribut).append(" - ");
        }
        logGresaka.append(e.getMessage());
        trenutnaGreska.append(e.getMessage());
        ispisiTrenutnuGresku(trenutnaGreska);
    }

    private void ispisiTrenutnuGresku(StringBuilder trenutnaGreska) {
        System.out.println(trenutnaGreska.toString());
    }

    public void ispisiSveGreske() {
        if (brojacGresakaGlobalno > 0) {
            System.out.println("\n----- Statistika Grešaka -----");
            System.out.println("Ukupan broj grešaka: " + brojacGresakaGlobalno);
            System.out.println("Broj grešaka po područjima:");
            System.out.println("1. Stanice: " + brojacGresakaStanice);
            System.out.println("2. Vozila: " + brojacGresakaVozila);
            System.out.println("3. Kompozicije: " + brojacGresakaKompozicije);
            System.out.println("4. Vozni Red: " + brojacGresakaVozniRed);
            System.out.println("5. Oznake Dana: " + brojacGresakaOznakeDana);

        } else {
            System.out.println("Nema prijavljenih grešaka.\n");
        }
        System.out.println("------------------------------\n");
    }

    private void povecajBrojacGresakaPrimjene(String podrucjePrimjene) {
        if (podrucjePrimjene.equals(podrucjaGresaka.get(0))) {
            brojacGresakaStanice++;
        } else if (podrucjePrimjene.equals(podrucjaGresaka.get(1))) {
            brojacGresakaVozila++;
        } else if (podrucjePrimjene.equals(podrucjaGresaka.get(2))) {
            brojacGresakaKompozicije++;
        } else if (podrucjePrimjene.equals(podrucjaGresaka.get(3))) {
            brojacGresakaVozniRed++;
        } else if (podrucjePrimjene.equals(podrucjaGresaka.get(4))) {
            brojacGresakaOznakeDana++;
        }
    }

    public void resetirajGreske() {
        brojacGresakaGlobalno = 0;
        logGresaka.setLength(0);
    }

    public int getBrojacGresakaGlobalno() {
        return brojacGresakaGlobalno;
    }

    public void setBrojacGresakaGlobalno(int brojacGresakaGlobalno) {
        this.brojacGresakaGlobalno = brojacGresakaGlobalno;
    }

    public int getBrojacGresakaStanice() {
        return brojacGresakaStanice;
    }

    public void setBrojacGresakaStanice(int brojacGresakaStanice) {
        this.brojacGresakaStanice = brojacGresakaStanice;
    }

    public int getBrojacGresakaVozila() {
        return brojacGresakaVozila;
    }

    public void setBrojacGresakaVozila(int brojacGresakaVozila) {
        this.brojacGresakaVozila = brojacGresakaVozila;
    }

    public int getBrojacGresakaKompozicije() {
        return brojacGresakaKompozicije;
    }

    public void setBrojacGresakaKompozicije(int brojacGresakaKompozicije) {
        this.brojacGresakaKompozicije = brojacGresakaKompozicije;
    }

    public int getBrojacGresakaVozniRed() {
        return brojacGresakaVozniRed;
    }

    public void setBrojacGresakaVozniRed(int brojacGresakaVozniRed) {
        this.brojacGresakaVozniRed = brojacGresakaVozniRed;
    }

    public int getBrojacGresakaOznakeDana() {
        return brojacGresakaOznakeDana;
    }

    public void setBrojacGresakaOznakeDana(int brojacGresakaOznakeDana) {
        this.brojacGresakaOznakeDana = brojacGresakaOznakeDana;
    }

    public StringBuilder getLogGresaka() {
        return logGresaka;
    }

    public void setLogGresaka(StringBuilder logGresaka) {
        this.logGresaka = logGresaka;
    }

    public StringBuilder getTrenutnaGreska() {
        return trenutnaGreska;
    }

    public void setTrenutnaGreska(StringBuilder trenutnaGreska) {
        this.trenutnaGreska = trenutnaGreska;
    }
}
