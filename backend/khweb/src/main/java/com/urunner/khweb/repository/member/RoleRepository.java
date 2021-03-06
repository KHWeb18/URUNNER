package com.urunner.khweb.repository.member;

import com.urunner.khweb.entity.member.Member;
import com.urunner.khweb.entity.member.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}
