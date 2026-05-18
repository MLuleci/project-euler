; Classic dynamic programming problem, with two solutions:
; 1. Top-down recursive algorithm with memoization
; 2. Bottom-up matrix-based dp algorithm
; The former is easier to implement in a functional language like Clojure,
; so I'm going to do the latter :)

(ns project-euler.solutions.31)

(def coins [1 2 5 10 20 50 100 200]) ; in pence

(defn make-change
  ([n] (make-change n coins (repeat (inc n) 0)))
  ([n [coin & rest] memo]
    (if (not coin)
        (last memo) ; 0 -> n
        (make-change n rest 
          (reduce (fn [acc i]
                      (conj acc (if (> coin i)
                                    (nth memo i) ; can't use the coin
                                    (+ (nth memo i)             ; don't use the coin
                                       (nth acc (- i coin)))))) ; use the coin
                  [1]
                  (range 1 (inc n)))))))

(defn -main [] (make-change 200))