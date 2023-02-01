package com.ipet.web.staff.services;

import com.ipet.web.staff.entities.Staff;
import com.ipet.web.staff.entities.unwinded.UnwindedStaff;
import com.ipet.web.staff.repositories.CustomStaffRepository;
import com.ipet.web.staff.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yu-Jing
 * @create 2023-01-25-上午 09:05
 */
@Service
public class StaffServices {
    private StaffRepository staffRepository;
    private CustomStaffRepository customStaffRepository;

    @Autowired
    public void setStaffRepository(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }
    @Autowired
    public void setCustomStaffRepository(CustomStaffRepository customStaffRepository){
        this.customStaffRepository = customStaffRepository;
    }


    // staff add

    // staff delete

    // staff edit

    // staff query
    public List<Staff> getAllStaffByPosi(String posi){
        return staffRepository.findAllByStaffPosi(posi);
    }
    public Staff getStaffById(String id){
        return staffRepository.findById(id).orElse(null);
    }

    public List<UnwindedStaff> getAllUnwindedStaff(){
        return customStaffRepository.findAll();
    }
    public UnwindedStaff getUnwindedStaffById(String id){
        return customStaffRepository.findById(id);
    }
    public UnwindedStaff getUnwindedStaffByStaffAc(String staffAc){
        return customStaffRepository.findByStaffAc(staffAc);
    }
    public UnwindedStaff getUnwindedStaffByStaffAcAndStaffPw(String staffAc, String staffPw){
        return customStaffRepository.findByStaffAcAndStaffPw(staffAc, staffPw);
    }
}
