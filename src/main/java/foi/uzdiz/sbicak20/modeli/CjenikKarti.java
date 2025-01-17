package foi.uzdiz.sbicak20.modeli;

public class CjenikKarti {

    private double cijenaNormalni;
    private double cijenaUbrzani;
    private double cijenaBrzi;
    private double popustSuN;
    private double popustWebMob;
    private double uvecanjeVlak;

    private CjenikKarti(CjenikKartiBuilder builder) {
        this.cijenaNormalni = builder.cijenaNormalni;
        this.cijenaUbrzani = builder.cijenaUbrzani;
        this.cijenaBrzi = builder.cijenaBrzi;
        this.popustSuN = builder.popustSuN;
        this.popustWebMob = builder.popustWebMob;
        this.uvecanjeVlak = builder.uvecanjeVlak;
    }

    public double getCijenaNormalni() {
        return cijenaNormalni;
    }

    public double getCijenaUbrzani() {
        return cijenaUbrzani;
    }

    public double getCijenaBrzi() {
        return cijenaBrzi;
    }

    public double getPopustSuN() {
        return popustSuN;
    }

    public double getPopustWebMob() {
        return popustWebMob;
    }

    public double getUvecanjeVlak() {
        return uvecanjeVlak;
    }
    public void ispisiCjenik() {
        System.out.println("Cjenik karata:");
        System.out.println("Cijena normalnog vlaka: " + cijenaNormalni + " €/km");
        System.out.println("Cijena ubrzanog vlaka: " + cijenaUbrzani + " €/km");
        System.out.println("Cijena brzog vlaka: " + cijenaBrzi + " €/km");
        System.out.println("Popust za subotu i nedjelju: " + popustSuN + "%");
        System.out.println("Popust za kupovinu putem web/mobilne aplikacije: " + popustWebMob + "%");
        System.out.println("Uvećanje za kupovinu karte u vlaku: " + uvecanjeVlak + "%");
        System.out.println();
    }

    public static class CjenikKartiBuilder {
        private double cijenaNormalni;
        private double cijenaUbrzani;
        private double cijenaBrzi;
        private double popustSuN;
        private double popustWebMob;
        private double uvecanjeVlak;

        public CjenikKartiBuilder setCijenaNormalni(double cijenaNormalni) {
            this.cijenaNormalni = cijenaNormalni;
            return this;
        }

        public CjenikKartiBuilder setCijenaUbrzani(double cijenaUbrzani) {
            this.cijenaUbrzani = cijenaUbrzani;
            return this;
        }

        public CjenikKartiBuilder setCijenaBrzi(double cijenaBrzi) {
            this.cijenaBrzi = cijenaBrzi;
            return this;
        }

        public CjenikKartiBuilder setPopustSuN(double popustSuN) {
            this.popustSuN = popustSuN;
            return this;
        }

        public CjenikKartiBuilder setPopustWebMob(double popustWebMob) {
            this.popustWebMob = popustWebMob;
            return this;
        }

        public CjenikKartiBuilder setUvecanjeVlak(double uvecanjeVlak) {
            this.uvecanjeVlak = uvecanjeVlak;
            return this;
        }

        public CjenikKarti build() {
            return new CjenikKarti(this);
        }
    }
}

