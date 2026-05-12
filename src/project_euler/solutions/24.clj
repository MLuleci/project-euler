; Idea: in lexicographic order we can skip entire groups
; e.g. 0xxxxxxxxx -> 9! = 362,880 permutations
;      1xxxxxxxxx -> another group of the same size (total 725,760 permutations)
;      2xxxxxxxxx -> total 1,088,640 permutations, past 1M
;
; The last group contains the 1Mth permutation, so we only have to search 
; through that one. We can recurse further:
; e.g. 20xxxxxxxx -> 8! = 40,320 permutations (total 725,760 + 40,320 = 766,080)
;      21xxxxxxxx -> total 806,400
;      ...
;      25xxxxxxxx -> total 967,680
;      26xxxxxxxx -> total 1,008,000
;
; then recurse again starting with the prefix '25', and so on.
(ns project-euler.solutions.24)

(defn factorial [n] (reduce * (range 1 (inc n))))

(defn without [coll n] (filterv #(not= % n) coll))

(defn search [coll limit]
  (when (and (not-empty coll) (> limit 0))
    (let [size (count coll)
          group (factorial (dec size))
          index (some #(when (>= (* (inc %) group) limit) %) (range size))
          digit (get coll index)]
      (cons digit
            (search (without coll digit)
                    (- limit (* index group)))))))

(defn -main [] (search (vec (range 10)) 1e6))