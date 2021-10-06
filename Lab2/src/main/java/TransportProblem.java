public class TransportProblem {

    int n;
    State state;
    State finalState;


    public void initialize(int n) {
        this.state = new State();
        this.finalState = new State();
        this.state.boatSide=0;
        this.finalState.boatSide=1;
        this.n = n;


        for (int i = 0; i < 2 * n; i++) {
            this.state.couples.add(0);
            this.finalState.couples.add(1);
        }
    }

    public boolean isFinal(){
        return state.equals(finalState);
    }
    public void showStates(){
        System.out.println("Initial state: " + state);
        System.out.println("Final state: " + finalState);
    }

}
