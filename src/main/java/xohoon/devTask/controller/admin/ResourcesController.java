package xohoon.devTask.controller.admin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import xohoon.devTask.domain.dto.admin.ResourcesDto;
import xohoon.devTask.domain.entity.admin.Resources;
import xohoon.devTask.domain.entity.admin.Role;
import xohoon.devTask.repository.admin.RoleRepository;
import xohoon.devTask.security.metadata.UrlFilterInvocationSecurityMetadataSource;
import xohoon.devTask.service.admin.ResourcesService;
import xohoon.devTask.service.admin.RoleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value="/hide")
public class ResourcesController {

    @Autowired
    private ResourcesService resourcesService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UrlFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    @GetMapping(value="/admin/resource")
    public String getResources(Model model) throws Exception {
        List<Resources> resources = resourcesService.getResources();
        model.addAttribute("resources", resources);

        return "admin/resource/list";
    }

    @PostMapping(value="/admin/resource")
    public String createResources(ResourcesDto resourcesDto) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Resources resources = modelMapper.map(resourcesDto, Resources.class);
        resources.setRoleSet(roles);

        resourcesService.createResources(resources);
        filterInvocationSecurityMetadataSource.reload();

        return "redirect:/hide/admin/resource";
    }

    @GetMapping(value="/admin/resource/register")
    public String viewRoles(Model model) throws Exception {
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);

        ResourcesDto resources = new ResourcesDto();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role());
        resources.setRoleSet(roleSet);
        model.addAttribute("resources", resources);

        return "admin/resource/detail";
    }

    @GetMapping(value="/admin/resource/{id}")
    public String getResources(@PathVariable String id, Model model) throws Exception {
        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);
        Resources resources = resourcesService.getResources(Long.valueOf(id));

        ModelMapper modelMapper = new ModelMapper();
        ResourcesDto resourcesDto = modelMapper.map(resources, ResourcesDto.class);
        model.addAttribute("resources", resourcesDto);

        return "admin/resource/detail";
    }

    @GetMapping(value="/admin/resource/delete/{id}")
    public String removeResources(@PathVariable String id, Model model) throws Exception {
        Resources resources = resourcesService.getResources(Long.valueOf(id));
        resourcesService.deleteResources(Long.valueOf(id));

        filterInvocationSecurityMetadataSource.reload();

        return "redirect:/hide/admin/resource";
    }
}
