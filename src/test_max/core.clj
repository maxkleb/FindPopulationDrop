(ns test-max.core
    (:require
        [clojure.data.json :as json]))

(defn get-next-element [n]
    (let [element (second n)]
        (if (and (not (nil? (first element)))
                 (not (nil? (second element)))
                 (> (Integer/parseInt (get (first element) "value"))
                    (Integer/parseInt (get (second element) "value")))
                 )
            [(get (second element) "year")
             (get (second element) "level_1")
             (get (second element) "level_2")]

            )))

(defn -main []
    (let [res (json/read-str (slurp "resources/input.json"))
          data (group-by #(select-keys % ["level_1" "level_2"]) res)]
        (remove empty? (map get-next-element data))))
