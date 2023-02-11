import java.util.Random;

public class HashTable implements Container{
    private Container[] fields;
    private final int size;
    private int a;
    private int b;
    private int c;

    private int p ;
    private final Random random;
    public HashTable(ComplexNumber[] array) {
        this.size = array.length;
        random = new Random();
        randomizeHashFunction();
        setP(array);
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
            if (stack[i] != null)
                subtables[i] = new HashTable(stack[i], stack[i].size()*stack[i].size(), p);
        }
        this.fields = subtables;
    }

    private HashTable(ComplexNumberStack stack, int size, int p) {
        this.p = p;
        random = new Random();
        this.size = size;
        toNumberContainers(stack);
    }

    private void setP(ComplexNumber[] forList) {
        int max = 0;
        for (ComplexNumber c : forList) {
            max = Math.max(sumHash(c), max);
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
        a = random.nextInt(1, size+1);
        b = random.nextInt(1, size+1);
        c = random.nextInt(0, size);
    }

    private int sumHash(ComplexNumber toHash) {
      return (a * a * toHash.re() + b * toHash.im()) + c;
    }
    public int hashFunction(ComplexNumber toHash) {
        return ((sumHash(toHash)) % p) % size;
    }


    @Override
    public String print(int tLevel) {
        StringBuilder builder = new StringBuilder("Hash table\n");
        for (int i = 0; i < size; i++) {
            builder.append("[").append(i).append("] :");
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
