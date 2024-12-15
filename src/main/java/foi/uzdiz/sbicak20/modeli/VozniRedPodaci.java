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
}


