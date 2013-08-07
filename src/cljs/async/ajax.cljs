(ns aync.ajax
  (:require [clojure.core.async :as asynclib :refer [<!! >!! timeout chan alt!!]]))

(defn fake-search [kind]
  (fn [c query]
    (future
     (<!! (timeout (rand-int 100)))
     (>!! c [kind query]))))

(def web1 (fake-search :web1))
(def web2 (fake-search :web2))
(def image1 (fake-search :image1))
(def image2 (fake-search :image2))
(def video1 (fake-search :video1))
(def video2 (fake-search :video2))

(defn fastest [query & replicas]
  (let [c (chan)]
    (doseq [replica replicas]
      (replica c query))
    c))

(defn google [query]
  (let [c (chan)
        t (timeout 80)]
    (future (>!! c (<!! (fastest query web1 web2))))
    (future (>!! c (<!! (fastest query image1 image2))))
    (future (>!! c (<!! (fastest query video1 video2))))
    (loop [i 0 ret []]
      (if (= i 3)
        ret
        (recur (inc i) (conj ret (alt!! [c t] ([v] v))))))))

;(google "clojure")

