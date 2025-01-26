package foi.uzdiz.sbicak20.modeli;

import foi.uzdiz.sbicak20.modeli.memento.Memento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Karta {
    private String oznakaVlaka;
    private String polaznaStanica;
    private String odredisnaStanica;
    private Date datum;
    private String vrijemeKretanja;
    private String vrijemeDolaska;
    private double izvornaCijena;
    private double uvecanje;
    private double popust;
    private double popustSuN;
    private double konacnaCijena;
    private String nacinKupovanja;
    private Date datumVrijemeKupovine;

    private Karta(KartaBuilder builder) {
        this.oznakaVlaka = builder.oznakaVlaka;
        this.polaznaStanica = builder.polaznaStanica;
        this.odredisnaStanica = builder.odredisnaStanica;
        this.datum = builder.datum;
        this.vrijemeKretanja = builder.vrijemeKretanja;
        this.vrijemeDolaska = builder.vrijemeDolaska;
        this.izvornaCijena = builder.izvornaCijena;
        this.uvecanje = builder.uvecanje;
        this.popust = builder.popust;
        this.popustSuN = builder.popustSuN;
        this.konacnaCijena = builder.konacnaCijena;
        this.nacinKupovanja = builder.nacinKupovanja;
        this.datumVrijemeKupovine = builder.datumVrijemeKupovine;
    }

    public String getOznakaVlaka() {
        return oznakaVlaka;
    }

    public String getPolaznaStanica() {
        return polaznaStanica;
    }

    public String getOdredisnaStanica() {
        return odredisnaStanica;
    }

    public Date getDatum() {
        return datum;
    }

    public String getVrijemeKretanja() {
        return vrijemeKretanja;
    }

    public String getVrijemeDolaska() {
        return vrijemeDolaska;
    }

    public double getIzvornaCijena() {
        return izvornaCijena;
    }

    public double getUvecanje() {
        return uvecanje;
    }

    public double getPopust() {
        return popust;
    }

    public double getPopustSuN() {
        return popustSuN;
    }

    public double getKonacnaCijena() {
        return konacnaCijena;
    }

    public String getNacinKupovanja() {
        return nacinKupovanja;
    }

    public Date getDatumVrijemeKupovine() {
        return datumVrijemeKupovine;
    }

    public static class KartaBuilder {
        private String oznakaVlaka;
        private String polaznaStanica;
        private String odredisnaStanica;
        private Date datum;
        private String vrijemeKretanja;
        private String vrijemeDolaska;
        private double izvornaCijena;
        private double uvecanje;
        private double popust;
        private double popustSuN;
        private double konacnaCijena;
        private String nacinKupovanja;
        private Date datumVrijemeKupovine;

        public KartaBuilder oznakaVlaka(String oznakaVlaka) {
            this.oznakaVlaka = oznakaVlaka;
            return this;
        }

        public KartaBuilder polaznaStanica(String polaznaStanica) {
            this.polaznaStanica = polaznaStanica;
            return this;
        }

        public KartaBuilder odredisnaStanica(String odredisnaStanica) {
            this.odredisnaStanica = odredisnaStanica;
            return this;
        }

        public KartaBuilder datum(String datum) throws ParseException {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
            this.datum = dateFormat.parse(datum);
            return this;
        }

        public KartaBuilder vrijemeKretanja(String vrijemeKretanja) {
            this.vrijemeKretanja = vrijemeKretanja;
            return this;
        }

        public KartaBuilder vrijemeDolaska(String vrijemeDolaska) {
            this.vrijemeDolaska = vrijemeDolaska;
            return this;
        }

        public KartaBuilder izvornaCijena(double izvornaCijena) {
            this.izvornaCijena = izvornaCijena;
            return this;
        }

        public KartaBuilder uvecanje(double uvecanje) {
            this.uvecanje = uvecanje;
            return this;
        }

        public KartaBuilder popust(double popust) {
            this.popust = popust;
            return this;
        }

        public KartaBuilder popustSuN(double popustSuN) {
            this.popustSuN = popustSuN;
            return this;
        }

        public KartaBuilder konacnaCijena(double konacnaCijena) {
            this.konacnaCijena = konacnaCijena;
            return this;
        }

        public KartaBuilder nacinKupovanja(String nacinKupovanja) {
            this.nacinKupovanja = nacinKupovanja;
            return this;
        }

        public KartaBuilder datumVrijemeKupovine(String datumVrijemeKupovine) throws ParseException {
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
            this.datumVrijemeKupovine = dateTimeFormat.parse(datumVrijemeKupovine);
            return this;
        }

        public Karta build() {
            return new Karta(this);
        }
    }


    public Memento sacuvajUMemento() {
        return new Memento(this);
    }

    public void dohvatiIzMementa(Memento memento) {
        Karta karta = memento.getSacuvanoStanjeKarta();
        this.oznakaVlaka = karta.oznakaVlaka;
        this.polaznaStanica = karta.polaznaStanica;
        this.odredisnaStanica = karta.odredisnaStanica;
        this.datum = karta.datum;
        this.vrijemeKretanja = karta.vrijemeKretanja;
        this.vrijemeDolaska = karta.vrijemeDolaska;
        this.izvornaCijena = karta.izvornaCijena;
        this.uvecanje = karta.uvecanje;
        this.popust = karta.popust;
        this.popustSuN = karta.popustSuN;
        this.konacnaCijena = karta.konacnaCijena;
        this.nacinKupovanja = karta.nacinKupovanja;
        this.datumVrijemeKupovine = karta.datumVrijemeKupovine;
    }
}

