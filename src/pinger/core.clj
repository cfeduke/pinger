(ns pinger.core
	(:gen-class)
	(:require [pinger.scheduler :as scheduler])
	(:import (java.net URL)))
	
(defn response-code [address]
	(let [connection (.openConnection (URL. address))]
	(doto connection
		(.setRequestMethod "GET")
		(.connect))
	(.getResponseCode connection)))

(defn available? [address]
	(= 200 (response-code address)))
	
(defn check []
	(let [addresses '("http://www.somethingofthatilk.com"
									  "http://amazon.com"
										"http://google.com/badurl")]

		(doseq [address addresses]
			(println (available? address)))))

(def immediately 0)
(def every-minute (* 60 1000))

(defn start []
  (scheduler/periodically check immediately every-minute))
  
(defn stop []
  (scheduler/shutdown))
  
(defn -main []
  (start))
