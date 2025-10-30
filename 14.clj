(defn collatz [n] 
  (cond (= n 1) 1 
        (= (mod n 2) 0) (/ n 2)
        :else (+ (* 3 n) 1)))

(def length 
  (memoize (fn [n] 
            (if (= n 1) 1 
                (inc (length (collatz n)))))))

(println (time (apply max-key length (range 1 1e6))))