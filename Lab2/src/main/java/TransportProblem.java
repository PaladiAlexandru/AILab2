/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TransportProblem {


    Deque queue1,queue2,queue;
    int n;
    State state;
    State finalState;
    List<State> solution=new ArrayList<State>();

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

    public boolean isFinal(State state){
        return state.equals(finalState);
    }
    public void showStates(){
        System.out.println("Initial state: " + state);
        System.out.println("Final state: " + finalState);
    }

    public State transition(State state,int p1,int p2){
        State state1=state;

        if (state1.boatSide==1)
        {
            state1.boatSide=0;
            state1.couples.set(p1,0);
            if(p2!=-1)
                state1.couples.set(p2,0);
        }
        else {
            state1.boatSide=1;
            state1.couples.set(p1,1);
            if(p2!=-1)
                state1.couples.set(p2,1);
        }

        return state1;
    }

    public boolean isWife(int index){
        return index % 2 != 0;
    }
    public boolean onlyWifesOnOpposite(int index){
        for(int i =0;i<state.couples.size();i+=2){
            if(!isWife(i))
                return false;
        }
        return true;
    }

    public boolean validation(State state,int index1,int index2){
        if(state.couples.get(index1).equals(state.couples.get(index2))){
            if(state.couples.get(index1).equals(state.boatSide)){

            }
        }
    }

    public boolean bkt(State state){
        if(this.isFinal(state))
            return true;

        for (int i=0;i<2*n-1;i++){
            for(int j=0;j<2*n-1;j++){
                if(i!=j){
                    if(this.validation(state, i, -1)) {
                        state=this.transition(state, i, -1);
                        if(bkt(state)) {
                            solution.add(state);
                            return true;
                        }
                        state=this.transition(state, i, -1);
                    }
                    if(this.validation(state, j, -1)) {
                        state=this.transition(state, j, -1);
                        if(bkt(state)) {
                            solution.add(state);
                            return true;
                        }
                        state=this.transition(state, j, -1);
                    }
                    if(this.validation(state, i, j)) {
                        state=this.transition(state, i,j);
                        if(bkt(state)) {
                            solution.add(state);
                            return true;
                        }
                        state=this.transition(state, i, j);
                    }
                }
            }
        }
        return false;

    }
    public void bfs(){

        queue1.add(0);
        queue2.add(1);
        State state1=new State();
        state1.boatSide=state.boatSide;
        for(int k=0;k<state.couples.size();k++){
            state1.couples.add(state.couples.get(k));
        }
        state1=this.transition(state1, 0,1);
        queue.add(state1);

        if(isFinal(state1))
            solution.add(state1);
        else{

            while(!queue.isEmpty()){
                for (int i=0;i<2*n;i++){
                    for(int j=i+1;j<2*n;j++){
                        if(i!=j){
                            if(queue1.getLast()!=i||queue2.getLast()!=j)
                                if(this.validation(state, i, j)) {
                                    queue1.add(i);
                                    queue2.add(j);
                                    State state2=new State();
                                    state2.boatSide=state.boatSide;
                                    for(int k=0;k<state.couples.size();k++){
                                        state2.couples.add(state.couples.get(k));
                                    }
                                    state2=this.transition(state2, i,j);
                                    queue.add(state2);
                                }
                            if(queue1.getLast()!=i||queue2.getLast()!=-1)
                                if(this.validation(state, i, -1)) {
                                    queue1.add(i);
                                    queue2.add(-1);
                                    State state2=new State();
                                    state2.boatSide=state.boatSide;
                                    for(int k=0;k<state.couples.size();k++){
                                        state2.couples.add(state.couples.get(k));
                                    }
                                    state2=this.transition(state2, i,-1);
                                    queue.add(state2);
                                }
                            if(queue1.getLast()!=j||queue2.getLast()!=-1)
                                if(this.validation(state, j, -1)) {
                                    queue1.add(j);
                                    queue2.add(-1);
                                    State state2=new State();
                                    state2.boatSide=state.boatSide;
                                    for(int k=0;k<state.couples.size();k++){
                                        state2.couples.add(state.couples.get(k));
                                    }
                                    state2=this.transition(state2, j,-1);
                                    queue.add(state2);
                                }

                        }
                    }
                }


                state=queue.poll();
                solution.add(state);
                if(isFinal(state)) break;

            }
        }
    }

    public void showSolution(){
        for(int i=this.solution.size()-1;i>=0;i--){
            System.out.println(this.solution.get(i).toString());
        }

    }


}
