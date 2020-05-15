package supwisodom.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
public class IndexController {

	@RequestMapping("welcom")
	public String welcom() {
		return "登录成功，欢迎来到首页";
	}
	
	@RequestMapping("student")
	public String  student() {
		return "学生管理";
	}
	
	@RequestMapping("contract")
	public String  contract() {
		return "合同管理";
	}
}
