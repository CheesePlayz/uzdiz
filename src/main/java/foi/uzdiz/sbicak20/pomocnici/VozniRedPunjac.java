package foi.uzdiz.sbicak20.pomocnici;

import foi.uzdiz.sbicak20.modeli.OznakaDana;
import foi.uzdiz.sbicak20.modeli.VozniRedPodaci;
import foi.uzdiz.sbicak20.modeli.ZeljeznickaStanica;
import foi.uzdiz.sbicak20.modeli.composite.Etapa;
import foi.uzdiz.sbicak20.modeli.composite.Vlak;
import foi.uzdiz.sbicak20.modeli.composite.VozniRed;

import java.time.LocalTime;
import java.util.List;

public class VozniRedPunjac {
        public VozniRed napuniVozniRed(List<ZeljeznickaStanica> stanice, List<VozniRedPodaci> vrpodaci, List<OznakaDana> oz){
            VozniRed vr = new VozniRed();
            return vr;
        }

        public Vlak napraviVlak(VozniRedPodaci vozniRedPodaci){
            Vlak vlak = new Vlak(vozniRedPodaci.getOznakaVlaka());

            return vlak;
        }

        public Etapa napraviEtapu(List<ZeljeznickaStanica> stanice, VozniRedPodaci vozniRedPodaci, List<OznakaDana> oz){
            LocalTime vrijemeDolaska = LocalTime.parse(vozniRedPodaci.getVrijemePolaska() + vozniRedPodaci.getTrajanjeVoznje());
            String daniUTjednu = getDaneUTjednu(vozniRedPodaci, oz);
            Etapa etapa = new Etapa(vozniRedPodaci.getOznakaVlaka(), vozniRedPodaci.getOznakaPruge(), LocalTime.parse(vozniRedPodaci.getVrijemePolaska()), vrijemeDolaska, daniUTjednu);

            return etapa;
        }

        private String getDaneUTjednu(VozniRedPodaci vozniRedPodaci, List<OznakaDana> oz){
            String dani = null;
            for(OznakaDana oznaka : oz){
                if (oznaka.getOznakaDana() == Integer.parseInt(vozniRedPodaci.getOznakaDana())){
                    dani = oznaka.getDani();
                };
            }
            return dani;
        }

        private ZeljeznickaStanica getPolaznaZeljeznickaStanica(List<ZeljeznickaStanica> stanice){

        }

        private ZeljeznickaStanica getOdredisnaZeljeznickaStanica(List<ZeljeznickaStanica> stanice){

        }
}
