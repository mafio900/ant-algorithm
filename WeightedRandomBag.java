import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightedRandomBag {

    private class Entry {
        double accumulatedWeight;
        int object;
    }

    public List<Entry> entries = new ArrayList<>();
    public double accumulatedWeight;
    private Random rand = new Random();

    public void addEntry(int object, double weight) {
        accumulatedWeight += weight;
        Entry e = new Entry();
        e.object = object;
        e.accumulatedWeight = accumulatedWeight;
        entries.add(e);
    }

    public int getRandom() {
        double r = rand.nextDouble() * accumulatedWeight;

        for (Entry entry: entries) {
            if (entry.accumulatedWeight >= r) {
                return entry.object;
            }
        }
        return -1; //should only happen when there are no entries
    }
}