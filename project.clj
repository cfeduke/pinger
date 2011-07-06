(defproject pinger "0.0.1-SNAPSHOT"
  :description "A website availability tester"
  :dependencies [[org.clojure/clojure "1.3.0-beta1"]]
	:main pinger.core)

(ns pinger.core
	(:import (java.net URL))
	(:gen-class))
	
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