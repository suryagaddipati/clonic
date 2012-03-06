# -*- Mode: ruby -*-

require 'rubygems'
require 'rake'

def jar_name
  text = File.read('project.clj')
  unless /clonic\s+"(\d+\.\d+\.\d+(-[A-Z]+)?)"/ =~ text ||
         /clonic\s+"(\d+\.\d-alpha\d)"/ =~ text || 
         /clonic\s+"(\d+\.\d-beta\d)"/ =~ text
    puts "Couldn't find version in project file."
    exit 1
  end
  puts "jar name: #{$1}"
   "clonic-#{$1}.jar"
end

def doit(text)
    puts "== " + text
    system(text)
end

task :default => :fresh

desc "Test a fresh build, manual checking for now"
task :fresh do
     doit("lein clean")
     doit("lein jar")
end

task :jar_name do 
  puts jar_name
end

desc "upload to clojars"
task :upload do
  doit("lein pom")
  if File.exist?("clonic.jar")
    doit("mv clonic.jar #{jar_name} ")
  end
  doit("scp pom.xml #{jar_name} clojars@clojars.org:")
end
