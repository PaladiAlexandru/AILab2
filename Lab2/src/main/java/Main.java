public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        TransportProblem transportProblem =new TransportProblem();
        transportProblem.initialize(3);
        transportProblem.showStates();
//        transportProblem.bkt(transportProblem.state);
        transportProblem.bfs();
        System.out.println("BFS");
        transportProblem.showSolution();
    }
}
