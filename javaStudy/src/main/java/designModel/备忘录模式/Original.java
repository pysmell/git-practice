package designModel.备忘录模式;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Original {

    private String value;

    public Memento createMemento() {
        return new Memento(value);
    }

    public void restoreMemento(Memento memento) {
        this.value = memento.getValue();
    }


}


















