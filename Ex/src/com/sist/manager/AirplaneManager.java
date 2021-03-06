package com.sist.manager;
import java.util.*;
import com.sist.dao.AirplaneDAO;
import com.sist.vo.AirTimeVO;
import com.sist.vo.AirplaneVO;

public class AirplaneManager {
	private int planeNumberPerAireline; //각 항공사마다 가질수 있는 비행기의 수
	private String[] airLineArr; //항공사 이름
	private int randomStartDateCount;
	private int airplaneCount;
	private String month;
	private String day;
	private String hour;
	private String minute;
	private String[] airports={"인천 국제공항","김포 국제공항","포항 공항","제주 국제공항"};
	private int[] monthDays={31,29,31,30,31,30,31,31,30,31,30,31};
	private int[] randomDays;
	private ArrayList<Integer> randCount;
	
	List<AirTimeVO> airTimeList=new ArrayList<AirTimeVO>();
	
	
	public AirplaneManager(){ // 각 항공사 및 항공사 마다의 가지는 비행기수 초기화 
		planeNumberPerAireline=30; 
		String[] line={"대한항공", "아시아나", "티웨이", "진에어", "에어서울", "에어부산", "제주항공", "이스타항공"};
		airLineArr=line;
		randomStartDateCount=0;
		airplaneCount=240;
		randCount=new ArrayList<Integer>();
		
	}
	
	public int[] getRandDay(int count,int month){
		 int[] result=new int[count];
		 for(int i=0; i<count; i++)
		 {
			 result[i]=((int)(Math.random()*monthDays[month-1])+1);
			for(int j=0; j<i; j++)
			{
				
				if(result[i]==result[j])
				{
					i--;
					break;
				}
			}
		}
		
		 return  result;
	}
	public ArrayList<String> setStartDate(){
		ArrayList<String> list=new ArrayList<String>();
		int monthT=4;
		for(int i=0; i<airplaneCount; i++)
		{
			randomStartDateCount=(int)(Math.random()*6)+10; //한 비행기가 가질 수 있는 예약 수 -> 15개
			randomDays = getRandDay(randomStartDateCount,monthT); //중복체크함수 (비행기 중복 안댐 - 성공)
			randCount.add(randomStartDateCount);
			
			for(int j=0;j<randomStartDateCount;j++)
			{
				int dayT;
				int hourT;
				int minuteT;
				
				hourT=(int)(Math.random()*17)+6; // 6시부터 22시까지
				minuteT=(int)(Math.random()*60); // 0분 ~ 59분 : 6시 60분
				/*System.out.print(hourT+" ");
				System.out.print(": "+minuteT+" ");*/
				
				dayT=randomDays[j]; //7일
				
				if(monthT < 10)
				{
					month="0"+Integer.toString(monthT);
				}
				else
				{
					month=Integer.toString(monthT);
				}
				
				if(dayT < 10)
				{
					day="0"+Integer.toString(dayT);
				}
				else
				{
					day=Integer.toString(dayT);
				}
				
				
				if(hourT<10)
				{
					hour="0"+Integer.toString(hourT);
				}
				else
				{
					hour=Integer.toString(hourT);
				}
				
				if(minuteT<10)
				{
					minute="0"+Integer.toString(minuteT);
				}
				else
				{
					minute=Integer.toString(minuteT);
				}
				System.out.print(hour+" ");
				System.out.print(": "+minute+" ");
				
				list.add("2020"+month+day+hour+minute);
				
			}
			System.out.println();
		}
		
		return list;
	}
	
	public ArrayList<String> setAirport(ArrayList<String> airportCount){
		ArrayList<String> result=new ArrayList<>();
		for(int i=0; i<airportCount.size(); i++)
		{
			int k=(int)(Math.random()*4);
			result.add(airports[k]);
			
		}
		return result;
		
	}
	
	
	public String[] setAirLine(){ //8개의 항공사 30개씩 배분 총 240
		String[] result= new String[airLineArr.length*planeNumberPerAireline];
		for(int i=0; i<airLineArr.length;i++)
		{
			for(int j=0; j<planeNumberPerAireline;j++)
			{
				result[i*planeNumberPerAireline+j] = airLineArr[i];
			}
			
		}
		return result;
	}
	
	public int[] setSize(){ // 각 항공사마다 대중소 10개씩 배분 총240
		int[] result=new int[airLineArr.length*planeNumberPerAireline];
		for(int i=0;i<airLineArr.length;i++)
		{
			for(int j=0;j<3;j++)
			{
				for(int k=0; k<10; k++)
				{
					result[i*planeNumberPerAireline+j*10+k]=j;
				}
			}
		}
		return result;
	}
	
	public Node[] setSeat(int[] sizeTArr) //대중소 사이즈를 받아서 각 항공기에 랜덤으로 좌석수 배분
	{
		Node[] result= new Node[airLineArr.length*planeNumberPerAireline];
		for(int i=0; i<sizeTArr.length; i++)
		{
			Node temp=new Node(sizeTArr[i]);
			result[i]=temp;
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AirplaneManager am=new AirplaneManager();
		AirplaneDAO dao=new AirplaneDAO();
		
		//비행기 만들기
		
		
		/*List<AirplaneVO> list=dao.airplaneSeatData();
		
		String[] airLineTArr= am.setAirLine();
		int[] sizeTArr=am.setSize();
		Node[] seatTArr=am.setSeat(sizeTArr);
		for(int i=0; i<am.airLineArr.length*am.planeNumberPerAireline;i++)
		{
			AirplaneVO vo=new AirplaneVO();
			
			vo.setFirst(seatTArr[i].getFirst());
			vo.setBusiness(seatTArr[i].getBusiness());
			vo.setEconomy(seatTArr[i].getEconomy());
			vo.setSizeType(sizeTArr[i]);
			vo.setAirline(airLineTArr[i]);
			
			list.add(vo);
		}
		int i=0;
		for(AirplaneVO vo:list){
			dao.insertAirplane(vo);
			System.out.println(vo.getAirline()+" ");
			i++;
		}
		System.out.println("Airplane Insert End...");
		*/
		//좌석 추가
		
<<<<<<< HEAD
			List<AirplaneVO> list=dao.airplaneAllData();
			dao.insertAirSeat(list);
		
=======
		/*List<AirplaneVO> list=dao.airplaneAllData();
		dao.insertAirSeat(list);
		*/
>>>>>>> branch 'master' of https://github.com/kimhm93/Home.git
		
		
		//출발 시간 및 공항 데이터 넣기
		/*ArrayList<String> startTime=am.setStartDate(); //비행기 하나당 15번 이륙 (모두 다른 날)
		System.out.println("startTime len : " + startTime.size());
		ArrayList<String> airport=am.setAirport(startTime);
	
		int random=0;
		int k=0;
		List<AirTimeVO> list=new ArrayList<AirTimeVO>();
		System.out.println("Airtime Insert...");
		//startTime을 모두 순회하는데, 정렬시켜서 출력
		Collections.sort(startTime, );
		for(int i = 0; i < startTime.size(); i++){
			startTime.get(i);
		}
		int k=0;
		for(int i=0; i<am.airplaneCount; i++) //airplanecount : 240
		{
			for(int j=0; j<am.randCount.get(i);j++) //randcount : 한 대당 랜덤 횟수(startTime[i]
			{
				AirTimeVO vo=new AirTimeVO();
				vo.setPlane_id(i+1);
				vo.setStart_time(startTime.get(k));
<<<<<<< HEAD
				System.out.print(am.randCount.get(i)+" ");
=======


				System.out.print(am.randCount.get(i)+" ");

>>>>>>> branch 'master' of https://github.com/kimhm93/Home.git
				vo.setStart_airport(airport.get(j));
				if(airport.get(j).equals("제주 국제공항"))
				{
					random=(int)(Math.random()*3);
					vo.setEnd_airport(am.airports[random]);
				}
				else
				{
					vo.setEnd_airport(am.airports[3]);
				}
				k++;
				list.add(vo);
				k++;
				
			}
			System.out.println();
			
		}
<<<<<<< HEAD
		
		System.out.println(list.size());		
=======
>>>>>>> branch 'master' of https://github.com/kimhm93/Home.git
		
		System.out.println(list.size());		
		System.out.println("Airtime Insert ...");
		for(AirTimeVO vo:list)
		{
			
			dao.insertAirtime(vo);
			System.out.println(vo.getPlane_id());
		}
		System.out.println("Airtime Insert End...");*/
		
	
	}
}
