(ns clonic.core
   (:use clj-time.core)
   (:use [clojure.contrib.string  :only (split)] ))

(def months-of-year {  ["jan" "january" ] 1 ,  ["feb" "february"] 2 ,  ["mar" "march" ] 3, ["apr" "april"] 4, ["may"] 5, ["jun" "june"] 6,
              ["jul" "july"] 7, ["aug" "august"] 8, ["sep" "september"] 9, ["oct" "october"] 10, ["nov" "november"] 11, ["dec" "december"] 12})


(defn- has? [coll x]
  (not (empty? (filter #(= % x ) coll))))

(defn- month-idx [month]
    (get months-of-year 
      (first (filter #(has? % month) (keys months-of-year)))))

 (defn to-int [str]
    (try (Integer/parseInt str) 
         (catch NumberFormatException nfe 0)))
 (defn- to-hr [hr]
   17)

(defn parse-natural [x] 
  (case x
    "now" (now)
    "tomorrow" (plus (now) (days 1))))

(defn- parse-month [start  more] 
  (let [year (year (now))
        month (month-idx start) ] 
  (cond 
    (=  1 (count more))  (date-time  year month  (to-int (first more)))
    (=  2 (count more))  (date-time  year month  (to-int (first more)) (to-hr (nth more 1))) 
    )))

(defn parse-date [x] 
 (let [ [start & more] (split  #"\s"  x )]
  (cond 
    (nil? (month-idx start)) (parse-natural start)
    :else (parse-month start more)
    ))) 
