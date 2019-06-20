package com.sc.crud.utils;

public enum SingleTonMethod {
	INTANCE{
		public void getHello() {
			System.out.println("2222222222");
		}
	},
	INTANCE2;
	
	public SingleTonMethod getInstance(){
		return INTANCE;
	}
	
	public  void getHello() {
		System.out.println("hello1111111");
	}
	
	public static void main(String[] args) {
		SingleTonMethod.INTANCE2.getHello();
	}
}
