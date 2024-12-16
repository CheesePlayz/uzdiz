package foi.uzdiz.sbicak20.validatori;

import foi.uzdiz.sbicak20.greske.PrazanStringGreska;
import foi.uzdiz.sbicak20.greske.SustavGresaka;
import foi.uzdiz.sbicak20.greske.NevaljaniFormatGreska;
import foi.uzdiz.sbicak20.modeli.Kompozicija;

import java.util.List;
import java.util.regex.Pattern;

public class  StanicaValidator implements IValidator {
    private Pattern STANICA_PATTERN = Pattern.compile("^([A-Za-z0-9ČčĆćĐđŠšŽž\\s-–]+)$");
    private Pattern PRUGA_PATTERN = Pattern.compile("^([A-Za-z0-9ČčĆćĐđŠšŽž]+)$");
    private Pattern VRSTA_STANICE_PATTERN = Pattern.compile("^(kol\\.|staj\\.)$");
    private Pattern STATUS_STANICE_PATTERN = Pattern.compile("^[OK]$");
    private Pattern PUTNICI_PATTERN = Pattern.compile("^(DA|NE)$");
    private Pattern ROBA_PATTERN = Pattern.compile("^(DA|NE)$");
    private Pattern KATEGORIJA_PATTERN = Pattern.compile("^[MLR]$");
    private Pattern BROJ_PERONA_PATTERN = Pattern.compile("^([1-9]|[1-9]\\d)$");
    private Pattern VRSTA_PRUGE_PATTERN = Pattern.compile("^[KE]$");
    private Pattern BROJ_KOLOSIJEKA_PATTERN = Pattern.compile("^[12]$");
    private Pattern DO_PO_OSOVINI_PATTERN = Pattern.compile("^(1[0-9]|[2-4][0-9]|50)(?:,[0-9])?$");
    private Pattern DO_PO_DUZINI_PATTERN = Pattern.compile("^(?:[2-9](?:,[0-9])?|10(?:,[0-9])?)$");
    private Pattern STATUS_PRUGE_PATTERN = Pattern.compile("^[IK]$");
    private Pattern DUZINA_PATTERN = Pattern.compile("^(?:[1-9]?[0-9]{0,2}|0)$");
    private Pattern VRIJEME_NORMALNI_VLAK = Pattern.compile("^([0-9]|[0-9]\\d)");
    private Pattern VRIJEME_UBRZANI_VLAK = Pattern.compile("^([0-9]|[0-9]\\d)");
    private Pattern VRIJEME_BRZI_VLAK = Pattern.compile("^([0-9]|[0-9]\\d)");

    @Override
    public boolean Validiraj(String[] redovi, int redakCSV, List<Kompozicija> kompzicije) {
        int redak = 0;
        try {
            if (!STANICA_PATTERN.matcher(redovi[0].trim()).matches()) {
                if (redovi[0].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za STANICA: očekuje se kombinacija slova, brojeva, ili znakova '-–'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za STANICA: očekuje se kombinacija slova, brojeva, ili znakova '-–'.");
            }
            if (!PRUGA_PATTERN.matcher(redovi[1].trim()).matches()) {
                redak = 1;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za PRUGA: dozvoljeni znakovi su slova, brojevi.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za PRUGA: dozvoljeni znakovi su slova, brojevi.");
            }
            if (!VRSTA_STANICE_PATTERN.matcher(redovi[2].trim()).matches()) {
                redak = 2;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za VRSTA_STANICE: očekuje se 'kol.' ili 'staj.'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za VRSTA_STANICE: očekuje se 'kol.' ili 'staj.'.");
            }
            if (!STATUS_STANICE_PATTERN.matcher(redovi[3].trim()).matches()) {
                redak = 3;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za STATUS_STANICE: očekuje se 'O' ili 'K'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za STATUS_STANICE: očekuje se 'O' ili 'K'.");
            }
            if (!PUTNICI_PATTERN.matcher(redovi[4].trim()).matches()) {
                redak = 4;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za PUTNICI: očekuje se 'DA' ili 'NE'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za PUTNICI: očekuje se 'DA' ili 'NE'.");
            }
            if (!ROBA_PATTERN.matcher(redovi[5].trim()).matches()) {
                redak = 5;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za ROBA: očekuje se 'DA' ili 'NE'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za ROBA: očekuje se 'DA' ili 'NE'.");
            }
            if (!KATEGORIJA_PATTERN.matcher(redovi[6].trim()).matches()) {
                redak = 6;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za KATEGORIJA: očekuje se 'M', 'L' ili 'R'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za KATEGORIJA: očekuje se 'M', 'L' ili 'R'.");
            }
            if (!BROJ_PERONA_PATTERN.matcher(redovi[7].trim()).matches()) {
                redak = 7;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za BROJ_PERONA: očekuje se broj od 1 do 99.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za BROJ_PERONA: očekuje se broj od 1 do 99.");
            }
            if (!VRSTA_PRUGE_PATTERN.matcher(redovi[8].trim()).matches()) {
                redak = 8;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za VRSTA_PRUGE: očekuje se 'K' ili 'E'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za VRSTA_PRUGE: očekuje se 'K' ili 'E'.");
            }
            if (!BROJ_KOLOSIJEKA_PATTERN.matcher(redovi[9].trim()).matches()) {
                redak = 9;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za BROJ_KOLOSIJEKA: očekuje se 1 ili 2.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za BROJ_KOLOSIJEKA: očekuje se 1 ili 2.");
            }
            if (!DO_PO_OSOVINI_PATTERN.matcher(redovi[10].trim()).matches()) {
                redak = 10;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za DO_PO_OSOVINI: očekuje se decimalna vrijednost između 10 i 50.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za DO_PO_OSOVINI: očekuje se decimalna vrijednost između 10 i 50.");
            }
            if (!DO_PO_DUZINI_PATTERN.matcher(redovi[11].trim()).matches()) {
                redak = 11;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za DO_PO_DUZINI: očekuje se vrijednost između 2 i 10.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za DO_PO_DUZINI: očekuje se vrijednost između 2 i 10.");
            }
            if (!STATUS_PRUGE_PATTERN.matcher(redovi[12].trim()).matches()) {
                redak = 12;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za STATUS_PRUGE: očekuje se 'I' ili 'K'.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za STATUS_PRUGE: očekuje se 'I' ili 'K'.");
            }
            if (!DUZINA_PATTERN.matcher(redovi[13].trim()).matches()) {
                redak = 13;
                if (redovi[redak].trim().isEmpty()) {
                    throw new PrazanStringGreska("Prazan format za DUZINA: očekuje se broj između 0 i 999.");
                }
                throw new NevaljaniFormatGreska("Neispravan format za DUZINA: očekuje se broj između 0 i 999.");
            }


            if (redovi != null && redovi.length > 14) {
                if (redovi[14] != null) {
                    if (!VRIJEME_NORMALNI_VLAK.matcher(redovi[14].trim()).matches()) {
                        redak = 14;
                        if (!redovi[redak].trim().isEmpty()) {
                            throw new NevaljaniFormatGreska("Neispravan format za VRIJEME_NORMALNI_VLAK: očekuje se broj između 0 i 99.");
                        }
                    }
                }
            }

            if (redovi != null && redovi.length > 15) {
                if (redovi[15] != null) {
                    if (!VRIJEME_UBRZANI_VLAK.matcher(redovi[15].trim()).matches()) {
                        redak = 15;
                        if (!redovi[redak].trim().isEmpty()) {
                            throw new NevaljaniFormatGreska("Neispravan format za VRIJEME_UBRZANI_VLAK: očekuje se broj između 0 i 99.");
                        }
                    }
                }
            }

            if (redovi != null && redovi.length > 16) {
                if (redovi[16] != null) {
                    if (!VRIJEME_BRZI_VLAK.matcher(redovi[16].trim()).matches()) {
                        redak = 16;
                        if (!redovi[redak].trim().isEmpty()) {
                            throw new NevaljaniFormatGreska("Neispravan format za VRIJEME_BRZI_VLAK: očekuje se broj između 0 i 99.");
                        }
                    }
                }
            }

        } catch (Exception e) {
            SustavGresaka.getInstance().prijaviGresku(e, SustavGresaka.getInstance().getPodrucjaGresaka().getFirst(), new String[]{"CSV redak: " + redakCSV, "Trenutni zapis: " + redovi[redak].trim()});
            return false;
        }
        return true;
    }
}

