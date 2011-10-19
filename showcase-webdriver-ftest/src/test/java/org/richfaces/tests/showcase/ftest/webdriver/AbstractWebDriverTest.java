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
package org.richfaces.tests.showcase.ftest.webdriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.jboss.test.selenium.android.support.pagefactory.StaleReferenceAwareFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.richfaces.tests.showcase.ftest.webdriver.page.ShowcasePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
* @author <a href="mailto:jpapouse@redhat.com">Jan Papousek</a>
*/
public abstract class AbstractWebDriverTest<Page extends ShowcasePage> extends AbstractShowcaseTest {
  
    private Page page;
    private WebDriver webDriver;
    
    /**
     * Creates a new instance of {@link AbstractWebDriverTest}
     * with the default configuration {@link PropertyTestConfiguration}
     */
    public AbstractWebDriverTest() {
        this(new PropertyTestConfiguration());
    }
    
    /**
     * Creates a new instance of {@link AbstractWebDriverTest} with
     * the given configuration
     * 
     * @param configuration
     */
    public AbstractWebDriverTest(TestConfiguration configuration) {
        super(configuration);
    }
    
    /**
     * Initializes web driver instance
     * 
     * @throws MalformedURLException
     */
    @BeforeClass(alwaysRun = true)
    public void initializeWebDriver() throws MalformedURLException {
        if (getConfiguration().isAndroid()) {
            webDriver = new AndroidDriver(getConfiguration().getWebDriverHost());
        }
        else {
            webDriver = new HtmlUnitDriver(getConfiguration().getWebDriverCapabilities());
        }
        webDriver.manage().timeouts().implicitlyWait(getConfiguration().getWebDriverTimeout(), TimeUnit.SECONDS);
    }
    
    @BeforeClass(alwaysRun = true, dependsOnMethods = { "initializeWebDriver" })
    public void initializePage() {
        FieldDecorator decorator = new StaleReferenceAwareFieldDecorator(createLocatorFactory(), getConfiguration().getWebDriverElementTries());
        PageFactory.initElements(decorator, getPage());
    }
    
    /**
     * Initializes web driver to open a test page 
     */
    @BeforeMethod(alwaysRun = true)
    public void initializePageUrl() {
        webDriver.get(getPath());
        // HACK: because of the bug in mobile version of showcase
        if (getConfiguration().isMobile()) {
            webDriver.get(getPath());
        }
    }
    
    protected ElementLocatorFactory createLocatorFactory() {
        return new DefaultElementLocatorFactory(getWebDriver());
//        return new AjaxElementLocatorFactory(getWebDriver(), getConfiguration().getWebDriverTimeout());
    }
    
    @Override
    protected String getDemoName() {
        return getPage().getDemoName();
    }
    
    protected Page getPage() {
        if (page == null) {
            page = createPage();
        }
        return page;
    }
    
    @Override
    protected String getSampleName() {
        return getPage().getSampleName();
    }
    
    /**
     * Returns a web driver
     * 
     * @return web driver
     */
    protected WebDriver getWebDriver() {
        return webDriver;
    }
    
    protected abstract Page createPage();
}
 