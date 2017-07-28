/* 
 * Copyright (C) 2017 David Pérez Cabrera <dperezcabrera@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.dperezcabrera.desktop;

import com.github.dperezcabrera.desktop.services.CustomerService;
import com.github.dperezcabrera.desktop.views.ViewForm;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerService service = applicationContext.getBean(CustomerService.class);
        service.addCustomer("000001", "Alice");
        service.addCustomer("000002", "Bob");
        service.addCustomer("000003", "Peter");
        service.addCustomer("000010", "John");
        applicationContext.getBean(ViewForm.class).setVisible(true);
    }
}
