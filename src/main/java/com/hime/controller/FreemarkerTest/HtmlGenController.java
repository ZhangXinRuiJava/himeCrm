package com.hime.controller.FreemarkerTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hime.testp.Student;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Controller
public class HtmlGenController {

	@Resource(name="freeMarkerConfigurer")
	private FreeMarkerConfigurer freemarkerConfigurer;
	
	@RequestMapping("/genhtml")
	@ResponseBody
	public String genHtml() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, java.text.ParseException{
		Configuration configuration = freemarkerConfigurer.getConfiguration();
		//第四步：加载一个模板，创建一个模板对象。
		Template template = configuration.getTemplate("tep04.html");
		//第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map<String, Object> map = new HashMap<>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = dateFormat.parse("1998-11-12");
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
		try(Writer writer = new FileWriter(new File("D:/Java开发相关/电子书/tep04_for1.html"));){
			//第七步：调用模板对象的process方法输出文件。
			template.process(map, writer);
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return "ok";
	}
	
}
