package foi.uzdiz.sbicak20.modeli.memento;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private final List<Memento> mementoList = new ArrayList<>();

    public void dodajMemento(Memento memento) {
        mementoList.add(memento);
    }

    public Memento dohvatiMemento(int index) {
        try {
            return mementoList.get(index);
        } catch (Exception e) {
            System.out.println("Index je izvan granica unutar liste Mementoa !");
        }
        return null;
    }

    public int velicinaMemento() {
        return mementoList.size();
    }
}
