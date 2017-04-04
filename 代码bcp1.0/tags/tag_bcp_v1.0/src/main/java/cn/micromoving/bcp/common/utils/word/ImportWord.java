	package cn.micromoving.bcp.common.utils.word;

	import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Encoder;
import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


	public class ImportWord {

		private Configuration configuration = null;

		public ImportWord() {
			configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
		}

		public void createDoc(Map<String,Object> dataMap,String fileName) throws UnsupportedEncodingException {
			//dataMap 要填入模本的数据文件
			//设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
			//这里我们的模板是放在com.havenliu.document.template包下面
			configuration.setClassForTemplateLoading(this.getClass(), "/cn/micromoving/bcp/common/utils/word/moban");
			Template t=null;
			try {
				//test.ftl为要装载的模板
				t = configuration.getTemplate("moban1.ftl");
			} catch (IOException e) {
				e.printStackTrace();
			}
			//输出文档路径及名称
			File outFile = new File(fileName);
			Writer out = null;
			FileOutputStream fos=null;
			try {
				fos = new FileOutputStream(outFile);
				OutputStreamWriter oWriter = new OutputStreamWriter(fos,"UTF-8");
				//这个地方对流的编码不可或缺，使用main（）单独调用时，应该可以，但是如果是web请求导出时导出后word文档就会打不开，并且包XML文件错误。主要是编码格式不正确，无法解析。
				//out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
				 out = new BufferedWriter(oWriter); 
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			 
	        try {
				t.process(dataMap, out);
				out.close();
				fos.close();
			} catch (TemplateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static String getImage(String path,String name) {
			String imgName = path+name;
			File imgFile = new File(imgName);
			if (!imgFile.exists()) {
			    return "";
			}
			InputStream in = null;
			byte[] data = null;
			try {
				in = new FileInputStream(imgFile);
				data = new byte[in.available()];
				in.read(data);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);
		}
	}
	


