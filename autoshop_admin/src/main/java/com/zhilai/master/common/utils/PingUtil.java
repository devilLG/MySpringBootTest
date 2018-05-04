package com.zhilai.master.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;

/**
 * ping net工具类 
 * @author zhiwu.zheng
 * 2017-11-29 11:12:36
 */
public class PingUtil {

	public static void main(String[] args) {
		PingNet("www.baidu.com");
		// PingWeb();
	}

	/**最常用的ping方法
	 * 1~30ms：极快，几乎察觉不出有延迟，玩任何游戏速度都特别顺畅 31~50ms：良好，可以正常游戏，没有明显的延迟情况
	 * 51~100ms：普通，对抗类游戏能感觉出明显延迟，稍有停顿 100ms：差，无法正常游戏，有卡顿，丢包并掉线现象
	 * 计算方法：1秒=1000毫秒（例：30ms为0.03秒） 
	 * @param ip 192.168.1.74  或www.baidu.com 两种格式数据都可以
	 * @return long 单位毫秒
	 */
	public static long PingNet(String ip) {
		long beginTime = System.currentTimeMillis();
		Runtime runtime = Runtime.getRuntime(); // 获取当前程序的运行进对象
		Process process = null; // 声明处理类对象
		String line = null; // 返回行信息
		InputStream is = null; // 输入流
		InputStreamReader isr = null;// 字节流
		BufferedReader br = null;
		boolean res = false;// 结果
		try {
			process = runtime.exec("ping " + ip); // PING
			is = process.getInputStream(); // 实例化输入流
			isr = new InputStreamReader(is);// 把输入流转换成字节流
			br = new BufferedReader(isr);// 从字节中读取文本
			while ((line = br.readLine()) != null) {
				if (line.contains("TTL")) {
					res = true;
					break;
				}
			}
			is.close();
			isr.close();
			br.close();
		} catch (IOException e) {
			System.out.println(e);
			runtime.exit(1);
		}
		long endTime = System.currentTimeMillis();
		long diffTimw = endTime - beginTime;
		System.out.println("ip:" + ip + " 连接" + (res ? "ping通" : "ping不通")	+ " 网络延迟" + diffTimw + "ms");
		return diffTimw;
	}

	/**
	 * 方法一 最常用的 PING 方法
	 */
	public static void PingNet() {
		Runtime runtime = Runtime.getRuntime(); // 获取当前程序的运行进对象
		Process process = null; // 声明处理类对象
		String line = null; // 返回行信息
		InputStream is = null; // 输入流
		InputStreamReader isr = null;// 字节流
		BufferedReader br = null;
		String ip = "www.baidu.com";
		boolean res = false;// 结果
		try {
			process = runtime.exec("ping " + ip); // PING
			is = process.getInputStream(); // 实例化输入流
			isr = new InputStreamReader(is);// 把输入流转换成字节流
			br = new BufferedReader(isr);// 从字节中读取文本
			while ((line = br.readLine()) != null) {
				if (line.contains("TTL")) {
					res = true;
					break;
				}
			}
			is.close();
			isr.close();
			br.close();
			if (res) {
				System.out.println("ping通  ...");
			} else {
				System.out.println("ping不通...");
			}
		} catch (IOException e) {
			System.out.println(e);
			runtime.exit(1);
		}
	}

	/**
	 * 方法二 下面代码为 JDK1.5PING的新方法但不能用，因为 该PING请求端口为7 而大型网站会关闭不需要的端口防止入侵
	 */
	public static void PingWeb() {
		// 
		long beginTime = System.currentTimeMillis();
		InetAddress address;
		try {
			address = InetAddress.getByName("www.baidu.com");
			System.out.println("Name:" + address.getHostName());
			System.out.println("Addr:" + address.getHostAddress());
			System.out.println("Reach:" + address.isReachable(3000)); // 是否能通信返回true或false
			System.out.println(address.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		long diffTimw = endTime - beginTime;
		System.out.println(" 网络延迟" + diffTimw + "ms");
	}

}
