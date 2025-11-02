(defn zip [& nums]
  (when (some not-empty nums)
    (let [heads (map #(or (first %) 0) nums)
          tails (map rest nums)]
      (lazy-seq (cons heads (apply zip tails))))))

(defn trim [n] (drop-while zero? n))

(defn sum-pair [x y]
  (trim (reduce #(let [[carry & rest] %1
                       sum (apply + carry %2)]
                    (conj rest (rem sum 10) (quot sum 10)))
                '(0)
                (zip (reverse x) (reverse y)))))

(defn sum [& nums]
  (reduce sum-pair nums))

(defn mantissa [n d]
  (rem (quot (* n 10) d) 10))

(defn is-even? [n]
  (= (mod (or (last n) 0) 2) 0))

(defn is-equal? [x y]
  (every? = (zip x y)))

(defn is-zero? [n]
  (empty? (trim n)))

(defn halve [n]
  (trim (map + (map #(quot % 2) n)
               (cons 0 (butlast (map #(mantissa % 2) n))))))

(defn twice [n]
  (sum-pair n n))

(defn multiply-pair [x y] ; russian peasant method
  (loop [acc nil lhs x rhs y]
    (if (is-zero? rhs) acc
        (recur (if (is-even? rhs) acc (sum acc lhs))
               (twice lhs)
               (halve rhs)))))

(defn multiply [& nums]
  (reduce multiply-pair nums))

(defn from-int [n]
  (reverse (map #(mod % 10) (take-while pos? (iterate #(quot % 10) n)))))

(defn factorial [n]
  (apply multiply (map from-int (range 1 (inc n)))))

(println (time (reduce + (factorial 100))))