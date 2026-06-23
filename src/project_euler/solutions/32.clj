(ns project-euler.solutions.32
  (:require (clojure [string :as string]
                     [set :as set]))
  (:require [project-euler.permute :as p]))

(defn parse-digits [xs]
  (Integer/parseInt (string/join xs)))

(defn split-digits [n xs]
  (map parse-digits (split-at n xs)))

(defn slide-products [xs]
  (map #(let [[a b] (split-digits % xs)] (* a b)) (range 1 (count xs))))

(defn pandigital? [digits product]
  (some #(= % product) (slide-products digits)))

(defn slide-pandigital 
  ([xs] (slide-pandigital 1 xs #{}))
  ([index xs found]
    (if (= index (count xs))
      found
      (let [[a b] (split-at index xs)
            product (parse-digits b)]
        (slide-pandigital (inc index) xs (if (pandigital? a product) (conj found product) found))))))

(defn -main []
  (->> (p/permute (range 1 10))
       (reduce #(set/union %1 (slide-pandigital %2)) #{})
       (apply +)))