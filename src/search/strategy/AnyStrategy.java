package search.strategy;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AnyStrategy implements SearchStrategy {

    @Override
    public Set<Integer> match(List<String> terms, Map<Integer, String> records, Map<String, Set<Integer>> index) {
        Set<Integer> matches = new TreeSet<>();

        for (String term: terms) {
            if (index.containsKey(term)) {
                matches.addAll(index.get(term));
            }
        }

        return matches;
    }
}
