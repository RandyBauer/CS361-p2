package fa.nfa;

import fa.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Models a state in a non-deterministic finite automaton (NFA). Each NFAState has a name and a transition function
 * that maps symbols to sets of NFAStates.
 * 
 * @author Randy Bauer
 * @author Oliver Hill
 */
public class NFAState extends State{

    private Map<Character, Set<NFAState>> transitions;
    /**
     * Default constructor for building NFAState with given name.
     * @param name String label for the state.
     */
    public NFAState(String name) {
        super(name);
        transitions = new HashMap<>();
    }

    /**
     * Returns the set of states that can be reached from this state on the given symbol.
     * @param symbol
     * @return set of states that can be reached from this state on the given symbol.
     */
    public Set<NFAState> getTransitions(char symbol) {
        return transitions.getOrDefault(symbol, new HashSet<>());
    }

    /**
     * Adds a transition from this state to the given state on the given symbol.
     * @param symbol
     * @param to
     */
    public void addTransition(char symbol, NFAState to) {
        if(!transitions.containsKey(symbol)){
            transitions.put(symbol, new HashSet<>());
        }
        transitions.get(symbol).add(to);
    }
}
