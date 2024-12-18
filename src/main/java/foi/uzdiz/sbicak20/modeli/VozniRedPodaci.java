package foi.uzdiz.sbicak20.modeli;

public class VozniRedPodaci {
    private String oznakaPruge;
    private String smjer;
    private String polaznaStanica;
    private String odredisnaStanica;
    private String oznakaVlaka;
    private String vrstaVlaka;
    private String vrijemePolaska;
    private String trajanjeVoznje;
    private String oznakaDana;

    private VozniRedPodaci(VozniRedPodaciBuilder vozniRedPodaciBuilder) {
        this.oznakaPruge = vozniRedPodaciBuilder.oznakaPruge;
        this.smjer = vozniRedPodaciBuilder.smjer;
        this.polaznaStanica = vozniRedPodaciBuilder.polaznaStanica;
        this.odredisnaStanica = vozniRedPodaciBuilder.odredisnaStanica;
        this.oznakaVlaka = vozniRedPodaciBuilder.oznakaVlaka;
        this.vrstaVlaka = vozniRedPodaciBuilder.vrstaVlaka;
        this.vrijemePolaska = vozniRedPodaciBuilder.vrijemePolaska;
        this.trajanjeVoznje = vozniRedPodaciBuilder.trajanjeVoznje;
        this.oznakaDana = vozniRedPodaciBuilder.oznakaDana;
    }

    public String getOznakaPruge() {
        return oznakaPruge;
    }

    public String getSmjer() {
        return smjer;
    }

    public String getPolaznaStanica() {
        return polaznaStanica;
    }

    public String getOdredisnaStanica() {
        return odredisnaStanica;
    }

    public String getOznakaVlaka() {
        return oznakaVlaka;
    }

    public String getVrstaVlaka() {
        return vrstaVlaka;
    }

    public String getVrijemePolaska() {
        return vrijemePolaska;
    }

    public String getTrajanjeVoznje() {
        return trajanjeVoznje;
    }

    public String getOznakaDana() {
        return oznakaDana;
    }

    public void setOznakaPruge(String oznakaPruge) {
        this.oznakaPruge = oznakaPruge;
    }

    public void setSmjer(String smjer) {
        this.smjer = smjer;
    }

    public void setPolaznaStanica(String polaznaStanica) {
        this.polaznaStanica = polaznaStanica;
    }

    public void setOdredisnaStanica(String odredisnaStanica) {
        this.odredisnaStanica = odredisnaStanica;
    }

    public void setOznakaVlaka(String oznakaVlaka) {
        this.oznakaVlaka = oznakaVlaka;
    }

    public void setVrstaVlaka(String vrstaVlaka) {
        this.vrstaVlaka = vrstaVlaka;
    }

    public void setVrijemePolaska(String vrijemePolaska) {
        this.vrijemePolaska = vrijemePolaska;
    }

    public void setTrajanjeVoznje(String trajanjeVoznje) {
        this.trajanjeVoznje = trajanjeVoznje;
    }

    public void setOznakaDana(String oznakaDana) {
        this.oznakaDana = oznakaDana;
    }

    public static class VozniRedPodaciBuilder {
        private String oznakaPruge;
        private String smjer;
        private String polaznaStanica;
        private String odredisnaStanica;
        private String oznakaVlaka;
        private String vrstaVlaka;
        private String vrijemePolaska;
        private String trajanjeVoznje;
        private String oznakaDana;

        public VozniRedPodaciBuilder oznakaPruge(String oznakaPruge) {
            this.oznakaPruge = oznakaPruge;
            return this;
        }

        public VozniRedPodaciBuilder smjer(String smjer) {
            this.smjer = smjer;
            return this;
        }

        public VozniRedPodaciBuilder polaznaStanica(String polaznaStanica) {
            this.polaznaStanica = polaznaStanica.trim();
            return this;
        }

        public VozniRedPodaciBuilder odredisnaStanica(String odredisnaStanica) {
            this.odredisnaStanica = odredisnaStanica.trim();
            return this;
        }

        public VozniRedPodaciBuilder oznakaVlaka(String oznakaVlaka) {
            this.oznakaVlaka = oznakaVlaka.trim();
            return this;
        }

        public VozniRedPodaciBuilder vrstaVlaka(String vrstaVlaka) {
            this.vrstaVlaka = vrstaVlaka.trim();
            return this;
        }

        public VozniRedPodaciBuilder vrijemePolaska(String vrijemePolaska) {
            this.vrijemePolaska = vrijemePolaska.trim();
            return this;
        }

        public VozniRedPodaciBuilder trajanjeVoznje(String trajanjeVoznje) {
            this.trajanjeVoznje = trajanjeVoznje.trim();
            return this;
        }

        public VozniRedPodaciBuilder oznakaDana(String oznakaDana) {
            this.oznakaDana = oznakaDana.trim();
            return this;
        }

        public VozniRedPodaci build() {
            return new VozniRedPodaci(this);
        }
    }

    @Override
    public String toString() {
        return "VozniRedPodaci {" +
                "oznakaPruge='" + oznakaPruge + '\'' +
                ", smjer='" + smjer + '\'' +
                ", polaznaStanica='" + polaznaStanica + '\'' +
                ", odredisnaStanica='" + odredisnaStanica + '\'' +
                ", oznakaVlaka='" + oznakaVlaka + '\'' +
                ", vrstaVlaka='" + vrstaVlaka + '\'' +
                ", vrijemePolaska='" + vrijemePolaska + '\'' +
                ", trajanjeVoznje='" + trajanjeVoznje + '\'' +
                ", oznakaDana='" + oznakaDana + '\'' +
                '}';
    }
}


