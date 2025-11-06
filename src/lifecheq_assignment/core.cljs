(ns lifecheq-assignment.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as rf]
   [lifecheq-assignment.events :as events]
   [lifecheq-assignment.views :as views]
   [lifecheq-assignment.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main] root-el)))

(defn init []
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
