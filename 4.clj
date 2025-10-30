(require '[clojure.string :as str])

(defn is-palindrome [s] (= s (str/reverse s)))

(println (apply max
    (for [a (reverse (range 1000)) 
          b (reverse (range 1000))
          :let [x (* a b)]
          :when (is-palindrome (str x))]
      x)))