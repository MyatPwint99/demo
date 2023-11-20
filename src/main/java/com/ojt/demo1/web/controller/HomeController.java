package com.ojt.demo1.web.controller;

import com.ojt.demo1.bl.service.*;
import com.ojt.demo1.persistance.entity.MyUser;
import com.ojt.demo1.web.form.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    public List<RegistrationUserForm> userlists = new ArrayList<>();
    // This list for storing data( Like DB )
    @Autowired
    MyUserRepository myUserRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;


//    @RequestMapping("/home")
//    public String home(){
//        System.out.println("home is Running*************");
//        return "home";
//    }
    @RequestMapping("/")
    public String home(Model model){
        UserForm userForm1 = new UserForm();
        UserForm userForm2 = new UserForm();
        UserForm userForm3 = new UserForm();
        List<UserForm> userFormList = new ArrayList<>();
        userFormList.add(userForm1);
        userFormList.add(userForm2);
        userFormList.add(userForm3);
        model.addAttribute("userlist",userFormList);
        return "home";
    }

//    @RequestMapping("/hello")
//    public String hello(){
//        System.out.println("home is Running*************");
//        return "hello";
//    }
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        System.out.println("home is Running*************");
        return "hello";
    }
    @GetMapping("/profile")
    public String profile(){
        System.out.println("home is Running*************");
        return "user/profile";
    }

    @GetMapping("/user/create")
    public String createUser(Model model){
        var user = new MyUser();
//        user.setUserName("Default User");
//        user.setPhoneNumber(123456789);
//        user.setMarried(true);
        List<String> cities = new ArrayList<>();
        cities.add("Yangon");
        cities.add("Mandalay");
        cities.add("Bagan");
        model.addAttribute("user",new RegistrationUserForm());
        model.addAttribute("cities",cities);
        return "user/create";
    }
    @PostMapping("/user/create")
    public String registerUser(@ModelAttribute MyUser user, Model model){

        System.out.println("User Name ---->"+user.getUsername());
        System.out.println("User Phone Number ---->"+user.getPhonenumber());
        System.out.println("Married ---->"+user.getMarried());

//        userlists.add(registerUserForm);
//        for (var user : userlists){
//            System.out.println("User Name : "+user.getUserName());
//            System.out.println("User Ph no : "+user.getPhoneNumber());
//            System.out.println("----------------------------------------");
//        }
//        System.out.println("User Creation*****");
//        model.addAttribute("user",registerUserForm);


        //  Save in DB
        // query ? session ? sessionFactory

        myUserRepository.save(user);

        model.addAttribute("userlists",myUserRepository.findAll());
        return "user/detail";
    }
//    @GetMapping("/user/profile/{userId}")
//    public String getUser(Model model , @PathVariable() Long userId){
//        var myUser = new MyUser();
//        myUser.setUserName("Fake name");
//        myUser.setPhoneNumber(124456);
//        try{
//            var myUser = myUserRepository.findById(userId).get();
//            model.addAttribute("user",myUser);
//        }catch (Exception e){
//            model.addAttribute("error","There is no User.");
//        }
//        return "user/profile";
//    }
    // Select By City
    @GetMapping("/user/profile")
    public String getUser(Model model){
        List<MyUser> userList = myUserRepository.findByCity("Mandalay");
        model.addAttribute("userlists",userList);
        return "user/profile";
    }
    @GetMapping("/user/edit/{userId}")
    public String editUser(Model model , @PathVariable Long userId){
//        var user = new MyUser();
        MyUser user = myUserRepository.findById(userId).get();
        List<String> cities = new ArrayList<>();
        cities.add("Yangon");
        cities.add("Mandalay");
        cities.add("Bagan");
        model.addAttribute("user",user);
        model.addAttribute("cities",cities);
        return "user/edit";
    }
    @PostMapping("/user/edit")
    public String editUser(@ModelAttribute MyUser user, Model model){
        myUserRepository.save(user);
        model.addAttribute("userlists",myUserRepository.findAll());
        return "user/profile";

    }
    @GetMapping("/user/delete/{userId}")
    public String deleteUser(Model model,@PathVariable Long userId){
//        myUserRepository.deleteById(userId);

        var user = myUserRepository.findById(userId).get();
        myUserRepository.delete(user);
        model.addAttribute("userlists",myUserRepository.findAll());
        return "user/profile";
    }

    @GetMapping("/book/create")
    public void createBook(){
        Author author1 = new Author();
        author1.setName("Author 1");
        authorRepository.save(author1);

        Book book1 = new Book();
        book1.setAuthor(author1);
        book1.setTitle("Book One Title");
        book1.setBody("Book One Body");
        bookRepository.save(book1);
        System.out.println("Book one is saved **************");
    }
    @GetMapping("/student/create")
    public void createStudent(){
        Student stu1=new Student();
        stu1.setName("MPP");
        Course course1 = new Course();
        course1.setName("JAVA");
        stu1.setCourses(Collections.singletonList(course1));
        course1.setStudents(Collections.singletonList(stu1));
        studentRepository.save(stu1);
        courseRepository.save(course1);

        System.out.println("Student MPP is saved **************");
//
//        Student stu2=new Student();
//        stu2.setName("PMM");
//        studentRepository.save(stu2);
//
//        Course course2 = new Course();
//
//        course2.setName("JAVA");
//        courseRepository.save(course2);
//        course2.setStudents(stu2);
//        stu2.setCourses(course2);
//        System.out.println("Student PMM is saved **************");
    }
}
