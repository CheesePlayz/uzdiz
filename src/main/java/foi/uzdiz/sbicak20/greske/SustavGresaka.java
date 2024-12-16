package foi.uzdiz.sbicak20.greske;

import java.util.ArrayList;
import java.util.List;

public class SustavGresaka {
    private static SustavGresaka instance;
    private int brojacGresakaGlobalno = 0;
    private int brojacGresakaStanice = 0;
    private int brojacGresakaVozila = 0;
    private int brojacGresakaKompozicije = 0;
    private int brojacGresakaVozniRed = 0;
    private StringBuilder logGresaka = new StringBuilder();
    private StringBuilder trenutnaGreska = new StringBuilder();
    private List<String> podrucjaGresaka = new ArrayList<>();

    private SustavGresaka() {
        podrucjaGresaka.add("Ucitavanje CSV-a Stanica");
        podrucjaGresaka.add("Ucitavanje CSV-a Vozila");
        podrucjaGresaka.add("Ucitavanje CSV-a Kompozicije");
        podrucjaGresaka.add("Ucitavanje CSV-a Vozni Red");
    }

    public static SustavGresaka getInstance() {
        if (instance == null) {
            instance = new SustavGresaka();
        }
        return instance;
    }

    public List<String> getPodrucjaGresaka() {
        return podrucjaGresaka;
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

        } else {
            System.out.println("Nema prijavljenih grešaka.\n");
        }
        System.out.println("------------------------------\n");
    }


    private void povecajBrojacGresakaPrimjene(String podrucjePrimjene){
        if (podrucjePrimjene.equals(podrucjaGresaka.get(0))) {
            brojacGresakaStanice++;
        } else if (podrucjePrimjene.equals(podrucjaGresaka.get(1))) {
            brojacGresakaVozila++;
        } else if (podrucjePrimjene.equals(podrucjaGresaka.get(2))) {
            brojacGresakaKompozicije++;
        } else if (podrucjePrimjene.equals(podrucjaGresaka.get(3))){
            brojacGresakaVozniRed++;
        }
    }

    public void resetirajGreske() {
        brojacGresakaGlobalno = 0;
        logGresaka.setLength(0);
    }
}
