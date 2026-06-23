(ns project-euler.permute)

(defn max-index [f & xs]
  (last (keep-indexed #(when %2 %1) (apply map f xs))))

(defn swap [coll i j]
  (-> coll
      (assoc i (nth coll j))
      (assoc j (nth coll i))))

(defn render [coll indices]
  (map #(nth coll %) indices))

(defn next-order [p]
  (when-let [k (max-index < p (rest p))]
    (let [pk (nth p k)
          l (max-index #(< pk %) p)
          [a b] (split-at (inc k) (swap p k l))]
      (vec (concat a (reverse b))))))

(defn permute 
  ([coll] (permute coll (vec (range (count coll)))))
  ([coll p]
    (lazy-seq
      (cons (render coll p)
            (when-let [n (next-order p)]
              (permute coll n))))))
