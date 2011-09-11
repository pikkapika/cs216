# CS216 Assignment 1 - Part C

## Author
Koichi Ishida: pikkapika@gmail.com

## Directory
* bin - java class file 
* data - input data
* jar - jar file
* src - java source code

## Explanation
The purpose of this application is to analyse WhiteHouse Visitor input file.

(i) The 10 most frequent visitors (NAMELAST, NAMEFIRST, NAMEMID) to the White House. 

* First step by WhiteHouseVisitorDriver
  * (K1, V1) = (line#, line value)
  * (K2, V2) = (Text(NAMELAST, NAMEFIRST, NAMEMID), 1)
  * (K2, V2) = (Text(NAMELAST, NAMEFIRST, NAMEMID), the number of samples)
  * (K3, V3) = (Text(NAMELAST, NAMEFIRST, NAMEMID), the number of samples)
* Second step by SortDriver
  * (K1, V1) = (line#, line value)
  * (K2, V2) = (the number of samples, Text(NAMELAST, NAMEFIRST, NAMEMID))
	* no reducer

(ii) The 10 most frequently visited people (visitee_namelast, visitee_namefirst) in the White House. 

* First step by WhiteHouseVisiteeDriver
  * (K1, V1) = (line#, line value)
  * (K2, V2) = (Text(visitee_namelast, visitee_namefirst), 1)
  * (K2, V2) = (Text(visitee_namelast, visitee_namefirst), the number of samples)
  * (K3, V3) = (Text(visitee_namelast, visitee_namefirst), the number of samples)

* Second step by SortDriver
  * (K1, V1) = (line#, line value)
  * (K2, V2) = (the number of samples, Text(visitee_namelast, visitee_namefirst))
	* no reducer

(iii) The 10 most frequent visitor-visitee combinations. 

* First step by WhiteHouseVisitorAndVisiteeDriver
  * (K1, V1) = (line#, line value)
  * (K2, V2) = (Text(MEETING_LOC, MEETING_ROOM), 1)
  * (K2, V2) = (Text(MEETING_LOC, MEETING_ROOM), the number of samples)
  * (K3, V3) = (Text(MEETING_LOC, MEETING_ROOM), the number of samples)

* Second step by SortDriver
  * (K1, V1) = (line#, line value)
  * (K2, V2) = (the number of samples, Text(MEETING_LOC, MEETING_ROOM))
	* no reducer

(iv) Some other interesting statistic that you can think of. (Please refer value for (K1,V1)(K2,V2) at source code and others)
(iv-i) The 10 most frequent meeting location.

* First step by WhiteHouseMeetingPlaceDriver
* Second step by SortDriver

(iv-ii) The 10 most frequent visitor-visitee, and meeting location combination.

* First step by WhiteHouseVisitorVisiteeMeetingPlaceDriver
* Second step by SortDriver

## Execution
* ./hadoop_dataload.sh
* ./hadoop_suit_exe.sh

Please take a look at the inside of shell for more detail

## Consideration
I program several mapper/reducer pairs which use the same input file (WhiteHouse-WAVES-Released-0827.csv) rather than using output from another mapper to chain mapper/reducer, because it is simple and faster.
If I use job chain of mapper/reducer, I have to wait previous result. However, my program allow users to use each mapper/reducer separately. Thus, it's much faster than chain of actions.

As for (iv), I chose to use meeting location since I thought some rooms can be used only for VIP.
I grep Obama in (iv-ii) result. The following is the result.

ki13@rack162:PartC$ hadoop fs -cat ~/PartC_output/SortWhiteHouseVisitorVisiteeMeetingPlaceDriver/part-* | grep -i Obama
1	BRANDZEL,BENJAMIN,I,OBAMA,BARACK,WH,MAP ROOM
1	GERLACH,JOHN,A,OBAMA,BARACK,WH,MAP ROOM
1	GROSSMAN,DAVID,B,OBAMA,BARACK,WH,MAP ROOM
1	PETTY,REED,A,OBAMA,BARACK,WH,MAP ROOM
1	SALVIA,PETER,E,OBAMA,BARACK,WH,MAP ROOM

(WH, MAP ROOM) seems very limited place. Then I grep it in the same file.

ki13@rack162:PartC$ hadoop fs -cat ~/PartC_output/SortWhiteHouseVisitorVisiteeMeetingPlaceDriver/part-* | grep -i "WH,MAP ROOM"
1	BOWEN,JANET,C,FLOTUS,,WH,MAP ROOM
1	BRANDZEL,BENJAMIN,I,OBAMA,BARACK,WH,MAP ROOM
1	CLAYTON,LAUREN,L,POTUS,,WH,MAP ROOM
1	GERLACH,JOHN,A,OBAMA,BARACK,WH,MAP ROOM
1	GROSSMAN,DAVID,B,OBAMA,BARACK,WH,MAP ROOM
1	HOFFINE,BRANDI,S,POTUS,,WH,MAP ROOM
1	KENT,ELIZABETH,,FLOTUS,,WH,MAP ROOM
1	LUBIN,MATTHEW,L,POTUS,,WH,MAP ROOM
1	PECK,JOSHUA,F,POTUS,,WH,MAP ROOM
1	PETTY,REED,A,LIMON,NOERENA,WH,MAP ROOM
1	PETTY,REED,A,OBAMA,BARACK,WH,MAP ROOM
1	PRESTON,KAREN,A,FLOTUS,,WH,MAP ROOM
1	ROY,PAULA,,FLOTUS,,WH,MAP ROOM
1	SALVIA,PETER,E,OBAMA,BARACK,WH,MAP ROOM
1	STARR,ERIKA,C,POTUS,,WH,MAP ROOM
1	TUBBS,ANN,L,FLOTUS,,WH,MAP ROOM
1	WEST,BOBBI,,FLOTUS,,WH,MAP ROOM
2	VITURRO,RAFAEL,,POTUS,,WH,MAP ROOM
2	GORDON,WILLIAM,K,POTUS,,WH,MAP ROOM
2	KNUUTILA,TIINA,J,POTUS,,WH,MAP ROOM
3	FLEISCHER,LUCAS,C,POTUS,,WH,MAP ROOM
3	PETTY,REED,A,POTUS,,WH,MAP ROOM
3	SALVIA,PETER,E,POTUS,,WH,MAP ROOM
4	GROSSMAN,DAVID,B,POTUS,,WH,MAP ROOM

Who is the last guy in last raw? He is an Israeli author. Interesting!
http://en.wikipedia.org/wiki/David_Grossman

