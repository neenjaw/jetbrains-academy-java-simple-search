package search.strategy;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SearchStrategy {
    public Set<Integer> match(List<String> terms, Map<Integer, String> records, Map<String, Set<Integer>> index);
}
