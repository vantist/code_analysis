package ntut.csie.detect.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ThreadByCallable {

    public static void main(String[] args) {
    	try {
    		File f = new File("a.txt");
    		System.out.println(f.getAbsolutePath());
			FileReader fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
//        ThreadByCallable rt = new ThreadByCallable();

        // 使用FutureTask来包装Callable对象
//        FutureTask<Integer> task = new FutureTask<Integer>(rt);
//        task.run();
//        new Thread(task, "有返回值的线程").start();
//        
//        rt = new ThreadByCallable();
//        task = new FutureTask<Integer>(rt);
//        task.run();
//        new Thread(task, "有返回值的线程222").start();
//        try {
//            // 获取线程返回值
//            System.out.println("子线程的返回值：" + task.get());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }
}