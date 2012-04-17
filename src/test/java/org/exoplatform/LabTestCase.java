/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.jcr.RepositoryException;

import junit.framework.TestCase;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.StandaloneContainer;
import org.exoplatform.container.component.RequestLifeCycle;
import org.exoplatform.entity.Book;
import org.exoplatform.entity.BookStore;
import org.exoplatform.entity.Model;
import org.exoplatform.service.MOBService;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.services.security.Identity;
import org.exoplatform.services.security.IdentityConstants;

/**
 * Created by The eXo Platform SAS
 * Author : Lai Trung Hieu
 *          hieult@exoplatform.com
 * Apr 16, 2012  
 */
public class LabTestCase extends TestCase {
  
  protected MOBService    mobService;
  
  protected static StandaloneContainer  container;

  static {
    initContainer();
  }
  
  @Override
  protected void setUp() throws Exception {
    begin();
    Identity systemIdentity = new Identity(IdentityConstants.SYSTEM);
    ConversationState.setCurrent(new ConversationState(systemIdentity));
    this.mobService = (MOBService) ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(MOBService.class);
  }

  /**
   * Set current container
   */
  private void begin() {
    RequestLifeCycle.begin(container);    
  }
  
  /**
   * Clear current container
   */
  protected void tearDown() throws Exception {
    RequestLifeCycle.end();
  }

  private static void initContainer() {
    try {
      String containerConf = Thread.currentThread()
                                   .getContextClassLoader()
                                   .getResource("conf/standalone/configuration.xml")
                                   .toString();
      StandaloneContainer.addConfigurationURL(containerConf);
      String loginConf = Thread.currentThread().getContextClassLoader().getResource("conf/standalone/login.conf").toString();
      System.setProperty("java.security.auth.login.config", loginConf);
      container = StandaloneContainer.getInstance();
    } catch (Exception e) {
      throw new RuntimeException("Failed to initialize standalone container: " + e.getMessage(), e);
    }
  }
  
  public void testGetStore() throws RepositoryException{
    Model model = mobService.getModel();
    assertNotNull(model.getBookStore());    
  }
  
  public void testCreateBook() throws RepositoryException{
    Model model = mobService.getModel();
    BookStore store = model.getBookStore();
    
    Book book = store.createBook();
    book.setId("1");
    store.addBook(book);
    book.setTitle("Gone with the wind");
    book.setCreatedDate(new Date());
    List<String> codes = Arrays.asList(new String[] { "12T3", "234P" });
    book.setCodes(codes);
    assertNotNull(book);
    
    book = store.getBook("1");
    assertNotNull(book);
    assertEquals(book.getAuthor(), "anonymous");
  }
  
  public void testRemoveBook() throws RepositoryException{
    Model model = mobService.getModel();
    BookStore store = model.getBookStore();
    
    Book book = store.createBook();
    book.setId("2");
    store.addBook(book);
    
    book = store.getBook("2");
    assertNotNull(book);
    
    book.remove();
    book = store.getBook("2");
    assertNull(book);
  }
  
  public void testUpdateBook() throws RepositoryException{
    Model model = mobService.getModel();
    BookStore store = model.getBookStore();

    Book book = store.createBook();
    book.setId("3");
    store.addBook(book);

    book = store.getBook("3");
    assertEquals(book.getAuthor(), "anonymous");
    book.setAuthor("John");

    book = store.getBook("3");
    assertEquals(book.getAuthor(), "John");
    book.setId("4");
    assertNotNull(store.getBook("4"));
  }
}
