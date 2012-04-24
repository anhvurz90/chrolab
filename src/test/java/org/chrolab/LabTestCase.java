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
package org.chrolab;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.jcr.RepositoryException;

import junit.framework.TestCase;

import org.chrolab.constant.LabNodeTypes;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.StandaloneContainer;
import org.exoplatform.container.component.RequestLifeCycle;
import org.chrolab.entity.Book;
import org.chrolab.entity.BookStore;
import org.chrolab.entity.Model;
import org.chrolab.entity.Tag;
import org.chrolab.entity.TagStore;
import org.chrolab.service.MOBService;
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
  
  /**
   * Test get the book store
   * @throws RepositoryException
   */
  public void testGetBookStore() throws RepositoryException{
    Model model = mobService.getModel();
    assertNotNull(model.getBookStore());    
  }
  
  /**
   * Test create books
   * @throws RepositoryException
   */
  public void testCreateBooks() throws RepositoryException{
    Model model = mobService.getModel();
    BookStore store = model.getBookStore();
    
    Book book = store.createBook();
    book.setName("1");
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
  /**
   * Test remove books
   * @throws RepositoryException
   */
  public void testRemoveBooks() throws RepositoryException{
    Model model = mobService.getModel();
    BookStore store = model.getBookStore();
    
    Book book = store.createBook();
    book.setName("2");
    store.addBook(book);
    
    book = store.getBook("2");
    assertNotNull(book);
    
    book.remove();
    book = store.getBook("2");
    assertNull(book);
  }
  
  /**
   * test update books
   * @throws RepositoryException
   */
  public void testUpdateBooks() throws RepositoryException{
    Model model = mobService.getModel();
    BookStore store = model.getBookStore();

    Book book = store.createBook();
    book.setName("3");
    store.addBook(book);

    book = store.getBook("3");
    assertEquals(book.getAuthor(), "anonymous");
    book.setAuthor("John");

    book = store.getBook("3");
    assertEquals(book.getAuthor(), "John");
    book.setTitle("Gone away");
    book.setName("4");
    assertNotNull(store.getBook("4"));
  }

  /**
   * Test search books
   */
  public void testSearchBooks(){
    StringBuilder whereStatement = new StringBuilder();
    whereStatement.append(LabNodeTypes.Property.TITLE).append(" LIKE ").append("'Gone %' AND jcr:path like '%'");
    assertEquals(2, mobService.getSession().createQueryBuilder(Book.class).where(whereStatement.toString()).get().objects()
        .size());
    whereStatement = new StringBuilder().append(LabNodeTypes.Property.AUTHOR).append("='John' AND jcr:path like '%'");
    assertEquals(1, mobService.getSession().createQueryBuilder(Book.class).where(whereStatement.toString()).get().objects()
        .size());
  }
  
  /**
   * Test create tags
   * @throws RepositoryException
   */
  public void testCreateTags() throws RepositoryException{
    Model model = mobService.getModel();
    BookStore store = model.getBookStore();
    TagStore tagStore = store.getTagStore();

    Tag tag = tagStore.createTag();
    tag.setName("0");
    tagStore.addTag(tag);
    tag.setTitle("Test");
    assertNotNull(tagStore.getTag("0"));
  }
  
  /**
   * Test add tags to books
   * @throws RepositoryException
   */
  public void testAddTagsToBooks() throws RepositoryException{    
    Model model = mobService.getModel();
    BookStore store = model.getBookStore();
    TagStore tagStore = store.getTagStore();

    Tag tag = tagStore.createTag();
    tag.setName("1");
    tagStore.addTag(tag);
    tag.setTitle("Novel");
    assertNotNull(tagStore.getTag("1"));
    
    Tag tag2 = tagStore.createTag();
    tag2.setName("2");
    tagStore.addTag(tag2);
    tag2.setTitle("Comic");
    assertNotNull(tagStore.getTag("2"));
    
    mobService.getSession().save();
    
    Book book = store.createBook();
    book.setName("5");
    store.addBook(book);
    book.setTitle("Slowly");
    tag.addBook(book);
    tag2.addBook(book);
    assertEquals(2, book.getTags().size());
    
    book = store.createBook();
    book.setName("6");
    store.addBook(book);
    book.setTitle("Quicklỵ̣̣̣̣");
    tag.addBook(book);   
    assertEquals(2, tag.getBooks().size());
  }
  
  public void testRenameBooks() throws RepositoryException{
    
  }
  
  
}
