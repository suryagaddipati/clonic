(ns clonic.test.core
  (:use [clonic.core] :reload-all)
  (:use [clojure.test ])
  (:use clojure.contrib.string)
  (:use clj-time.core))

;(run-tests 'clonic.test.core)

(defn date-eq? [date1 date2]
 (and  (= (day date1) (day date2)) 
       (= (month date1) (month date2)) 
       (= (year date1) (year date2)) ))


(defn deq? [date exp]  (is (date-eq? date (parse-date exp))) )

(def current (now))

; (symbol (str "should-be-" expectation ))


(defmacro expect [ expectation date ]
  `(deftest ~(symbol (str "test-"  (replace-str " " "-" expectation)))   (deq? ~date ~expectation))  )


(expect "now" current )

(expect  "tomorrow"   (plus current (days 1)))

(expect "may 27"  (date-time 2010 5 27) )


