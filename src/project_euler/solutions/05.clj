(ns project-euler.solutions.5)

(defn is-divisible [n m]
  (->> (range m)
       (map inc)
       (drop-while #(= (mod n %) 0))
       (empty?)))

(defn find-divisible [n]
  (->> (range)
       (map inc)
       (drop-while #(not (is-divisible % n)))
       (first)))

(defn -main [] (find-divisible 20))
