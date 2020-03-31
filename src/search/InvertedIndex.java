package search;
import search.strategy.SearchStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class InvertedIndex {
    private int nextId;
    private Map<Integer, String> records;
    private Map<String, Set<Integer>> index;

    public InvertedIndex() {
        nextId = 1;
        records = new TreeMap<>();
        index = new HashMap<>();
    }

    public void addRecord(String record) {
        records.put(nextId, record);
        addIndex(nextId, record);
        nextId += 1;
    }

    private void addIndex(int id, String record) {
        String[] keys = record.toLowerCase().split(" ");

        for (String key:keys) {
            index.putIfAbsent(key, new TreeSet<>());
            index.get(key).add(id);
        }
    }

    public List<String> search(String terms, SearchStrategy strategy) {
        List<String> normalized = Arrays.asList(terms.toLowerCase().split(" "));
        Set<Integer> matches = strategy.match(normalized, records, index);
        List<String> matchingRecords = new ArrayList<>();

        for (Integer match:matches) {
            matchingRecords.add(records.get(match));
        }

        return matchingRecords;
    }

    public List<String> records() {
        return records.values().stream().collect(Collectors.toUnmodifiableList());
    }
}
