package hochenchong.springcloud.service;

import java.util.List;
import hochenchong.springcloud.pojo.Dept;

public interface DeptService {
	public boolean add(Dept dept);

	public Dept get(Long id);

	public List<Dept> list();
}
