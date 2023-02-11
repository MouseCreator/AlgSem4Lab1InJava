public class Primes {
    public static int nextPrime(int a) {
        while (!isPrime(a))
            a++;
        return a;
    }
    public static boolean isPrime(int b) {
        int limit = (int) Math.sqrt(b);
        for (int i = 2; i < limit; i++) {
            if (b % i == 0) {
                return false;
            }
        }
        return true;
    }
}
