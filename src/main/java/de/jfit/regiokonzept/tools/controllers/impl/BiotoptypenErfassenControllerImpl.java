/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.jfit.regiokonzept.tools.config.ToolsConfiguration;
import de.jfit.regiokonzept.tools.form.BiotopTypForm;

/**
 * Description:<br>
 *
 * @author minion69
 */
@Controller
public class BiotoptypenErfassenControllerImpl {

    private ToolsConfiguration toolsConfiguration;

    @Autowired
    public BiotoptypenErfassenControllerImpl(final ToolsConfiguration toolsConfiguration) {
        super();
        this.toolsConfiguration = toolsConfiguration;
    }

    @PostMapping("/addbiotoptyp")
    public String addBiotoptyp(final Model model, @ModelAttribute("biotopTypForm") final BiotopTypForm biotopTypForm) {

//        String firstName = personForm.getFirstName();
//        String lastName = personForm.getLastName();
//
//        if (firstName != null && firstName.length() > 0 //
//                && lastName != null && lastName.length() > 0) {
//            Person newPerson = new Person(firstName, lastName);
//            persons.add(newPerson);
//
//            return "redirect:/personList";
//        }
//
//        model.addAttribute("errorMessage", errorMessage);
        biotopTypForm.setNumber(biotopTypForm.getNumber() + 1);

        return "home/homeSignedIn";
    }
}
