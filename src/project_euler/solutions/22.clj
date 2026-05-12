(ns project-euler.solutions.22
  (:require [clojure.string :as string]))

(def NAMES (->> (slurp "names.txt")
                (#(string/split % #","))
                (map read-string)
                (sort)
                (vec)))

(defn name-value [s]
  (reduce #(+ %1 (inc (- (int %2) (int \A)))) 0 s))

(defn -main [] (reduce + (map-indexed #(* (inc %1) (name-value %2)) NAMES)))