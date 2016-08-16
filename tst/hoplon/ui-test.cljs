(ns+ hoplon.ui-test
  (:page
    "index.html")
  (:refer-clojure
    :exclude [- test])
  (:require
    [javelin.core    :refer [defc defc= cell= cell]]
    [hoplon.core     :refer [defelem for-tpl when-tpl case-tpl]]
    [hoplon.ui       :refer [elem image window s b]]
    [hoplon.ui.elems :refer [markdown]]
    [hoplon.ui.attrs :refer [- c r d]]))

(defc things ["a" "b" "c"])

(def metadata
  [{:property "og:url"                   :content "http://www.mysite.com/"}
   {:name    "image_src"                 :content "http://www.mysite.com/images/logo-fb.png"}
   {:property "og:image"                 :content "http://www.mysite.com/images/logo-180.png"}
   {:property "og:image:width"           :content "180"}
   {:property "og:image:height"          :content "110"}
   {:property "og:image"                 :content "http://www.mysite.com/images/logo-250.png"}
   {:property "og:image:width"           :content "250"}
   {:property "og:image:height"          :content "250"}
   {:content  "text/html; charset=UTF-8" :http-equiv "content-type"}])

;;; styles ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def sm 736)
(def lg 1069)

(def gutter 6)

(def stroke-grey (c 0xCCCCCC))
(def fill-grey   (c 0xEEEEEE))
(def font-grey   (c 0x888888))

(def fail-color  (c 0xd43f3a))
(def pass-color  (c 0x4cae4c))

;;; views ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defelem suite [{:keys [title pass code] :as attrs} elems]
  (elem :s (r 1 1) :p gutter :g gutter
    (elem :sh (r 1 1) :ph gutter
      (elem :sh (r 1 2) :f 21 :fc font-grey
        title)
      (elem :sh (r 1 2) :ah :end
        (when-tpl code
          (elem :p gutter :c fill-grey :b 1 :bc stroke-grey :r 3
            code))))
    (dissoc attrs :code :pass :title) elems))

(defelem box [attrs elems]
  (elem :s 40 :a :mid :c (c 0xFFFFFF) :b 2 :bc stroke-grey
    attrs elems))

(defelem test [{:keys [title pass] :as attrs} elems]
  (elem :sh 200
    (elem :sv 26 :ph gutter :gh gutter :av :mid
      (elem :s 6 :r 6 :c (cell= (if pass pass-color fail-color)))
      (elem :sh 170 :f 10 :fc font-grey :v :hidden title))
    (elem :s 200                                          ; sizing
            :p gutter :g gutter                             ; spacing
            :c fill-grey :b 2 :bc stroke-grey               ; coloring
            :d (d 3 3 (c 0xAAAAAA) 4)                       ; shaDow
            (dissoc attrs :pass :title)
      elems)))

(defelem text-test [{:keys [title pass] :as attrs} elems]
  (elem :sh 400
    (elem :sv 26 :ph gutter :gh gutter :av :mid
      (elem :s 6 :r 6 :c (cell= (if pass pass-color fail-color)))
      (elem :sh 170 :f 10 :fc font-grey :v :hidden title))
    (elem :s (r 1 1)                                          ; sizing
            :p gutter :g gutter                             ; spacing
            :c fill-grey :b 2 :bc stroke-grey               ; coloring
            :d (d 3 3 (c 0xAAAAAA) 4)                       ; shaDow
            (dissoc attrs :pass :title)
      elems)))

(window
  :title    "Hoplon UI"
  :route    [["tests"] {:foo "bar" :baz "barf"}]
  :metadata metadata
  :scroll   true
  (elem :sh (r 1 1) :p 6 :av :mid :b 2 :bc stroke-grey
    (image :s 50 :url "http://hoplon.github.io/assets/images/logos/hoplon-logo.png")
    (elem :pl 6 :f 21 "Hoplon UI Live Reference & Functional Tests"))
  (suite :title "alignments" :code ":a :av :ah [:beg :bid :end :jst]" :pass false
    (test :ah :beg :av :beg :title "box aligns horizontal left & vertical top" :pass true
      (box "a"))
    (test :ah :mid :av :beg :title "box aligns horizontal center & vertical top" :pass true
      (box "a"))
    (test :ah :end :av :beg :title "box aligns horizontal right & vertical top" :pass true
      (box "a"))
    (test :ah :beg :av :mid :title "box aligns horizontal left & vertical middle" :pass true
      (box "a"))
    (test :ah :mid :av :mid :title "box aligns horizontal center & vertical middle" :pass true
      (box "a"))
    (test :ah :end :av :mid :title "box aligns horizontal right & vertical middle" :pass true
      (box "a"))
    (test :ah :beg :av :end :title "box aligns horizontal left & vertical bottom" :pass true
      (box "a"))
    (test :ah :mid :av :end :title "box aligns horizontal center & vertical bottom" :pass true
      (box "a"))
    (test :ah :end :av :end :title "box aligns horizontal right & vertical bottom" :pass true
      (box "a"))
    (test :ah :beg :av :beg :title "boxes align horizontal left & vertical top" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c"))
    (test :ah :mid :av :beg :title "boxes align horizontal center & vertical top" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c"))
    (test :ah :end :av :beg :title "boxes align horizontal right & vertical top" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c"))
    (test :ah :beg :av :mid :title "boxes align horizontal left & vertical middle" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c"))
    (test :ah :mid :av :mid :title "boxes align horizontal center & vertical middle" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c"))
    (test :ah :end :av :mid :title "boxes align horizontal right & vertical middle" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c"))
    (test :ah :beg :av :end :title "boxes align horizontal left & vertical bottom" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c"))
    (test :ah :mid :av :end :title "boxes align horizontal center & vertical bottom" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c"))
    (test :ah :end :av :end :title "boxes align horizontal right & vertical bottom" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c"))
    (test :ah :beg :av :beg :title "boxes align horizontal left & vertical top" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c")
      (box :sv 40 "d")
      (box :sv 60 "e")
      (box :sv 60 "f")
      (box :sv 40 "g")
      (box :sv 20 "h")
      (box :sv 40 "i")
      (box :sv 40 "j"))
    (test :ah :mid :av :beg :title "boxes align horizontal center & vertical top" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c")
      (box :sv 40 "d")
      (box :sv 60 "e")
      (box :sv 60 "f")
      (box :sv 40 "g")
      (box :sv 20 "h")
      (box :sv 40 "i")
      (box :sv 40 "j"))
    (test :ah :end :av :beg :title "boxes align horizontal right & vertical top" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c")
      (box :sv 40 "d")
      (box :sv 60 "e")
      (box :sv 60 "f")
      (box :sv 40 "g")
      (box :sv 20 "h")
      (box :sv 40 "i")
      (box :sv 40 "j"))
    (test :ah :beg :av :mid :title "boxes align horizontal left & vertical middle" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c")
      (box :sv 40 "d")
      (box :sv 60 "e")
      (box :sv 60 "f")
      (box :sv 40 "g")
      (box :sv 20 "h")
      (box :sv 40 "i")
      (box :sv 40 "j"))
    (test :ah :mid :av :mid :title "boxes align horizontal center & vertical middle" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c")
      (box :sv 40 "d")
      (box :sv 60 "e")
      (box :sv 60 "f")
      (box :sv 40 "g")
      (box :sv 20 "h")
      (box :sv 40 "i")
      (box :sv 40 "j"))
    (test :ah :end :av :mid :title "boxes align horizontal right & vertical middle" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c")
      (box :sv 40 "d")
      (box :sv 60 "e")
      (box :sv 60 "f")
      (box :sv 40 "g")
      (box :sv 20 "h")
      (box :sv 40 "i")
      (box :sv 40 "j"))
    (test :ah :beg :av :end :title "boxes align horizontal left & vertical bottom" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c")
      (box :sv 40 "d")
      (box :sv 60 "e")
      (box :sv 60 "f")
      (box :sv 40 "g")
      (box :sv 20 "h")
      (box :sv 40 "i")
      (box :sv 40 "j"))
    (test :ah :mid :av :end :title "boxes align horizontal center & vertical bottom" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c")
      (box :sv 40 "d")
      (box :sv 60 "e")
      (box :sv 60 "f")
      (box :sv 40 "g")
      (box :sv 20 "h")
      (box :sv 40 "i")
      (box :sv 40 "j"))
    (test :ah :end :av :end :title "boxes align horizontal right & vertical bottom" :pass true
      (box :sv 60 "a")
      (box :sv 40 "b")
      (box :sv 20 "c")
      (box :sv 40 "d")
      (box :sv 60 "e")
      (box :sv 60 "f")
      (box :sv 40 "g")
      (box :sv 20 "h")
      (box :sv 40 "i")
      (box :sv 40 "j"))
    (text-test
      (markdown
        "#header one\n##header two\n###header three\n####header four\n* bullet one\n* bullet two\nsome *italic text* and **bold text**\n"))))

  ; (test :ah :mid  :av :mid :title "box in cell aligns horizontal center & vertical center" :pass false
  ;   (cell (box "a")))
  ; (test :ah :mid  :av :mid :title "boxes in formula cell align horizontal center & vertical center" :pass false
  ;   (for-tpl [thing things]
  ;     (box thing)))
  ; (suite :title "layouts"
  ;   (test :a :mid :title "elem % sizes to column width set by sibling" :pass false
  ;     (elem
  ;       (box :sh(r 1 1) "a")
  ;       (box "b")))
  ;   (test :a :mid :title "fonts below size 16 adjust vertical position of siblings" :pass false
  ;       ;; caused by
  ;     (elem
  ;       (elem :f 10 "a")
  ;       (box "b")))
  ;   (test :a :mid :title "elem of fixed width remains constant when gutter is applied to parent." :pass false
  ;       ;; solution: apply lengths to middle, but percentages to outer
  ;     (elem :g 50
  ;       (box "a")))))
  ; (test :a :mid :title "elem sizes to width of attribute in cell" :pass false
  ;   (elem
  ;     (box :sh(cell 80) "a")))
  ; (suite :title "controls"
  ;   (button :sh300 :sv 150 "Click Me" :click #(swap! things conj "d"))
  ;   (button :sh150 :sv 75 :fc :white "Click Me")
  ;   (button :sh300 :sv 200 "Click Me")
  ;   (button :sh300 :sv 200 "Click Me")
  ;   #_(elem :sv 25 :sh[(r 1 1) sm (- (r 1 1) 220)] :ah :end :g 6)))