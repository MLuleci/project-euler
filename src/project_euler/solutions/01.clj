(ns project-euler.solutions.1)

(defn is-multiple [i]
  (or (= 0 (mod i 3)) (= 0 (mod i 5))))

(defn fizzbuzz [n]
  (reduce #(+ %1 (if (is-multiple %2) %2 0)) 0 (range n)))

(defn -main [] (fizzbuzz 1000))
