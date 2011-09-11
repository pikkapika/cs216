jar -cvf jar/WhiteHouse.jar -C bin/ .

hadoop fs -rmr ~/PartC_output

hadoop jar jar/WhiteHouse.jar edu/duke/cs/WhiteHouseVisitorDriver  ~/PartC_input/WhiteHouse-WAVES-Released-0827.csv ~/PartC_output/WhiteHouseVisitorDriver
hadoop jar jar/WhiteHouse.jar edu/duke/cs/WhiteHouseVisiteeDriver  ~/PartC_input/WhiteHouse-WAVES-Released-0827.csv ~/PartC_output/WhiteHouseVisiteeDriver
hadoop jar jar/WhiteHouse.jar edu/duke/cs/WhiteHouseVisitorAndVisiteeDriver  ~/PartC_input/WhiteHouse-WAVES-Released-0827.csv ~/PartC_output/WhiteHouseVisitorAndVisiteeDriver
hadoop jar jar/WhiteHouse.jar edu/duke/cs/WhiteHouseMeetingPlaceDriver  ~/PartC_input/WhiteHouse-WAVES-Released-0827.csv ~/PartC_output/WhiteHouseMeetingPlaceDriver
hadoop jar jar/WhiteHouse.jar edu/duke/cs/WhiteHouseVisitorVisiteeMeetingPlaceDriver  ~/PartC_input/WhiteHouse-WAVES-Released-0827.csv ~/PartC_output/WhiteHouseVisitorVisiteeMeetingPlaceDriver

hadoop jar jar/WhiteHouse.jar edu/duke/cs/SortDriver  ~/PartC_output/WhiteHouseVisitorDriver/part-* ~/PartC_output/SortWhiteHouseVisitorDriver
hadoop jar jar/WhiteHouse.jar edu/duke/cs/SortDriver  ~/PartC_output/WhiteHouseVisiteeDriver/part-* ~/PartC_output/SortWhiteHouseVisiteeDriver
hadoop jar jar/WhiteHouse.jar edu/duke/cs/SortDriver  ~/PartC_output/WhiteHouseVisitorAndVisiteeDriver/part-* ~/PartC_output/SortWhiteHouseVisitorAndVisiteeDriver
hadoop jar jar/WhiteHouse.jar edu/duke/cs/SortDriver  ~/PartC_output/WhiteHouseMeetingPlaceDriver/part-* ~/PartC_output/SortWhiteHouseMeetingPlaceDriverDriver
hadoop jar jar/WhiteHouse.jar edu/duke/cs/SortDriver  ~/PartC_output/WhiteHouseVisitorVisiteeMeetingPlaceDriver/part-* ~/PartC_output/SortWhiteHouseVisitorVisiteeMeetingPlaceDriver
  
hadoop fs -lsr ~/PartC_output
	  
echo "\n#### (1) Visitor ####"
hadoop fs -cat ~/PartC_output/SortWhiteHouseVisitorDriver/part-* | tail -20
echo "\n#### (2) Visitee ####"
hadoop fs -cat ~/PartC_output/SortWhiteHouseVisiteeDriver/part-* | tail -20
echo "\n#### (3) Visitor and Visitee ####"
hadoop fs -cat ~/PartC_output/SortWhiteHouseVisitorAndVisiteeDriver/part-* | tail -20
echo "\n#### (4-1) Meeting Place ####"
hadoop fs -cat ~/PartC_output/SortWhiteHouseMeetingPlaceDriverDriver/part-*| tail -20
echo "\n#### (4-2) Visitor, Visitee, and Meeting Place ####"
hadoop fs -cat ~/PartC_output/SortWhiteHouseVisitorVisiteeMeetingPlaceDriver/part-* | tail -20

