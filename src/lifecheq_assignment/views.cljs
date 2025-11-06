(ns lifecheq-assignment.views
  (:require
   [re-frame.core :as rf]
   [lifecheq-assignment.subs :as subs]))

(defn main-panel []
  (let [name @(rf/subscribe [::subs/name])]
    [:div
     [:h1
      "Hello from " name]]))
