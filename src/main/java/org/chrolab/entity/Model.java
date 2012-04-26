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

import javax.jcr.RepositoryException;

import org.chromattic.api.ChromatticSession;

/**
 * Created by The eXo Platform SAS
 * Author : Lai Trung Hieu
 *          hieult@exoplatform.com
 * Apr 17, 2012  
 */
public class Model {
  
  private ChromatticSession session;
  
  private BookStore         bookStore;
  
  private static final String BOOK_STORE_NAME = "Store";

  /**
   * Construct based on a chromattic session
   * @param chromSession
   */
  public Model(ChromatticSession chromSession) {
    this.session = chromSession;
  }
  /**
   * Get book store of model
   * @return book store
   * @throws RepositoryException
   */
  public BookStore getBookStore() throws RepositoryException {
    bookStore = session.findByPath(BookStore.class, BOOK_STORE_NAME);
    if (bookStore == null) {
      bookStore = session.insert(BookStore.class, BOOK_STORE_NAME);
      session.save();
    }
    bookStore.setSession(session);
    return bookStore;
  }
}
