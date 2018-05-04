package com.zhilai.master.modules.sys.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.zhilai.master.modules.report.entity.CorpSaleMonth;

/**
 * 字典Entity
 * @author zhilai
 * @version 2017-08-04
 */
public class ChartTest {
	
	private int orders_number=151;
	private int bounce_rate=53;
	private int user_registrations=44;
	private int unique_visitors=65;
	private int[] sales_y=new int[]{0,7500,15000,22500,30000};
	private int[] sales_year=new int[]{2015,2016,2017};
	private String[] visitors=new String[]{"Vistors","Online","Existo"};
	private int[] calendar_date=new int[]{1,5,7};
	private int[] calendar_task=new int[]{90,70,60,40,50,30};
	private int[] sales_graph_y=new int[]{0,5000,10000,15000,20000};
	private int[] sales_graph_year=new int[]{0,2012,2013,2014,2015};
	private String[][] sales_graph_type={{"Mail-Orders","Online","In-Store"},{"20","50","30"}};
	private int radomInt=new Random().nextInt(10);
	
	private List<Object[]> perList=new ArrayList<Object[]>();
	private String perJsons;// 2017各组站点占比百分比饼图传值

	public String getPerJsons() {
		perJsons=JSON.toJSONString(getPerList());
		return perJsons;
	}

	public void setPerJsons(String perJsons) {
		this.perJsons = perJsons;
	}

	public List<Object[]> getPerList() {
		if(null==perList){
			perList=new ArrayList<Object[]>();
		}if(perList.size()<1){
			perList.add(new Object[]{"Sunfeng",45.0});
			perList.add(new Object[]{"ZhiLai",26.8});
			perList.add(new Object[]{"AMAZON",12.8});
			perList.add(new Object[]{"JD",8.5});
			perList.add(new Object[]{"Hair",6.2});
			perList.add(new Object[]{"其他",0.7});
		}
		return perList;
	}

	public void setPerList(List<Object[]> perList) {
		this.perList = perList;
	}
	
	private List<CorpSaleMonth> seriesDatas=new ArrayList<CorpSaleMonth>();
	private String seriesDatasJson;// 智莱云设备在线占比折线图传值
	
	public List<CorpSaleMonth> getSeriesDatas() {
		return seriesDatas;
	}

	public void setSeriesDatas(List<CorpSaleMonth> seriesDatas) {
		this.seriesDatas = seriesDatas;
	}

	public String getSeriesDatasJson() {
		seriesDatasJson=JSON.toJSONString(getSeriesDatas());
		return seriesDatasJson;
	}

	public void setSeriesDatasJson(String seriesDatasJson) {
		this.seriesDatasJson = seriesDatasJson;
	}
	
	private String[]keys;
	private int[]values;
	private String[]counts;
	
	public String[] getCounts() {
		return counts;
	}
	public void setCounts(String[] counts) {
		this.counts = counts;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	public int[] getValues() {
		return values;
	}
	public void setValues(int[] values) {
		this.values = values;
	}

	private List<int[]> myvaluesList=new ArrayList<int[]>();
	public List<int[]> getMyvaluesList() {
		if(null==myvaluesList){
			myvaluesList=new ArrayList<int[]>();
		}if(myvaluesList.size()<1){
			myvaluesList.add(new int[]{1000, 1200, 920, 927, 931, 1027, 819, 930, 1021});
			myvaluesList.add(new int[]{515, 519, 520, 522, 652, 810, 370, 627, 319, 630, 921});
			myvaluesList.add(new int[]{15, 19, 20, 22, 33, 27, 31, 27, 19, 30, 21});
		}
		return myvaluesList;
	}

	private String myV1Jsons;
	public String getMyV1Jsons() {
		myV1Jsons=JSON.toJSONString(getMyvaluesList());
		return myV1Jsons;
	}
	public void setMyV1Jsons(String myV1Jsons) {
		this.myV1Jsons = myV1Jsons;
	}
	public void setMyvaluesList(List<int[]> myvaluesList) {
		this.myvaluesList = myvaluesList;
	}
	private List<AreaData> areaDatas=new ArrayList<AreaData>();
	private List<LineData> lineDatas=new ArrayList<LineData>();
	private List<DonutData> donutDatas=new ArrayList<DonutData>();
	
	private String areaDatasJson;
	private String lineDatasJson;
	private String donutDatasJson;
	
	public List<DonutData> getDonutDatas() {
		return donutDatas;
	}
	public void setDonutDatas(List<DonutData> donutDatas) {
		this.donutDatas = donutDatas;
	}
	public String getDonutDatasJson() {
		donutDatasJson=JSON.toJSONString(getDonutDatas());
		return donutDatasJson;
	}
	public void setDonutDatasJson(String donutDatasJson) {
		this.donutDatasJson = donutDatasJson;
	}
	public List<AreaData> getAreaDatas() {
//		if(null==areaDatas){
//			areaDatas=new ArrayList<AreaData>();
//		}
//		if(areaDatas.size()<1){
//		areaDatas.add(new AreaData("2015 Q1",2666,2566));
//		areaDatas.add(new AreaData("2015 Q2",2778,2294));
//		areaDatas.add(new AreaData("2015 Q3",4912,1969));
//		areaDatas.add(new AreaData("2015 Q4",3767,3597));
//		areaDatas.add(new AreaData("2016 Q1",6810,1914));
//		areaDatas.add(new AreaData("2016 Q2",5670,4293));
//		areaDatas.add(new AreaData("2016 Q3",4820,3765));
//		areaDatas.add(new AreaData("2016 Q4",15073,5967));
//		areaDatas.add(new AreaData("2017 Q1",10687,4460));
//		areaDatas.add(new AreaData("2017 Q2",8492,5713));
//		areaDatas.add(new AreaData("2017 Q3",6321,4400));
//		}
		return areaDatas;
	}
	
	
	
	public List<LineData> getLineDatas() {
//		if(null==lineDatas){
//			lineDatas=new ArrayList<LineData>();
//		}
//		if(lineDatas.size()<1){
//		lineDatas.add(new LineData("2015 Q1",2666));
//		lineDatas.add(new LineData("2015 Q2",2778));
//		lineDatas.add(new LineData("2015 Q3",4912));
//		lineDatas.add(new LineData("2015 Q4",3767));
//		lineDatas.add(new LineData("2016 Q1",6810));
//		lineDatas.add(new LineData("2016 Q2",5670));
//		lineDatas.add(new LineData("2016 Q3",4820));
//		lineDatas.add(new LineData("2016 Q4",15073));
//		lineDatas.add(new LineData("2017 Q1",10687));
//		lineDatas.add(new LineData("2017 Q2",8432));
//		lineDatas.add(new LineData("2017 Q3",4123));
//		}
		return lineDatas;
	}



	public void setLineDatas(List<LineData> lineDatas) {
		this.lineDatas = lineDatas;
	}


	public String getLineDatasJson() {
		lineDatasJson=JSON.toJSONString(getLineDatas());
		return lineDatasJson;
	}



	public void setLineDatasJson(String lineDatasJson) {
		this.lineDatasJson = lineDatasJson;
	}



	public int getRadomInt() {
		return radomInt;
	}

	public void setRadomInt(int radomInt) {
		this.radomInt = radomInt;
	}

	public String getAreaDatasJson() {
		areaDatasJson=JSON.toJSONString(getAreaDatas());
		return areaDatasJson;
	}

	public void setAreaDatasJson(String areaDatasJson) {
		this.areaDatasJson = areaDatasJson;
	}

	public void setAreaDatas(List<AreaData> areaDatas) {
		this.areaDatas = areaDatas;
	}
	public int getOrders_number() {
		return orders_number+radomInt;
	}
	public void setOrders_number(int orders_number) {
		this.orders_number = orders_number;
	}
	public int getBounce_rate() {
		return bounce_rate+radomInt;
	}
	public void setBounce_rate(int bounce_rate) {
		this.bounce_rate = bounce_rate;
	}
	public int getUser_registrations() {
		return user_registrations+radomInt;
	}
	public void setUser_registrations(int user_registrations) {
		this.user_registrations = user_registrations;
	}
	public int getUnique_visitors() {
		return unique_visitors+radomInt;
	}
	public void setUnique_visitors(int unique_visitors) {
		this.unique_visitors = unique_visitors;
	}
	public int[] getSales_y() {
		return sales_y;
	}
	public void setSales_y(int[] sales_y) {
		this.sales_y = sales_y;
	}
	public int[] getSales_year() {
		return sales_year;
	}
	public void setSales_year(int[] sales_year) {
		this.sales_year = sales_year;
	}
	public String[] getVisitors() {
		return visitors;
	}
	public void setVisitors(String[] visitors) {
		this.visitors = visitors;
	}
	public int[] getCalendar_date() {
		return calendar_date;
	}
	public void setCalendar_date(int[] calendar_date) {
		this.calendar_date = calendar_date;
	}
	public int[] getCalendar_task() {
		return calendar_task;
	}
	public void setCalendar_task(int[] calendar_task) {
		this.calendar_task = calendar_task;
	}
	public int[] getSales_graph_y() {
		return sales_graph_y;
	}
	public void setSales_graph_y(int[] sales_graph_y) {
		this.sales_graph_y = sales_graph_y;
	}
	public int[] getSales_graph_year() {
		return sales_graph_year;
	}
	public void setSales_graph_year(int[] sales_graph_year) {
		this.sales_graph_year = sales_graph_year;
	}
	public String[][] getSales_graph_type() {
		return sales_graph_type;
	}
	public void setSales_graph_type(String[][] sales_graph_type) {
		this.sales_graph_type = sales_graph_type;
	}
}