(ns compojure.api.routes-test
  (:require [compojure.api.routes :refer :all]
            [midje.sweet :refer :all])
  (:import [java.security SecureRandom]
           [org.joda.time LocalDate]))

(facts "->path"

  (fact "missing path parameter"
    (->path "/api/:kikka" {})
    => (throws IllegalArgumentException))

  (fact "missing serialization"
    (->path "/api/:kikka" {:kikka (SecureRandom.)})
    => (throws com.fasterxml.jackson.core.JsonGenerationException))

  (fact "happy path"
    (->path "/a/:b/:c/d/:e/f" {:b (LocalDate/parse "2015-05-22")
                               :c 12345
                               :e :kikka})
    => "/a/2015-05-22/12345/d/kikka/f"))
