(require '(clojure [string :as str] [math :as math]))

(def ONES {
  0 ""
  1 "one"
  2 "two"
  3 "three"
  4 "four"
  5 "five"
  6 "six"
  7 "seven"
  8 "eight"
  9 "nine"
})

(def TEENS {
  11 "eleven"
  12 "twelve"
  13 "thirteen"
  14 "fourteen"
  15 "fifteen"
  16 "sixteen"
  17 "seventeen"
  18 "eighteen"
  19 "nineteen"
})

(def TENS {
  0 ""
  1 "ten"
  2 "twenty"
  3 "thirty"
  4 "forty"
  5 "fifty"
  6 "sixty"
  7 "seventy"
  8 "eighty"
  9 "ninety"
})

(defn number->string [n]
  (let [ones (mod n 10)
        teens (mod n 100)
        tens (mod (math/floor-div n 10) 10)
        hundreds (mod (math/floor-div n 100) 10)
        thousands (mod (math/floor-div n 1000) 10)]
    (str/join " " (remove str/blank? [
      (when-not (= thousands 0) (format "%s thousand" (ONES thousands))) ; thousands
      (when-not (= hundreds 0) (format "%s hundred" (ONES hundreds))) ; hundreds
      (when-not (or (and (= thousands 0) (= hundreds 0)) (= teens 0)) "and") ; thousands/hundreds + tens/teens/ones
      (or (TEENS teens) (TENS tens)) ; tens/teens
      (when-not (contains? TEENS teens) (ONES ones)) ; ones
    ]))))

(defn count-chars [s]
  (count (str/replace s #"\s+" "")))

(println (time (reduce + (map #(count-chars (number->string %)) (range 1 1001)))))