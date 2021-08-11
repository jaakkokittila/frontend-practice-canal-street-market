(ns frontend-practice-canal-street-market.views
  (:require
   [re-frame.core :as re]
   [frontend-practice-canal-street-market.subs :as subs]
   [frontend-practice-canal-street-market.events :as events]))

(defn footer []
 (let [email @(re/subscribe [::subs/email])]
  [:div.footer
   [:div.footer-top
    [:p.footer-text "Interested in becoming a vendor?"]
    [:p.footer-button.amatic.margin-center.pointer "click here"]]
   [:div.contact-section.flex-row
    [:div.contact-box.dashed-border.amatic.pointer "Email us"]
    [:div.contact-box.dashed-border.amatic.pointer "Follow us on Facebook"]
    [:div.contact-box.dashed-border.amatic.pointer "Follow us on Instagram"]]
   [:div.news-letter.dashed-border.margin-center.flex-row
    [:p.news-letter-text.amatic "Stay up to date with our newsletter"]
    [:input.email {:type "text" 
                   :value email 
                   :placeholder "Email"
                   :on-change #(re/dispatch [::events/set-email (-> % .-target .-value)])}]]
   [:p.footer-bottom.amatic "Jaakko Kittilä - Made for practice purposes"]]))

(defn top-part [title image-text text-section img-path]
  [:div.active-section-top
   [:p.active-section-title title]
   [:div.active-section-top-right
     text-section
    [:p.active-section-top-right-text image-text]
    (when img-path [:img.active-section-img {:src img-path}])]])


(defn animated-section [title text]
  [:div.animated-section.flex-row
   [:div.text-center.logo-large.logo-left.amatic "CM"]
   [:div.animated-section-content
    [:p.animated-title.margin-center title]
    [:p.animated-text.amatic.margin-center text]]
   [:div.text-center.logo-large.logo-right.amatic "CM"]])

(defn community []
  (let [active-section @(re/subscribe [::subs/active-section])]
    (if (= active-section "community")
      [:div.community.active-section
       [top-part
       "Canal St. Community"
       "文化"
        [:div.active-section-opening-hours
         [:p "Our mixed use space hosts"]
         [:p "ongoing events, podcasts"]
         [:p "& artists in residence"]]
        nil]
       [animated-section "Market Radio" "Podcasted from the market"]
       [footer]]
      [:div.community.inactive-section.pointer {:on-click #(re/dispatch [::events/set-active-section "community"])}
       [:p.inactive-section-text-1 "文化"]
       [:p.inactive-section-text-2.amatic "Community"]])))


(defn retail []
  (let [active-section @(re/subscribe [::subs/active-section])]
    (if (= active-section "retail")  
      [:div.retail.active-section
       [top-part
       "The Retail Market"
       "購物"
       [:div.active-section-opening-hours
        [:p "Retail Market (AFRAME Coffee) Hours:"]
        [:p "Mon – Fri: 8:00AM - 3:00PM"]
        [:p "Sat & Sun: 9:00AM - 3:00PM"]]
        "retail-section.jpg"]
       [animated-section "The Best of NYC" "All under one roof"]
       [footer]]
      [:div.retail.inactive-section.pointer {:on-click #(re/dispatch [::events/set-active-section "retail"])}
       [:p.inactive-section-text-1 "購物"]
       [:p.inactive-section-text-2.amatic "Retail"]])))

(defn food []
  (let [active-section @(re/subscribe [::subs/active-section])]
    (if (= active-section "food")
      [:div.food.active-section
       [top-part
        "The Food Hall"
        "餐饮"
        [:div.active-section-opening-hours
         [:p "Food Hall Hours:"]
         [:p "Mon – Sat: 11:00AM - 6:00PM"]
         [:p "Sun: 11:00AM - 6:00PM"]]
        "food-section.jpg"]
       [animated-section "Happy Hour" "Every Weekday, 5 - 7PM $4 Beer & $7 Wine Come Hang With Us! **** Tappy Tuesday Pay with Apple Pay and receive 20% off *Beer and Wine excluded*"]
       [footer]]
      [:div.food.inactive-section.pointer {:on-click #(re/dispatch [::events/set-active-section "food"])}
       [:p.inactive-section-text-1 "餐饮"]
       [:p.inactive-section-text-2.amatic "Food"]])))

(defn home []
  (let [active-section @(re/subscribe [::subs/active-section])]
    (if (= active-section "home")
      [:div.home.active-section
       [:div.text-center.pointer
        [:p.logo-text-active.amatic "CM"]]
       [:p.home-section-title "Canal Street Market is a carefully curated retail market, food hall & community space open year-round at 265 Canal Street."]
       [:img.home-img {:src "home-section.jpg"}]
       [animated-section "Market Events" ""]
       [footer]]
      [:div.home.inactive-section.pointer {:on-click #(re/dispatch [::events/set-active-section "home"])}
       [:div.text-center.pointer
        [:p.logo-text.amatic.margin-center "CM"]]])))

(defn main-panel []
  [:div.container.flex-row
   [home]
   [food]
   [retail]
   [community]])
