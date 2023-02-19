import java.util.Random;

/**
 * Хеш-таблиця для хешування комплексних чисел
 */
public class HashTable implements Container{
    private Container[] fields; //комірки хеш-таблиці
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
     * @param v - вхідне число
     * @return об'єкт вхідного числа з таблиці, null - якщо такого об'єкту не знайдено
     */
    public ComplexNumber get(ComplexNumber v) {
        return this.fields[hashFunction(v)].get(v);
    }
    /**
     *
     * @param array - вхідний масив комплексних чисел, який треба захешувати
     */
    public void hash(ComplexNumber[] array) {
        removeLargeInputs(array); //заміна небезпечних вхідних даних
        array = getAllValuesWithOutDuplicates(array); //поєднати вхідні дані з наявними та вилучити дублікати
        if (array == null)
            return;
        this.size = array.length; //встановлення розміру хеш-таблиці
        setP(array); //визначення параметра P - простого числа
        randomizeHashFunction(); //підбір випадкової хеш-функції з множини універсальних хеш-функцій
        hashArrayToNextLayer(array); //хешування даних
    }

    private void removeLargeInputs(ComplexNumber[] array) {
        for (int i = 0; i < array.length; i++) {
            int key = array[i].toInteger();
            System.out.println(array[i] + " " + key);
            if (key > ComplexNumberGenerator.LIMIT) {
                key = key % ComplexNumberGenerator.LIMIT;
                ComplexNumber alternate = new ComplexNumber(key);
                OutputWriter.logError("Complex number " + array[i]+ "is too large. It will be replaced with " + alternate);
                array[i] = new ComplexNumber(key);
            }
        }
    }

    /**
     *
     * @param array - додаткові вхідні дані
     * @return масив комплексних чисел, який містить попередні та додаткові вхідні дані без повторень
     */
    private ComplexNumber[] getAllValuesWithOutDuplicates(ComplexNumber[] array) {
        ComplexNumberStack stack = new ComplexNumberStack(array);
        pickHashedData(stack);
        return stack.size() == 0 ? null : removeDuplicates(stack.toComplexNumbers());
    }

    /**
     * Передає захешовані значення стеку
     * @param pushTo - стек, який збирає дані по "дереву" контейнерів
     */
    public void pickHashedData(ComplexNumberList pushTo) {
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
     * @return масив, елементи якого не повторюються
     */
    private ComplexNumber[] removeDuplicates(ComplexNumber[] array) {
        ComplexNumberQueue queue = new ComplexNumberQueue();
        for (int i = 0; i < array.length; i++) {
            boolean isDistinct = true;
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                    isDistinct = false;
                    break;
                }
            }
            if (isDistinct) {
                queue.push(array[i]);
            }
        }
        return queue.toComplexNumbers();
    }

    /**
     * Заповнення полів таблиці новими хеш-таблицями та поміщення відповідних значень до них
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

    /**
     *
     * @param queue - черга комплексних чисел, яку потрібно захешувати
     * @param size - необхідний розмір таблиці
     * @param p - поперенє значення параметру P (єдине для всіх таблиць)
     */
    private HashTable(ComplexNumberQueue queue, int size, int p) {
        this.p = p;
        this.size = size;
        boolean success = false;
        while (!success)
            success = toNumberContainers(queue);
    }

    /**
     * Обрахування найбільшого ключа та встановлення значення P
     * @param numbersToHash - масив комплексних чисел, який потрібно захешувати
     */
    protected void setP(ComplexNumber[] numbersToHash) {
        int max = 0; //максимальний ключ
        for (ComplexNumber c : numbersToHash) {
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
               return false; //повторити на новиій хеш-функції
            }
            fields[hash] = new ComplexNumberContainer(container.get());
        }
        for (ComplexNumberContainer container : containers) {
            System.out.print(container.toString() + " " + hashFunction(container.get()));
        }
        System.out.println();
        System.out.println("a: " + a + " b: " + b + " p: " + p + " size: " + size);
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
            builder.append("\n").append("\t".repeat(Math.max(0, tLevel))).append("[").append(i).append("]:\t");
            if (fields[i] == null)
                builder.append("-");
            else
                builder.append(fields[i].print(tLevel+1));
        }
        return builder.toString();
    }

    /**
     *
     * @param value - значення, яке потрібно додати до хеш-таблиці.
     * Якщо таблиця містить інші хеш-таблиці - значення передасться їм.
     * Якщо таблиця містить контейнери, то значення захешується (з можливою заміною попереднього значення).
     */
    @Override
    public void insert(ComplexNumber value) {
        int hash = hashFunction(value);
        if(this.fields[hashFunction(value)] != null)
            this.fields[hashFunction(value)].insert(value); //замінити значення у відповідну комірку
        else
            this.fields[hash] = new ComplexNumberContainer(value); //помістити у новий контейнер
    }
}
