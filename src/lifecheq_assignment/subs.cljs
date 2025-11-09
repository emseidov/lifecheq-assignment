(ns lifecheq-assignment.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 ::timeline-items
 (fn [db]
   (:timeline-items db)))

