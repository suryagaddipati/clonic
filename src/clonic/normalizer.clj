(ns clonic.normalizer
  (:use  [clojure.contrib.string  :only (split lower-case replace-re)] 
        [clojure.contrib.str-utils2  :only (replace)]    ))

(defn pattern-replacer ( [x] x)
  ([x y]
    (replace x (first y) (second y))))

(defn pattern-formatter ( [x] x)
  ([x y]
     (.replaceAll (re-matcher (first y) x)(second y))
    ))

(defn normalize [in]
  (let [replace-patterns [(lower-case  in) [#"['\"\.,]", ""]
                 [#"\btoday\b", "this day"]
                 [#"\btomm?orr?ow\b", "next day"]
                 [#"\byesterday\b", "last day"]
                 [#"\bnoon\b", "12:00"]
                 [#"\bmidnight\b", "24:00"]
                 [#"\bbefore now\b", "past"]
                 [#"\bnow\b", "this second"]
                 [#"\b(ago|before)\b", "past"]
                 [#"\bthis past\b", "last"]
                 [#"\bthis last\b", "last"]
                 [#"\btonight\b", "this night"]
                 [#"\b(hence|after|from)\b", "future"]
                 ]
        format-patterns [ [#" \-(\d{4})\b", " tzminus$1"]
                              [#"\b(?:in|during) the (morning)\b", "$1"]
                              [#"\b(?:in the|during the|at) (afternoon|evening|night)\b", "$1"]
                              [#"\b\d+:?\d*[ap]\b","$0m"]
                            ;  [#"(\d)([ap]m|oclock)\b", "$1 $2"]
                              ]
        replaced-input  (reduce pattern-replacer replace-patterns)
         ] 
     
   (reduce pattern-formatter (cons  replaced-input  format-patterns))
  ))