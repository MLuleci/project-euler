; The quadratics of the form n^2 + an + b, where |a| < 1000 and |b| <= 1000
; which generate consecutive primes starting with n = 0.
; The search space is limited by `b` required to be prime and positive (168 such numbers).
; And since negative numbers are not prime, `a` must be such that:
; a^2 - 4b < 0, i.e. discriminant indicates no solutions hence -2√b < a < 1000

(ns project-euler.solutions.27
  (:require [project-euler.primes :as p])
  (:require [clojure.math :as math]))

(def bs (take-while #(< % 1000) (p/primes)))

(defn qf [a b n] (+ (* n n) (* a n) b))

(defn count-qf [a b]
  (count (take-while #(p/prime? (qf a b %)) (range))))

(defn max-qf [b]
  (let [sb (int (math/floor (math/sqrt b)))
        lo (* -2 sb)
        hi (* 2 sb)]
    (reduce (fn [acc a]
              (let [m (first acc)
                    n (count-qf a b)]
                (if (> m n) acc [n (* a b)])))
            [0 0] ; [n, a * b]
            (range lo (inc hi)))))

(defn -main []
  (second
    (reduce (fn [acc b]
              (let [m (first acc)
                    x (max-qf b)
                    n (first x)]
                (if (> m n) acc x)))
            [0 0]
            bs)))