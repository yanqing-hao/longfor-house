package com.lf.background.staff.mapper;

import com.lf.background.staff.domain.Staff;
import com.lf.background.staff.domain.StaffSearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffMapper {

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(Integer staffId);

    int updateByPrimaryKeySelective(Staff record);

    int deleteUpdate(Integer staffId);

    List<Staff> queryStaffList(@Param("sort") String sort,
                               @Param("order") String order,
                               @Param("staffSearchVo") StaffSearchVo staffSearchVo);

    void addStaff(Staff staff);
    //根据用户名获取用户对象
    Staff getStaffByName(@Param("name") String staffLoginName);
}