package foi.uzdiz.sbicak20.modeli;

public class ZeljeznickeStanice {

    private String stanica;
    private String oznakaPruge;
    private String vrstaStanice;
    private String statusStanice;
    private boolean putniciUlIz;
    private boolean robaUtIst;
    private String kategorijaPruge;
    private int brojPerona;
    private String vrstaPruge;
    private int brojKolosjeka;
    private double doPoOsovini;
    private double doPoDuznomM;
    private String statusPruge;
    private int duzina;

    // Getters and Setters for each field
    public String getStanica() {
        return stanica;
    }

    public void setStanica(String stanica) {
        this.stanica = stanica;
    }

    public String getOznakaPruge() {
        return oznakaPruge;
    }

    public void setOznakaPruge(String oznakaPruge) {
        this.oznakaPruge = oznakaPruge;
    }

    public String getVrstaStanice() {
        return vrstaStanice;
    }

    public void setVrstaStanice(String vrstaStanice) {
        this.vrstaStanice = vrstaStanice;
    }

    public String getStatusStanice() {
        return statusStanice;
    }

    public void setStatusStanice(String statusStanice) {
        this.statusStanice = statusStanice;
    }

    public boolean isPutniciUlIz() {
        return putniciUlIz;
    }

    public void setPutniciUlIz(boolean putniciUlIz) {
        this.putniciUlIz = putniciUlIz;
    }

    public boolean isRobaUtIst() {
        return robaUtIst;
    }

    public void setRobaUtIst(boolean robaUtIst) {
        this.robaUtIst = robaUtIst;
    }

    public String getKategorijaPruge() {
        return kategorijaPruge;
    }

    public void setKategorijaPruge(String kategorijaPruge) {
        this.kategorijaPruge = kategorijaPruge;
    }

    public int getBrojPerona() {
        return brojPerona;
    }

    public void setBrojPerona(int brojPerona) {
        this.brojPerona = brojPerona;
    }

    public String getVrstaPruge() {
        return vrstaPruge;
    }

    public void setVrstaPruge(String vrstaPruge) {
        this.vrstaPruge = vrstaPruge;
    }

    public int getBrojKolosjeka() {
        return brojKolosjeka;
    }

    public void setBrojKolosjeka(int brojKolosjeka) {
        this.brojKolosjeka = brojKolosjeka;
    }

    public double getDoPoOsovini() {
        return doPoOsovini;
    }

    public void setDoPoOsovini(double doPoOsovini) {
        this.doPoOsovini = doPoOsovini;
    }

    public double getDoPoDuznomM() {
        return doPoDuznomM;
    }

    public void setDoPoDuznomM(double doPoDuznomM) {
        this.doPoDuznomM = doPoDuznomM;
    }

    public String getStatusPruge() {
        return statusPruge;
    }

    public void setStatusPruge(String statusPruge) {
        this.statusPruge = statusPruge;
    }

    public int getDuzina() {
        return duzina;
    }

    public void setDuzina(int duzina) {
        this.duzina = duzina;
    }
    
    private ZeljeznickeStanice(StanicaBuilder builder) {
        this.stanica = builder.stanica;
        this.oznakaPruge = builder.oznakaPruge;
        this.vrstaStanice = builder.vrstaStanice;
        this.statusStanice = builder.statusStanice;
        this.putniciUlIz = builder.putniciUlIz;
        this.robaUtIst = builder.robaUtIst;
        this.kategorijaPruge = builder.kategorijaPruge;
        this.brojPerona = builder.brojPerona;
        this.vrstaPruge = builder.vrstaPruge;
        this.brojKolosjeka = builder.brojKolosjeka;
        this.doPoOsovini = builder.doPoOsovini;
        this.doPoDuznomM = builder.doPoDuznomM;
        this.statusPruge = builder.statusPruge;
        this.duzina = builder.duzina;
    }

    public static class StanicaBuilder {
        private String stanica;
        private String oznakaPruge;
        private String vrstaStanice;
        private String statusStanice;
        private boolean putniciUlIz;
        private boolean robaUtIst;
        private String kategorijaPruge;
        private int brojPerona;
        private String vrstaPruge;
        private int brojKolosjeka;
        private double doPoOsovini;
        private double doPoDuznomM;
        private String statusPruge;
        private int duzina;

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

        public StanicaBuilder setPutniciUlIz(boolean putniciUlIz) {
            this.putniciUlIz = putniciUlIz;
            return this;
        }

        public StanicaBuilder setRobaUtIst(boolean robaUtIst) {
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

        public ZeljeznickeStanice build() {
            return new ZeljeznickeStanice(this);
        }
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
                '}';
    }
}

