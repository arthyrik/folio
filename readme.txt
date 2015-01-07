1. Build the project: mvn package
2. Go to "target" folder.
3. Run jar with dependencies: java -jar folio-jar-with-dependencies.jar -xml -file path_to_fff_file -out path_to_xml_file
EXAMPLE:
If files are in the same "target" folder:
java -jar folio-jar-with-dependencies.jar -xml -file .\xxx.fff -out .\yyy.xml

If files are not in the same "target" folder:
java -jar folio-jar-with-dependencies.jar -xml -file C:\Temp\xxx.fff -out C:\Temp\yyy.xml 