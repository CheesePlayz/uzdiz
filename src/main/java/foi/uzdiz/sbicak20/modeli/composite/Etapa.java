package foi.uzdiz.sbicak20.modeli.composite;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Etapa extends VozniRedKomponenta{

    private List<VozniRedKomponenta> stanice = new ArrayList<>();
    private String oznakaVlaka;
    private String oznakaPruge;
    private String daniUTjednu;
    private LocalTime vrijemePolaska;
    private LocalTime vrijemeDolaska;
    private double ukupnaKilometraza = 0;

    public Etapa(String oznakaVlaka, String oznakaPruge, LocalTime vrijemePolaska, LocalTime vrijemeDolaska, String daniUTjednu) {
        this.oznakaVlaka = oznakaVlaka;
        this.oznakaPruge = oznakaPruge;
        this.vrijemePolaska = vrijemePolaska;
        this.vrijemeDolaska = vrijemeDolaska;
        this.daniUTjednu = daniUTjednu;
    }

    public String getOznakaVlaka() {
        return oznakaVlaka;
    }
    public String getOznakaPruge() { return oznakaPruge; }
    public LocalTime getVrijemePolaska(){
        return vrijemePolaska;
    }
    public LocalTime getVrijemeDolaska(){
        return vrijemeDolaska;
    }
    public String getDaniUTjednu() {
        return daniUTjednu;
    }
    public double getUkupnaKilometraza(){ return ukupnaKilometraza; }
    public void setUkupnaKilometraza(double km){
        ukupnaKilometraza = km;
    }

    @Override
    public void dodajKomponentu(VozniRedKomponenta komponenta) {
        stanice.add(komponenta);
    }

    @Override
    public void ukloniKomponentu(VozniRedKomponenta komponenta) {
        stanice.remove(komponenta);
    }

    @Override
    public List<VozniRedKomponenta> getDjeca() {
        return stanice;
    }

    @Override
    public void prikaziDetalje() {
        int[] maxDuljine = {12, 12, 23, 23, 15, 15, 18, 15};

        String polaznaStanica = stanice.getFirst().toString();
        String odredisnaStanica = stanice.getLast().toString();

        maxDuljine[0] = Math.max(maxDuljine[0], oznakaVlaka.length());
        maxDuljine[1] = Math.max(maxDuljine[1], oznakaPruge.length());
        maxDuljine[2] = Math.max(maxDuljine[2], polaznaStanica.length());
        maxDuljine[3] = Math.max(maxDuljine[3], odredisnaStanica.length());
        maxDuljine[4] = Math.max(maxDuljine[4], vrijemePolaska.toString().length());
        maxDuljine[5] = Math.max(maxDuljine[5], vrijemeDolaska.toString().length());
        maxDuljine[6] = Math.max(maxDuljine[6], String.format("%.2f", ukupnaKilometraza).length());
        maxDuljine[7] = Math.max(maxDuljine[7], daniUTjednu.length());

        StringBuilder formatBuilder = new StringBuilder("|");
        for (int duljina : maxDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        System.out.printf(format, oznakaVlaka, oznakaPruge, polaznaStanica,
                odredisnaStanica, vrijemePolaska, vrijemeDolaska,
                String.format("%.2f", ukupnaKilometraza), daniUTjednu);
    }

    @Override
    public Object dohvatiObjekt() {
        return this;
    }
}
