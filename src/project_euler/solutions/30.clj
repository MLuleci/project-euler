(ns project-euler.solutions.30
  (:require [clojure.math :as math]))

(def powers (zipmap (range 0 10) 
                    (map #(int (math/pow % 5)) 
                          (range 0 10))))

(defn digits 
  ([n] (digits n '()))
  ([n acc] (if (= n 0) acc
               (let [i (mod n 10)
                     r (int (/ n 10))]
                  (digits r (conj acc i))))))

(defn valid? [n]
  (let [ds (digits n)]
    (and (> (count ds) 1)
         (= n (apply + (map powers ds))))))

(defn -main []
  (->> (range 0 1000000) ; Wild guess at the upper bound :)
       (filter valid?)
       (apply +)))