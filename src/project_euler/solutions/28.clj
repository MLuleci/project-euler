(ns project-euler.solutions.28)

(defn sum-ring [n]
  (if (= n 1) 1
      (let [i (* n n)
            s (- n 1)]
        (apply + (map #(- i (* s %)) (range 0 4))))))

(defn sum-spiral [n]
  (apply + (map #(sum-ring %) (range 1 (inc n) 2))))

(defn -main [] (sum-spiral 1001))