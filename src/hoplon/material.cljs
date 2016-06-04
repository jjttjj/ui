(ns hoplon.material
  (:require
    [hoplon.ui       :refer [elem image]]
    [hoplon.ui.attrs :refer [c rt hx ev bk pt em px]])
  (:require-macros
    [hoplon.core  :refer [defelem for-tpl]]
    [javelin.core :refer [cell=]]))

;;; styles ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def f 21)
(def p 18)

(def card-title-font    15)
(def card-subtitle-font 14)

(def sc 0xCCC)

(def bgcolor :grey)

;;; elements ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defelem button [attrs elems]
  (button* :ph 14 :pv 10 :m :pointer :ah :center  :f p ;:r 5 :c (hx 0xFFF) :s 1 :sc (hx 0xCCC):f p :fc (hx 0x333)
    attrs elems))

(defelem image [attrs elems]
  (image* attrs elems))

(defelem card [{:keys [header media title text actions] :as attrs} elems]
  (elem :w 300 :h 600 :r 2 :d 1 :db 6 :ds 0 :dc (c 0 0 0 0.117647) ;0px 1px 6px, 0px 1px 4px
    (dissoc attrs :header :media :title :text :actions)
    elems))

(defelem card-header [{:keys [icon title subtitle] :as attrs} elems]
  (elem :w (rt 1 1) :h 50 :p 16 :g 16 :av :middle (dissoc attrs :icon :title :subtitle)
    (image :w 40 :h 40 :r 40 :v :hidden :url icon)
    (elem :w (ev - (rt 1 1) 56)
      (elem :w (rt 1 1) :f card-title-font    :fc (c 0 0 0 0.870588)    title)
      (elem :w (rt 1 1) :f card-subtitle-font :fc (c 0 0 0 0.541176) subtitle))))

(defelem card-media [{:keys [photo] :as attrs} elems]
  (elem :w (rt 1 1) :h 500
    (image :w (rt 1 1) :h 300 :url photo
      (dissoc attrs :photo)
      elems)))
