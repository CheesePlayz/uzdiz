package foi.uzdiz.sbicak20.modeli;

public class Kompozicija {
    private String oznaka;
    private String oznakaPrijevoznogSredstva;
    private String uloga;

    public Kompozicija(String oznaka, String oznakaPrijevoznogSredstva, String uloga) {
        this.oznaka = oznaka;
        this.oznakaPrijevoznogSredstva = oznakaPrijevoznogSredstva;
        this.uloga = uloga;
    }

    public String getOznaka() {
        return oznaka;
    }

    public String getOznakaPrijevoznogSredstva() {
        return oznakaPrijevoznogSredstva;
    }

    public String getUloga() {
        return uloga;
    }

    @Override
    public String toString() {
        return "Kompozicija{" +
                "oznaka='" + oznaka + '\'' +
                ", oznakaPrijevoznogSredstva='" + oznakaPrijevoznogSredstva + '\'' +
                ", uloga='" + uloga + '\'' +
                '}';
    }
}
