package fa.nfa;

import java.util.*;

import fa.State;

/**
 * Models a non-deterministic finite automaton (NFA).
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSigma(char symbol) throws IllegalArgumentException {
        if (symbol == 'e') {
            throw new IllegalArgumentException("e is reserved for epsilon transitions and cannot be added to the alphabet.");
        }
        sigma.add(symbol);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Character> getSigma() {
       if (sigma.isEmpty()) {
           return new HashSet<>();
       }
       return sigma;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State getState(String name) {
        for (NFAState state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinal(String name) {
        if (finalStates.isEmpty()) {
            return false;
        }
        return finalStates.contains(getState(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStart(String name) {
        if (startState == null) {
            return false;
        }
        return startState.getName().equals(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return from.getTransitions(onSymb);
    }

    /**
     * Helper method that allows calls to eClosure with a State parameter
     * instead of an NFAState.
     * @param s 
     * @return set of states that can be reached from s on epsilon
     */
    public Set<NFAState> eClosure(State s) {
        return eClosure((NFAState) s);
    }

    /**
     * {@inheritDoc}
     */
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean accepts(String s) {
        // get eClosure of startState as initial copies
        Set<NFAState> currStates = eClosure(startState);

        // Process each symbol in the input string
        for (Character symbol : s.toCharArray()) {
            
            // States reachable by consuming this symbol
            Set<NFAState> reachableStates = new HashSet<>();

            // for each current copy, find reachable states on this symbol
            for (NFAState state : currStates) {
                reachableStates.addAll(getToState(state, symbol));
            }
            
            // take eClosure of every state in nextCopies
            Set<NFAState> nextStates = new HashSet<>();
            for (NFAState nextState : reachableStates) {
                nextStates.addAll(eClosure(nextState));
            }
            // update current copies
            currStates = nextStates;
        }

        // ACCEPT if any of the current states is a final state
        for (NFAState state : currStates) {
            if (finalStates.contains(state)) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * {@inheritDoc}
     */
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
            if (currCopies.size() > maxSize) {
                maxSize = currCopies.size();
            }
        }
        // return maxSize
        return maxSize;
    }

    
    /**
     * {@inheritDoc}
     */
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

    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDFA() {
        for (NFAState state : states) {
            if (!getToState(state, 'e').isEmpty()) {
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
