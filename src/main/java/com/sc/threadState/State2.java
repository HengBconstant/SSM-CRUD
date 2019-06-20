package com.sc.threadState;

public class State2 {
	
	public static void main(String[] args) {
		new Thread(()-> {
			for(int i=0;i<100;i++) {
				System.out.println("支线"+i);
			}
		}).start();
		
		for(int i = 0;i<100;i++) {
			if(i%20==0) {
				Thread.yield();
				System.out.println("我礼让了！！！！");
			}
			System.out.println("主线程任务"+i);
		}
	}
}
