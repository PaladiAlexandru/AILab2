/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;

public class TransportProblem {

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

    public boolean validation(State state,int p1,int p2){
        if( p2!=-1) if( state.couples.get(p1)!= state.couples.get(p2) )
            return false;
        if(p1>2*n||p2>2*n) return false;
        if(p1<0||p2<-1) return false;
        State state1=state;
        if(p1!=-1){
        if (state1.boatSide==1)
            {
                if (state1.couples.get(p1)==1)
                    state1.couples.set(p1,0);
                else return false;
                
                if(p2!=-1)
                    if(state1.couples.get(p2)==1)
                        state1.couples.set(p2,0);
                    else return false;
            }
        else {
                if(state1.couples.get(p1)==0)
                    state1.couples.set(p1,1);
                else return false;
                if(p2!=-1)
                    if(state1.couples.get(p1)==0)
                        state1.couples.set(p2,1);
                    else return false;
            }
        }
        for(int i=0;i<2*n;i+=2){
        int sumBm1=0,sumBm0=0;
        if(state1.couples.get(i)==1){
            if (state1.couples.get(i+1)==0){
                for(int j=0;j<2*n;j+=2){
                    if(state1.couples.get(j)==1) {
                        sumBm1++;
                        if(sumBm0>0) return false;
                    }
                    
                    else return false;
                }
            }
           
        }
        else {
            if (state1.couples.get(i+1)==1){
                for(int j=0;j<2*n;j+=2){
                    if(state1.couples.get(j)==0) {
                        sumBm0++;
                        if(sumBm1>0) return false;
                        }
                    else return false;
                    }
                }
        }
        }
        return true;
    
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
    
  public void showSolution(){
      for(int i=this.solution.size()-1;i>=0;i--){
          System.out.println(this.solution.get(i).toString());
      }
  
  }
  
  
}
