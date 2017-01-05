(ns botsponder.service-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [botsponder.service :refer :all]))

(def apiai-test-payload
  "{\"result\": {\"action\": \"magic\", \"parameters\": {\"item\": \"rabbit\"}}}")

(deftest test-service
  (testing "speech-response"
    (let [response (speech-response "miracles" "dinosaur")]
      (is (= (response {:status 200
                        :headers {"Content-Type" "application/json"}
                        :body "{\"speech\":You asked me to perform miracles on a dinosaur.\"}"})))))

  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:headers response) {"Content-Type" "text/html; charset=utf-8"}))
      (is (= (:body response) "<h1>Botsponder</h1><p>It's working...</p>"))))

  (testing "bot route"
    (let [response (app (mock/body (mock/request :post "/bot") apiai-test-payload) )]
      (is (= (:status response) 200))
      (is (= (:headers response) {"Content-Type" "application/json"}))
      (is (= (:body response) "{\"speech\":\"You asked me to perform magic on a rabbit.\"}"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))
      (is (= (:body response) "Not Found")))))
