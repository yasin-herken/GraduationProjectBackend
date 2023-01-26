package graduationproject.backend.Role.controller;

import graduationproject.backend.Role.entity.ERole;
import graduationproject.backend.Role.entity.Role;
import graduationproject.backend.Role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    @Autowired
    private final RoleRepository roleRepository;

    @PostMapping
    public void addRoles() {

        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(ERole.ROLE_ADMIN));
        roleList.add(new Role(ERole.ROLE_SELLER));
        roleList.add(new Role(ERole.ROLE_USER));
        roleRepository.saveAll(roleList);
    }
}
