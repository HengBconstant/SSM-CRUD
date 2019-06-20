package com.sc.threadState;

public class State1 implements Runnable{

	private String name;
	
	public State1(String name) {
		super();
		this.name = name;
	}
	
	private boolean flag = true;
	
	@Override
	public void run() {
		int i = 0;
		// TODO Auto-generated method stub
		while(flag) {
			System.out.println(name+"得分："+ i++);
		}
	}
	
	public void stopMatchs() {
		this.flag = false;
	}

	public static void main(String[] args) {
		State1 s = new State1("石城");
		new Thread(s).start();
		for(int i = 0;i<800;i++) {
			if(i==500) {
				s.stopMatchs();
				System.out.println("比赛终止");
				return;
			}
			System.out.println("时间过去了"+i+"分钟了");
		}
	}

}
