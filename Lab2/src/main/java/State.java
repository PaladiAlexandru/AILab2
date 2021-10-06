import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class State {

    List<Integer> couples;
    int boatSide;

    public State() {
        this.couples = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "State{" +
                "couples=" + couples +
                ", boatSide=" + boatSide +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return boatSide == state.boatSide && Objects.equals(couples, state.couples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couples, boatSide);
    }
}
