package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;

import java.util.List;

import java.util.*;

public class ISI2S implements Komanda {
    private String oznakaStanicePolazna;
    private String oznakaStaniceOdredisna;
    private List<ZeljeznickaStanica> stanice;

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
        Map<String, List<String>> graf = new HashMap<>();
        Map<String, Map<String, Integer>> udaljenosti = new HashMap<>();

        // Organiziraj stanice po oznakama pruga
        for (ZeljeznickaStanica stanica : stanice) {
            pruge.putIfAbsent(stanica.getOznakaPruge(), new ArrayList<>());
            pruge.get(stanica.getOznakaPruge()).add(stanica);
        }

        // Popunjavaj graf i udaljenosti na temelju stvarnih duljina
        for (String oznakaPruge : pruge.keySet()) {
            List<ZeljeznickaStanica> trenutnaPruga = pruge.get(oznakaPruge);

            for (int i = 0; i < trenutnaPruga.size() - 1; i++) {
                ZeljeznickaStanica trenutna = trenutnaPruga.get(i);
                ZeljeznickaStanica sljedeca = trenutnaPruga.get(i + 1);

                graf.putIfAbsent(trenutna.getStanica(), new ArrayList<>());
                graf.putIfAbsent(sljedeca.getStanica(), new ArrayList<>());
                graf.get(trenutna.getStanica()).add(sljedeca.getStanica());
                graf.get(sljedeca.getStanica()).add(trenutna.getStanica());

                udaljenosti.putIfAbsent(trenutna.getStanica(), new HashMap<>());
                udaljenosti.putIfAbsent(sljedeca.getStanica(), new HashMap<>());

                if (trenutna.getDuzina() > 0) {
                    // Dodaj stvarnu udaljenost samo ako je veća od 0
                    udaljenosti.get(trenutna.getStanica()).put(sljedeca.getStanica(), trenutna.getDuzina());
                    udaljenosti.get(sljedeca.getStanica()).put(trenutna.getStanica(), trenutna.getDuzina());
                } else {
                    // Ako udaljenost nije definirana, traži iduću stvarnu udaljenost
                    int j = i;
                    while (j < trenutnaPruga.size() - 1 && trenutnaPruga.get(j + 1).getDuzina() == 0) {
                        j++;  // Preskoči stanice koje nemaju udaljenost
                    }

                    if (j < trenutnaPruga.size() - 1) {
                        ZeljeznickaStanica stvarnaSljedeca = trenutnaPruga.get(j + 1);
                        if (trenutna.getDuzina() == 0 && stvarnaSljedeca.getDuzina() > 0) {
                            udaljenosti.get(trenutna.getStanica()).put(stvarnaSljedeca.getStanica(), stvarnaSljedeca.getDuzina());
                            udaljenosti.get(stvarnaSljedeca.getStanica()).put(trenutna.getStanica(), stvarnaSljedeca.getDuzina());
                            i = j; // Preskoči nepotrebne stanice
                        }
                    }
                }
            }
        }

        // Izračunaj put koristeći BFS
        List<String> put = bfs(graf, udaljenosti, oznakaStanicePolazna, oznakaStaniceOdredisna);

        if (put != null) {
            double ukupnaKilometraža = 0.0;
            int[] maxDuljine = {20, 20, 20};
            StringBuilder formatBuilder = new StringBuilder("|");
            for (int duljina : maxDuljine) {
                formatBuilder.append(" %-").append(duljina).append("s |");
            }
            formatBuilder.append("%n");
            String format = formatBuilder.toString();
            System.out.printf(format, "Naziv stanice", "Vrsta stanice", "Ukupna kilometraža");

            // Ispis stanica i kilometraže
            for (int i = 0; i < put.size(); i++) {
                String trenutna = put.get(i);

                ZeljeznickaStanica stanicaObjekt = stanice.stream()
                        .filter(s -> s.getStanica().equals(trenutna))
                        .findFirst()
                        .orElse(null);

                if (stanicaObjekt == null) {
                    continue;
                }

                if (i > 0) {
                    String prethodna = put.get(i - 1);

                    // Pronađi stvarnu udaljenost između prethodne i trenutne stanice
                    Integer udaljenost = udaljenosti.get(prethodna).get(trenutna);
                    if (udaljenost != null) {
                        ukupnaKilometraža += udaljenost; // Dodaj stvarnu udaljenost
                    }
                }

                System.out.printf(
                        format,
                        trenutna,
                        stanicaObjekt.getVrstaStanice(),
                        String.format("%.1f km", ukupnaKilometraža)
                );
            }
        } else {
            System.out.println("Nije moguće pronaći put između " + oznakaStanicePolazna + " i " + oznakaStaniceOdredisna);
        }
    }

    private List<String> bfs(Map<String, List<String>> graf, Map<String, Map<String, Integer>> udaljenosti, String start, String cilj) {
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> posjecene = new HashSet<>();

        queue.add(Collections.singletonList(start));
        posjecene.add(start);

        while (!queue.isEmpty()) {
            List<String> put = queue.poll();
            String trenutni = put.get(put.size() - 1);

            if (trenutni.equals(cilj)) {
                return put;
            }

            for (String susjed : graf.getOrDefault(trenutni, new ArrayList<>())) {
                if (!posjecene.contains(susjed)) {
                    List<String> noviPut = new ArrayList<>(put);
                    noviPut.add(susjed);
                    queue.add(noviPut);
                    posjecene.add(susjed);
                }
            }
        }
        return null;
    }
}