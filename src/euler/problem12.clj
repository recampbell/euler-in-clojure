(ns euler.problem12
  (:require [clojure.math.numeric-tower :as num]))


;;The sequence of triangle numbers is generated by adding the natural
;;numbers. So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 +
;;7 = 28. The first ten terms would be:

;;1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

;;;Let us list the factors of the first seven triangle numbers:

;;1: 1
;;3: 1,3
;;6: 1,2,3,6
;;10: 1,2,5,10
;;15: 1,3,5,15
;;21: 1,3,7,21
;;28: 1,2,4,7,14,28
;;We can see that 28 is the first triangle number to have over five divisors.

;;What is the value of the first triangle number to have over five hundred divisors?

;; lazy sequence of triangle numbers
(defn tri-stream
  ([] (tri-stream 2 1))
  ([n sum]
     (lazy-seq (cons sum (tri-stream (inc n) (+ sum n))))))

;; calculate factors upto sqrt of n
;; put into set to dedup (really only needed for the last factor, could be smarter)
(defn factors [n] 
  (into #{}
        (reduce concat
                (for [factor (range 1 (inc (int (num/sqrt n))))
                      :when (zero? (mod n factor))]
                  [factor (/ n factor)]))))

(defn number-of-factors [n]
  (count (factors n)))

(defn answer
  ([] (answer 500))
  ([n]
     (->> (tri-stream)
          (filter #(> (number-of-factors %) n))
          (first))))


