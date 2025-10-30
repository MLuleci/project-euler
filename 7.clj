(require '[clojure.math :as math])

(defn filter-multiples [i coll]
  (filter #(not (= (mod % i) 0)) coll))

(defn sieve-simple [n]
  (loop [ps [] xs (range 2 n)]
    (if (empty? xs)
      ps
      (let [[p & rest] xs]
        (recur
          (conj ps p)
          (filter-multiples p rest))))))

(defn sieve-chunk [primes chunk]
  (let [m (last chunk)
        ps (filter #(<= (* % %) m) primes)]
    (reduce #(filter-multiples %2 %1) chunk ps)))

(defn sieve [n]
  (let [d (int (math/sqrt n))
        init-d (+ d (- n (* d d)))
        primes (sieve-simple (inc init-d))]
    (loop [ps primes index init-d]
      (if (>= index n)
        ps
        (recur
          (concat ps (sieve-chunk ps (range index (+ index d 1))))
          (+ index d))))))

(println (nth (sieve 1000000) 10000))