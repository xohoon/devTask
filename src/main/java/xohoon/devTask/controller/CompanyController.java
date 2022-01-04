package xohoon.devTask.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xohoon.devTask.domain.dto.CompanyDto;
import xohoon.devTask.domain.entity.Company;
import xohoon.devTask.domain.entity.Member;
import xohoon.devTask.service.CompanyService;

@Controller
@RequestMapping(value = "co")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    /*
     * company
     * */
    @GetMapping(value = "main")
    public String main(Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Company company = companyService.getCompany(member.getId());
        // 리스트 전달
//        List<CompanyDto> comList = modelMapper.map(companyList, new TypeToken<List<CompanyDto>>() {}.getType());
        model.addAttribute("company", company);

        return "company/main";
    }

    @GetMapping(value = "register") // 기업 글쓰기 폼
    public String registerForm(Model model) {
        CompanyDto company = new CompanyDto();
        company.setCompany("newRegisterKey");
        model.addAttribute("company", company);

        return "company/register";
    }

    @PostMapping(value = "register") // 저장 및 수정
    public String register(CompanyDto companyDto) throws Exception {
        ModelMapper mapper = new ModelMapper();
        Company company = mapper.map(companyDto, Company.class);
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        companyService.register(company, member);

        return "redirect:/co/main";
    }

    @GetMapping(value = "detail/{id}") // 상세페이지
    public String detail(@PathVariable(value = "id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Company companyDetail = companyService.getCompanyDetail(Long.valueOf(id));
        CompanyDto company = modelMapper.map(companyDetail, CompanyDto.class);
        model.addAttribute("company", company);

        return "company/detail";
    }

    @GetMapping(value = "modify/{id}") // 수정 페이지
    public String modifyForm(@PathVariable(value="id") String id, Model model) {
        ModelMapper modelMapper = new ModelMapper();
        Company companyDetail = companyService.getCompanyDetail(Long.valueOf(id));
        CompanyDto companyDto = modelMapper.map(companyDetail, CompanyDto.class);
        model.addAttribute("company", companyDto);

        return "company/register";
    }

    @PostMapping(value = "co/delete") // 삭제
    public String delete() {
        return "redirect:/co/main";
    }


    /*
    * task
    * */
    @GetMapping(value = "task/list")
    public String taskList() {
        return "comapny/task/list";
    }

    @GetMapping(value = "task/register") // 과제 글쓰기 폼
    public String taskRegisterForm() {
        return "company/task/register";
    }

    @PostMapping(value = "task/register") // 과제 저장
    public String taskRegister() {
        return "redirect:/co/task/detail";
    }

    @GetMapping(value = "task/detail")
    public String taskDetail() {
        return "company/task/detail";
    }

    @GetMapping(value = "task/modify")
    public String taskModifyForm() {
        return "company/task/register";
    }

    @PostMapping(value = "task/modify")
    public String taskModify() {
        return "redirect:/co/task/detail";
    }

    @PostMapping(value = "task/delete")
    public String taskDelete() {
        return "redirect:/co/task/list";
    }
}
