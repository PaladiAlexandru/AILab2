public class Main {

    public static void main(String[] args) {
        TransportProblem transportProblem =new TransportProblem();
        transportProblem.initialize(3);
        transportProblem.showStates();
        transportProblem.bkt(transportProblem.state);
        transportProblem.showSolution();
    }
}
