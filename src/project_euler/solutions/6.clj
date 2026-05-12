(ns project-euler.solutions.6)

(defn square [i] (* i i))

(defn sum-of-squares [n]
  (reduce #(+ %1 (square %2)) (map inc (range n))))

(defn square-of-sum [n]
  (square (reduce + (map inc (range n)))))

(defn solve [n]
  (- (square-of-sum n) (sum-of-squares n)))

(defn -main [] (solve 100))
