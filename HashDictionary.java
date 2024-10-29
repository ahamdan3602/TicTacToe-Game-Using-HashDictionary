
import java.util.*;
public class HashDictionary{   

    private int size;
    private LinkedList<Data>[] record_dict;
    private Data DELETED = new Data("", -1);
    private int count_record = 0;
    LinkedList<Data> list;
    private int x = 59;

    /* Scores:
     * HUMAN WINS = 0;
     * UNDECIDED = 1
     * DRAW = 2
     * COMPUTER WINS = 3
    */

    //Constructor - Initalize size of hashtable and implement buckets
    @SuppressWarnings("unchecked")
    public HashDictionary(int size) {
        record_dict = new LinkedList[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            record_dict[i] = new LinkedList<>();
        }
    }
    //Add a record to a hashdictonary
    public int put (Data record) throws DictionaryException {
    
        int idx = hash(record.getConfiguration(), x, size);
        LinkedList<Data> bucket = record_dict[idx];

        //Checks if the item exists in the hashdictionary/speciifc bucket in the dictionary
        for (Data data : bucket) {
            if (data.getConfiguration().equals(record.getConfiguration())) {
                throw new DictionaryException();
            }
        }
        int num_col = 0;
        //Checks if an item is already in the bucket, if it is calculate # of collisions
        if (!bucket.isEmpty()) {
            num_col += 1;

        }
        bucket.addLast(record);

        count_record+=1;

        return num_col;
    }

    //Polynomial hashing technique used to retrieve/add/remove specific entry on the hashdictionary
    private int hash(String s, int x, int size) {
        long hash = 41;
        long powerOfX = 1;
        for (char c : s.toCharArray()) {
            hash = (hash + (c * powerOfX) % size) % size;
            powerOfX = (powerOfX * x) % size;
        }
        return (int) hash;
    }

    //removes a specific congiuration if it exists in the dictionary
    public void remove(String config) throws DictionaryException {
        int idx = hash(config, x, size);
        LinkedList<Data> bucket = record_dict[idx];
        Iterator<Data> curr = bucket.iterator();

        while (curr.hasNext()) {
            Data data = curr.next();
            if (data.getConfiguration().equals(config)) {
                curr.remove();
                return;
            }
        }

        throw new DictionaryException();
    }


    // Retrieves a specific configuration's score if it exists in the hash dictionar
    public int get(String config) {
        int idx =  hash(config, x, size);
        LinkedList<Data> bucket = record_dict[idx];

        for (Data data : bucket) { // iterate through the specific bucket
            if (config.equals(data.getConfiguration())) {
                return data.getScore();
            }
        }

        return -1;
    }

}






