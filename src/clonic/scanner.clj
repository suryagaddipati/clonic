(ns clonic.scanner
 (:use  [clojure.contrib.string  :only (split lower-case replace-re)] 
      [clojure.contrib.seq  :only (find-first)] 
        [clonic.normalizer :only (normalize)]
        [clonic.util :only (re-matches? chunkify)]
        ))
(def components  {
                  
 :chronology {#"last"  :last
               #"this" :this
               #"next"  :next}                 
  
 :pointer { #"\bpast\b"   :past,
                 #"\bfuture\b"  :future,
                 #"\bin\b"  :future}                
 
 :day-of-month  { #"^(\d*)(st|nd|rd|th)$" #(second (re-find #"^(\d*)(st|nd|rd|th)$" %))}     
 
  :time     { #"(\d)([ap]m|oclock)\b" #(second (re-find #"(\d)([ap]m|oclock)\b" %))}
                  
                  
 :season     {#"^springs?$"  :spring
               #"^summers?$"  :summer
               #"^(autumn)|(fall)s?$"  :autumn
               #"^winters?$"  :winter}

 :month      {#"^jan\.?(uary)?$" 1
               #"^feb\.?(ruary)?$" 2
               #"^mar\.?(ch)?$" 3
               #"^apr\.?(il)?$" 4
               #"^may$" 5
               #"^jun\.?e?$" 6
               #"^jul\.?y?$" 7
               #"^aug\.?(ust)?$" 8
               #"^sep\.?(t\.?|tember)?$" 9
               #"^oct\.?(ober)?$" 10
               #"^nov\.?(ember)?$" 11
               #"^dec\.?(ember)?$" 12}

 :day-of-week  {#"^m[ou]n(day)?$" :monday
               #"^t(ue|eu|oo|u|)s(day)?$" :tuesday
               #"^tue$" :tuesday
               #"^we(dnes|nds|nns)day$" :wednesday
               #"^wed$" :wednesday
               #"^th(urs|ers)day$" :thursday
               #"^thu$" :thursday
               #"^fr[iy](day)?$" :friday
               #"^sat(t?[ue]rday)?$" :saturday
               #"^su[nm](day)?$" :sunday} 

 :period   {#"^ams?$" :am
               #"^pms?$" :pm
               #"^mornings?$" :morning
               #"^afternoons?$" :afternoon
               #"^evenings?$" :evening
               #"^(night|nite)s?$" :night}



 :unit       {#"^years?$" :year
               #"^seasons?$" :season
               #"^months?$" :month
               #"^fortnights?$" :fortnight
               #"^weeks?$" :week
               #"^weekends?$" :weekend
               #"^(week|business)days?$" :weekday
               #"^days?$" :day
               #"^hours?$" :hour
               #"^minutes?$" :minute
               #"^seconds?$" :second}
})



(defn find-unit [s key]
 (let [key-map  (get components key )
       match-key  (find-first #(re-matches? % s) (keys key-map))
       match-val (get key-map match-key)]
  (if (fn? match-val)
     {key  (match-val  s)}
     {key  match-val }
     )))
 

(defn parse-units [input]
  (let [ mapper (partial find-unit input)]
    (reduce merge (map mapper (keys components) ))))


(defn parse-toke [x] 
 (let [ tokens (split  #"\s"  (normalize x) )
        reducer (partial merge-with str)]
  (reduce reducer (map parse-units  tokens)))) 
