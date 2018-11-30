package com.itxin.ModelController;

import com.itxin.ServiceModel.ServiceTwo;
import com.itxin.domain.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("stucon")
public class StuContrloller {

   @Autowired
    private ServiceTwo st;


    @RequestMapping("findAll")
    public String findAll(Model model){
        List<student> all = st.findAll();
        model.addAttribute("list",all);
        return "success";
    }
}
