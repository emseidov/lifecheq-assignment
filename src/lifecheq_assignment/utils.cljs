(ns lifecheq-assignment.utils
  (:require
   [clojure.string :as str]))

(defn cx [& classes]
  (->> classes
       (remove nil?)
       (str/join " ")))

