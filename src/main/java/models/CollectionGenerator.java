package models;

import java.util.Collection;

public class CollectionGenerator {
    private double minBound;
    private double maxBound;
    private int count;

    public CollectionGenerator(
            double minBound,
            double maxBound,
            int count
    ) {
        this.minBound = minBound;
        this.maxBound = maxBound;
        this.count = count;
    }

    public void fill(Collection<Double> collection){
        collection.clear();
        while (collection.size() < count){
            collection.add(Math.random() * (maxBound - minBound) + minBound);
        }
    }
}
