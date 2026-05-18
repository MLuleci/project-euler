(ns project-euler.permute)

(defn swap [i j coll]
  (let [ei (nth coll i)
        ej (nth coll j)]
    (assoc (assoc coll i ej) j ei)))

(defn permute 
  ([coll] (permute (count coll) (vec coll)))
  ([k coll] (cond (<= k 0) nil
                  (= k 1) (list coll)
                  :else (reduce (fn [acc index]
                                  (let [head (first acc)]
                                    (concat (permute (- k 1) (swap (if (even? k) index 0) (- k 1) head)) acc)))
                                (permute (- k 1) coll)
                                (range 0 (- k 1))))))

