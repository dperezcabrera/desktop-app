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

import com.github.dperezcabrera.desktop.controllers.CustomerControllerImpl;
import com.github.dperezcabrera.desktop.entities.Customer;
import com.github.dperezcabrera.desktop.services.CustomerService;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.junit.Test;
import org.junit.Before;
import org.mockito.ArgumentMatcher;
import static org.mockito.BDDMockito.given;
import org.mockito.Matchers;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CustomerControllerImplTest {

    CustomerControllerImpl instance = new CustomerControllerImpl();
    CustomerService service = mock(CustomerService.class);

    @Before
    public void setUp(){
        instance.customerService = service;
    }
    
    @Test
    public void testSearchCustomer() {
        System.out.println("searchCustomer");
        String id = "id";
        String name = "name";
        JTable table = mock(JTable.class);
        DefaultTableModel model = mock(DefaultTableModel.class);
        given(service.customerSearch(id)).willReturn(Arrays.asList(new Customer(id, name)));
        given(table.getModel()).willReturn(model);
        given(model.getRowCount()).willReturn(0);
        
        instance.searchCustomer(id, table);

        verify(service,times(1)).customerSearch(id);
        verify(model,times(0)).removeRow(anyInt());
        verify(model).addRow(Matchers.argThat(new ArgumentMatcher<Object[]>(){
            @Override
            public boolean matches(Object argument) {
                Object [] array = (Object[])argument;
                return array != null && array.length == 2 && array[0].equals(id)  && array[1].equals(name);
            }
        }));
    }

    
    @Test
    public void testSearchCustomer2() {
        System.out.println("searchCustomer2");
        String id = "id";
        JTable table = mock(JTable.class);
        DefaultTableModel model = mock(DefaultTableModel.class);
        given(service.customerSearch(id)).willReturn(Arrays.asList());
        given(table.getModel()).willReturn(model);
        given(model.getRowCount()).willReturn(1,0);
        
        instance.searchCustomer(id, table);

        verify(service,times(1)).customerSearch(id);
        verify(model,times(1)).removeRow(0);
        verify(model,times(0)).addRow(any(Object[].class));
    }
    
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        String id = "id";
        String name = "name";
 
        instance.addCustomer(id, name);
        
        verify(service, times(1)).addCustomer(id, name);
    }
}
