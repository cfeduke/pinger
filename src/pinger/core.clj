(ns pinger.core
	(:gen-class)
	(:use [clojure.tools.logging :only (info error)])
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

(defn record-availability [address]
  (if (available? address)
    (info (str address "is responding normally"))
    (error (str address "is not available"))))
	
(defn check []
	(let [addresses '("http://www.somethingofthatilk.com"
									  "http://amazon.com"
										"http://google.com/badurl")]

		(doseq [address addresses]
			(record-availability address))))

(def immediately 0)
(def every-minute (* 60 1000))

(defn start []
  (scheduler/periodically check immediately every-minute))
  
(defn stop []
  (scheduler/shutdown))
  
(defn -main []
  (start))
