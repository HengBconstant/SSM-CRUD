package com.sc.thread;

import java.util.ArrayList;
import java.util.List;

public class UnsafeThread2 {
	public static void main(String[] args) {

		List list = new ArrayList();
		
		for(int i=0;i<1000;i++) {
			new Thread(()-> {
				synchronized (list) {
					list.add(Thread.currentThread().getName());
				}
			}).start();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list.size());
	}
}

