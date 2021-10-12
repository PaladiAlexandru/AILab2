public class Main {

    public static void main(String[] args) {
        TransportProblem transportProblem =new TransportProblem();
        transportProblem.initialize(2);
        transportProblem.showStates();
        //transportProblem.bkt(transportProblem.state);
        transportProblem.bfs();
        System.out.println("BFS");
        transportProblem.showSolution();
    }
}
