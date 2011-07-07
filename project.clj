(defproject pinger "0.0.1-SNAPSHOT"
  :description "A website availability tester"
  :dependencies [[org.clojure/clojure "1.3.0-beta1"]
                 [org.clojure/tools.logging "0.1.2"]
                 [log4j "1.2.16"]
                 [javax.mail/mail "1.4.1"]]
	:main pinger.core)

