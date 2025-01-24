package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.modeli.composite.Etapa;
import foi.uzdiz.sbicak20.modeli.composite.VozniRed;
import foi.uzdiz.sbicak20.modeli.composite.VozniRedKomponenta;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IEVD implements Komanda {

    private final VozniRed vozniRed;
    private final String dani;

    public IEVD(VozniRed vozniRed, String dani) {
        this.vozniRed = vozniRed;
        this.dani = dani;
    }

    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        int[] maxDuljine = {12, 12, 23, 23, 15, 15, 15};
        StringBuilder formatBuilder = new StringBuilder("|");
        for (int duljina : maxDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        String[] oznakeDana = {"Po", "U", "Sr", "Č", "Pe", "Su", "N"};

        if (!dani.matches("(Po|U|Sr|Č|Pe|Su|N)+")) {
            StringBuilder builder = new StringBuilder();
            builder.append("Dozvoljene su kombinacije samo ovih oznaka: ");
            for (String oznakaDana : oznakeDana) {
                builder.append(oznakaDana).append(" ");
            }
            System.out.println(builder);
            return;
        }
        System.out.printf(format, "Oznaka Vlaka", "Oznaka Pruge", "Polazna Stanica",
                "Odredišna Stanica", "Vrijeme Polaska", "Vrijeme Dolaska", "Dani u Tjednu");

        List<String> trazeniDani = new ArrayList<>();
        int i = 0;
        while (i < dani.length()) {
            boolean pronadenDan = false;
            for (String oznakaDana : oznakeDana) {
                if (dani.startsWith(oznakaDana, i)) {
                    trazeniDani.add(oznakaDana);
                    i += oznakaDana.length();
                    pronadenDan = true;
                    break;
                }
            }
            if (!pronadenDan) {
                i++;
            }
        }

        vozniRed.getDjeca().sort(Comparator.comparing(vlak -> {
            Etapa prvaEtapa = (Etapa) vlak.getDjeca().get(0).dohvatiObjekt();
            return prvaEtapa.getVrijemePolaska();
        }));

        for (VozniRedKomponenta vlak : vozniRed.getDjeca()) {
            List<VozniRedKomponenta> etapeVoznogReda = vlak.getDjeca();

            boolean sveEtapeIspunjavajuUvjet = true;

            for (VozniRedKomponenta etapaKomponenta : etapeVoznogReda) {
                Etapa etapa = (Etapa) etapaKomponenta.dohvatiObjekt();
                List<String> daniEtape = new ArrayList<>();
                int j = 0;
                while (j < etapa.getDaniUTjednu().length()) {
                    boolean pronadenDan = false;
                    for (String oznakaDana : oznakeDana) {
                        if (etapa.getDaniUTjednu().startsWith(oznakaDana, j)) {
                            daniEtape.add(oznakaDana);
                            j += oznakaDana.length();
                            pronadenDan = true;
                            break;
                        }
                    }
                    if (!pronadenDan) {
                        j++;
                    }
                }

                boolean sviDaniPronađeni = true;
                for (String trazenDan : trazeniDani) {
                    if (!daniEtape.contains(trazenDan)) {
                        sviDaniPronađeni = false;
                        break;
                    }
                }

                if (!sviDaniPronađeni) {
                    sveEtapeIspunjavajuUvjet = false;
                    break;
                }
            }

            if (sveEtapeIspunjavajuUvjet) {
                int brojac = 0;
                for (VozniRedKomponenta etapaKomponenta : etapeVoznogReda) {
                    brojac++;
                    Etapa etapa = (Etapa) etapaKomponenta.dohvatiObjekt();

                    String polaznaStanica = etapa.getDjeca().get(0).toString();
                    String odredisnaStanica = etapa.getDjeca().get(etapa.getDjeca().size() - 1).toString();
                    String vrijemePolaska = etapa.getVrijemePolaska().toString();
                    String vrijemeDolaska = etapa.getVrijemeDolaska().toString();
                    String daniUTjednu = etapa.getDaniUTjednu();
                    if (brojac == 1) {
                        System.out.printf(format, etapa.getOznakaVlaka(), etapa.getOznakaPruge(), polaznaStanica,
                                odredisnaStanica, vrijemePolaska, vrijemeDolaska, daniUTjednu);
                    } else if (brojac > 1) {
                        System.out.printf(format, "#" + brojac + "------->", etapa.getOznakaPruge(), polaznaStanica,
                                odredisnaStanica, vrijemePolaska, vrijemeDolaska, daniUTjednu);
                    }
                }
            }
        }
    }
}
