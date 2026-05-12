(ns project-euler.solutions.15)

(def paths (memoize (fn [x y n]
                      (cond (= x y n) 0
                            (or (= x n) (= y n)) 1
                            :else (+ (paths (inc x) y n)
                                     (paths x (inc y) n))))))

(defn -main [] (paths 0 0 20))