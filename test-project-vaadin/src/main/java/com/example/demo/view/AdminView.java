package com.example.demo.view;

import com.example.demo.model.Employee;
import com.example.demo.services.EmployeeService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route("admin")
@RolesAllowed("ADMIN")
public class AdminView extends VerticalLayout {

    public AdminView(EmployeeService service) {
        var crud = new GridCrud<>(Employee.class, service);
        crud.getGrid().setColumns("surname", "name", "patronymic", "birthdate", "email", "number");
        crud.getCrudFormFactory().setVisibleProperties("surname", "name", "patronymic", "birthdate", "email", "number");

        add(new H1("Admin view"), crud);
    }
}
