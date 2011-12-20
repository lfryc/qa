/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2010-2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *******************************************************************************/
package org.richfaces.tests.showcase.ftest.webdriver.ftest.richSelect;

import org.jboss.test.selenium.android.ToolKitException;
import org.richfaces.tests.showcase.ftest.webdriver.AbstractAndroidTest;
import org.richfaces.tests.showcase.ftest.webdriver.page.richSelect.SelectPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author <a href="mailto:jpapouse@redhat.com">Jan Papousek</a>
 */
public class TestRichSelectSimple extends AbstractAndroidTest<SelectPage>{

    @Test
    public void testInit() {
        assertFalse(getPage().getFirstSelect().isPopupPresent());
        assertFalse(getPage().getSecondSelect().isPopupPresent());
    }
    
    @Test
    public void testShowPopupByClickingOnArrow() {
        getPage().getFirstSelect().showPopupByArrow();
        getPage().getSecondSelect().showPopupByArrow();
    }
    
    @Test
    public void testShowPopupByClickingOnInput() {
        getPage().getFirstSelect().showPopupByClick();
        getPage().getSecondSelect().showPopupByClick();
    }
    
    @Test
    public void testSelectByMouse() throws ToolKitException {
        getPage().getFirstSelect().showPopupByArrow();
        getPage().getFirstSelect().selectFromPopupByIndex(0);
        
        getPage().getSecondSelect().showPopupByArrow();
        getPage().getSecondSelect().selectFromPopupByIndex(0);
    }
    
    @Override
    protected SelectPage createPage() {
        return new SelectPage(getWebDriver(), getToolKit());
    }

}