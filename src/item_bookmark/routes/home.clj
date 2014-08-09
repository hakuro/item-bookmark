(ns item-bookmark.routes.home
  (:require [item-bookmark.layout :as layout]
            [item-bookmark.util :as util]
            [item-bookmark.db.core :as db]
            [compojure.core :refer :all]
            [noir.response :refer [edn]]
            [clojure.pprint :refer [pprint]]))

(defn home-page []
  (layout/render
   "home.html" {:content (util/md->html "/md/docs.md")}))

(defn bookmark []
  (layout/render
   "bookmark.html" {:bookmarks (db/get-favorites-all)}))

(defn save-bookmark [url]
  (cond (empty? url) (bookmark)
        :else (do (db/save-favorite url 0) (bookmark))))

(defn save-document [doc]
  (pprint doc)
  {:status "ok"})

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/bookmark" []  (bookmark))
  (POST "/bookmark/save" [url]  (save-bookmark url))
  (POST "/save" {:keys [body-params]}
        (edn (save-document body-params))))
