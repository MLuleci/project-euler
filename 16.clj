(require '[clojure.math :as math])

(defn zip [& xs] 
  (when (some not-empty xs)
    (let [fs (map #(or (first %) 0) xs)
          rs (map rest xs)]
      (lazy-seq (cons fs (apply zip rs))))))

(defn add [& xs]
  (let [parts (map reverse xs)
        added (map #(apply + %) (apply zip parts))]
    (reduce #(let [[carry & rest] %1
                   value (+ carry %2)]
                (conj rest (mod value 10) (math/floor-div value 10)))
            '(0) added)))

(defn trim [n] (drop-while #(= % 0) n))

(defn multiply
  ([a b]
    (let [lhs (reverse a)
          rhs (reverse b)
          parts (map #(multiply lhs %1 (inc %2)) rhs (range))]
      (trim (apply add parts))))
  ([lhs num scale]
    ; Multiply `lhs` by `num` at the `scale`s place
    ; e.g. (multiply 123 5 2) = 123 * 50
    ; name `scale` is misleading but 1 = 1s, 2 = 10s, etc.
    (reduce #(let [[carry & rest] %1
                   value (+ carry (* %2 num))]
                (conj rest (mod value 10) (math/floor-div value 10)))
            (repeat scale 0) lhs)))

(def pow (memoize (fn [a b]
  (cond (= b 0) 1
        (= b 1) a
        (= (mod b 2) 0) (let [r (pow a (/ b 2))] (multiply r r))
        :else (multiply (pow a (- b 1)) a)))))

(println (time (apply + (pow '(2) 1000))))