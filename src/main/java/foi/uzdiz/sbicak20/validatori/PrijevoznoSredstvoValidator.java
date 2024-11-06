package foi.uzdiz.sbicak20.validatori;

import java.util.regex.Pattern;

public class PrijevoznoSredstvoValidator implements IValidator {

    private   Pattern OZNAKA_PATTERN = Pattern.compile("^([A-Za-z0-9ČčĆćĐđŠšŽž\\s-–]+)$");
    private   Pattern OPIS_PATTERN = Pattern.compile("^([A-Za-z0-9ČčĆćĐđŠšŽž\\s-–\"„“.…]+)$");
    private   Pattern PROIZVODAC_PATTERN = Pattern.compile("^([A-Za-z0-9ČčĆćĐđŠšŽž\\s-–]+)$");
    private   Pattern GODINA_PATTERN = Pattern.compile("^([0-2]\\d{3}|3000)$");
    private   Pattern NAMJENA_PATTERN = Pattern.compile("^(PSVPVK|PSVP|PSBP)$");
    private   Pattern VRSTA_PRIJEVOZA_PATTERN = Pattern.compile("^(N|P|TA|TK|TRS|TTS)$");
    private   Pattern VRSTA_POGONA_PATTERN = Pattern.compile("^[DBEN]$");
    private   Pattern MAX_BRZINA_PATTERN = Pattern.compile("^([1-9][0-9]?|1[0-9]{2}|200)$");
    private   Pattern MAX_SNAGA_PATTERN = Pattern.compile("^(-1|([0-9]|10)(,\\d+)?)$");
    private   Pattern BROJ_SJEDECIH_MJESTA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)$");
    private   Pattern BROJ_STAJUCIH_MJESTA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)$");
    private   Pattern BROJ_BICIKALA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)$");
    private   Pattern BROJ_KREVETA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)$");
    private   Pattern BROJ_AUTOMOBILA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)$");
    private   Pattern NOSIVOST_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)(,\\d+)?$");
    private   Pattern POVRSINA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)(,\\d+)?$");
    private   Pattern ZAPREMINA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)(,\\d+)?$");
    private   Pattern STATUS_PATTERN = Pattern.compile("^[IK]$");

    @Override
    public boolean Validiraj(String[] redovi) {
            if (!OZNAKA_PATTERN.matcher(redovi[0].trim()).matches()) return false;
            if (!OPIS_PATTERN.matcher(redovi[1].trim()).matches()) return false;
            if (!PROIZVODAC_PATTERN.matcher(redovi[2].trim()).matches()) return false;
            if (!GODINA_PATTERN.matcher(redovi[3].trim()).matches()) return false;
            if (!NAMJENA_PATTERN.matcher(redovi[4].trim()).matches()) return false;
            if (!VRSTA_PRIJEVOZA_PATTERN.matcher(redovi[5].trim()).matches()) return false;
            if (!VRSTA_POGONA_PATTERN.matcher(redovi[6].trim()).matches()) return false;
            if (!MAX_BRZINA_PATTERN.matcher(redovi[7].trim()).matches()) return false;
            if (!MAX_SNAGA_PATTERN.matcher(redovi[8].trim()).matches()) return false;
            if (!BROJ_SJEDECIH_MJESTA_PATTERN.matcher(redovi[9].trim()).matches()) return false;
            if (!BROJ_STAJUCIH_MJESTA_PATTERN.matcher(redovi[10].trim()).matches()) return false;
            if (!BROJ_BICIKALA_PATTERN.matcher(redovi[11].trim()).matches()) return false;
            if (!BROJ_KREVETA_PATTERN.matcher(redovi[12].trim()).matches()) return false;
            if (!BROJ_AUTOMOBILA_PATTERN.matcher(redovi[13].trim()).matches()) return false;
            if (!NOSIVOST_PATTERN.matcher(redovi[14].trim()).matches()) return false;
            if (!POVRSINA_PATTERN.matcher(redovi[15].trim()).matches()) return false;
            if (!ZAPREMINA_PATTERN.matcher(redovi[16].trim()).matches()) return false;
            if (!STATUS_PATTERN.matcher(redovi[17].trim()).matches()) return false;
            return true;
    }
}
