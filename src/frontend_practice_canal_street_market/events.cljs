(ns frontend-practice-canal-street-market.events
  (:require
   [re-frame.core :as re]))

(def default-db
  {:active-section "home"
   :email ""})

(re/reg-event-db 
 ::set-active-section
 (fn [db [_ section]]
   (assoc db :active-section section)))

(re/reg-event-db
 ::set-email
 (fn [db [_ email]]
   (assoc db :email email)))

(re/reg-event-db
 ::initialize-db
 (fn [_ _]
   default-db))
