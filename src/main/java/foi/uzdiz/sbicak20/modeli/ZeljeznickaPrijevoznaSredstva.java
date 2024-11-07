package foi.uzdiz.sbicak20.modeli;

import foi.uzdiz.sbicak20.enumeracije.ZeljeznickaPrijevoznaSredstvaEnum.ZPSNamjenaEnum;
import foi.uzdiz.sbicak20.enumeracije.ZeljeznickaPrijevoznaSredstvaEnum.ZPSStatusEnum;
import foi.uzdiz.sbicak20.enumeracije.ZeljeznickaPrijevoznaSredstvaEnum.ZPSVrstaPogona;
import foi.uzdiz.sbicak20.enumeracije.ZeljeznickaPrijevoznaSredstvaEnum.ZPSVrstaPrijevozaEnum;

public class ZeljeznickaPrijevoznaSredstva {

    private String oznaka;
    private String opis;
    private String proizvodac;
    private int godina;
    private ZPSNamjenaEnum namjena;
    private ZPSVrstaPrijevozaEnum vrstaPrijevoza;
    private ZPSVrstaPogona vrstaPogona;
    private int maxBrzina;
    private double maxSnaga;
    private int brojSjedecihMjesta;
    private int brojStajucihMjesta;
    private int brojBicikala;
    private int brojKreveta;
    private int brojAutomobila;
    private double nosivost;
    private double povrsina;
    private double zapremina;
    private ZPSStatusEnum status;


    private ZeljeznickaPrijevoznaSredstva(VoziloBuilder builder) {
        this.oznaka = builder.oznaka;
        this.opis = builder.opis;
        this.proizvodac = builder.proizvodac;
        this.godina = builder.godina;
        this.namjena = ZPSNamjenaEnum.valueOf(builder.namjena);
        this.vrstaPrijevoza = ZPSVrstaPrijevozaEnum.valueOf(builder.vrstaPrijevoza);
        this.vrstaPogona = ZPSVrstaPogona.valueOf(builder.vrstaPogona);
        this.maxBrzina = builder.maxBrzina;
        this.maxSnaga = builder.maxSnaga;
        this.brojSjedecihMjesta = builder.brojSjedecihMjesta;
        this.brojStajucihMjesta = builder.brojStajucihMjesta;
        this.brojBicikala = builder.brojBicikala;
        this.brojKreveta = builder.brojKreveta;
        this.brojAutomobila = builder.brojAutomobila;
        this.nosivost = builder.nosivost;
        this.povrsina = builder.povrsina;
        this.zapremina = builder.zapremina;
        this.status = ZPSStatusEnum.valueOf(builder.status);
    }

    public String getOznaka() {
        return oznaka;
    }

    public String getOpis() {
        return opis;
    }

    public int getGodina() {
        return godina;
    }

    public ZPSNamjenaEnum getNamjena() {
        return namjena;
    }

    public ZPSVrstaPogona getVrstaPogona() {
        return vrstaPogona;
    }

    public int getMaxBrzina() {
        return maxBrzina;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public static class VoziloBuilder {
        private String oznaka;
        private String opis;
        private String proizvodac;
        private int godina;
        private String namjena;
        private String vrstaPrijevoza;
        private String vrstaPogona;
        private int maxBrzina;
        private double maxSnaga;
        private int brojSjedecihMjesta;
        private int brojStajucihMjesta;
        private int brojBicikala;
        private int brojKreveta;
        private int brojAutomobila;
        private double nosivost;
        private double povrsina;
        private double zapremina;
        private String status;

        public VoziloBuilder setOznaka(String oznaka) {
            this.oznaka = oznaka;
            return this;
        }

        public VoziloBuilder setOpis(String opis) {
            this.opis = opis;
            return this;
        }

        public VoziloBuilder setProizvodac(String proizvodac) {
            this.proizvodac = proizvodac;
            return this;
        }

        public VoziloBuilder setGodina(int godina) {
            this.godina = godina;
            return this;
        }

        public VoziloBuilder setNamjena(String namjena) {
            this.namjena = namjena;
            return this;
        }

        public VoziloBuilder setVrstaPrijevoza(String vrstaPrijevoza) {
            this.vrstaPrijevoza = vrstaPrijevoza;
            return this;
        }

        public VoziloBuilder setVrstaPogona(String vrstaPogona) {
            this.vrstaPogona = vrstaPogona;
            return this;
        }

        public VoziloBuilder setMaxBrzina(int maxBrzina) {
            this.maxBrzina = maxBrzina;
            return this;
        }

        public VoziloBuilder setMaxSnaga(double maxSnaga) {
            this.maxSnaga = maxSnaga;
            return this;
        }

        public VoziloBuilder setBrojSjedecihMjesta(int brojSjedećihMjesta) {
            this.brojSjedecihMjesta = brojSjedećihMjesta;
            return this;
        }

        public VoziloBuilder setBrojStajucihMjesta(int brojStajucihMjesta) {
            this.brojStajucihMjesta = brojStajucihMjesta;
            return this;
        }

        public VoziloBuilder setBrojBicikala(int brojBicikala) {
            this.brojBicikala = brojBicikala;
            return this;
        }

        public VoziloBuilder setBrojKreveta(int brojKreveta) {
            this.brojKreveta = brojKreveta;
            return this;
        }

        public VoziloBuilder setBrojAutomobila(int brojAutomobila) {
            this.brojAutomobila = brojAutomobila;
            return this;
        }

        public VoziloBuilder setNosivost(double nosivost) {
            this.nosivost = nosivost;
            return this;
        }

        public VoziloBuilder setPovrsina(double povrsina) {
            this.povrsina = povrsina;
            return this;
        }

        public VoziloBuilder setZapremina(double zapremina) {
            this.zapremina = zapremina;
            return this;
        }

        public VoziloBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ZeljeznickaPrijevoznaSredstva build() {
            return new ZeljeznickaPrijevoznaSredstva(this);
        }
    }

    @Override
    public String toString() {
        return "PrijevoznoSredstvo {" +
                "oznaka='" + oznaka + '\'' +
                ", opis='" + opis + '\'' +
                ", proizvodac='" + proizvodac + '\'' +
                ", godina=" + godina +
                ", namjena='" + namjena + '\'' +
                ", vrstaPrijevoza='" + vrstaPrijevoza + '\'' +
                ", vrstaPogona='" + vrstaPogona + '\'' +
                ", maxBrzina=" + maxBrzina +
                " km/h" +
                ", maxSnaga=" + maxSnaga +
                " kW" +
                ", brojSjedecihMjesta=" + brojSjedecihMjesta +
                ", brojStajucihMjesta=" + brojStajucihMjesta +
                ", brojBicikala=" + brojBicikala +
                ", brojKreveta=" + brojKreveta +
                ", brojAutomobila=" + brojAutomobila +
                ", nosivost=" + nosivost +
                " kg" +
                ", povrsina=" + povrsina +
                " m²" +
                ", zapremina=" + zapremina +
                " m³" +
                ", status='" + status + '\'' +
                '}';
    }
}

