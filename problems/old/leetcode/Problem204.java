package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 12/5/2017.
 *
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * 10 minutes - ok!
 */
public class Problem204 {
    public int countPrimes(int n) {
        List<Integer> primes = new ArrayList<>();

        for(int i = 2; i < n; i++) {
            boolean isPrime = true;
            for(int prime : primes) {
                if(prime > Math.sqrt(i)) {
                    break;
                }
                if(i % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if(isPrime) primes.add(i);
        }
        return primes.size();
    }
}

