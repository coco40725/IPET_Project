package com.ipet.core.services;


import com.ipet.web.staff.entities.JobPermission;
import com.ipet.web.staff.entities.unwinded.UnwindedJobRole;
import com.ipet.web.staff.entities.unwinded.UnwindedStaff;
import com.ipet.web.staff.repositories.CustomStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Yu-Jing
 * @create 2023-01-31-上午 10:24
 */
@Service
public class IPetUserDetailService  implements UserDetailsService {
    private CustomStaffRepository customStaffRepository;

    @Autowired
    public void setCustomStaffRepository(CustomStaffRepository customStaffRepository){
        this.customStaffRepository = customStaffRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        // 調用 staffRepository 裡面的方法來查數據庫，找到對應的 staff
        // 回傳一個  "合法的 staff，包含其正確的帳密 和 權限" ， 之後spring 會自動去檢查這個 staff 的內容是否與 使用者輸入的相同
        UnwindedStaff byStaff = customStaffRepository.findByStaffAc(account);
        if (byStaff == null){
            throw new UsernameNotFoundException("該用戶不存在");
        }else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            List<String> auths = new ArrayList<>();
            for (UnwindedJobRole role : byStaff.getUnwindedJobRoles()){
                if (role != null){
                    auths.add("ROLE_" + role.getRoleName());
                    for (JobPermission permission : role.getJobPermissions()){
                        if (permission != null){
                            auths.add(permission.getPermissionName());
                        }
                    }
                }
            }

            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.join(auths,","));
            return new User(byStaff.getStaffName(), passwordEncoder.encode(byStaff.getStaffPw()), authorities);
        }
    }
}
