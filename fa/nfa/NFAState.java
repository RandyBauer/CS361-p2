package fa.nfa;

import fa.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public Set<NFAState> getTransitions(char symbol) {
        return transitions.getOrDefault(symbol, new HashSet<>());
    }

    public void addTransition(char symbol, NFAState to) {
        if(!transitions.containsKey(symbol)){
            transitions.put(symbol, new HashSet<>());
        }
        transitions.get(symbol).add(to);
    }
}
