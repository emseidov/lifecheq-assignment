(ns lifecheq-assignment.db)

(def default-db
  {:milestones
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
              :title "Baby's Birth"}]}]})
