@echo off

md c:\Application\app
md c:\Application\app\tmp
md c:\Application\app\log

java -Xms256M -Xmx256M -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -server -Djava.awt.headless=true -jar wordtopdf-0.1.jar