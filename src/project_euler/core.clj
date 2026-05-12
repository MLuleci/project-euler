(ns project-euler.core)

(defn -main [n & args]
  (let [sym (symbol (format "project-euler.solutions.%s" n))]
    (require sym)
    (time (println (apply (ns-resolve sym '-main) args)))))
