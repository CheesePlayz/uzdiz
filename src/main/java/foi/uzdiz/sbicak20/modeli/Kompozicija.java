package foi.uzdiz.sbicak20.modeli;

import foi.uzdiz.sbicak20.enumeracije.KompozicijeEnum.KUlogeEnum;

public class Kompozicija {
    private final String oznaka;
    private final String oznakaPrijevoznogSredstva;
    private final KUlogeEnum uloga;

    public Kompozicija(String oznaka, String oznakaPrijevoznogSredstva, String uloga) {
        this.oznaka = oznaka;
        this.oznakaPrijevoznogSredstva = oznakaPrijevoznogSredstva;
        this.uloga = KUlogeEnum.valueOf(uloga);
    }

    public String getOznaka() {
        return oznaka;
    }

    public String getOznakaPrijevoznogSredstva() {
        return oznakaPrijevoznogSredstva;
    }

    public KUlogeEnum getUloga() {
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
