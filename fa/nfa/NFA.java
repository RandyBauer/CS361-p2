package fa.nfa;

import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;


import fa.State;

/**
 * Models a non-deterministic finite automaton (NFA) and supports its construction, simulation, symbol swapping
 * for transitions, and ...(TODO: finish)
 * 
 * @author Randy Bauer
 * @author Oliver Hill
 */
public class NFA implements NFAInterface {

    private Set<NFAState> states;
    private Set<Character> sigma;
    private NFAState startState;
    private Set<NFAState> finalStates;
    private Map<NFAState, HashMap<Character, NFAState>> transitions;

    /**
     * Default constructor. Initializes empty NFA 5-tuple:
     * Q: the set of states
     * Sigma: the alphabet
     * q0: the start state
     * F: the set of final states
     * Delta: the transition function
     */
    public NFA() {
        states = new HashSet<>();
        sigma = new HashSet<>();
        startState = null;
        finalStates = new HashSet<>();
        transitions = new HashMap<>();
    }

    @Override
    public boolean addState(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name)) {
                return false;
            }
        }
        NFAState newState = new NFAState(name);
        states.add(newState);
        return true;
    }

    @Override
    public boolean setFinal(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name)) {
                finalStates.add(state);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setStart(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name)) {
                startState = state;
                return true;
            }
        }
        return false;
    }

    @Override
    public void addSigma(char symbol) {
        if (symbol == 'e') {
            System.out.println("e is reserved for epsilon transitions and cannot be added to the alphabet.");
            return;
        }
        sigma.add(symbol);
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    @Override
    public Set<Character> getSigma() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSigma'");
    }

    @Override
    public State getState(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
    }

    @Override
    public boolean isFinal(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isFinal'");
    }

    @Override
    public boolean isStart(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isStart'");
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToState'");
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eClosure'");
    }

    @Override
    public int maxCopies(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maxCopies'");
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
    }

    @Override
    public boolean isDFA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDFA'");
    }
    //
}