package foi.uzdiz.sbicak20.enumeracije.ZeljeznickeStaniceEnum;

public enum ZSVrstaStaniceEnum {
    KOL("kol."),
    STAJ("staj.");

    private String oznaka;

    ZSVrstaStaniceEnum(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getLabel() {
        return oznaka;
    }

    public static ZSVrstaStaniceEnum fromLabel(String oznaka) {
        for (ZSVrstaStaniceEnum vrsta : values()) {
            if (vrsta.getLabel().equals(oznaka)) {
                return vrsta;
            }
        }
        return null;
    }
}
