package com.sankadilshan.myday;

import com.sankadilshan.myday.config.ApplicationcontextProvider;
import com.sankadilshan.myday.dao.MyDayUserDao;
import com.sankadilshan.myday.model.dto.MyDayUserInput;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MydayApplication implements CommandLineRunner {

//	@Autowired
//	private PasswordEncoder passwordEncoder;
	private boolean dataEntryFlag=false;
	@Autowired
	private ApplicationcontextProvider applicationcontextProvider;

	public static void main(String[] args) {
		SpringApplication.run(MydayApplication.class, args);
		System.out.println("### MyDay Application started on port 8081 ###");
	}

	@Override
	public void run(String... args) throws Exception {

		MyDayUserDao mydayUserDaoBean = applicationcontextProvider.getApplicationContext().getBean(MyDayUserDao.class);

		MyDayUserInput myDayUser0 =  new MyDayUserInput();
		myDayUser0.setEmail("mydayauseradmin0@yopmail.com");
		myDayUser0.setFirstName("mydayuseradmin0");
		myDayUser0.setLastName("test0");
//		myDayUser0.setPassword(passwordEncoder.encode("mydayuseradmin0"));
		myDayUser0.setRoleId(1);

		MyDayUserInput myDayUser1 =  new MyDayUserInput();
		myDayUser1.setEmail("mydayauseradmin1@yopmail.com");
		myDayUser1.setFirstName("mydayuseradmin1");
		myDayUser1.setLastName("test1");
//		myDayUser1.setPassword(passwordEncoder.encode("mydayuseradmin1"));
		myDayUser1.setRoleId(1);

		MyDayUserInput myDayUser2 =  new MyDayUserInput();
		myDayUser2.setEmail("mydayauser0@yopmail.com");
		myDayUser2.setFirstName("mydayuser0");
		myDayUser2.setLastName("test0");
//		myDayUser2.setPassword(passwordEncoder.encode("mydayuser0"));
		myDayUser2.setRoleId(2);

		if (dataEntryFlag) {
			mydayUserDaoBean.insertMydayUser(myDayUser0);
			mydayUserDaoBean.insertMydayUser(myDayUser1);
			mydayUserDaoBean.insertMydayUser(myDayUser2);
		}

	}
}
