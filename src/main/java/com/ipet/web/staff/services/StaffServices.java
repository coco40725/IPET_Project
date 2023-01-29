package com.ipet.web.staff.services;

import com.ipet.web.staff.entities.Staff;
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

    @Autowired
    public void setStaffRepository(StaffRepository staffRepository){
        this.staffRepository = staffRepository;
    }


    // staff add

    // staff delete

    // staff edit

    // staff query
    public List<Staff> findAllStaffByPosi(String posi){
        return staffRepository.findAllByStaffPosi(posi);
    }
}
