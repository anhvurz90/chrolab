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
package org.chrolab.entity;

import java.util.Map;

import org.chromattic.api.annotations.Create;
import org.chromattic.api.annotations.MappedBy;
import org.chromattic.api.annotations.OneToMany;
import org.chromattic.api.annotations.OneToOne;
import org.chromattic.api.annotations.Owner;
import org.chromattic.api.annotations.Path;
import org.chromattic.api.annotations.PrimaryType;
import org.chromattic.api.annotations.WorkspaceName;
import org.chrolab.constant.LabNodeTypes;

/**
 * Created by The eXo Platform SAS
 * Author : Lai Trung Hieu
 *          hieult@exoplatform.com
 * Apr 16, 2012  
 */
@PrimaryType(name = LabNodeTypes.BOOK_STORE)
public abstract class BookStore {

  /**
   * Get workspace name
   * @return work space name
   */
  @WorkspaceName
  public abstract String getWorkspaceName();  
  
  /**
   * Get jcr path
   * @return jcr path
   */
  @Path
  public abstract String getPath();
  
  /**
   * Get collection of books
   * @return books
   */
  @OneToMany
  public abstract Map<String,Book> getBooks();
  
  /**
   * Set book collection to this book store
   * @param book the book
   */
  public abstract void setBooks(Map<String,Book> book);

  /**
   * Get a book with a given id
   * @param id is the book id
   * @return a book
   */
  public Book getBook(String id) {
   return getBooks().get(id);
  }
  
  /**
   * Add a book
   * @param book
   */
  public void addBook(Book book) {
    getBooks().put(book.getName(), book);
  }
  
  /**
   * Create a book
   * @return book
   */
  @Create
  public abstract Book createBook();  
  
  /**
   * Get tag store
   * @return tag store
   */
  public TagStore getTagStore() {
    TagStore tagStore = getTags();
    if (tagStore == null) {
      tagStore = createTags();
      setTags(tagStore);
    }
    return tagStore;
  }
 
  @OneToOne
  @Owner
  @MappedBy(LabNodeTypes.Property.TAGSTORE)
  protected abstract TagStore getTags(); 
  
  protected abstract void setTags(TagStore tagStore);
  
  @Create
  protected abstract TagStore createTags();
}
