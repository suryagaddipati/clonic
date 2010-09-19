(ns clonic.util)

(defn re-matches? [regex input]
  (not(nil?(re-find regex input))))


(defn safe-nth [lst idx]
  (if (> (count lst) idx)
    (nth lst idx)
    nil))


(defn chunkify [lst]
  (map-indexed #(vector %2 (safe-nth lst (inc %1))) lst) )