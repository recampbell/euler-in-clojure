(ns euler.problem45)

;; Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:

;; Triangle Tn=n (n+1)/2 1, 3, 6, 10, 15, ...
;; Pentagonal Pn=n (3n1)/2 1, 5, 12, 22, 35, ...
;; Hexagonal Hn=n (2n1) 1, 6, 15, 28, 45, ...

;; It can be verified that T285 = P165 = H143 = 40755.

;; Find the next triangle number that is also pentagonal and hexagonal.


(defn triangle [n]
  (/ (* n (inc n)) 2))

(defn pentagonal [n]
  (/ (* n (- (* 3 n) 1)) 2))

(defn hexagonal [n]
  (* n (- (* 2 n) 1)))


;; thought about using lazy sequences here, but wht not keep it simple this time?
(defn answer
  ([]
     ;; start at t+1, p, h from the problem statement
     (answer 286 165 143))

  ([tn pn hn]
     (let [t (triangle tn)
           p (pentagonal pn)
           h (hexagonal hn)]
       (cond
        (= t p h)
        t

        (and (< t p) (< t h))
        (recur (inc tn) pn hn)

        (< p h)
        (recur tn (inc pn) hn)

        :else
        (recur tn pn (inc hn))))))
