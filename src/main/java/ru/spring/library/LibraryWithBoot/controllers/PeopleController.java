package ru.spring.library.LibraryWithBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.library.LibraryWithBoot.models.Person;
import ru.spring.library.LibraryWithBoot.services.PersonService;
import ru.spring.library.LibraryWithBoot.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonValidator personValidator;
    private final PersonService personService;

    @Autowired
    public PeopleController(PersonValidator personValidator, PersonService personService) {
        this.personValidator = personValidator;
        this.personService = personService;
    }


    @GetMapping()
    public String index(Model model) {

        model.addAttribute("people", personService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
//        Person person = personService.findOne(id);
//        List<Book> books = personService.findBookByPerson(id);
//
//        System.out.println("Person: " + person);
//        System.out.println("Books: " + books);
//
//        model.addAttribute("person", person);
//        model.addAttribute("books", books);

        model.addAttribute("overdueBooks", personService.checkBook(id));

        model.addAttribute("person", personService.findOne(id));
        model.addAttribute("books", personService.findBookByPerson(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personService.findOne(id));
        return "people/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }

}
