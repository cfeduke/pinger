(ns pinger.core
	(:gen-class)
	(:import (java.net URL)))
	
(defn response-code [address]
	(let [connection (.openConnection (URL. address))]
	(doto connection
		(.setRequestMethod "GET")
		(.connect))
	(.getResponseCode connection)))

(defn available? [address]
	(= 200 (response-code address)))
	
(defn -main []
	(let [addresses '("http://www.somethingofthatilk.com"
									  "http://amazon.com"
										"http://google.com/badurl")]

	(while true
		(doseq [address addresses]
			(println (available? address)))
			(Thread/sleep (* 1000 60)))))
