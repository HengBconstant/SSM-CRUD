package com.sc.thread;
/**
 * 抢票
 * @author Administrator
 *
 */
public class Thread3 implements Runnable{
	private static int tick = 100;
	@Override
	public void run() {
		while(true) {
			if(tick<=0) {
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tick--;
			System.out.println(Thread.currentThread().getName()+"抢到了"+tick);
		}
	}
	
	public static void main(String[] args) {
		Thread3 thread3 = new Thread3();
		new Thread(thread3,"高崎圣子").start();
		new Thread(thread3,"小田有").start();
		new Thread(thread3,"蔡美旬果").start();
	}
}
