/**
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and individual contributors
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
 */
package org.richfaces.tests.metamer.ftest.richInplaceInput;

import org.richfaces.tests.metamer.ftest.abstractions.InputValidationPage;
import org.richfaces.tests.metamer.ftest.abstractions.StringInputComponentCSVTest;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:jstefek@redhat.com">Jiri Stefek</a>
 */
//FIXME shoud not be generic (Graphene bug)
public class TestRichInplaceInputCSV extends StringInputComponentCSVTest<InputValidationPage> {

    @Override
    public String getComponentName() {
        return "richInplaceInput";
    }

    @Test
    public void testAllInputsWrong() {
        verifyAllInputs();
    }

    @Test
    public void testCustomString() {
        verifyCustomString();
    }

    @Test
    public void testNotEmpty() {
        verifyNotEmpty();
    }

    @Test
    public void testRegExpPattern() {
        verifyRegExpPattern();
    }

    @Test
    public void testStringSize() {
        verifyStringSize();
    }
}