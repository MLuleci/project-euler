(ns project-euler.solutions.26
  (:require [project-euler.primes :as p])
  (:require [clojure.math :as math]))

(defn order [a n]
  (reduce (fn [acc i] (let [r (mod acc n)] (if (= 1 r) (reduced i) (* r a))))
          a
          (rest (range))))

(defn -main []
  (->> (p/primes)
       (take-while #(< % 1000))
       (drop 3) ; skip 2, 3, and 5
       (apply max-key #(order 10 %))))
