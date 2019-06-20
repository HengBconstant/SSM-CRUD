package com.sc.thread;
//真实角色   代理角色
public class StaticPoxy {
	public static void main(String[] args) {
		new Company(new You()).happyMarry();
	}
	
	
}

interface Marry{
	void happyMarry();
}
//真实角色
class You implements Marry {

	@Override
	public void happyMarry() {
		// TODO Auto-generated method stub
		System.out.println("我和刘亦菲结婚");
	}
	
}
//代理角色
class Company implements Marry{

	private Marry people;
	
	public Company(Marry people) {
		super();
		this.people = people;
	}

	@Override
	public void happyMarry() {
		// TODO Auto-generated method stub
		read();
		this.people.happyMarry();
		after();
	}

	private void after() {
		// TODO Auto-generated method stub
		System.out.println("布置新房");
	}

	private void read() {
		// TODO Auto-generated method stub
		System.out.println("闹洞房");
	}
	
}

