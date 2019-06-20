package com.sc.thread;

public class Thread4 implements Runnable{

	private String winner ; 
	
	@Override
	public void run() {
		for(int i=0;i<=100;i++) {
			System.out.println("竞赛者："+Thread.currentThread().getName()+"已经跑了"+i+"步");
			if(isWin(i)) {
				break;
			}
				
		}
	}
	
	//做一个判定
	public boolean isWin(int step) {
		if(winner!=null) {
			return true;
		}
		if(step == 100 ){
			winner = Thread.currentThread().getName();
			System.out.println("胜利者2"+winner);
			return true;
		}
		return false;
	}
	
	//main入口
	public static void main(String[] args) {
		Thread4 thread4 = new Thread4();
		new Thread(thread4,"乌龟").start();
		new Thread(thread4,"兔子").start();
		
	}
		
}
