package foi.uzdiz.sbicak20.validatori;

import java.util.regex.Pattern;
public class StanicaValidator implements IValidator {
    private Pattern VRSTA_STANICE_PATTERN = Pattern.compile("^(kol\\.|staj\\.)$");
    private Pattern STATUS_STANICE_PATTERN = Pattern.compile("^[OK]$");
    private Pattern PUTNICI_PATTERN = Pattern.compile("^(DA|NE)$");
    private Pattern ROBA_PATTERN = Pattern.compile("^(DA|NE)$");
    private Pattern KATEGORIJA_PATTERN = Pattern.compile("^[MLR]$");
    private Pattern BROJ_PERONA_PATTERN = Pattern.compile("^([1-9]|[1-9]\\d)$");  // Broj u rasponu od 1 do 99
    private Pattern VRSTA_PRUGE_PATTERN = Pattern.compile("^[KE]$");
    private Pattern BROJ_KOLOSIJEKA_PATTERN = Pattern.compile("^[12]$");  // Samo 1 ili 2
    private Pattern DO_PO_OSOVINI_PATTERN = Pattern.compile("^(1[0-9]|[2-4][0-9]|50)(?:,[0-9])?$");  // Decimale 10 do 50, npr. 22,5
    private Pattern DO_PO_DUZINI_PATTERN = Pattern.compile("^(?:[2-9](?:,[0-9])?|10(?:,[0-9])?)$");  // Od 2 do 10
    private Pattern STATUS_PRUGE_PATTERN = Pattern.compile("^[IK]$");
     private Pattern DUZINA_PATTERN = Pattern.compile("^(?:[1-9]?[0-9]{0,2}|0)$");  // 0 do 999

    @Override
    public boolean Validiraj(String[] redovi) {
        if (!VRSTA_STANICE_PATTERN.matcher(redovi[2].trim()).matches()) return false;
        if (!STATUS_STANICE_PATTERN.matcher(redovi[3].trim()).matches()) return false;
        if (!PUTNICI_PATTERN.matcher(redovi[4].trim()).matches()) return false;
        if (!ROBA_PATTERN.matcher(redovi[5].trim()).matches()) return false;
        if (!KATEGORIJA_PATTERN.matcher(redovi[6].trim()).matches()) return false;
        if (!BROJ_PERONA_PATTERN.matcher(redovi[7].trim()).matches()) return false;
        if (!VRSTA_PRUGE_PATTERN.matcher(redovi[8].trim()).matches()) return false;
        if (!BROJ_KOLOSIJEKA_PATTERN.matcher(redovi[9].trim()).matches()) return false;
        if (!DO_PO_OSOVINI_PATTERN.matcher(redovi[10].trim()).matches()) return false;
        if (!DO_PO_DUZINI_PATTERN.matcher(redovi[11].trim()).matches()) return false;
        if (!STATUS_PRUGE_PATTERN.matcher(redovi[12].trim()).matches()) return false;
        if (!DUZINA_PATTERN.matcher(redovi[13].trim()).matches()) return false;

        return true;
    }
}

