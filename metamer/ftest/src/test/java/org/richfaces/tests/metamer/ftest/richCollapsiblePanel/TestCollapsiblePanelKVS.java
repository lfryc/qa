/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
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
package org.richfaces.tests.metamer.ftest.richCollapsiblePanel;

import static org.jboss.arquillian.ajocado.Ajocado.guardXhr;
import static org.jboss.arquillian.ajocado.Ajocado.retrieveText;
import static org.jboss.arquillian.ajocado.Ajocado.waitGui;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.net.URL;

import org.jboss.arquillian.ajocado.locator.JQueryLocator;
import org.jboss.arquillian.ajocado.utils.URLUtils;
import org.richfaces.tests.metamer.ftest.AbstractAjocadoTest;
import org.testng.annotations.Test;

/**
 *  Test keeping visual state for page /faces/components/richCollapsiblePanel/simple.xhtml
 * @author <a href="mailto:jjamrich@redhat.com">Jan Jamrich</a>
 * @version $Revision$
 */
public class TestCollapsiblePanelKVS extends AbstractAjocadoTest {

    CollapsiblePanelReloadTester reloadTester = new CollapsiblePanelReloadTester();

    @Override
    public URL getTestUrl() {
        return URLUtils.buildUrl(contextPath, "faces/components/richCollapsiblePanel/simple.xhtml");
    }

    @Test(groups = {"keepVisualStateTesting"})
    public void testRefreshFullPage(){
        reloadTester.testFullPageRefresh();
    }

    @Test(groups = {"keepVisualStateTesting"})
    public void testRenderAll() {
        reloadTester.testRerenderAll();
    }

    private class CollapsiblePanelReloadTester extends ReloadTester<Boolean> {

        private JQueryLocator content = pjq("div[id$=collapsiblePanel:content]");
        private JQueryLocator header = pjq("div[id$=collapsiblePanel:header]");
        private JQueryLocator headerExp = pjq("div[id$=collapsiblePanel:header] div.rf-cp-lbl-exp");
        private JQueryLocator headerColps = pjq("div[id$=collapsiblePanel:header] div.rf-cp-lbl-colps");

        @Override
        public void doRequest(Boolean expanded) {
            String reqTime = selenium.getText(time);
            if (expanded) {
                if (!isExpanded()){
                    guardXhr(selenium).click(header);
                    waitGui.failWith("Page was not updated").waitForChange(reqTime, retrieveText.locator(time));
                }
            } else {
                if (isExpanded()) {
                    guardXhr(selenium).click(header);
                    waitGui.failWith("Page was not updated").waitForChange(reqTime, retrieveText.locator(time));
                }
            }
        }

        @Override
        public void verifyResponse(Boolean expanded) {
            if (expanded) {
                assertTrue(isExpanded());
            } else {
                assertFalse(isExpanded());
            }
        }

        @Override
        public Boolean[] getInputValues() {
            return new Boolean[] {Boolean.TRUE, Boolean.FALSE};
        }

        private boolean isExpanded() {
            return selenium.isVisible(headerExp) &&
                    !selenium.isVisible(headerColps) &&
                    selenium.isVisible(content);
        }
    }
}