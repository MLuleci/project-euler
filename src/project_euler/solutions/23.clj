(ns project-euler.solutions.23
  (:require [clojure.math :as math]))

(def sum-of-factors
  (memoize (fn [n] 
    (if (= n 1) 1
        (let [l (int (math/ceil (math/sqrt n)))]
          (->> (range 2 l)
               (filter #(= (mod n %) 0))
               (map #(+ % (/ n %)))
               (reduce + (inc (if (= (* l l) n) l 0)))))))))

(defn abundant? [n]
  (> (sum-of-factors n) n))

(def LIMIT 28124)
(def ABUNDANTS (filter abundant? (range 1 LIMIT)))
(def PAIRS (into #{} (mapcat (fn [x] (filter #(< % LIMIT) (map #(+ x %) ABUNDANTS))) ABUNDANTS)))

(defn -main [] (reduce + (filter #(nil? (PAIRS %)) (range 1 LIMIT))))