(defproject async "0.1.0-SNAPSHOT"
  :description "core.async experiments"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/core.async "0.1.0-SNAPSHOT"]
                 [org.clojure/clojurescript "0.0-1806"]]
  :repositories {"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}
  :plugins [[lein-cljsbuild "0.3.2" :exclusions [org.clojure/google-closure-library]]]
  :source-paths ["src/clj"]
  :cljsbuild {
              :builds
              [{:source-paths ["src/cljs"]
                :incremental false
                :compiler
                {:pretty-print true
                 :output-to "resources/public/async.js"
                 :optimizations :whitespace}}]})
