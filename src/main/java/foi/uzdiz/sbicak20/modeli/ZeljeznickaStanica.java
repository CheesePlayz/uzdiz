package foi.uzdiz.sbicak20.modeli;

import foi.uzdiz.sbicak20.enumeracije.ZeljeznickeStaniceEnum.*;

public class ZeljeznickaStanica {

    private final String stanica;
    private final String oznakaPruge;
    private final ZSVrstaStaniceEnum vrstaStanice;
    private final ZSStatusStaniceEnum statusStanice;
    private final ZSPutniciUlIzEnum putniciUlIz;
    private final ZSRobaUtIstEnum robaUtIst;
    private final ZSKategorijaPrugeEnum kategorijaPruge;
    private final int brojPerona;
    private final ZSVrstaPrugeEnum vrstaPruge;
    private final int brojKolosjeka;
    private final double doPoOsovini;
    private final double doPoDuznomM;
    private ZSStatusPrugeEnum statusPruge;
    private final int duzina;
    private final Integer vrijemeNormalniVlak;
    private final Integer vrijemeUbrzaniVlak;
    private final Integer vrijemeBrziVlak;

    private ZeljeznickaStanica(StanicaBuilder builder) {
        this.stanica = builder.stanica;
        this.oznakaPruge = builder.oznakaPruge;
        this.vrstaStanice = ZSVrstaStaniceEnum.fromLabel(builder.vrstaStanice);
        this.statusStanice = ZSStatusStaniceEnum.valueOf(builder.statusStanice);
        this.putniciUlIz = ZSPutniciUlIzEnum.valueOf(builder.putniciUlIz);
        this.robaUtIst = ZSRobaUtIstEnum.valueOf(builder.robaUtIst);
        this.kategorijaPruge = ZSKategorijaPrugeEnum.valueOf(builder.kategorijaPruge);
        this.brojPerona = builder.brojPerona;
        this.vrstaPruge = ZSVrstaPrugeEnum.valueOf(builder.vrstaPruge);
        this.brojKolosjeka = builder.brojKolosjeka;
        this.doPoOsovini = builder.doPoOsovini;
        this.doPoDuznomM = builder.doPoDuznomM;
        this.statusPruge = ZSStatusPrugeEnum.valueOf(builder.statusPruge);
        this.duzina = builder.duzina;
        this.vrijemeNormalniVlak = builder.vrijemeNormalniVlak;
        this.vrijemeUbrzaniVlak = builder.vrijemeUbrzaniVlak;
        this.vrijemeBrziVlak = builder.vrijemeBrziVlak;
    }
    public void setStatusPruge(String oznakaStatusa){
        statusPruge = ZSStatusPrugeEnum.valueOf(oznakaStatusa);
    }
    public String getStatusPruge(){
        return statusPruge.toString();
    }

    public String getStanica() {
        return stanica;
    }

    public String getOznakaPruge() {
        return oznakaPruge;
    }

    public ZSVrstaStaniceEnum getVrstaStanice() {
        return vrstaStanice;
    }

    public int getDuzina() {
        return duzina;
    }

    public Integer getVrijemeNormalniVlak() {
        return vrijemeNormalniVlak;
    }

    public Integer getVrijemeUbrzaniVlak() {
        return vrijemeUbrzaniVlak;
    }

    public Integer getVrijemeBrziVlak() {
        return vrijemeBrziVlak;
    }

    @Override
    public String toString() {
        return "ZeljeznickeStanice{" +
                "stanica='" + stanica + '\'' +
                ", oznakaPruge='" + oznakaPruge + '\'' +
                ", vrstaStanice='" + vrstaStanice + '\'' +
                ", statusStanice='" + statusStanice + '\'' +
                ", putniciUlIz=" + putniciUlIz +
                ", robaUtIst=" + robaUtIst +
                ", kategorijaPruge='" + kategorijaPruge + '\'' +
                ", brojPerona=" + brojPerona +
                ", vrstaPruge='" + vrstaPruge + '\'' +
                ", brojKolosjeka=" + brojKolosjeka +
                ", doPoOsovini=" + doPoOsovini +
                ", doPoDuznomM=" + doPoDuznomM +
                ", statusPruge='" + statusPruge + '\'' +
                ", duzina=" + duzina +
                ", vrijemeNormalniVlak=" + vrijemeNormalniVlak +
                ", vrijemeUbrzaniVlak=" + vrijemeUbrzaniVlak +
                ", vrijemeBrziVlak=" + vrijemeBrziVlak +
                '}';
    }

    public static class StanicaBuilder {
        private String stanica;
        private String oznakaPruge;
        private String vrstaStanice;
        private String statusStanice;
        private String putniciUlIz;
        private String robaUtIst;
        private String kategorijaPruge;
        private int brojPerona;
        private String vrstaPruge;
        private int brojKolosjeka;
        private double doPoOsovini;
        private double doPoDuznomM;
        private String statusPruge;
        private int duzina;
        private Integer vrijemeNormalniVlak;
        private Integer vrijemeUbrzaniVlak;
        private Integer vrijemeBrziVlak;

        public StanicaBuilder setStanica(String stanica) {
            this.stanica = stanica;
            return this;
        }

        public StanicaBuilder setOznakaPruge(String oznakaPruge) {
            this.oznakaPruge = oznakaPruge;
            return this;
        }

        public StanicaBuilder setVrstaStanice(String vrstaStanice) {
            this.vrstaStanice = vrstaStanice;
            return this;
        }

        public StanicaBuilder setStatusStanice(String statusStanice) {
            this.statusStanice = statusStanice;
            return this;
        }

        public StanicaBuilder setPutniciUlIz(String putniciUlIz) {
            this.putniciUlIz = putniciUlIz;
            return this;
        }

        public StanicaBuilder setRobaUtIst(String robaUtIst) {
            this.robaUtIst = robaUtIst;
            return this;
        }

        public StanicaBuilder setKategorijaPruge(String kategorijaPruge) {
            this.kategorijaPruge = kategorijaPruge;
            return this;
        }

        public StanicaBuilder setBrojPerona(int brojPerona) {
            this.brojPerona = brojPerona;
            return this;
        }

        public StanicaBuilder setVrstaPruge(String vrstaPruge) {
            this.vrstaPruge = vrstaPruge;
            return this;
        }

        public StanicaBuilder setBrojKolosjeka(int brojKolosjeka) {
            this.brojKolosjeka = brojKolosjeka;
            return this;
        }

        public StanicaBuilder setDoPoOsovini(double doPoOsovini) {
            this.doPoOsovini = doPoOsovini;
            return this;
        }

        public StanicaBuilder setDoPoDuznomM(double doPoDuznomM) {
            this.doPoDuznomM = doPoDuznomM;
            return this;
        }

        public StanicaBuilder setStatusPruge(String statusPruge) {
            this.statusPruge = statusPruge;
            return this;
        }

        public StanicaBuilder setDuzina(int duzina) {
            this.duzina = duzina;
            return this;
        }

        public StanicaBuilder setVrijemeNormalniVlak(int vrijemeNormalniVlak) {
            this.vrijemeNormalniVlak = vrijemeNormalniVlak != 0 ? vrijemeNormalniVlak : null;
            return this;
        }

        public StanicaBuilder setVrijemeUbrzaniVlak(int vrijemeUbrzaniVlak) {
            this.vrijemeUbrzaniVlak = vrijemeUbrzaniVlak != 0 ? vrijemeUbrzaniVlak : null;
            return this;
        }

        public StanicaBuilder setVrijemeBrziVlak(int vrijemeBrziVlak) {
            this.vrijemeBrziVlak = vrijemeBrziVlak != 0 ? vrijemeBrziVlak : null;
            return this;
        }

        public ZeljeznickaStanica build() {
            return new ZeljeznickaStanica(this);
        }
    }
}

