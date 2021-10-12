/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TransportProblem {


    int n;
    State state;
    State finalState;
    List<State> solution = new ArrayList<>();

    public void initialize(int n) {
        this.state = new State();
        this.finalState = new State();
        this.state.boatSide = 0;
        this.finalState.boatSide = 1;
        this.n = n;


        for (int i = 0; i < 2 * n; i++) {
            this.state.couples.add(0);
            this.finalState.couples.add(1);
        }
    }

    public boolean isFinal(State state) {
        return state.equals(finalState);
    }

    public void showStates() {
        System.out.println("Initial state: " + state);
        System.out.println("Final state: " + finalState);
    }

    public void transition(State state, int p1, int p2) {


        if (state.boatSide == 1) {

            state.boatSide = 0;
            state.couples.set(p1, 0);
            if (p2 != -1)
                state.couples.set(p2, 0);
        } else {
            state.boatSide = 1;
            state.couples.set(p1, 1);
            if (p2 != -1)
                state.couples.set(p2, 1);
        }

    }

    public boolean isWife(int index) {
        return index % 2 != 0;
    }

    public boolean onlyWifesOnOpposite(int index) {
        for (int i = 0; i < state.couples.size(); i += 2) {
                if (state.couples.get(i) == (index + 1) % 2)
                    return false;
        }
        return true;
    }

    public boolean onlyHusbandsLeft(int index) {
        for (int i = 1; i < state.couples.size(); i += 2){
            if(state.couples.get(i) == index)
                return false;
        }
        return true;
    }

    public boolean validation(State state, int index1, int index2) {
        if (index2 == -1) {
            if (isWife(index1)) {
                return onlyWifesOnOpposite(index1);
            } else {
                return true;
            }
        } else {
            if (state.couples.get(index1).equals(state.couples.get(index2))) {
                if (state.couples.get(index1).equals(state.boatSide)) {
                    if (isWife(index1) || isWife(index2)) {
                        return onlyWifesOnOpposite(index1);
                    } else {
                        return onlyHusbandsLeft(state.boatSide);
                    }
                }
            }

        }
        return false;
    }

    public boolean bkt(State state) {
        System.out.println(state);
        if (this.isFinal(state))
            return true;
        if (state.boatSide == 0) {
            for (int i = 0; i < state.couples.size() - 1; i++) {
                if (state.couples.get(i) == 0) {
                    for (int j = i + 1; j < state.couples.size(); j++) {
                        if (state.couples.get(j) == 0) {
                            if (validation(state, i, j)) {
                                transition(state, i, j);
                                if (bkt(state)) {
                                    solution.add(state);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }


        } else {
            for (int i = 0; i < state.couples.size() - 1; i++) {
                if (state.couples.get(i) == 1) {
                    if (validation(state, i, -1)) {
                        transition(state, i, -1);
                        if (bkt(state)) {
                            solution.add(state);
                            return true;
                        }
                    }
                }
            }

        }

        return false;

    }

    public void showSolution() {
        for (int i = this.solution.size() - 1; i >= 0; i--) {
            System.out.println(this.solution.get(i).toString());
        }

    }


}
