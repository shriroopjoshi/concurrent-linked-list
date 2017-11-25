package app;

import java.util.Random;

public class Main {

	static int BOUND = 50;
	static int NUMBER = 8;

	public static void main(String[] args) throws InterruptedException {
		LazyList<Integer> list = new LazyList<>();
		Random r = new Random();
		Thread[] threads = new Thread[NUMBER];
		for (int i = 0; i < NUMBER; ++i) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 1; ++i) {
						Integer integer = r.nextInt(BOUND);
						list.add(integer);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (list.contains(integer)) {
							// list.remove(integer);
						}

					}
				}
			});
			threads[i].start();
		}
		for(int i = 0; i < NUMBER; ++i) {
			threads[i].join();
		}
		list.add(4);
		list.replace(4, -1);
		/*list.add(5);
		list.add(7);
		list.add(9);
		System.out.println("LIST: " + list);
		if(list.add(4)) {
			System.out.println("added 4");
		} else {
			System.out.println("4 was present");
		}
		System.out.println("LIST: " + list);
		if(list.replace(4, -1)) {
			System.out.println("replaced");
		} else {
			System.out.println("all work is lost");
		}*/
		System.out.println("LIST: " + list);
	}
}