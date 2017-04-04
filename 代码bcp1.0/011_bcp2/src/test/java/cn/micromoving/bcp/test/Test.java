package cn.micromoving.bcp.test;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;



public class Test {

	public static void main(String[] args) {

		String name=UserUtils.getOfficeRootName("4");
		System.out.println(name);
	}
	 
  

	public static void Thumbnails(String starFile,int weight,int hight,String scoreFile){
		try {
			Thumbnails.of(starFile).size(weight, hight).toFile(scoreFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
