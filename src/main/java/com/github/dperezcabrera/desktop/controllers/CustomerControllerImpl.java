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
package com.github.dperezcabrera.desktop.controllers;

import com.github.dperezcabrera.desktop.entities.Customer;
import com.github.dperezcabrera.desktop.services.CustomerService;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerControllerImpl implements CustomerController {
    
    @Autowired
    CustomerService customerService;
    
    @Override
    public void searchCustomer(String id, JTable result){
        List<Customer> customers = customerService.customerSearch(id);
        DefaultTableModel model = (DefaultTableModel) result.getModel();
        while (model.getRowCount() > 0){
            model.removeRow(0);
        }
        for (Customer customer : customers) {
            model.addRow(new Object[]{customer.getId(),customer.getName()});
        }
    }

    @Override
    public void addCustomer(String id, String name) {
        customerService.addCustomer(id, name);
    }
}
