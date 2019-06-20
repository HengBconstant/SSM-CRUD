package com.sc.thread;

public class UnsafeThread {
	public static void main(String[] args) {
		Acount acount = new Acount(1000, "工商银行卡");
		new Drawing(acount, 800, "石城").start();
		new Drawing(acount, 900, "冯荣荣").start();
		
	}
}
//账户
class Acount{
	int money;
	String name;//账户名称
	public Acount(int money, String name) {
		this.money = money;
		this.name = name;
	}
}
//取款
class Drawing extends Thread{
	Acount acount;//取钱的账户
	int dawingMoney;//取的钱数
	int packageTotal;//兜里的钱
	
	
	
	@Override
	public  void  run() {
		if(acount.money<0) {
			return;
		}
		synchronized (acount) {
			if(acount.money-dawingMoney <0) {
				return;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			acount.money -=dawingMoney;
			packageTotal += dawingMoney;
			System.out.println(this.getName()+"从"+acount.name+"取出了"+dawingMoney+"钱");
			System.out.println(this.getName()+"的兜里现在有"+packageTotal+"钱");
			System.out.println(acount.name+"还剩"+acount.money);
		}
		
		
	}

	public Drawing(Acount acount, int dawingMoney, String name) {
		super(name);
		this.acount = acount;
		this.dawingMoney = dawingMoney;
	}
	
	
}

