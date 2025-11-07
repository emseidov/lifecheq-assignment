(ns lifecheq-assignment.views
  (:require
   [re-frame.core :as rf]
   [lifecheq-assignment.subs :as subs]
   [lifecheq-assignment.utils :refer [cx]]))

(defn timeline-card [{:keys [img title highlighted?]}]
  [:div
   {:class (cx "timeline-card"
               (when highlighted? "highlighted"))}
   [:div.pointer
    [:div.pointer-line]
    [:div.pointer-triangle]]
   [:div.content
    [:div.img
     [:img
      {:src (:src img)
       :alt (:alt img)}]]
    [:div.title
     [:span title]]]])

(defn timeline-opposite-content [& children]
  [:div.timeline-opposite-content
   children])

(defn timeline-milestone [& children]
  [:div.timeline-milestone
   children])

(defn timeline-content [& children]
  [:div.timeline-content
   children])

(defn timeline-connector [{:keys [type]}]
  [:div
   {:class (cx "timeline-connector"
               (when (= type :dotted) "dotted"))}
   (when (= type :dotted)
     (for [_ (range 1 (/ 140 6.8))]
       [:span.connector-dot]))])

(defn timeline-dot [{:keys [curr-milestone? type]}]
  [:div
   {:class (if (= type :arrow)
             "timeline-arrow"
             (cx "timeline-dot"
                 (when curr-milestone? "is-curr-milestone")))}])

(defn timeline-separator [& children]
  [:div.timeline-separator
   children])

(defn timeline-item [& children]
  [:li.timeline-item
   children])

(defn timeline [& children]
  [:ul.timeline
   children])

(defn main []
  (let [milestones @(rf/subscribe [::subs/milestones])]
    [:div.main
     [timeline
      [timeline-item
       [timeline-separator
        [timeline-dot
         {:curr-milestone? true}]
        [timeline-connector]]
       [timeline-content
        [timeline-milestone "You are here"]]]
      [timeline-item
       [timeline-opposite-content
        [timeline-card
         {:img {:src "/assets/family.svg"
                :alt "Baby's birth milestone"}
          :title "Baby's birth"}]]
       [timeline-separator
        [timeline-dot]
        [timeline-connector]]
       [timeline-content
        [timeline-milestone "In 1 year and 9 months"]
        [timeline-card
         {:img {:src "/assets/home.svg"
                :alt "New home milestone"}}]]]
      [timeline-item
       [timeline-opposite-content
        [timeline-card
         {:img {:src "/assets/going-holiday.svg"
                :alt "Holiday milestone"}
          :title "Holiday"}]]
       [timeline-separator
        [timeline-dot]
        [timeline-connector]]
       [timeline-content
        [timeline-milestone "In 3 years and 2 months"]]]
      [timeline-item
       [timeline-separator
        [timeline-dot]
        [timeline-connector]]
       [timeline-content
        [timeline-milestone "In 4 years and 9 months"]
        [timeline-card
         {:img {:src "/assets/em-fund.svg"
                :alt "Emergency fund milestone"}
          :title "Emergency fund"}]]]
      [timeline-item
       [timeline-separator
        [timeline-dot]
        [timeline-connector
         {:type :dotted}]]
       [timeline-content
        [timeline-milestone "In 8 years and 11 months"]
        [timeline-card
         {:img {:src "/assets/debt.svg"
                :alt "Debt free milestone"}
          :title "Debt free"}]]]
      [timeline-item
       [timeline-opposite-content
        [timeline-card
         {:img {:src "/assets/retirement.svg"
                :alt "Retirement milestone"}
          :title "Retire"}]]
       [timeline-separator
        [timeline-dot]
        [timeline-connector
         {:type :dotted}]]]
      [timeline-item
       [timeline-separator
        [timeline-dot
         {:type :arrow}]]
       [timeline-content
        [timeline-milestone "Ultimately"]
        [timeline-card
         {:img {:src "/assets/vision.svg"
                :alt "Ultimate milestone"}
          :title "Make a contribution to my community thorough philantrophy"
          :highlighted? true}]]]]]))
