package com.ipet.web.staff.repositories;

import com.ipet.web.staff.entities.unwinded.UnwindedStaff;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-31-上午 10:58
 */
public interface CustomStaffRepository {
    List<UnwindedStaff> findAll();
    UnwindedStaff findById(String id);
    UnwindedStaff findByStaffAc(String staffAc);
    UnwindedStaff findByStaffAcAndStaffPw(String staffAc, String staffPw);


}
