import java.util.Random;

public class HashTable implements Container{
    private Container[] fields;
    private final int size;
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private final Random random;
    public HashTable(ComplexNumber[] array) {
        this.size = array.length;
        randomizeHashFunction();
        random = new Random();
        fromComplexNumberArray(array);
    }

    private void fromComplexNumberArray(ComplexNumber[] array) {
        int size = array.length;
        ComplexNumberStack[] stack = new ComplexNumberStack[size];
        for (ComplexNumber complexNumber : array) {
            int hash = hashFunction(complexNumber);
            if (stack[hash] == null) {
                stack[hash] = new ComplexNumberStack();
            }
            stack[hash].push(complexNumber);
        }
        HashTable[] subtables = new HashTable[size];
        for (int i = 0; i < array.length; i++) {
            subtables[i] = new HashTable(stack[i].toContainers(), stack[i].size()*stack[i].size());
        }
        this.fields = subtables;
    }

    public HashTable(Container[] fields, int size) {
        this.fields = fields;
        random = new Random();
        this.size = size;
        randomizeHashFunction();
    }


    public void hash(ComplexNumber[] numbers) {
        for (ComplexNumber cn : numbers) {
            insert(cn);
        }
    }

    public void randomizeHashFunction() {
        a = random.nextInt(1, size);
        b = random.nextInt(1, size);
        c = random.nextInt(0, size);
    }

    public int hashFunction(ComplexNumber toHash) {
        int p = 101;
        return ((a * a * toHash.re() + b * toHash.im() + c) % p) % size;
    }
    @Override
    public void insert(ComplexNumber value) {
        this.fields[hashFunction(value)].insert(value);
    }
}
