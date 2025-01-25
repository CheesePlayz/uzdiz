package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;
import java.util.*;

public class ISI2Spomocnik {

    public static double izracunajUdaljenost(
                                             String pocetnaStanica,
                                             String odredisnaStanica) {
        Map<String, List<ZeljeznickaStanica>> pruge = new HashMap<>();
        assert ZeljeznickiSustavSingleton.getInstanca() != null;
        for (ZeljeznickaStanica s : ZeljeznickiSustavSingleton.getInstanca().getStanice()) {
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
        List<String> put = bfsPronadjiPut(grafTezine, pocetnaStanica, odredisnaStanica);
        if (put == null) {
            return 0.0;
        }
        double ukupnaKm = 0.0;
        for (int i = 1; i < put.size(); i++) {
            String prethodna = put.get(i - 1);
            String trenutna = put.get(i);
            Integer dist = grafTezine.getOrDefault(prethodna, Collections.emptyMap()).get(trenutna);
            if (dist != null) {
                ukupnaKm += dist;
            }
        }
        return ukupnaKm;
    }

    private static List<String> bfsPronadjiPut(Map<String, Map<String, Integer>> graf,
                                               String pocetna,
                                               String odredisna) {
        Queue<List<String>> red = new LinkedList<>();
        Set<String> posjecene = new HashSet<>();
        List<String> startPut = new ArrayList<>();
        startPut.add(pocetna);
        red.add(startPut);
        posjecene.add(pocetna);
        while (!red.isEmpty()) {
            List<String> trenutniPut = red.poll();
            String zadnja = trenutniPut.get(trenutniPut.size() - 1);
            if (zadnja.equals(odredisna)) {
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

