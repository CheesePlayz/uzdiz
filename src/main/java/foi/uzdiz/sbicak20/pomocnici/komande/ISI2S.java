package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;
import java.util.*;

public class ISI2S implements Komanda {
    private final String oznakaStanicePolazna;
    private final String oznakaStaniceOdredisna;
    private final List<ZeljeznickaStanica> stanice;

    public ISI2S(List<ZeljeznickaStanica> stanice, String oznakaStaniceStart, String oznakaStaniceEnd) {
        this.stanice = stanice;
        this.oznakaStanicePolazna = oznakaStaniceStart;
        this.oznakaStaniceOdredisna = oznakaStaniceEnd;
    }

    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        Map<String, List<ZeljeznickaStanica>> pruge = new HashMap<>();
        for (ZeljeznickaStanica s : stanice) {
            pruge.putIfAbsent(s.getOznakaPruge(), new ArrayList<>());
            pruge.get(s.getOznakaPruge()).add(s);
        }
        Map<String, Map<String, Integer>> grafTezine = new HashMap<>();
        for (String p : pruge.keySet()) {
            List<ZeljeznickaStanica> lista = pruge.get(p);
            for (int i = 0; i < lista.size(); i++) {
                ZeljeznickaStanica s = lista.get(i);
                grafTezine.putIfAbsent(s.getStanica(), new HashMap<>());
                if (i < lista.size() - 1) {
                    ZeljeznickaStanica sn = lista.get(i + 1);
                    grafTezine.putIfAbsent(sn.getStanica(), new HashMap<>());
                    int udalj = sn.getDuzina();
                    grafTezine.get(s.getStanica()).put(sn.getStanica(), udalj);
                    grafTezine.get(sn.getStanica()).put(s.getStanica(), udalj);
                }
            }
        }
        List<String> put = bfs(grafTezine, oznakaStanicePolazna, oznakaStaniceOdredisna);
        if (put == null) {
            System.out.println("Nije moguće pronaći put između " + oznakaStanicePolazna + " i " + oznakaStaniceOdredisna);
        } else {
            double ukupnaKm = 0;
            System.out.printf("| %-20s | %-20s%n", "Stanica", "Ukupna Kilometraža");
            for (int i = 0; i < put.size(); i++) {
                if (i > 0) {
                    String prethodna = put.get(i - 1);
                    String trenutna = put.get(i);
                    Integer dist = grafTezine.getOrDefault(prethodna, Collections.emptyMap()).get(trenutna);
                    if (dist != null) {
                        ukupnaKm += dist;
                    }
                }
                System.out.printf("| %-20s | %1s%n", put.get(i), ukupnaKm);
            }
        }
    }

    private List<String> bfs(Map<String, Map<String, Integer>> graf, String start, String cilj) {
        Queue<List<String>> red = new LinkedList<>();
        Set<String> posjecene = new HashSet<>();
        List<String> pocetniPut = new ArrayList<>();
        pocetniPut.add(start);
        red.add(pocetniPut);
        posjecene.add(start);
        while (!red.isEmpty()) {
            List<String> trenutniPut = red.poll();
            String zadnja = trenutniPut.get(trenutniPut.size() - 1);
            if (zadnja.equals(cilj)) {
                return trenutniPut;
            }
            for (Map.Entry<String, Integer> e : graf.getOrDefault(zadnja, Collections.emptyMap()).entrySet()) {
                if (!posjecene.contains(e.getKey())) {
                    List<String> noviPut = new ArrayList<>(trenutniPut);
                    noviPut.add(e.getKey());
                    red.add(noviPut);
                    posjecene.add(e.getKey());
                }
            }
        }
        return null;
    }
}
