(ns film-ratings.views.index
  (:require [film-ratings.views.template :refer [page]]))
(defn list-options []
  (page
   [:div.container.jumbotron.bg-white.text-center
    [:row
     [:p
      [:a.btn.btn-primary {:href "/add-fil"} "映画を追加"]]]
    [:row
     [:p
      [:a.btn.btn-primary {:href "/list-films"} "映画リスト"]]]]))
