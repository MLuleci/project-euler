(ns project-euler.solutions.19)

(def start (java.util.Calendar/getInstance))
(.set start 1901 0 1)

(def end (java.util.Calendar/getInstance))
(.set end 2000 12 31)

(defn add-months [n c]
  (let [clone (.clone c)]
    (.add clone java.util.Calendar/MONTH n) 
    clone))

(defn sunday? [c]
  (= (.get c java.util.Calendar/DAY_OF_WEEK) java.util.Calendar/SUNDAY))

(defn sundays [start end]
  (filter sunday? 
          (take-while #(.before % end) 
                      (iterate (partial add-months 1) start))))

(defn -main [] (count (sundays start end)))