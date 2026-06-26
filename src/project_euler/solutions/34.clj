(ns project-euler.solutions.34)

(def factorial 
  (memoize (fn [n]
              (if (< n 2) 1
                  (* n (factorial (dec n)))))))

(defn digits 
  ([n] (digits n nil))
  ([n ds] (if (= n 0) ds 
              (digits (quot n 10) 
                      (conj ds (rem n 10))))))

(defn fod [n]
  (reduce + (map factorial (digits n))))

(defn -main []
  (reduce + (filter #(= (fod %) %) 
                    (drop 10 (range 50000))))) ; Trial-and-error for upper bound