package fa.nfa;

import java.util.*;


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
       if (sigma.isEmpty()) {
           return new HashSet<>();
       }
       return sigma;
    }

    @Override
    public State getState(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        if (finalStates.isEmpty()) {
            return false;
        }
        return finalStates.contains(getState(name));
    }

    @Override
    public boolean isStart(String name) {
        if (startState == null) {
            return false;
        }
        return startState.getName().equals(name);
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return from.getTransitions(onSymb);
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        Set<NFAState> visit = new HashSet<>();
        Stack<NFAState> stack = new Stack<>();

        stack.push(s);
        visit.add(s);

        while (!stack.isEmpty()) {
            NFAState current = stack.pop();

            for (NFAState nextState : getToState(current, 'e')){
                //if state not visited
                if (!visit.contains(nextState)) {
                    visit.add(nextState);
                    stack.push(nextState);
                }
            }
        }
        return visit;
    }

    @Override
    public int maxCopies(String s) {
        // get eClosure of startState as initial copies
        Set<NFAState> currCopies = eClosure(startState);
        int maxSize = currCopies.size();

        // for each symbol in the input string
        for (Character symbol : s.toCharArray()) {
            Set<NFAState> nextCopy = new HashSet<>();

            // for each current copy, find reachable states on this symbol
            for (NFAState curr : currCopies) {
                // add all reachable states to nextCopy
                nextCopy.addAll(getToState(curr, symbol));
            }

            // take eClosure of every state in nextCopies
            Set<NFAState> closedCopy = new HashSet<>();
            for (NFAState nextState : nextCopy) {
                closedCopy.addAll(eClosure(nextState));
            }
            // update current copies
            currCopies = closedCopy;

            // update max if needed
            if (currCopies.size() < maxSize) {
                maxSize = currCopies.size();
            }
        }
        // return maxSize
        return maxSize;
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        if (getState(fromState) == null) {
            return false;
        }
        for (String toState : toStates) {
            if (getState(toState) == null) {
                return false;
            }
        }
        if (onSymb != 'e' && !sigma.contains(onSymb)) {
            return false;
        }
        NFAState from = (NFAState) getState(fromState);
        for (String toState : toStates) {
            NFAState to = (NFAState) getState(toState);
            from.addTransition(onSymb, to);
        }
        return true;
    }

    @Override
    public boolean isDFA() {
        for (NFAState state : states) {
            if (getToState(state, 'e').isEmpty()) {
                return false;
            }
            for (char symbol : sigma){
                Set<NFAState> transitions = getToState(state, symbol);

                if (transitions.size() != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
