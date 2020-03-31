package search.strategy;

import java.util.*;

public class NoneStrategy implements SearchStrategy {

    @Override
    public Set<Integer> match(List<String> terms, Map<Integer, String> records, Map<String, Set<Integer>> index) {
        Set<Integer> anyMatches = (new AnyStrategy()).match(terms, records, index);
        Set<Integer> noneMatches = new TreeSet<>();

        for (Integer id: records.keySet()) {
            if (!anyMatches.contains(id)) {
                noneMatches.add(id);
            }
        }

        return noneMatches;
    }
}
