(ns film-ratings.views.film
  (:require [film-ratings.views.template :refer [page labeled-radio]]
            [hiccup.form :refer [form-to label text-field text-area submit-button]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]))
(defn create-film-view
  []
  (page
   [:div.container.jumbotron.bg-light
    [:div.row
     [:h2 "映画を追加"]]
    [:div
     (form-to [:post "/add-film"]
              (anti-forgery-field)
              [:div.form-group.col-12
               (label :name "題名：")
               (text-field {:class "mb-3 form-control" :placeholder "映画の題名を入力"} :name)]
              [:div.form-group.col-12
               (label :description "詳細：")
               (text-area {:class "mb-3 form-control" :placeholder "映画の詳細を入力"} :description)]
              [:div.form-group.col-12
               (label :ratings "評価(1-5)：")]
              [:div.form-group.btn-group.col-12
               (map (labeled-radio "rating") (repeat 5 false) (range 1 6))]
              [:div.form-group.col-12.text-center
               (submit-button {:class "btn btn-primary text-center"} "追加")])]]))
(defn- film-attributes-view
  [name description rating]
  [:div
   [:div.row
    [:div.col-2 "題名："]
    [:div.col-10 name]]
   (when description
     [:div.row
      [:div.col-2 "詳細："]
      [:div.col-10 description]])
   (when rating
     [:div.row
      [:div.col-2 "評価："]
      [:div.col-10 rating]])])
(defn film-view
  [{:keys [name description rating]} {:keys [errors messages]}]
  (page
   [:div.container.jumbotron.bg-light
    [:div.row
     [:h2 "映画"]]
    (film-attributes-view name description rating)
    (when errors
      (for [error (doall errors)]
        [:div.row.alert.alert-danger
         [:div.col error]]))
    (when messages
      (for [message (doall messages)]
        [:div.row.alert.alert-success
         [:div.col message]]))]))

(defn list-films-view
  [films {:keys [messages]}]
  (page
   [:div.container.jumbotron.bg-light
    [:div.row [:h2 "映画"]]
    (for [{:keys [name description rating]} (doall films)]
      [:div
       (film-attributes-view name description rating)
       [:hr]])
    (when messages
      (for [message (doall messages)]
        [:div.row.alert.alert-success
         [:div.col message]]))]))
