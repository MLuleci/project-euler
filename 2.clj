(def LIMIT 4e6)

(defn fib
  ([] (fib 1 2))
  ([n m] (lazy-seq (cons n (fib m (+ m n))))))

(print 
  (reduce + 
          (filter #(= (mod % 2) 0) 
                  (take-while #(<= % LIMIT) 
                              (fib)))))
