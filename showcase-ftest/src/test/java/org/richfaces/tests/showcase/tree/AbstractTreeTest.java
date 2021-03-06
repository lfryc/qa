/*******************************************************************************
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc. and individual contributors
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

package org.richfaces.tests.showcase.tree;

import static org.jboss.arquillian.ajocado.locator.LocatorFactory.jq;

import org.jboss.arquillian.ajocado.locator.JQueryLocator;
import org.richfaces.tests.showcase.AbstractShowcaseTest;

/**
 * @author <a href="mailto:jhuska@redhat.com">Juraj Huska</a>
 * @version $Revision$
 */
public class AbstractTreeTest extends AbstractShowcaseTest {
	
	/* *************************************************************************************************
	 * Constants
	 * *****************************************************************
	 * ********************************
	 */

	protected final String FIRST_LVL_NODE = "div.rf-tr > div[class*=rf-tr-nd]";
	protected final String SECOND_LVL_NODE = "div[class*='rf-tr-nd']";
	protected final String LABEL_NODE = "div > span > span.rf-trn-lbl";
	protected final String EXPAND_SIGN = "div > span[class*='rf-trn-hnd-colps']";
	protected final String COLLAPSE_SIGN = "div > span[class*='rf-trn-hnd-exp']";
	protected final String LEAF = "div[class*=rf-tr-nd-lf]";
	
	/* **************************************************************************************************************
	 * Locators 
	 ***************************************************************************************************************/
	
	protected JQueryLocator allExpandedHnd = jq("div.rf-tr-nd div.rf-trn span[class*='rf-trn-hnd-exp']");
	protected JQueryLocator allColapsedHnd = jq("div.rf-tr-nd div.rf-trn span[class*='rf-trn-hnd-colps']");
	
	/* *****************************************************************************************************
	 * Help methods
	 *******************************************************************************************************/
	
	/**
	 * Collapses or expands all nodes
	 * 
	 * @param collapseOrExpand
	 *            the locator of all expaned or collapsed nodes, when it is
	 *            allExpandedHnd it collapse all nodes, and vice versa
	 * @return the number of nodes which was expanded or collapsed
	 */
	protected int collapseOrExpandAllNodes(JQueryLocator collapseOrExpand) {

		int numberOfNodes = selenium.getCount(collapseOrExpand);

		for (int i = 0; i < numberOfNodes; i++) {

			selenium.click(collapseOrExpand);
		}

		return numberOfNodes;

	}

}
