(require '[clojure.math :as math])

(defn int-hypot [x y]
  (let [z (int (math/hypot x y))]
    (when (= (+ (* x x) (* y y)) (* z z)) z)))

(defn triples
  ([n] (triples 0 n (range 1 n)))
  ([a n bs]
    (lazy-seq
      (cond (nil? bs) (when (< a n) 
                        (triples (inc a) n (range (inc a) n)))
            (< a n) (let [[b & rest] bs
                          c (int-hypot a b)]
                      (if (nil? c)
                        (triples a n rest)
                        (cons (list a b c) (triples a n rest))))))))

(defn test-triple [a b c]
  (when (and (< a b c) (= (+ a b c) 1000))
    (* a b c)))

(->> (triples 1000)
     (filter #(apply test-triple %))
     (first)
     (apply *)
     (print))