package hochenchong.mybatis01.dao;

import hochenchong.mybatis01.bean.Employee;

public interface EmployeeMapper {
	
	Employee selectEmp(int id);
}
