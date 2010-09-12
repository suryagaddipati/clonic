(ns clonic.core
   (:use clj-time.core)
   (:use clojure.contrib.string))




(defn parse-date [x] 
 (let [ [start & more] (split  #"\s"  x )]
  (case start
    "now" (now)
    "tomorrow" (plus (now) (days 1))
    ))) 
