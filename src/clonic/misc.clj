
(defn token-result-merger [val1 val2]
  (cond 
      (nil? (first val1)) val2
      :else val1))