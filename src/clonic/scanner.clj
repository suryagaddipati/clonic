(ns clonic.scanner)
(def components  {
 :season {#"^springs?$"  :spring
               #"^summers?$"  :summer
               #"^(autumn)|(fall)s?$"  :autumn
               #"^winters?$"  :winter}

 :month {#"^jan\.?(uary)?$" 1
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

 :day-of-week         {#"^m[ou]n(day)?$" :monday
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

 :time     { #"^\d{12}(:?\d{2})?([\.:]?\d{2})?$" :time}

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
       match-key (first (filter #(not(nil?(re-find % s))) (keys key-map)))
       match-val (get key-map match-key)]
   {key match-val}))
 



(defn parse-units [input]
  (let [ mapper (partial find-unit input)]
    (reduce merge (map mapper (keys components) ))))
