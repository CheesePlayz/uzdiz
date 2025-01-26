package foi.uzdiz.sbicak20.pomocnici.komande;

import foi.uzdiz.sbicak20.ZeljeznickiSustavSingleton;
import foi.uzdiz.sbicak20.modeli.Pruga;
import foi.uzdiz.sbicak20.pomocnici.stanja.IspravnaState;
import foi.uzdiz.sbicak20.pomocnici.stanja.KvarState;
import foi.uzdiz.sbicak20.pomocnici.stanja.TestiranjeState;
import foi.uzdiz.sbicak20.pomocnici.stanja.ZatvorenaState;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PSP2S implements Komanda{

    private String oznaka;
    private String polaznaStanica;
    private String odredisnaStanica;
    private String status;

    public PSP2S(String oznaka, String polaznaStanica, String odredisnaStanica, String status){
        this.oznaka = oznaka;
        this.polaznaStanica = polaznaStanica;
        this.odredisnaStanica = odredisnaStanica;
        this.status = status;
    }

    @Override
    public void prihvati(KomandaVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void izvrsi() {
        assert ZeljeznickiSustavSingleton.getInstanca() != null;
        for (Pruga pruga : ZeljeznickiSustavSingleton.getInstanca().getPruge()){
            if (Objects.equals(oznaka, pruga.getOznaka())){
                switch(status){
                    case "I": pruga.postaviStatus(new IspravnaState(), polaznaStanica, odredisnaStanica); break;
                    case "T": pruga.postaviStatus(new TestiranjeState(), polaznaStanica, odredisnaStanica); break;
                    case "K": pruga.postaviStatus(new KvarState(), polaznaStanica, odredisnaStanica); break;
                    case "Z": pruga.postaviStatus(new ZatvorenaState(), polaznaStanica, odredisnaStanica); break;
                    default:
                        System.out.println("Nije moguće postaviti status, jer takav status ne postoji!");
                }

            }
        }
    }
}
