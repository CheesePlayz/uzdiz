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
        System.out.printf("%-50s | %1s%n", "Cjenik karata", "Vrijednosti");
        System.out.printf("%-50s | %1.2f €/km%n", "Cijena normalnog vlaka:", cijenaNormalni);
        System.out.printf("%-50s | %1.2f €/km%n", "Cijena ubrzanog vlaka:", cijenaUbrzani);
        System.out.printf("%-50s | %1.2f €/km%n", "Cijena brzog vlaka:", cijenaBrzi);
        System.out.printf("%-50s | %1.2f%%%n", "Popust za subotu i nedjelju:", popustSuN);
        System.out.printf("%-50s | %1.2f%%%n", "Popust za kupovinu putem web/mobilne aplikacije:", popustWebMob);
        System.out.printf("%-50s | %1.2f%%%n", "Uvećanje za kupovinu karte u vlaku:", uvecanjeVlak);
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

