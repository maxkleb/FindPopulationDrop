(ns test-max.core
    (:require
        [clojure.data.json :as json]))

(defn get-next-element [n]
    (let [element (second n)]
        (remove nil? (map
             (fn [k] (if (and (not (nil? (first k)))
                              (not (nil? (second k)))
                              (> (Integer/parseInt (get (first k) "value"))
                                 (Integer/parseInt (get (second k) "value")))
                              )
                         [(get (second k) "year")
                          (get (second k) "level_1")
                          (get (second k) "level_2")]
                         ))
             (map #(conj %) (partition 2 1 element))))
        ))

(defn -main []
    (let [res (json/read-str (slurp "resources/input.json"))
          data (group-by #(select-keys % ["level_1" "level_2"]) res)]
        (remove nil? (map get-next-element data))))
