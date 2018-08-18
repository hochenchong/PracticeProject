package hochenchong.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import hochenchong.springcloud.pojo.Dept;
import hochenchong.springcloud.service.DeptClientService;

@RestController
public class DeptController_Consumer {
	// private static final String REST_URL_PREFIX = "http://localhost:8001";
	/*private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";

	@Autowired
	private RestTemplate restTemplate;*/
	
	// 使用 Feign 接口
	@Autowired
	private DeptClientService deptClientService;

	@RequestMapping(value = "/consumer/dept/add")
	public boolean add(Dept dept) {
		// return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
		return deptClientService.add(dept);
	}

	@RequestMapping(value = "/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
		// return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
		return deptClientService.get(id);
	}

	@RequestMapping(value = "/consumer/dept/list")
	public List<Dept> list() {
		// return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
		return deptClientService.list();
	}

}
