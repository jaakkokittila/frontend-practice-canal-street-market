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
    [:p.footer-button "click here"]]
   [:div.contact-section
    [:div.contact-box.dashed-border "Email us"]
    [:div.contact-box.dashed-border "Follow us on Facebook"]
    [:div.contact-box.dashed-border "Follow us on Instagram"]]
   [:div.news-letter.dashed-border
    [:p.news-letter-text "Stay up to date with our newsletter"]
    [:input.email {:type "text" 
                   :value email 
                   :placeholder "Email"
                   :on-change #(re/dispatch [::events/set-email (-> % .-target .-value)])}]]
   [:p.footer-bottom "Jaakko Kittilä - Made for practice purposes"]]))


(defn animated-section [title text]
  [:div.animated-section
   [:div.logo.logo-large.logo-left "CM"]
   [:div.animated-section-content
    [:p.animated-title title]
    [:p.animated-text text]]
   [:div.logo.logo-large.logo-right "CM"]])

(defn community []
  (let [active-section @(re/subscribe [::subs/active-section])]
    (if (= active-section "community")
      [:div.community.active-section
       [:p.active-section-title "Canal St. Community"]
       [:div.active-section-top-right
        [:div.active-section-opening-hours
         [:p "Our mixed use space hosts"]
         [:p "ongoing events, podcasts"]
         [:p "& artists in residence"]]
        [:p.active-section-top-right-text "文化"]]
       [animated-section "Market Radio" "Podcasted from the market"]
       [footer]]
      [:div.community.inactive-section {:on-click #(re/dispatch [::events/set-active-section "community"])}
       [:p.inactive-section-text-1 "文化"]
       [:p.inactive-section-text-2 "Community"]])))


(defn retail []
  (let [active-section @(re/subscribe [::subs/active-section])]
    (if (= active-section "retail")
      [:div.retail.active-section
       [:p.active-section-title "The Retail Market"]
       [:div.active-section-top-right
        [:div.active-section-opening-hours
         [:p "Retail Market (AFRAME Coffee) Hours:"]
         [:p "Mon – Fri: 8:00AM - 3:00PM"]
         [:p "Sat & Sun: 9:00AM - 3:00PM"]]
        [:p.active-section-top-right-text "購物"]
        [:img.active-section-img {:src "retail-section.jpg"}]]
       [animated-section "The Best of NYC" "All under one roof"]
       [footer]]
      [:div.retail.inactive-section {:on-click #(re/dispatch [::events/set-active-section "retail"])}
       [:p.inactive-section-text-1 "購物"]
       [:p.inactive-section-text-2 "Retail"]])))

(defn food []
  (let [active-section @(re/subscribe [::subs/active-section])]
    (if (= active-section "food")
      [:div.food.active-section
       [:p.active-section-title "The Food Hall"]
       [:div.active-section-top-right
        [:div.active-section-opening-hours
         [:p "Food Hall Hours:"]
         [:p "Mon – Sat: 11:00AM - 6:00PM"]
         [:p "Sun: 11:00AM - 6:00PM"]]
        [:p.active-section-top-right-text "餐饮"]
        [:img.active-section-img {:src "food-section.jpg"}]]
       [animated-section "Happy Hour" "Every Weekday, 5 - 7PM $4 Beer & $7 Wine Come Hang With Us! **** Tappy Tuesday Pay with Apple Pay and receive 20% off *Beer and Wine excluded*"]
       [footer]]
      [:div.food.inactive-section {:on-click #(re/dispatch [::events/set-active-section "food"])}
       [:p.inactive-section-text-1 "餐饮"]
       [:p.inactive-section-text-2 "Food"]])))

(defn home []
  (let [active-section @(re/subscribe [::subs/active-section])]
    (if (= active-section "home")
      [:div.home.active-section
       [:div.logo
        [:p.logo-text-active "CM"]]
       [:p.home-section-title "Canal Street Market is a carefully curated retail market, food hall & community space open year-round at 265 Canal Street."]
       [:img.home-img {:src "home-section.jpg"}]
       [animated-section "Market Events" ""]
       [footer]]
      [:div.home.inactive-section {:on-click #(re/dispatch [::events/set-active-section "home"])}
       [:div.logo
        [:p.logo-text "CM"]]])))

(defn main-panel []
  [:div.container
   [home]
   [food]
   [retail]
   [community]])
