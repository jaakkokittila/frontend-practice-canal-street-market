(ns frontend-practice-canal-street-market.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [frontend-practice-canal-street-market.events :as events]
   [frontend-practice-canal-street-market.views :as views]))

(def debug?
  ^boolean goog.DEBUG)

(defn dev-setup []
  (when debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
