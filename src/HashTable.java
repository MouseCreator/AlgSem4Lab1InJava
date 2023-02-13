import java.util.Random;

public class HashTable implements Container{
    private Container[] fields;
    private int size;
    private int a;
    private int b;
    private int p ;
    private static Random random;

    public HashTable(long seed) {
        random = new Random(seed);
    }
    public HashTable() {
        random = new Random();
    }

    public void hash(ComplexNumber[] array) {
        array = removeDuplicates(array);
        this.size = array.length;
        setP(array);
        randomizeHashFunction();
        fromComplexNumberArray(array);
    }
    private ComplexNumber[] removeDuplicates (ComplexNumber[] array) {
        ComplexNumberStack stack = new ComplexNumberStack();
        for (int i = 0; i < array.length; i++) {
            boolean isDistinct = true;
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                    isDistinct = false;
                    break;
                }
            }
            if (isDistinct)
                stack.push(array[i]);
        }
        return stack.toComplexNumbers();
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
        HashTable[] secondLayer = new HashTable[size];
        for (int i = 0; i < array.length; i++) {
            if (stack[i] != null)
                secondLayer[i] = new HashTable(stack[i], stack[i].size()*stack[i].size(), p);
        }
        this.fields = secondLayer;
    }

    private HashTable(ComplexNumberStack stack, int size, int p) {
        this.p = p;
        this.size = size;
        toNumberContainers(stack);
    }
    protected void setP(ComplexNumber[] forList) {
        int max = 0;
        for (ComplexNumber c : forList) {
            max = Math.max(c.toInteger(), max);
        }
        p = Primes.nextPrime(max);
    }

    private void toNumberContainers(ComplexNumberStack stack) {
        randomizeHashFunction();
        fields = new NumberContainer[size];
        NumberContainer[] containers = stack.toContainers();
        for (NumberContainer container : containers) {
            int hash = hashFunction(container.get());
            if (fields[hash] != null) {
                toNumberContainers(stack);
                break;
            }
            fields[hash] = new NumberContainer(container.get());
        }
    }

    public void randomizeHashFunction() {
        a = random.nextInt(0, p);
        b = (size == 1) ? 1 : random.nextInt(1, p);
    }

    private int sumHash(ComplexNumber toHash) {
        int key = toHash.toInteger();
        return a * key + b;
    }
    public int hashFunction(ComplexNumber toHash) {
        return ((sumHash(toHash)) % p) % size;
    }


    @Override
    public String print(int tLevel) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(tabulation(tLevel)).append("[").append(i).append("]:\t");
            if (fields[i] == null)
                builder.append("-");
            else
                builder.append(fields[i].print(tLevel+1));
            builder.append("\n");
        }
        return builder.toString();
    }
    public String tabulation(int tLevel) {
        return "\t".repeat(Math.max(0, tLevel));
    }

    @Override
    public void insert(ComplexNumber value) {
        this.fields[hashFunction(value)].insert(value);
    }
}
