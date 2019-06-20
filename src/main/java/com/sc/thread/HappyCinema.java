package com.sc.thread;
/**
 * 快乐电影院
 * @author Administrator
 *
 */
public class HappyCinema {
	public static void main(String[] args) {
		Cinema cinema = new Cinema(30, "水井湾电影院");
		new Thread(new Customer(2, cinema),"石城").start();
		new Thread(new Customer(2, cinema),"琪琪").start();
	}
}
//设计一个电影院
class Cinema {
	int seats;
	String name;
	
	public Cinema(int seats, String name) {
		this.seats = seats;
		this.name = name;
	}
	
	public boolean hasTick(int num) {
		if(num>seats) {
			return false;
		}
		return true;
	}
}
//设计观众
class Customer implements Runnable{
	int num;//买票数量
	Cinema cinema;//选择的电影院
	
	public Customer(int num, Cinema cinema) {
		this.num = num;
		this.cinema = cinema;
	}


	@Override
	public void run() {
		if(cinema.hasTick(num)) {
			cinema.seats-=num;
			System.out.println("用户"+Thread.currentThread().getName()+"抢到了"+num+"张票；");
			System.out.println(cinema.name+"还剩"+cinema.seats+"张票");
		}
		
	}
}