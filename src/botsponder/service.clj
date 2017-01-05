(ns botsponder.service
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [clojure.tools.cli :as cli]
            [org.httpkit.server :refer [run-server]]))

(defn speech-response
  "Takes two strings, 'action' and 'item', concatenates them into a
  JSON-formatted speech act and returns it as part of a successful response payload."
  [action item]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/write-str
            {:speech (str "You asked me to perform " action " on a " item ".")})})

(defroutes app
  "Define all compojure routes for our bot application."
  (GET "/"
       []
       "<h1>Botsponder</h1><p>It's working...</p>")
  (POST "/bot"
        req
        (let [body   (-> req :body slurp (json/read-str :key-fn keyword))
              action (-> body :result :action)
              item   (-> body :result :parameters :item)]
          (speech-response action item)))
  (route/not-found "Not Found"))

(def cli-options
  [["-p" "--port PORT" "port number"
    :default 3000
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "must be a number between 0 and 65536"]]])

(defn -main
  "Serve the application with http-kit."
  [& args]
  (run-server app (:port (cli/parse-opts args cli-options))))
