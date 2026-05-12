(ns project-euler.solutions.12
  (:require (clojure [math :as math] [set :as set])))

(defn triangles
  ([] (triangles 1 0))
  ([i n] (lazy-seq (cons (+ i n) (triangles (inc i) (+ i n))))))

(defn limit [n]
  (math/ceil (math/sqrt n)))

(defn prime? [n]
  (cond (< n 2) false
        (< n 4) true ; 2 & 3
        (= (mod n 2) 0) false
        (< n 9) true ; 5 & 7 (4, 6, and 8 were excluded)
        (= (mod n 3) 0) false
        :else (every? #(and (not (= (mod n %) 0))         ; test 6k-1
                            (not (= (mod n (+ % 2)) 0)))  ; test 6k+1
                      (range 5 (limit n) 6))))            ; every prime >3 can be written as 6k±1

(defn primes 
  ([] (cons 2 (primes 3)))
  ([n] (lazy-seq (if (prime? n) 
                     (cons n (primes (+ n 2)))
                     (primes (+ n 2))))))

(defn factors [n]
  (->> (range 1 (limit n))
       (filter #(= (mod n %) 0))
       (mapcat #(list % (/ n %)))
       (distinct)))

(defn -main [] (some #(when (> (count (factors %)) 500) %) (triangles)))