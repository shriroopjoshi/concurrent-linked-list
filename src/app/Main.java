package app;

import java.util.Random;

public class Main {

	static int BOUND = 50;
	
    public static void main(String[] args) {
        LazyList<Integer> list = new LazyList<>();
        Random r = new Random();
        for(int i = 0; i < 32; ++i) {
        	Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i = 0; i < 10; ++i) {
						Integer integer = r.nextInt(BOUND);
						list.add(integer);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(list.contains(integer)) {
							list.remove(integer);
						}
						
					}
				}
        	});
        	t.start();
        }
        System.out.println(list);
    }
}