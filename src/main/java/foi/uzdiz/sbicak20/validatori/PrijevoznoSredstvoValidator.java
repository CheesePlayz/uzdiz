package foi.uzdiz.sbicak20.validatori;

import java.util.List;
import java.util.regex.Pattern;

import foi.uzdiz.sbicak20.greske.NevaljaniFormatGreska;
import foi.uzdiz.sbicak20.greske.NullVrijednostGreska;
import foi.uzdiz.sbicak20.greske.PrazanStringGreska;
import foi.uzdiz.sbicak20.greske.SustavGresaka;
import foi.uzdiz.sbicak20.modeli.Kompozicija;

public class PrijevoznoSredstvoValidator implements IValidator {

    private Pattern OZNAKA_PATTERN = Pattern.compile("^([A-Za-z0-9ČčĆćĐđŠšŽž\\s-–]+)$");
    private Pattern OPIS_PATTERN = Pattern.compile("^([A-Za-z0-9ČčĆćĐđŠšŽž\\s-–\"„“.…]+)$");
    private Pattern PROIZVODAC_PATTERN = Pattern.compile("^([A-Za-z0-9ČčĆćĐđŠšŽž\\s-–]+)$");
    private Pattern GODINA_PATTERN = Pattern.compile("^([0-2]\\d{3}|3000)$");
    private Pattern NAMJENA_PATTERN = Pattern.compile("^(PSVPVK|PSVP|PSBP)$");
    private Pattern VRSTA_PRIJEVOZA_PATTERN = Pattern.compile("^(N|P|TA|TK|TRS|TTS)$");
    private Pattern VRSTA_POGONA_PATTERN = Pattern.compile("^[DBEN]$");
    private Pattern MAX_BRZINA_PATTERN = Pattern.compile("^([1-9][0-9]?|1[0-9]{2}|200)$");
    private Pattern MAX_SNAGA_PATTERN = Pattern.compile("^(-1|([0-9]|10)(,\\d+)?)$");
    private Pattern BROJ_SJEDECIH_MJESTA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)$");
    private Pattern BROJ_STAJUCIH_MJESTA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)$");
    private Pattern BROJ_BICIKALA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)$");
    private Pattern BROJ_KREVETA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)$");
    private Pattern BROJ_AUTOMOBILA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)$");
    private Pattern NOSIVOST_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)(,\\d+)?$");
    private Pattern POVRSINA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)(,\\d+)?$");
    private Pattern ZAPREMINA_PATTERN = Pattern.compile("^([0-9]{1,3}|1000)(,\\d+)?$");
    private Pattern STATUS_PATTERN = Pattern.compile("^[IK]$");

    @Override
    public boolean Validiraj(String[] redovi, int redakCSV, List<Kompozicija> kompozicije) {
        int redak = 0;
        if (redovi.length != 18) {
            SustavGresaka.getInstance().prijaviGresku(new NevaljaniFormatGreska("Nema dovoljno podataka u redu."), SustavGresaka.getInstance().getPodrucjaGresaka().get(1));
            return false;
        }
        try {
            if (!OZNAKA_PATTERN.matcher(redovi[0].trim()).matches()) {
                if (redovi[0].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za OZNAKA: očekuje se kombinacija slova, brojeva, ili znakova '-–'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za OZNAKA: očekuje se kombinacija slova, brojeva, ili znakova '-–'.");
            }
            if (!OPIS_PATTERN.matcher(redovi[1].trim()).matches()) {
                redak = 1;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za OPIS: dozvoljeni znakovi su slova, brojevi, i neki specijalni znakovi.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za OPIS: dozvoljeni znakovi su slova, brojevi, i neki specijalni znakovi.");
            }
            if (!PROIZVODAC_PATTERN.matcher(redovi[2].trim()).matches()) {
                redak = 2;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za PROIZVODAC: dozvoljeni znakovi su slova, brojevi i '-–'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za PROIZVODAC: dozvoljeni znakovi su slova, brojevi i '-–'.");
            }
            if (!GODINA_PATTERN.matcher(redovi[3].trim()).matches()) {
                redak = 3;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za GODINA: očekuje se godina između 0000 i 3000.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za GODINA: očekuje se godina između 0000 i 3000.");
            }
            if (!NAMJENA_PATTERN.matcher(redovi[4].trim()).matches()) {
                redak = 4;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za NAMJENA: očekuje se 'PSVPVK', 'PSVP', ili 'PSBP'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za NAMJENA: očekuje se 'PSVPVK', 'PSVP', ili 'PSBP'.");
            }
            if (!VRSTA_PRIJEVOZA_PATTERN.matcher(redovi[5].trim()).matches()) {
                redak = 5;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za VRSTA_PRIJEVOZA: očekuje se jedan od 'N', 'P', 'TA', 'TK', 'TRS', ili 'TTS'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za VRSTA_PRIJEVOZA: očekuje se jedan od 'N', 'P', 'TA', 'TK', 'TRS', ili 'TTS'.");
            }
            if (!VRSTA_POGONA_PATTERN.matcher(redovi[6].trim()).matches()) {
                redak = 6;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za VRSTA_POGONA: očekuje se 'D', 'B', 'E', ili 'N'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za VRSTA_POGONA: očekuje se 'D', 'B', 'E', ili 'N'.");
            }
            if (!MAX_BRZINA_PATTERN.matcher(redovi[7].trim()).matches()) {
                redak = 7;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za MAX_BRZINA: čekuje se broj od 1 do 200.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za MAX_BRZINA: očekuje se broj od 1 do 200.");
            }
            if (!MAX_SNAGA_PATTERN.matcher(redovi[8].trim()).matches()) {
                redak = 8;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za MAX_SNAGA: očekuje se broj od -1 do 10 s opcionalnim decimalama.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za MAX_SNAGA: očekuje se broj od -1 do 10 s opcionalnim decimalama.");
            }
            if (!BROJ_SJEDECIH_MJESTA_PATTERN.matcher(redovi[9].trim()).matches()) {
                redak = 9;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za BROJ_SJEDECIH_MJESTA: očekuje se broj od 0 do 1000.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za BROJ_SJEDECIH_MJESTA: očekuje se broj od 0 do 1000.");
            }
            if (!BROJ_STAJUCIH_MJESTA_PATTERN.matcher(redovi[10].trim()).matches()) {
                redak = 10;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za BROJ_STAJUCIH_MJESTA: očekuje se broj od 0 do 1000.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za BROJ_STAJUCIH_MJESTA: očekuje se broj od 0 do 1000.");
            }
            if (!BROJ_BICIKALA_PATTERN.matcher(redovi[11].trim()).matches()) {
                redak = 11;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za BROJ_BICIKALA: očekuje se broj od 0 do 1000.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za BROJ_BICIKALA: očekuje se broj od 0 do 1000.");
            }
            if (!BROJ_KREVETA_PATTERN.matcher(redovi[12].trim()).matches()) {
                redak = 12;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za BROJ_KREVETA: očekuje se broj od 0 do 1000.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za BROJ_KREVETA: očekuje se broj od 0 do 1000.");
            }
            if (!BROJ_AUTOMOBILA_PATTERN.matcher(redovi[13].trim()).matches()) {
                redak = 13;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za BROJ_AUTOMOBILA: očekuje se broj od 0 do 1000.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za BROJ_AUTOMOBILA: očekuje se broj od 0 do 1000.");
            }
            if (!NOSIVOST_PATTERN.matcher(redovi[14].trim()).matches()) {
                redak = 14;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za NOSIVOST: očekuje se broj od 0 do 1000 s opcionalnim decimalama.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za NOSIVOST: očekuje se broj od 0 do 1000 s opcionalnim decimalama.");
            }
            if (!POVRSINA_PATTERN.matcher(redovi[15].trim()).matches()) {
                redak = 15;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za POVRSINA: očekuje se broj od 0 do 1000 s opcionalnim decimalama.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za POVRSINA: očekuje se broj od 0 do 1000 s opcionalnim decimalama.");
            }
            if (!ZAPREMINA_PATTERN.matcher(redovi[16].trim()).matches()) {
                redak = 16;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za ZAPREMINA: očekuje se broj od 0 do 1000 s opcionalnim decimalama.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za ZAPREMINA: očekuje se broj od 0 do 1000 s opcionalnim decimalama.");
            }
            if (!STATUS_PATTERN.matcher(redovi[17].trim()).matches()) {
                redak = 17;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za STATUS: očekuje se 'I' ili 'K'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za STATUS: očekuje se 'I' ili 'K'.");
            }
        } catch (Exception e) {
            SustavGresaka.getInstance().prijaviGresku(e, SustavGresaka.getInstance().getPodrucjaGresaka().get(1), new String[]{"CSV redak: " + redakCSV, "Trenutni zapis: " + redovi[redak].trim()});
            return false;
        }

        return true;
    }
}

