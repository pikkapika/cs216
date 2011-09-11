jar -cvf jar/WhiteHouse.jar -C bin/ .

hadoop fs -rmr ~/PartB_output

hadoop jar jar/WhiteHouse.jar edu/duke/cs/WordCountDriver  ~/PartB_input/customer.dat ~/PartB_output/WordCountDriver_Customer
hadoop jar jar/WhiteHouse.jar edu/duke/cs/WordCountDriver  ~/PartB_input/web_sales.dat ~/PartB_output/WordCountDriver_WebSales

hadoop jar jar/WhiteHouse.jar edu/duke/cs/SortDriver  ~/PartB_output/WordCountDriver_Customer/part-* ~/PartB_output/SortWordCountDriver_Customer
hadoop jar jar/WhiteHouse.jar edu/duke/cs/SortDriver  ~/PartB_output/WordCountDriver_WebSales/part-* ~/PartB_output/SortWordCountDriver_WebSales
  
hadoop fs -lsr ~/PartB_output
	  
echo "#### SortWordCountDriver_Customer ####"
hadoop fs -cat ~/PartB_output/SortWordCountDriver_Customer/part-* | tail -20

echo ""
echo "#### SortWordCountDriver_WebSales ####"
hadoop fs -cat ~/PartB_output/SortWordCountDriver_WebSales/part-* | tail -20

