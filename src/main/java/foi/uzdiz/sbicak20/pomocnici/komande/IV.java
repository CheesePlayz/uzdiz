package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.modeli.composite.Etapa;
import foi.uzdiz.sbicak20.modeli.composite.VozniRed;
import foi.uzdiz.sbicak20.modeli.composite.VozniRedKomponenta;

import java.util.Comparator;
import java.util.List;

public class IV implements Komanda{

    private VozniRed vozniRed;

    public IV(VozniRed vozniRed){
        this.vozniRed = vozniRed;
    }
    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        int[] maxDuljine = {12, 20, 20, 20, 15, 15};
        StringBuilder formatBuilder = new StringBuilder("|");
        for (int duljina : maxDuljine) {
            formatBuilder.append(" %-").append(duljina).append("s |");
        }
        formatBuilder.append("%n");
        String format = formatBuilder.toString();

        System.out.printf(format, "Oznaka Vlaka", "Polazna Stanica", "Odredišna Stanica",
                "Vrijeme Polaska", "Vrijeme Dolaska", "Ukupna Kilometraža");

        vozniRed.getDjeca().sort(Comparator.comparing(vlak -> ((Etapa) vlak.getDjeca().get(0).dohvatiObjekt()).getOznakaVlaka()));

        for (VozniRedKomponenta vlak : vozniRed.getDjeca()) {
            List<VozniRedKomponenta> etapeVoznogReda = vlak.getDjeca();

            if (!etapeVoznogReda.isEmpty()) {
                Etapa prvaEtapa = (Etapa) etapeVoznogReda.get(0).dohvatiObjekt();
                Etapa zadnjaEtapa = (Etapa) etapeVoznogReda.get(etapeVoznogReda.size() - 1).dohvatiObjekt();

                String polaznaStanica = prvaEtapa.getDjeca().get(0).toString();
                String odredisnaStanica = zadnjaEtapa.getDjeca().getLast().toString();

                String vrijemePolaska = prvaEtapa.getVrijemePolaska().toString();
                String vrijemeDolaska = zadnjaEtapa.getVrijemeDolaska().toString();

                double ukupnaKilometraza = 0;
                for (VozniRedKomponenta etapa : etapeVoznogReda) {
                    Etapa et = (Etapa) etapa.dohvatiObjekt();
                    ukupnaKilometraza += et.getUkupnaKilometraza();
                }

                System.out.printf(format, prvaEtapa.getOznakaVlaka(), polaznaStanica, odredisnaStanica,
                        vrijemePolaska, vrijemeDolaska, String.format("%.2f km", ukupnaKilometraza));
            }
        }
    }
}
