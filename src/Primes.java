/**
 * Клас для дій з простими числами.
 * Простими числами вважатимуться всі числа >1, які діляться лише на себе та на 1
 */
public abstract class Primes {

    /**
     *
     * @param a - деяке ціле число
     * @return найтупне просте число після a.
     * Якщо a є простим - повертається a.
     * Якщо a від'ємне - повератєтсья 2 як найменше просте число.
     */
    public static int nextPrime(int a) {
        if (a < 2)
            return 2;
        while (!isPrime(a))
            a++;
        return a;
    }

    /**
     *
     * @param b - деяке число
     * @return true, якщо число є простим
     */
    public static boolean isPrime(int b) {
        if (b < 2)
            return false;
        int limit = (int) Math.sqrt(b);
        for (int i = 2; i < limit; i++) {
            if (b % i == 0) {
                return false;
            }
        }
        return true;
    }
}
