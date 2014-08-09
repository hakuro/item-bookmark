(ns item-bookmark.db.core
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [item-bookmark.db.schema :as schema]))

(defdb db schema/db-spec)

(defentity users)

(defn create-user [user]
  (insert users
          (values user)))

(defn update-user [id first-name last-name email]
  (update users
  (set-fields {:first_name first-name
               :last_name last-name
               :email email})
  (where {:id id})))

(defn get-user [id]
  (first (select users
                 (where {:id id})
                 (limit 1))))


(defentity favorite)

(defn create-favorite [favorite]
  (insert favorite
          (values favorite)))

(defn save-favorite [url price]
  (insert favorite
          (values {:user_id 0
               :url url
               :price price
               :last_updated (new java.util.Date)})))

(defn update-favorite [id user-id url price]
  (update favorite
  (set-fields {:user_id user-id
               :url url
               :price price
               :last_updated (new java.util.Date)})
  (where {:id id})))

(defn get-favorite [id]
  (first (select favorite
                 (where {:id id})
                 (limit 1))))

(defn get-favorites-all []
  (select favorite))

