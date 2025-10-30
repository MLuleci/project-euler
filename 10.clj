(require '[clojure.math :as math])

(defn k-primes [n]
  (filter #(<= (* % %) n)
    (lazy-cat
      (range 7 n 6)     ; 6k+1 -> 7, 13, 19, ...
      (range 11 n 6)))) ; 6k+5 -> 11, 17, 23, ...

(defn divisible-by? [n p]
  (= (mod n p) 0))

(defn is-prime? [n]
  (or (contains? #{2 3 5} n)
      (not (or (< n 2)
               (some #(divisible-by? n %) '(2 3 5))
               (some #(divisible-by? n %) (k-primes n))))))

; 2e6 -> 142,913,828,922
; takes about 10 minutes to compute :)
; see solution #12 for a faster prime tester
(println (apply + (filter is-prime? (range 2e6))))