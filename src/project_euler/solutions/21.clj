(ns project-euler.solutions.21
  (:require [clojure.math :as math]))

(defn factors [n]
  (cons 1 
        (->> (range 2 (math/ceil (math/sqrt n)))
            (filter #(= (mod n %) 0))
            (mapcat #(list % (/ n %)))
            (distinct))))

(defn d [n] (reduce + (factors n)))
(def m (zipmap (range 10000) (map d (range 10000))))

(defn -main [] (reduce-kv (fn [acc k v] (if (and (not= (m k) k) (= (m v) k)) (+ acc k) acc)) 0 m))