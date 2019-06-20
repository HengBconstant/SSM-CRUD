package com.sc.thread;

import java.util.ArrayList;
import java.util.List;

public class HappyCinema2 {
	public static void main(String[] args) {
		List<Integer> zuowei  = new ArrayList<Integer>();
		zuowei.add(1);
		zuowei.add(2);
		zuowei.add(3);
		Dianyingyuan dianyingyuan = new Dianyingyuan(zuowei, "水井湾电影院");
		List<Integer> piao1  = new ArrayList<Integer>();
		piao1.add(1);
		piao1.add(2);
		List<Integer> piao2  = new ArrayList<Integer>();
		piao2.add(3);
		piao2.add(4);
		new Thread(new guke(dianyingyuan, piao1),"石城").start();
		new Thread(new guke(dianyingyuan, piao2),"琪琪").start();
		
	}
}
//电影院
class Dianyingyuan {
	List<Integer> zuowei;
	String name;

	public Dianyingyuan(List<Integer> zuowei, String name) {
		super();
		this.zuowei = zuowei;
		this.name = name;
	}

	public boolean mai(List<Integer> piao) {
		System.out.println("请选择可用位置:" + zuowei);
		List<Integer> copy = new ArrayList<>();
		copy.addAll(zuowei);
		zuowei.removeAll(piao);
		if(copy.size()-zuowei.size() != piao.size()) {
			return false;
		}
		zuowei = copy;
		return true;
	}
}
class guke implements Runnable{
	
	Dianyingyuan dianyingyuan;
	List<Integer> piao;
	
	public guke(Dianyingyuan dianyingyuan, List<Integer> piao) {
		this.dianyingyuan = dianyingyuan;
		this.piao = piao;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (dianyingyuan) {
			boolean flag = dianyingyuan.mai(piao);
			if (flag) {
				System.out.println("用户" + Thread.currentThread().getName() + "购买成功，你的座位是：" + piao);
				dianyingyuan.zuowei.removeAll(piao);
			} else {
				System.out.println("购买失败");
			}
		}
		
	}
	
}