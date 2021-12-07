package com.example.appointment_with_a_doctor;

import com.example.appointment_with_a_doctor.db.DoctorRepository;
import com.example.appointment_with_a_doctor.db.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class MvcController {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String getLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, RedirectAttributes redirectAttributes) {
        Doctor doctor = doctorRepository.findDoctorByUsername(username);
//        User user = usersRepository.findUserByUsername(username);
        if (doctor != null) {
            redirectAttributes.addFlashAttribute("doctorEmail", String.valueOf(doctor.getEmail()));
            return "redirect:/doctor-profile";
        }
//        if (user != null) {
//            redirectAttributes.addFlashAttribute("userId", String.valueOf(user.getId()));
//            return "redirect:/user";
//        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/doctor-profile", method = RequestMethod.GET)
    public String getDoctorProfile(@ModelAttribute("doctorEmail") Object doctorEmail, Model model, @ModelAttribute("err") Object err) {
        Doctor doctor = doctorRepository.findDoctorByUsername((String) doctorEmail);
        model.addAttribute("doctorEmail", doctorEmail);
        model.addAttribute("lastname", doctor.getLastName());
        model.addAttribute("firstname", doctor.getFirstName());
        model.addAttribute("secondname", doctor.getSecondName());
        model.addAttribute("specialization", doctor.getSpecialization());
        model.addAttribute("specialisations", Specializations.values());
        System.out.println(doctor.getWorkingDays());
        String[] days = doctor.getWorkingDays().split("\\|");
        System.out.println(days[0] + " size:" + days.length);
        model.addAttribute("monday", days[0]);
        model.addAttribute("tuesday", days[1]);
        model.addAttribute("wednesday", days[2]);
        model.addAttribute("thursday", days[3]);
        model.addAttribute("friday", days[4]);
        model.addAttribute("saturday", days[5]);
        model.addAttribute("sunday", days[6]);
        String[] workTime = doctor.getWorkingHours().split("\\|");
        model.addAttribute("work_since", workTime[0]);
        model.addAttribute("work_to", workTime[1]);
        model.addAttribute("photo", doctor.getPhoto());
        model.addAttribute("err", err);
        return "doctor-profile";
    }

    @RequestMapping(value = "/doctor-profile", method = RequestMethod.POST)
    public String getDoctorProfile(RedirectAttributes redirectAttributes,
                                   @RequestParam(name = "doctorEmail", defaultValue = "") String doctorEmail, HttpServletResponse httpServletResponse,
                                   @RequestParam(name = "lastname", defaultValue = "") String lastname,
                                   @RequestParam(name = "firstname", defaultValue = "") String firstname,
                                   @RequestParam(name = "secondname", defaultValue = "") String secondname,
                                   @RequestParam(name = "specialization", defaultValue = "") String specialization,
                                   @RequestParam(name = "monday", defaultValue = "false") String monday,
                                   @RequestParam(name = "tuesday", defaultValue = "false") String tuesday,
                                   @RequestParam(name = "wednesday", defaultValue = "false") String wednesday,
                                   @RequestParam(name = "thursday", defaultValue = "false") String thursday,
                                   @RequestParam(name = "friday", defaultValue = "false") String friday,
                                   @RequestParam(name = "saturday", defaultValue = "false") String saturday,
                                   @RequestParam(name = "sunday", defaultValue = "false") String sunday,
                                   @RequestParam(name = "work_since", defaultValue = "0") String work_since,
                                   @RequestParam(name = "work_to", defaultValue = "0") String work_to,
                                   @RequestParam(name ="photo", defaultValue = "") String photo) {
        try {
            Doctor doctor = new Doctor(lastname, firstname, secondname, specialization,
                    monday + "|" + tuesday + "|" + wednesday + "|" + thursday + "|" + friday + "|" + saturday + "|" + sunday,
                    work_since + "|" + work_to, photo);
            doctor.setEmail(doctorEmail);
            doctorRepository.saveDoctor(doctor);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("err", e.getMessage());
        }
        redirectAttributes.addFlashAttribute("doctorEmail", doctorEmail);
        return "redirect:/doctor-profile";
    }
}
