package search.strategy;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AllStrategy implements SearchStrategy {

    @Override
    public Set<Integer> match(List<String> terms, Map<Integer, String> records, Map<String, Set<Integer>> index) {
        return terms.stream()
                .map(term -> index.getOrDefault(term, new TreeSet<Integer>()))
                .reduce(new TreeSet<Integer>(), (partialSet, set) -> { partialSet.retainAll(set); return partialSet; });
    }
}
