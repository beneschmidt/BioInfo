Infos zum Programmstart:

- das Programm ist eine Jar die mit java -jar gestartet werden kann
- die Datei-Referenz ist hardcoded und sollte daher im Ordner resources mit dem Namen frag.dat liegen
- das Programm erzeugt ein log-File im Unterordner log
- zusätzlich werden zu Beginn und nach jedem merge eine Graphviz Datei des aktuellen Graphen im Unterordner target erzeugt
- das Programm braucht sehr viele Ressourcen. Mit zu wenig RAM ist es nicht möglich die vorgegebene Datei zu assemblen,
  daher sollte das Programm mit folgendem Befehl gestartet werden:
-----> java -Xms1024M -Xmx4096M -jar BioInfo.jar <-------