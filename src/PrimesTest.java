import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimesTest {

    @Test
    void getPrime() {
        assertEquals(46349, Primes.nextPrime(46340));
        assertEquals(2, Primes.nextPrime(2));
        assertEquals(2, Primes.nextPrime(1));
    }

}