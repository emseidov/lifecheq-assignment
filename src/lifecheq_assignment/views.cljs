(ns lifecheq-assignment.views
  (:require
   [lifecheq-assignment.utils :refer [cx]]))

(defn timeline-card [{:keys [img desc highlighted? align-left?]
                      :or {img {:src ""
                                :alt "No image provided."}
                           desc ""}}]
  [:div
   {:class (cx "timeline-card"
               (when highlighted? "highlighted")
               (when align-left? "align-left"))}
   [:div.pointer
    [:div.pointer-line]
    [:div.pointer-triangle]]
   [:div.content
    [:figure.img
     [:img
      {:src (:src img)
       :alt (:alt img)}]]
    [:div.desc
     [:span desc]]]])

(defn timeline-milestone [{:keys [label width]
                           :or {label "" width 100}}]
  [:div.timeline-milestone
   {:style {:width width}}
   [:span label]])

(defn timeline-opposite-content [& children]
  (into [:div.timeline-opposite-content] children))

(defn timeline-content [& children]
  (into [:div.timeline-content] children))

(defn timeline-connector [{:keys [style length]
                           :or {style :solid length 140}}]
  (let [spacing 6.5]
    [:div
     {:class (cx "timeline-connector"
                 (when (= style :dotted) "dotted"))
      :style {:width length}}
     (when (= style :dotted)
       (for [idx (range 1 (/ length spacing))]
         ^{:key idx}
         [:span.connector-dot]))]))

(defn timeline-dot [{:keys [type]
                     :or {type :dot}}]
  [:div
   {:class (if (= type :arrow)
             "timeline-arrow"
             "timeline-dot")}])

(defn timeline-separator [& children]
  (into [:div.timeline-separator] children))

(defn timeline-item [{:keys [curr-milestone?]} & children]
  (into [:li
         {:class (cx "timeline-item"
                     (when curr-milestone? "curr-milestone"))}] children))

(defn timeline [{:keys [curr-milestone-idx]
                 :or {curr-milestone-idx 0}} & children]
  (into
   [:ul.timeline]
   (map-indexed
    (fn [idx [child & grandchildren]]
      (into [child
             {:curr-milestone? (= idx curr-milestone-idx)}] grandchildren))
    children)))

(defn main []
  [:div.main
   ;; Timeline items can be generated dynamically from data, but we define
   ;; them explicitly here to make it easier to play with the timeline API.
   [timeline
    {:curr-milestone-idx 0}
    [timeline-item
     [timeline-separator
      [timeline-dot]
      [timeline-connector
       {:length 67}]]
     [timeline-content
      [timeline-milestone
       {:label  "You are here"
        :width 60}]]]
    [timeline-item
     [timeline-opposite-content
      [timeline-card
       {:img {:src "/assets/family.svg"
              :alt "Baby’s birth milestone"}
        :desc "Baby’s birth"}]]
     [timeline-separator
      [timeline-dot]
      [timeline-connector
       {:length 127}]]
     [timeline-content
      [timeline-milestone
       {:label "In 1 year and 9 months"}]
      [timeline-card
       {:img {:src "/assets/home.svg"
              :alt "New home milestone"}
        :desc "New home"}]]]
    [timeline-item
     [timeline-opposite-content
      [timeline-card
       {:img {:src "/assets/going-holiday.svg"
              :alt "Holiday milestone"}
        :desc "Holiday"}]]
     [timeline-separator
      [timeline-dot]
      [timeline-connector
       {:length 133}]]
     [timeline-content
      [timeline-milestone
       {:label "In 3 years and 2 months"
        :width 90}]]]
    [timeline-item
     [timeline-separator
      [timeline-dot]
      [timeline-connector
       {:length 191}]]
     [timeline-content
      [timeline-milestone
       {:label "In 4 years and 9 months"
        :width 90}]
      [timeline-card
       {:img {:src "/assets/em-fund.svg"
              :alt "Emergency fund milestone"}
        :desc "Emergency fund"}]]]
    [timeline-item
     [timeline-separator
      [timeline-dot]
      [timeline-connector
       {:style :dotted
        :length 84}]]
     [timeline-content
      [timeline-milestone
       {:label "In 8 years and 11 months"}]
      [timeline-card
       {:img {:src "/assets/debt.svg"
              :alt "Debt free milestone"}
        :desc "Debt free"}]]]
    [timeline-item
     [timeline-opposite-content
      [timeline-card
       {:img {:src "/assets/retirement.svg"
              :alt "Retirement milestone"}
        :desc "Retire"}]]
     [timeline-separator
      [timeline-connector
       {:style :dotted
        :length 87}]]]
    [timeline-item
     [timeline-separator
      [timeline-dot
       {:type :arrow}]]
     [timeline-content
      [timeline-milestone
       {:label "Ultimately"}]
      [timeline-card
       {:img {:src "/assets/vision.svg"
              :alt "Ultimate milestone"}
        :desc "Make a contribution to my community through philantrophy"
        :align-left? true
        :highlighted? true}]]]]])
