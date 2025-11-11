(defn add 
  ([a b] (reverse (add (reverse a) (reverse b) 0)))
  ([lhs rhs carry] 
    (lazy-seq
      (when (or (some not-empty (list lhs rhs)) (not= carry 0))
        (let [l (or (first lhs) 0)
              r (or (first rhs) 0)
              s (+ l r carry)
              c (quot s 10)
              v (mod s 10)]
          (cons v (add (rest lhs) (rest rhs) c)))))))

(defn fib
  ([] (fib '(1) '(1)))
  ([a b] (lazy-seq (cons a (fib b (add a b))))))

(println (time (first (keep-indexed #(when (= (count %2) 1000) (inc %1)) (fib)))))