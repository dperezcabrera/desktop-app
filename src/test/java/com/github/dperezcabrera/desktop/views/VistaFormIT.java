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
package com.github.dperezcabrera.desktop.views;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.dperezcabrera.desktop.AppConfig;
import org.junit.Test;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class VistaFormIT {

    @Autowired
    ViewForm viewForm;
        
    FrameFixture window;

    @Before
    public void beforeTest() {
        window = new FrameFixture(viewForm);
        window.show();
    }

    @After
    public void afterTest() {
        window.cleanUp();
    }

    @Test
    @DatabaseSetup("customers_db.xml")
    public void test_search_all() {
        String[][] expectResult = {{"000001", "Alice"}, {"000002","Bob"},{"000003","Peter"},{"000010", "John"}};

        window.textBox(ViewForm.ID_FILTER_NAME).setText("");
        window.button(ViewForm.SEARCH_BUTTON_NAME).click();

        assertArrayEquals(expectResult, window.table(ViewForm.RESULTS_TABLE_NAME).contents());
    }

    @Test
    @DatabaseSetup("customers_db.xml")
    public void test_search_none() {
        String[][] expectResult = {};

        window.textBox(ViewForm.ID_FILTER_NAME).setText("123");
        window.button(ViewForm.SEARCH_BUTTON_NAME).click();

        assertArrayEquals(expectResult, window.table(ViewForm.RESULTS_TABLE_NAME).contents());
    }
   
    @Test
    @DatabaseSetup("customers_db.xml")
    public void test_search_for_one() {
        String[][] expectResult = {{"000002", "Bob"}};

        window.textBox(ViewForm.ID_FILTER_NAME).setText("2");
        window.button(ViewForm.SEARCH_BUTTON_NAME).click();

        assertArrayEquals(expectResult, window.table(ViewForm.RESULTS_TABLE_NAME).contents());
    }
    
    @Test
    @DatabaseSetup("customers_db.xml")
    public void test_search_for_two() {
        String[][] expectResult = {{"000001", "Alice"},{"000010", "John"}};

        window.textBox(ViewForm.ID_FILTER_NAME).setText("1");
        window.button(ViewForm.SEARCH_BUTTON_NAME).click();

        assertArrayEquals(expectResult, window.table(ViewForm.RESULTS_TABLE_NAME).contents());
    }
}
