(import java.util.BitSet)
(require '[clojure.math :as math])

; Generate primes using the Sieve of Eratosthenes and a bit set
(defn primes [n]
  (let [k (inc n) ; Use k = n + 1 for simpler indexing
        x (BitSet. k)] ; Use bit set for space efficiency
    (doseq [i (range 4 k 2)] (.set x i)) ; Mark all even numbers >2 as non-prime
    (doseq [i (range 3 (math/sqrt n) 2)] ; For all odd numbers [3, sqrt(n)]
      (when (not (.get x i)) ; If number is marked as prime...
        (doseq [j (range (* i i) n i)] ; ...mark its multiples as non-prime
          (.set x j))))
    ; For all numbers [2, k], include it in the answer if it hasn't been marked non-prime
    (reduce (fn [acc index] 
              (if (.get x index) acc (conj acc index)))
            []
            (range 2 k))))

(def PRIMES (primes 10000)) ; Trial and error, baby!

(defn factors 
  ([n] (factors n []))
  ([n f]
    (if (= n 1)
      f
      (when-let [p (first (drop-while #(not= (mod n %) 0) PRIMES))]
        (factors (/ n p) (conj f p))))))

(def LIMIT 600851475143)

(println (factors LIMIT))