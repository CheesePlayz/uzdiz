package foi.uzdiz.sbicak20.greske;

public class SustavGresaka {
    private static SustavGresaka instance;
    private int brojacGresaka = 0;
    private StringBuilder logGresaka = new StringBuilder();
    private StringBuilder trenutnaGreska = new StringBuilder();

    private SustavGresaka() {
    }

    public static SustavGresaka getInstance() {
        if (instance == null) {
            instance = new SustavGresaka();
        }
        return instance;
    }

    public void prijaviGresku(Exception e) {
        brojacGresaka++;
        logGresaka.append("Greška #").append(brojacGresaka).append(" - ")
                .append(e.getMessage());
        trenutnaGreska.setLength(0);
        trenutnaGreska.append("Greška #").append(brojacGresaka).append(" - ")
                .append(e.getMessage());
        ispisiTrenutnuGresku(trenutnaGreska);
    }

    public void prijaviGresku(Exception e, String podrucjeGreske) {
        brojacGresaka++;
        logGresaka.append("Greška #").append(brojacGresaka).append(" - ").append(podrucjeGreske).append(" - ")
                .append(e.getMessage());
        trenutnaGreska.setLength(0);
        trenutnaGreska.append("Greška #").append(brojacGresaka).append(" - ").append(podrucjeGreske).append(" - ")
                .append(e.getMessage());
        ispisiTrenutnuGresku(trenutnaGreska);
    }

    public void prijaviGresku(Exception e, String podrucjeGreske, String dodatniAtribut) {
        brojacGresaka++;
        logGresaka.append("Greška #").append(brojacGresaka).append(" - ").append(podrucjeGreske).append(" - ").append(dodatniAtribut).append(" - ")
                .append(e.getMessage());
        trenutnaGreska.setLength(0);
        trenutnaGreska.append("Greška #").append(brojacGresaka).append(" - ").append(podrucjeGreske).append(" - ").append(dodatniAtribut).append(" - ")
                .append(e.getMessage());
        ispisiTrenutnuGresku(trenutnaGreska);
    }

    public void prijaviGresku(Exception e, String podrucjeGreske, String[] dodatniAtributi) {
        brojacGresaka++;
        trenutnaGreska.setLength(0);

        logGresaka.append("Greška #").append(brojacGresaka).append(" - ").append(podrucjeGreske).append(" - ");
        trenutnaGreska.append("Greška #").append(brojacGresaka).append(" - ").append(podrucjeGreske).append(" - ");
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
        if (brojacGresaka > 0) {
            System.out.println("Ukupan broj grešaka: " + brojacGresaka);
            System.out.println(logGresaka.toString());
        } else {
            System.out.println("Nema prijavljenih grešaka.");
        }
    }

    public void resetirajGreske() {
        brojacGresaka = 0;
        logGresaka.setLength(0);
    }
}
