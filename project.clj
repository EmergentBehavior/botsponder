(defproject botsponder "0.1.0-SNAPSHOT"
  :description "Botsponder is a simple application demonstrating the handling of, and response to, api.ai intents"
  :url "https://github.com/emergentbehavior/botsponder"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [compojure "1.5.1"]
                 [http-kit "2.2.0"]
                 [ring/ring-mock "0.3.0"]
                 [org.clojure/data.json "0.2.6"]]
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"
            :distribution :repo})
