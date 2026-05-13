(ns project-euler.solutions.29
  (:require [clojure.math :as math]))

(def xs (range 2 101))

(defn -main []
  (count (into #{} (mapcat (fn [a] (map #(math/pow a %) xs)) xs))))