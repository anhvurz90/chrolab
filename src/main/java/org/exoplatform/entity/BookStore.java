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
package org.exoplatform.entity;

import java.util.Map;

import org.chromattic.api.annotations.Create;
import org.chromattic.api.annotations.OneToMany;
import org.chromattic.api.annotations.Path;
import org.chromattic.api.annotations.PrimaryType;
import org.chromattic.api.annotations.WorkspaceName;
import org.exoplatform.constant.LabNodeTypes;

/**
 * Created by The eXo Platform SAS
 * Author : Lai Trung Hieu
 *          hieult@exoplatform.com
 * Apr 16, 2012  
 */
@PrimaryType(name = LabNodeTypes.BOOK_STORE)
public abstract class BookStore {

  @WorkspaceName
  public abstract String getWorkspaceName();  
  
  @Path
  public abstract String getPath();
  
  /**
   * 
   * @return books
   */
  @OneToMany
  public abstract Map<String,Book> getBooks();
  
  /**
   * 
   * @param book the book
   */
  public abstract void setBooks(Map<String,Book> book);

  public Book getBook(String id) {
   return getBooks().get(id);
  }
  
  public void addBook(Book book) {
    getBooks().put(book.getId(), book);
  }
  
  /**
   * Create a book
   */
  @Create
  public abstract Book createBook();
}
