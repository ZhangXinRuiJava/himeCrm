package com.hime.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hime.testp.Student;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerTest {
	
	@Test
	public void testGenFile() throws IOException{
		//创建模板文件
		//第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
		Configuration configuration = new Configuration(Configuration.getVersion());
		//第二步：设置模板文件所在的路径。
		configuration.setDirectoryForTemplateLoading(new File("E:/workspace/himeCrm/src/main/webapp/WEB-INF/ftl/"));
		//第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
		//第四步：加载一个模板，创建一个模板对象。
		Template template = configuration.getTemplate("tep01.ftl");
		//第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map<String, Object> map = new HashMap<>();
		map.put("name", "freemarker");
		//第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
		try(Writer writer = new FileWriter(new File("D:/Java开发相关/电子书/tep01_for.txt"));){
			//第七步：调用模板对象的process方法输出文件。
			template.process(map, writer);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		//第八步：关闭流。
	}
	
	@Test
	public void testPojo() throws IOException, ParseException{
		//第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
		Configuration configuration = new Configuration(Configuration.getVersion());
		//第二步：设置模板文件所在的路径。
		configuration.setDirectoryForTemplateLoading(new File("E:/workspace/himeCrm/src/main/webapp/WEB-INF/ftl/"));
		//第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
		//第四步：加载一个模板，创建一个模板对象。
		Template template = configuration.getTemplate("tep02.html");
		//第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map<String, Object> map = new HashMap<>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = dateFormat.parse("1998-11-12");
		Student student = new Student(1, "张三", birthday, '男');
		
		map.put("stu", student);
		//第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
		try(Writer writer = new FileWriter(new File("D:/Java开发相关/电子书/tep02_for.html"));){
			//第七步：调用模板对象的process方法输出文件。
			template.process(map, writer);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testList() throws IOException, ParseException{
		//第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
		Configuration configuration = new Configuration(Configuration.getVersion());
		//第二步：设置模板文件所在的路径。
		configuration.setDirectoryForTemplateLoading(new File("E:/workspace/himeCrm/src/main/webapp/WEB-INF/ftl/"));
		//第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
		//第四步：加载一个模板，创建一个模板对象。
		Template template = configuration.getTemplate("tep04.html");
		//第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map<String, Object> map = new HashMap<>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date birthday = dateFormat.parse("1998/11/12");
		Student student = new Student(1, "张三", birthday, '男');
		Student student2 = new Student(2, "李四", birthday, '女');
		Student student3 = new Student(3, "王五", birthday, '男');
		List<Student> list = new ArrayList<Student>();
		list.add(student);
		list.add(student2);
		list.add(student3);
		Date date = new Date();
		
		map.put("stus", list);
		map.put("da", date);
		//第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
		try(Writer writer = new FileWriter(new File("D:/Java开发相关/电子书/tep04_for.html"));){
			//第七步：调用模板对象的process方法输出文件。
			template.process(map, writer);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
}
