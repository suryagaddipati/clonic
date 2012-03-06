Description
==========
Chronic is a natural language date/time parser written in pure Clojure. See below for the wide variety of formats Clonic will parse.
Inspired by Ruby's Chronic

Installation
==========
You can include it in your `project.clj`:

      :dev-dependencies [[clonic "1.0.0-SNAPSHOT"]])


Use
==========


(parse-date "now" )
;current 

(parse-date  "tomorrow") 
;(plus current (days 1)))

(parse-date "may 27") 
;2012 5 27

(parse-date "may 28 5pm") 
; 2012 5 28 17

(parse-date "may 28 5am") 
; 2012 5 28 5
