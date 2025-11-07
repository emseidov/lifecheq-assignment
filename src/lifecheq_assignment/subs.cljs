(ns lifecheq-assignment.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 ::milestones
 (fn [db]
   (:milestones db)))

