(ns frontend-practice-canal-street-market.subs
  (:require
   [re-frame.core :as re]))

(re/reg-sub
 ::active-section
 (fn [db]
   (:active-section db)))

(re/reg-sub
 ::email
 (fn [db]
   (:email db)))
