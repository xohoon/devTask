package xohoon.devTask.controller.admin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xohoon.devTask.domain.dto.RoleDto;
import xohoon.devTask.domain.entity.Role;
import xohoon.devTask.service.RoleService;

import java.util.List;


@Controller
@RequestMapping(value="/hide")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value="/admin/role")
    public String getRoles(Model model) throws Exception {

        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);

        return "admin/role/list";
    }

    @GetMapping(value="/admin/role/register")
    public String viewRoles(Model model) throws Exception {

        RoleDto role = new RoleDto();
        model.addAttribute("role", role);

        return "admin/role/detail";
    }

    @PostMapping(value="/admin/role")
    public String createRole(RoleDto roleDto) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDto, Role.class);
        roleService.createRole(role);

        return "redirect:/hide/admin/role";
    }

    @GetMapping(value="/admin/role/{id}")
    public String getRole(@PathVariable String id, Model model) throws Exception {

        Role role = roleService.getRole(Long.valueOf(id));

        ModelMapper modelMapper = new ModelMapper();
        RoleDto roleDto = modelMapper.map(role, RoleDto.class);
        model.addAttribute("role", roleDto);

        return "admin/role/detail";
    }

    @GetMapping(value="/admin/role/delete/{id}")
    public String removeResources(@PathVariable String id, Model model) throws Exception {

        Role role = roleService.getRole(Long.valueOf(id));
        roleService.deleteRole(Long.valueOf(id));

        return "redirect:/hide/admin/role";
    }
}
