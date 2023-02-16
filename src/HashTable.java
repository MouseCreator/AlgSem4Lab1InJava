import java.util.Random;

public class HashTable implements Container{
    private Container[] fields;
    private int size; //Кількість комірок в хеш-таблиці


    private int a;
    private int b;
    private int p; //Просте число, більше будь-якого ключа
    private static Random random;

    public HashTable(long seed) {
        random = new Random(seed);
    }
    public HashTable() {
        random = new Random();
    }

    /**
     *
     * @param array - вхідний масив комплексних чисел, який треба захешувати
     */
    public void hash(ComplexNumber[] array) {
        array = getAllValuesWithOutDuplicates(array); //поєднати вхідні дані з наявними та вилучити дублікати
        if (array == null)
            return;
        this.size = array.length; //встановлення розміру хеш-таблиці
        setP(array); //визначення параметра P - простого числа
        randomizeHashFunction(); //підбір випадкової хеш-функції з множини універсальних хеш-функцій
        hashArrayToNextLayer(array); //хешування даних
    }

    /**
     *
     * @param array - додаткові вхідні дані
     * @return масив комплексних чисел, який містить додаткові та попередні вхідні дані без повторень
     */
    private ComplexNumber[] getAllValuesWithOutDuplicates(ComplexNumber[] array) {
        ComplexNumberStack stack = new ComplexNumberStack(array);
        pickHashedData(stack);
        return stack.size() == 0 ? null : removeDuplicates(stack.toComplexNumbers());
    }

    /**
     *
     * @param pushTo - стек, який збирає дані по "дереву" контейнерів
     */
    public void pickHashedData(ComplexNumberStack pushTo) {
        if (this.size > 0 && this.fields != null) {
            for (Container container : fields) {
                if (container != null)
                    container.pickHashedData(pushTo);
            }
        }
    }

    /**
     *
     * @param array - масив комплексних числе
     * @return вхідний масив, який не містить однакових елементів
     */
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
            if (isDistinct) {
                stack.push(array[i]);
            }
        }
        return stack.toComplexNumbers();
    }

    /**
     *
     * @param array - масив, який потрібно захешувати
     */
    private void hashArrayToNextLayer(ComplexNumber[] array) {
        ComplexNumberQueue[] queue = new ComplexNumberQueue[size];
        for (ComplexNumber complexNumber : array) { //перебір всіх елементів масиву
            int hash = hashFunction(complexNumber);
            if (queue[hash] == null) {
                queue[hash] = new ComplexNumberQueue();
            }
            queue[hash].push(complexNumber); //додати поточне число в чергу до майбутньої хеш-таблиці другого рівня
        }

        this.fields = new HashTable[size];
        for (int i = 0; i < array.length; i++) {
            if (queue[i] != null) {
                this.fields[i] = new HashTable(queue[i], queue[i].size() * queue[i].size(), p);
            }
        }
    }
    private HashTable(ComplexNumberQueue queue, int size, int p) {
        this.p = p;
        this.size = size;
        boolean success = false;
        while (!success)
            success = toNumberContainers(queue);
    }
    protected void setP(ComplexNumber[] forList) { //Обрахування найбільшого ключа та встановлення значення P
        int max = 0; //максимальний ключ
        for (ComplexNumber c : forList) {
            max = Math.max(c.toInteger(), max);
        }
        p = Primes.nextPrime(max); //наступне просте число
    }

    /**
     *
     * @param queue - черга комплексних чисел, які необхідно помістити в хеш-таблицю
     * @return true, якщо числа були захешовані успішно
     */
    private boolean toNumberContainers(ComplexNumberQueue queue) {
        randomizeHashFunction();
        fields = new ComplexNumberContainer[size];
        ComplexNumberContainer[] containers = queue.toContainers();
        for (ComplexNumberContainer container : containers) {
            int hash = hashFunction(container.get()); //обрахувати номер комірки для вхідного числа
            if (fields[hash] != null) { //якщо комірка вже зайнята
               return false;//повторити на новиій хеш-функції
            }
            fields[hash] = new ComplexNumberContainer(container.get());
        }
        return true;
    }

    /**
     * Встановлення випадкових допустимих параметрів для хешування
     */
    protected void randomizeHashFunction() {
        a = random.nextInt(0, p);
        b = (size == 1) ? 1 : random.nextInt(1, p);
    }

    /**
     *
     * @param toHash - число, яке потрібно захешувати
     * @return хеш з множини [0, size)
     */
    public int hashFunction(ComplexNumber toHash) {
        return ((a * toHash.toInteger() + b) % p) % size;
    }

    /**
     *
     * @param tLevel - рівень табуляцій
     * @return текстове предствавлення таблиці для виведення в консоль
     */
    @Override
    public String print(int tLevel) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append("\t".repeat(Math.max(0, tLevel))).append("[").append(i).append("]:\t");
            if (fields[i] == null)
                builder.append("-");
            else
                builder.append(fields[i].print(tLevel+1));
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     *
     * @param value - значення, яке потрібно додати до хеш-таблиці.
     * Якщо таблиця містить інші хеш-таблиці - значення передасться їм.
     * Якщо таблиця містить контейнери, то значення захешується (з можливою заміною іншогго значення).
     */
    @Override
    public void insert(ComplexNumber value) {
        this.fields[hashFunction(value)].insert(value);
    }
}
