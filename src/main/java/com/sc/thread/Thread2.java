package com.sc.thread;

public class Thread2 implements Runnable{

	private String url;
	private String name;
	
	
	public Thread2(String url, String name) {
		this.url = url;
		this.name = name;
	}

	@Override
	public void run() {
		WebDownload wd = new WebDownload();
		wd.downloadPIC(url, name);
		System.out.println("asdf");
	}
	
	public static void main(String[] args) {
		Thread2 thread1 = new Thread2("http://i1.hdslb.com/bfs/archive/67218f4b531c1705482c8d6e554e6faf33de7de7.jpg", "1.jpg");
		Thread2 thread2 = new Thread2("http://n.sinaimg.cn/sinacn14/106/w821h885/20180605/16e9-hcqccin4931856.png", "2.png");
		Thread2 thread3 = new Thread2("http://5b0988e595225.cdn.sohucs.com/q_70,c_zoom,w_640/images/20180814/fb4862102e1c4ac09061e62020e74823.jpeg", "3.jpg");
		
		new Thread( thread1).start();
		new Thread( thread2).start();
		new Thread( thread3).start();
	}
	
}
