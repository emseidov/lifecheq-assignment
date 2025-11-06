(ns lifecheq-assignment.views)

(def milestones
  [{:curr-milestone? true
    :milestone "You are here"
    :cards []}
   {:curr-milestone? false
    :milestone "In 1 year and 9 months"
    :cards [{:img {:src "/assets/home.svg"
                   :alt "New home milestone"}
             :title "New Home"}
            {:img {:src "/assets/family.svg"
                   :alt "Baby's birth milestone"}
             :title "Baby's Birth"}]}])

(defn timeline-card [{:keys [img title position]}]
  [:div.timeline-card
   (when (not= position :top)
     [:div.timeline-card-pointer
      [:div.line]
      [:div.triangle]])
   [:div.timeline-card-body
    [:div.timeline-card-img
     [:img
      {:src (:src img)
       :alt (:alt img)}]]
    [:div.timeline-card-title title]]
   (when (= position :top)
     [:div.timeline-card-pointer.top
      [:div.line]
      [:div.triangle]])])

(defn timeline-content-top [& children]
  [:div.timeline-content-top
   children])

(defn timeline-content [& children]
  [:div.timeline-content
   children])

(defn timeline-connector []
  [:div.timeline-connector])

(defn timeline-dot [{:keys [milestone?]}]
  [:div
   {:class (str "timeline-dot" (when milestone? " is-milestone"))}])

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
  [:div.main
   [timeline
    [timeline-item
     [timeline-separator
      [timeline-dot
       {:milestone? true}]
      [timeline-connector]]
     [timeline-content
      "You are here"]]
    [timeline-item
     [timeline-content-top
      [timeline-card
       {:img {:src "/assets/family.svg"
              :alt "Baby's birth milestone"}
        :title "Baby's Birth"
        :position :top}]]
     [timeline-separator
      [timeline-dot]
      [timeline-connector]]
     [timeline-content
      [:div "In 1 year and 9 months"]
      [timeline-card
       {:img {:src "/assets/home.svg"
              :alt "New home milestone"}
        :title "New Home"}]]]
    [timeline-item
     [timeline-separator
      [timeline-dot]
      [timeline-connector]]
     [timeline-content
      "In 3 years and 2 months"]]
    [timeline-item
     [timeline-separator
      [timeline-dot]
      [timeline-connector]]
     [timeline-content
      "In 4 years and 9 months"]]
    [timeline-item
     [timeline-separator
      [timeline-dot]
      [timeline-connector]
      [:div.arrow]]
     [timeline-content
      "In 8 years and 11 months"]]]])
